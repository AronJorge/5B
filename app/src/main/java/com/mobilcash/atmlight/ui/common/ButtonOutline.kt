package com.mobilcash.atmlight.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonOutline(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isPositive: Boolean = true
) {
    val backgroundColor = if (isPositive) Color.White else Color(0xFFDCE3E7)
    val borderColor = if (isPositive) Color(0xFF002C77) else Color.Transparent
    val textColor = Color(0xFF002C77)

    Box(
        modifier = modifier
            .border(
                width = if (isPositive) 2.dp else 0.dp,
                color = borderColor,
                shape = RoundedCornerShape(4.dp)
            )
            .background(backgroundColor, shape = RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .padding(horizontal = 50.dp, vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    }
}