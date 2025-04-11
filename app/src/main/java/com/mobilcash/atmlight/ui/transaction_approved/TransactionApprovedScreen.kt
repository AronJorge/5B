package com.mobilcash.atmlight.ui.transaction_approved

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
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
fun TransactionApprovedScreen(navController: NavController) {
    BaseScreen(title = "TRANSACCIÓN APROBADA.", showBack = false) {
        Spacer(modifier = Modifier.height(40.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 40.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cash),
                    contentDescription = "Dinero",
                    modifier = Modifier.size(54.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "POR FAVOR",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "SOLICITA TU DINERO\nEN LA TIENDA",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF002C77)
                    )
                }
            }

            ButtonOutline(text = "Continuar", onClick = {
                navController.navigate(Destinations.PRINT_VOUCHER)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TransactionApprovedPreview() {
    TransactionApprovedScreen(rememberNavController())
}