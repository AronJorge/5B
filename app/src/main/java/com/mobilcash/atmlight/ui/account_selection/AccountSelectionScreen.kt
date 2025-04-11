package com.mobilcash.atmlight.ui.account_selection

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
fun AccountTypeSelectionScreen(navController: NavController) {
    var selectedOption by remember { mutableStateOf("Ahorros") }

    BaseScreen(title = "SELECCIONA TIPO DE CUENTA") {
        Spacer(modifier = Modifier.height(24.dp))

        val options = listOf(
            "Logo del Banco\nEmisor",
            "Monetaria",
            "Ahorros"
        )

        options.forEach { option ->
            val isSelected = option == selectedOption

            Box(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .padding(vertical = 6.dp)
                    .border(
                        width = if (isSelected) 2.dp else 0.dp,
                        color = Color(0xFF002C77),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(
                        Color(0xFFDCE3E7),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .clickable { selectedOption = option }
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = option,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF002C77)
                )
            }
        }

        Spacer(modifier = Modifier.weight(7f))

        ButtonOutline(text = "Continuar", onClick = {
            navController.navigate(Destinations.SELECT_WITHDRAWAL_AMOUNT)
        })

    }
}

@Preview(showBackground = true)
@Composable
fun AccountSelectionPreview() {
    AccountTypeSelectionScreen(rememberNavController())
}