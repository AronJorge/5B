package com.mobilcash.atmlight.ui.select_amount

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilcash.atmlight.navigation.Destinations
import com.mobilcash.atmlight.ui.common.BaseScreen
import com.mobilcash.atmlight.ui.common.ButtonOutline

@Composable
fun WithdrawalAmountScreen(navController: NavController) {
    val selectedAmount = remember { mutableStateOf("Q 2,000") }

    val amountOptions = listOf(
        "Q 100", "Q 200",
        "Q 300", "Q 400",
        "Q 500", "Q 600",
        "Q 1,000", "Q 1,500",
        "Q 2,000", "Variable"
    )

    BaseScreen(title = "SELECCIONA EL MONTO DE RETIRO") {
        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .background(Color(0xFFDCE3E7), RoundedCornerShape(4.dp))
                .padding(vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Logo del Banco\nEmisor",
                textAlign = TextAlign.Center,
                color = Color(0xFF002C77),
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            amountOptions.chunked(2).forEach { row ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    row.forEach { amount ->
                        val isSelected = selectedAmount.value == amount
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 4.dp)
                                .border(
                                    width = if (isSelected) 2.dp else 0.dp,
                                    color = Color(0xFF002C77),
                                    shape = RoundedCornerShape(4.dp)
                                )
                                .background(Color(0xFFDCE3E7), shape = RoundedCornerShape(4.dp))
                                .clickable {
                                    selectedAmount.value = amount
                                }
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = amount,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF002C77)
                            )
                        }
                    }

                    if (row.size == 1) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }

        Spacer(modifier = Modifier.weight(7f))

        ButtonOutline(text = "Continuar", onClick = {
            navController.navigate(Destinations.PROCESSING_TRANSACTION_SCREEN)
        })
    }
}

@Preview(showBackground = true)
@Composable
fun WithdrawalAmountPreview() {
    WithdrawalAmountScreen(rememberNavController())
}