package com.mobilcash.atmlight.ui.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilcash.atmlight.navigation.Destinations
import com.mobilcash.atmlight.ui.common.BaseScreen
import com.mobilcash.atmlight.ui.common.ButtonOutline

@Composable
fun ThanksFeedbackScreen(navController: NavController) {
    BaseScreen(showBack = false) {
        Spacer(modifier = Modifier.height(80.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            Text(
                text = "GRACIAS POR USAR\nNUESTROS SERVICIOS",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(80.dp))

            Box(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                contentAlignment = Alignment.CenterStart
            ) {

                Text(
                    "Siempre hay un cajero \n",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF002C77),
                    fontSize = 30.sp,
                )
                Text(
                    buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFF9B100),
                                fontSize = 34.sp,
                            )
                        ) {
                            append("5B ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF002C77),
                                fontSize = 30.sp,
                                baselineShift = BaselineShift(0.2f)
                            )
                        ) {
                            append("cerca de ti.")
                        }
                    },
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(top = 60.dp),
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            ButtonOutline(
                text = "Finalizar",
                onClick = {
                    navController.navigate(Destinations.WELCOME) {
                        popUpTo(0) { inclusive = true }
                        launchSingleTop = true
                    }
                },
            )


            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThanksFeedbackPreview() {
    ThanksFeedbackScreen(rememberNavController())
}
