package com.mobilcash.atmlight.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mobilcash.atmlight.navigation.Destinations
import com.mobilcash.atmlight.ui.common.BaseScreen
import com.mobilcash.atmlight.ui.common.ButtonOutline


@Composable
fun SignatureScreen(navController: NavController) {
    val paths = remember { mutableStateListOf<Path>() }
    var currentPath by remember { mutableStateOf(Path()) }

    BaseScreen(title = "REALIZA TU FIRMA") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Firma dentro del recuadro",
                fontSize = 18.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp))
                    .background(Color.White, RoundedCornerShape(8.dp))
                    .padding(4.dp)
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectDragGestures(
                                onDragStart = { offset ->
                                    currentPath.moveTo(offset.x, offset.y)
                                },
                                onDrag = { change, _ ->
                                    currentPath.lineTo(change.position.x, change.position.y)
                                },
                                onDragEnd = {
                                    paths.add(currentPath)
                                    currentPath = Path()
                                }
                            )
                        }
                ) {
                    paths.forEach { path ->
                        drawPath(
                            path = path,
                            color = Color.Black,
                            style = Stroke(width = 4f, cap = StrokeCap.Round)
                        )
                    }
                    drawPath(
                        path = currentPath,
                        color = Color.Black,
                        style = Stroke(width = 4f, cap = StrokeCap.Round)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                ButtonOutline(text = "Limpiar", onClick = {
                    paths.clear()
                    currentPath = Path()
                }, isPositive = false)

                ButtonOutline(text = "Guardar", onClick = {
                    navController.navigate(Destinations.APPROVED_TRANSACTION)
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignatureScreenPreview() {
    SignatureScreen(rememberNavController())
}