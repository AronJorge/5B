package com.mobilcash.atmlight.ui.processing_transaction

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilcash.atmlight.navigation.Destinations
import com.mobilcash.atmlight.ui.common.BaseScreen
import kotlinx.coroutines.delay

@Composable
fun ProcessingTransactionScreen(navController: NavController) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val paddingValue = screenHeight * 0.35f

    BaseScreen(title = "PROCESANDO SU TRANSACCIÓN", showBack = false) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = paddingValue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                color = Color.DarkGray,
                strokeWidth = 6.dp
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "PROCESANDO",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    LaunchedEffect(true) {
        delay(3000L)
        navController.navigate(Destinations.SIGNATURE_SCREEN)
    }
}


@Preview(showBackground = true)
@Composable
fun ProcessingTransactionPreview() {
    ProcessingTransactionScreen(rememberNavController())
}