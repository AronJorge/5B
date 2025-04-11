package com.mobilcash.atmlight.ui.login

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilcash.atmlight.navigation.Destinations

@Composable
fun EnterPinScreen(navController: NavController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val heightHeader = screenHeight * 0.35f
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val inputWidth = screenWidthDp * 0.60f

    var pin by remember { mutableStateOf("") }

    BackHandler {
        navController.navigate(Destinations.ENTER_PIN)
    }

    Scaffold(
        containerColor = Color(0xFF002C77),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier.height(heightHeader),
                    contentAlignment = Alignment.Center,
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "5B",
                                color = Color(0xFFF9B100),
                                fontWeight = FontWeight.Bold,
                                fontSize = 40.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "INGRESA TU PIN",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = pin.map { "*" }.joinToString(" "),
                            color = Color.Black,
                            fontSize = 40.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(8.dp))
                                .width(inputWidth)
                                .height(60.dp)
                                .padding(top = 10.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFF2F4F3))
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    PinKeyboard(pin = pin, onPinChange = { pin = it }, onEnter = {
                        if (pin.length == 4) {
                            navController.navigate(Destinations.SELECTION_TRANSACTION)
                        }
                    })

                    Text(
                        text = "Regresar",
                        color = Color.Black,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(vertical = 6.dp)
                            .clickable { navController.popBackStack() }
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .statusBarsPadding(),
                contentAlignment = Alignment.TopEnd
            ) {
                Row(
                    modifier = Modifier
                        .background(Color.Gray, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Llamar",
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "775", color = Color.White, fontSize = 12.sp)
                }
            }
        }
    )
}

@Composable
fun PinKeyboard(pin: String, onPinChange: (String) -> Unit, onEnter: () -> Unit) {
    val keys = listOf(
        listOf("1", "2", "3", "CANCEL"),
        listOf("4", "5", "6", "CLEAR"),
        listOf("7", "8", "9", "ENTER"),
        listOf(" ", "0", " ", "    ")
    )

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFF8F8F8), Color(0xFFE0E0E0))
    )

    Column {
        keys.forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                row.forEach { btn ->
                    if (btn.isNotBlank()) {
                        val isSpecial =
                            btn.uppercase() in listOf("CANCEL", "CLEAR", "ENTER", "    ")
                        val contentColor = when (btn.uppercase()) {
                            "CANCEL", "ENTER" -> Color.White
                            "CLEAR" -> Color.Black
                            else -> Color.Black
                        }

                        val onClickAction: () -> Unit = {
                            when (btn.uppercase()) {
                                "CANCEL" -> onPinChange("")
                                "CLEAR" -> if (pin.isNotEmpty()) onPinChange(pin.dropLast(1))
                                "ENTER" -> onEnter()
                                else -> if (pin.length < 4 && btn.all { it.isDigit() }) onPinChange(
                                    pin + btn
                                )
                            }
                        }

                        BoxWithConstraints(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp)
                        ) {
                            val constraints = this
                            val fontSize = with(LocalDensity.current) {
                                (constraints.maxWidth * if (isSpecial) 0.18f else 0.50f).toSp()
                            }

                            Box(
                                modifier = Modifier
                                    .background(gradientBrush, shape = RoundedCornerShape(4.dp))
                                    .border(
                                        1.dp,
                                        Color(0xFFD2D2D2),
                                        shape = RoundedCornerShape(4.dp)
                                    )
                            ) {
                                Button(
                                    onClick = onClickAction,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent,
                                        contentColor = contentColor
                                    ),
                                    shape = RoundedCornerShape(4.dp),
                                    contentPadding = PaddingValues(0.dp)
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        if (isSpecial) {
                                            val stripeColor = when (btn.uppercase()) {
                                                "CANCEL" -> Color(0xFFD32F2F)
                                                "CLEAR" -> Color(0xFFFFEB3B)
                                                "ENTER" -> Color(0xFF4CAF50)
                                                else -> Color.Transparent
                                            }

                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth(0.7f)
                                                    .height(6.dp)
                                                    .background(
                                                        stripeColor,
                                                        shape = RoundedCornerShape(12.dp)
                                                    )
                                            )
                                            Spacer(modifier = Modifier.height(4.dp))
                                        }

                                        Text(
                                            text = btn.uppercase(),
                                            fontSize = fontSize,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center,
                                            maxLines = 1,
                                            color = if (isSpecial) Color(0xFF4A4A4A) else Color(
                                                0xFF676767
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    } else {
                        Spacer(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp)
                                .background(brush = gradientBrush)
                                .border(
                                    BorderStroke(1.dp, Color(0xFFD2D2D2)),
                                    shape = RoundedCornerShape(4.dp)
                                )
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun PinEntryScreenPreview() {
    EnterPinScreen(rememberNavController())
}
