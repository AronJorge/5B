package com.mobilcash.atmlight.ui

import AppNavigation
import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.mobilcash.atmlight.ui.theme.ATMLightTheme
import com.mobilcash.atmlight.utils.nfc.NfcCardReader

class MainActivity : ComponentActivity() {
    private var nfcAdapter: NfcAdapter? = null

    private var currentIntent: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        currentIntent = intent

        enableEdgeToEdge()
        setContent {
            ATMLightTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(currentIntent, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Configurar NFC Reader Mode con timeout al volver a la actividad
        setupNfcReaderMode()
    }

    override fun onPause() {
        super.onPause()
        // Desactivar NFC Reader Mode al pausar
        nfcAdapter?.disableReaderMode(this)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        currentIntent = intent
        /*setContent {
            ATMLightTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(currentIntent, modifier = Modifier)
                }
            }
        }*/

        if (NfcAdapter.ACTION_TECH_DISCOVERED == intent.action) {
            setContent {
                ATMLightTheme {
                    AppNavigation(currentIntent, modifier = Modifier)
                }
            }
        }
    }

    private fun setupNfcReaderMode() {
        nfcAdapter?.let { adapter ->
            try {
                adapter?.enableReaderMode(
                    this,
                    { tag ->
                        Log.d("NFC", "Tag detectado: ${tag.id.joinToString(" ") { "%02X".format(it) }}")
                        val result = NfcCardReader.read(tag)
                        /*result?.let {
                            Log.i("NFC", "Card ID: ${it.cardId}")
                            Log.i("NFC", "PPSE: ${it.rawPpseResponse}")
                            Log.i("NFC", "AID: ${it.rawAidResponse}")
                            Log.i("NFC", "Card Data: ${it.rawCardData}")
                        }*/
                    },
                    NfcAdapter.FLAG_READER_NFC_A or
                            NfcAdapter.FLAG_READER_NFC_B or
                            NfcAdapter.FLAG_READER_SKIP_NDEF_CHECK,
                    null
                )
            } catch (e: Exception) {
                Log.e("NFC", "Error setting up reader mode", e)
            }
        }
    }

    private fun ByteArray.toHexString(): String = joinToString("") { "%02X".format(it) }
}
