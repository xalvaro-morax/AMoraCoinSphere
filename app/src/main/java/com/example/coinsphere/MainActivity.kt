package com.example.coinsphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Coin Sphere", style = MaterialTheme.typography.headlineSmall, color = TextMain)
            Spacer(modifier = Modifier.height(12.dp))
            InformativeCards()
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Top 10 Cryptocurrencies", color = TextDim, style = MaterialTheme.typography.titleMedium)
        }
    }
}

@Composable
fun InformativeCards() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        InfoCard("Global Market Cap", "$2.18T")
        InfoCard("Fear & Greed", "Neutral (54)")
        InfoCard("Altcoin Season", "No")
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .height(72.dp)
            .fillMaxWidth(1f),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = SurfaceColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = title, color = TextDim, style = MaterialTheme.typography.labelSmall)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = value, color = TextMain, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenCardsPreview() {
    CoinSphereTheme {
        HomeScreen()
    }
}
