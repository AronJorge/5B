package com.mobilcash.atmlight.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun BaseScreen(
    title: String? = null,
    showBack: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
) {
    androidx.compose.material3.Scaffold(
        containerColor = Color(0xFFF2F4F3),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(rememberBottomCurveShape())
                    .background(Color(0xFF002C77))
            ) {
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp, bottom = 16.dp)
                ) {
                    val (titleRef, phoneRef) = createRefs()

                    Text(
                        text = "5B",
                        color = Color(0xFFF9B100),
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.constrainAs(titleRef) {
                            centerHorizontallyTo(parent)
                            top.linkTo(parent.top)
                        }
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .constrainAs(phoneRef) {
                                end.linkTo(parent.end, margin = 16.dp)
                                top.linkTo(parent.top)
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Llamar",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "1775",
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                title?.let {
                    Spacer(Modifier.height(20.dp))
                    Text(
                        text = it,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp)
                    )
                }

                content()

                if (showBack) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "Regresar",
                        color = Color.Black,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(bottom = 24.dp)
                            .clickable { /* Acción de regreso */ }
                    )
                }
            }
        }
    )
}

@Composable
fun rememberBottomCurveShape(
    curveHeight: Float = 40f,
    curveWidthFactor: Float = 1f
): Shape {
    return remember(curveHeight, curveWidthFactor) {
        GenericShape { size, _ ->
            moveTo(0f, 0f)
            lineTo(0f, size.height - curveHeight)

            quadraticTo(
                size.width / 2,
                size.height + curveHeight,
                size.width,
                size.height - curveHeight
            )

            lineTo(size.width, 0f)
            close()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BaseScreenPreview() {
    BaseScreen(
        title = "Transaction",
        content = {
            Text("TEST", modifier = Modifier.padding(16.dp))
        }
    )
}