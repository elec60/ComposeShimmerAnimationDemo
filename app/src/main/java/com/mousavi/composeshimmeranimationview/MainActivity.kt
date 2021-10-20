package com.mousavi.composeshimmeranimationview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mousavi.composeshimmeranimationview.ui.theme.ComposeShimmerAnimationViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeShimmerAnimationViewTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(10) {
                            ShimmerAnimationView()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShimmerAnimationView() {
    val colors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transient = rememberInfiniteTransition()
    val animateFloat = transient.animateFloat(
        initialValue = 0f,
        targetValue = 800f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset(x = 10f, y = 10f),
        end = Offset(animateFloat.value, animateFloat.value)
    )

    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(100.dp)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .background(brush)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .height(30.dp)
                .fillMaxWidth()
                .background(brush))

            Box(modifier = Modifier
                .padding(top = 10.dp)
                .height(30.dp)
                .fillMaxWidth(0.5f)
                .background(brush))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeShimmerAnimationViewTheme {
    }
}