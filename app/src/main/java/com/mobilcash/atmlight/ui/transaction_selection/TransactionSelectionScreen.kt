package com.mobilcash.atmlight.ui.transaction_selection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilcash.atmlight.navigation.Destinations
import com.mobilcash.atmlight.ui.common.BaseScreen
import com.mobilcash.atmlight.ui.common.ButtonOutline

@Composable
fun TransactionSelectionScreen(navController: NavController) {
    BaseScreen(title = "SELECCIONA TU TRANSACCIÓN") {
        Spacer(modifier = Modifier.height(24.dp))

        val options = listOf(
            "BIENVENIDO\nLOGO DE TU BANCO",
            "Consulta",
            "Retiros",
            "Opción 3",
            "Opción 4",
            "Opción 5"
        )

        options.forEach { option ->
            val isSelected = option == "Retiros"
            val isWelcome = option.startsWith("BIENVENIDO")

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 4.dp)
                    .border(
                        width = if (isSelected) 2.dp else 0.dp,
                        color = Color(0xFF002C77),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(
                        if (isWelcome) Color(0xFFDCE3E7)
                        else Color(0xFFE9EEF0),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable {
                        when (option) {
                            "Retiros" -> {}
                            else -> {}
                        }
                    }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option,
                    textAlign = TextAlign.Center,
                    fontWeight = if (isWelcome || isSelected) FontWeight.Bold else FontWeight.Normal,
                    fontSize = 16.sp,
                    color = Color(0xFF002C77)
                )
            }
        }

        Spacer(modifier = Modifier.weight(7f))

        ButtonOutline(text = "Continuar", onClick = {
            navController.navigate(Destinations.SELECT_ACCOUNT_TYPE)
        })
    }
}


@Preview(showBackground = true)
@Composable
fun TransactionSelectionPreview() {
    TransactionSelectionScreen(rememberNavController())
}