package com.example.coinsphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinSphereTheme {
                HomeScreen()
            }
        }
    }
}


val Background = Color(0xFF0B1020)
val SurfaceColor = Color(0xFF151B2E)
val TextMain = Color(0xFFE8ECF8)
val TextDim = Color(0xFF9AA3B2)
val AccentBlue = Color(0xFF2E7FFF)


@Composable
fun CoinSphereTheme(content: @Composable () -> Unit) {
    val darkColors = darkColorScheme(
        primary = AccentBlue,
        background = Background,
        surface = SurfaceColor,
        onBackground = TextMain,
        onSurface = TextMain
    )

    MaterialTheme(
        colorScheme = darkColors,
        content = content,
        typography = Typography()
    )
}


@Composable
fun HomeScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Background
    ) {
        Text(text = "Cargando...", color = TextMain)
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    CoinSphereTheme {
        HomeScreen()
    }
}
