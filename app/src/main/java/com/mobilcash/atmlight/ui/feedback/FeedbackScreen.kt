package com.mobilcash.atmlight.ui.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobilcash.atmlight.ui.common.BaseScreen
import com.mobilcash.atmlight.ui.common.ButtonOutline

@Composable
fun FeedbackScreen() {
    BaseScreen(showBack = false) {
        Spacer(modifier = Modifier.height(100.dp))
        Text(
            text = "NECESITAS REALIZAR\nOTRA TRANSACCIÓN",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp, start = 32.dp, end = 32.dp),
        ) {
            ButtonOutline(text = "SI", onClick = {

            })
            ButtonOutline(text = "NO", onClick = {}, isPositive = false)
        }
    }
}

@Preview
@Composable
fun FeedbackPreview() {
    FeedbackScreen()
}