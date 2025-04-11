package com.mobilcash.atmlight.ui.welcome

import android.content.Intent
import android.nfc.NfcAdapter
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilcash.atmlight.R
import com.mobilcash.atmlight.utils.nfc.NfcController
import com.mobilcash.atmlight.utils.nfc.NfcUtils
import androidx.activity.ComponentActivity
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.mobilcash.atmlight.navigation.Destinations
import com.mobilcash.atmlight.utils.nfc.NfcCardReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun InsertCardScreen(navController: NavController, nfcIntent: Intent?) {
    val context = LocalContext.current
    val activity = context as? ComponentActivity ?: return
    val isSupported = NfcUtils.isNfcSupported(activity)
    val isEnabled = NfcUtils.isNfcEnabled(activity)
    val nfcAdapter = remember { NfcAdapter.getDefaultAdapter(activity) }

    var message by remember { mutableStateOf("COLOQUE SU TARJETA\nEN EL LECTOR") }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()


    DisposableEffect(Unit) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    nfcAdapter?.enableReaderMode(
                        activity,
                        { tag: Tag ->
                            coroutineScope.launch {
                                isLoading = true
                                message = "PROCESANDO TARJETA..."

                                Log.d("NFC", "Tag detectado: ${tag.id.joinToString(" ") { "%02X".format(it) }}")

                                val result = withContext(Dispatchers.IO) {
                                    NfcCardReader.read(tag)
                                }

                                result?.let {
                                    Log.i("NFC", "Card ID: ${it.cardId}")
                                    Log.i("NFC", "PPSE: ${it.rawPpseResponse}")
                                    Log.i("NFC", "AID: ${it.rawAidResponse}")
                                    Log.i("NFC", "Card Data: ${it.rawCardData}")

                                    navController.navigate(Destinations.ENTER_PIN) {
                                        popUpTo(Destinations.ENTER_PIN) { inclusive = true }
                                    }
                                } ?: run {
                                    message = "ERROR DE LECTURA\nINTENTE NUEVAMENTE"
                                    isLoading = false
                                }
                            }
                        },
                        NfcAdapter.FLAG_READER_NFC_A or
                                NfcAdapter.FLAG_READER_NFC_B or
                                NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
                        null
                    )
                    message = "COLOQUE SU TARJETA\nEN EL LECTOR"
                }
                Lifecycle.Event.ON_PAUSE -> {
                    nfcAdapter?.disableReaderMode(activity)
                }
                else -> {}
            }
        }

        activity.lifecycle.addObserver(observer)
        onDispose {
            activity.lifecycle.removeObserver(observer)
            nfcAdapter?.disableReaderMode(activity)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF002C77)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 80.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "5B",
                    color = Color(0xFFF9B100),
                    fontWeight = FontWeight.Bold,
                    fontSize = 34.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "cerca de ti",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Top)
                )
            }

            Box(
                modifier = Modifier
                    .size(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.hand_with_card),
                    contentDescription = "Pago con tarjeta",
                    modifier = Modifier.size(150.dp)
                )
            }

            Text(
                text = if (!isSupported) "NFC NO SOPORTADO"
                else if (!isEnabled) "ACTIVA NFC EN CONFIGURACIÓN"
                else message,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                ),
                lineHeight = 32.sp,
                modifier = Modifier.padding(top = 40.dp),
            )


        }
    }
}

private suspend fun handleNfcIntent(intent: Intent): Boolean {
    val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG) ?: return false
    return try {
        val cardInfo = withContext(Dispatchers.IO) {
            NfcCardReader.read(tag)
        }
        cardInfo != null
    } catch (e: Exception) {
        Log.e("NFC", "Error procesando tarjeta", e)
        false
    }
}

suspend fun handleIntent(navController: NavController, intent: Intent?) {
    if (intent?.action == NfcAdapter.ACTION_TECH_DISCOVERED) {
        val tag = intent.getParcelableExtra<Tag>(NfcAdapter.EXTRA_TAG) ?: run {
            Log.w("NFC", "Tag nulo en intent")
            return
        }

        val cardInfo = withContext(Dispatchers.IO) {
            NfcCardReader.read(tag)
        }

        cardInfo?.let {
            Log.i("NFC", "Datos leídos: ${it.cardId}")
            navController.navigate(Destinations.ENTER_PIN)
        } ?: run {
            Log.e("NFC", "No se pudo leer la tarjeta")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InsertCardScreenPreview() {
    InsertCardScreen(rememberNavController(), Intent())
}