package com.mobilcash.atmlight.ui.print_voucher

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import com.mobilcash.atmlight.ui.common.BaseScreen
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.core.graphics.set
import androidx.core.graphics.createBitmap
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilcash.atmlight.navigation.Destinations
import com.mobilcash.atmlight.ui.common.ButtonOutline

@Composable
fun QRReceiptScreen(navController: NavController) {
    val dateTime = Date()
    val formattedDate =
        SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(dateTime)
    val qrContent = "DEMO RECIBO\n$formattedDate"

    val qrBitmap = generateQRCode(qrContent)

    BaseScreen(title = "RECIBO DIGITAL", showBack = false) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(20.dp)
            ) {

                Text(
                    text = "TU RECIBO DIGITAL",
                    color = Color(0xFF002C77),
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    bitmap = qrBitmap.asImageBitmap(),
                    contentDescription = "Código QR",
                    modifier = Modifier.size(200.dp)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = formattedDate,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(1.dp))

            ButtonOutline(text = "Continuar", onClick = {
                navController.navigate(Destinations.THANKS_FEEDBACK_SCREEN)
            })
        }
    }
}

fun generateQRCode(content: String): Bitmap {
    val size = 512
    val bits = QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, size, size)
    val bitmap = createBitmap(size, size, Bitmap.Config.RGB_565)

    for (x in 0 until size) {
        for (y in 0 until size) {
            bitmap[x, y] =
                if (bits.get(x, y)) android.graphics.Color.BLACK else android.graphics.Color.WHITE
        }
    }

    return bitmap
}

@Preview(showBackground = true)
@Composable
fun QRReceiptScreenPreview() {
    QRReceiptScreen(rememberNavController())
}