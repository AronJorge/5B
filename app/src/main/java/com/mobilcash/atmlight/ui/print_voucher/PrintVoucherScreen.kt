package com.mobilcash.atmlight.ui.print_voucher

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilcash.atmlight.R
import com.mobilcash.atmlight.navigation.Destinations
import com.mobilcash.atmlight.ui.common.BaseScreen
import com.mobilcash.atmlight.ui.common.ButtonOutline

@Composable
fun PrintReceiptScreen(navController: NavController) {
    BaseScreen(title = "TRANSACCIÓN APROBADA", showBack = false) {
        Spacer(modifier = Modifier.height(80.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(
                text = "¿DESEAS IMPRIMIR\nTU RECIBO?",
                color = Color(0xFF002C77),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )

            Text(
                text = "DO YOU WANT TO PRINT YOUR RECEIPT?",
                fontSize = 12.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(id = R.drawable.print),
                contentDescription = "Imprimir",
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 32.dp, end = 32.dp),
        ) {
            ButtonOutline(text = "SI", onClick = {
                navController.navigate(Destinations.QR_RECEIPT_SCREEN)
            })
            ButtonOutline(text = "NO", onClick = {
                navController.navigate(Destinations.THANKS_FEEDBACK_SCREEN)
            }, isPositive = false)
        }
    }
}

@Preview
@Composable
fun PrintVoucherPreview() {
    PrintReceiptScreen(rememberNavController())
}