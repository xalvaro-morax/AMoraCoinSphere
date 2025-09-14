@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.coinsphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Background = Color(0xFF0B1020)
private val Surface    = Color(0xFF151B2E)
private val TextMain   = Color(0xFFE8ECF8)
private val TextDim    = Color(0xFF9AA3B2)
private val Accent     = Color(0xFF3EA6FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppThemeCoinsphere {
                HomeScreen()
            }
        }
    }
}

@Composable
fun AppThemeCoinsphere(content: @Composable () -> Unit) {
    val colors = darkColorScheme(
        primary = Accent,
        onPrimary = Color.Black,
        background = Background,
        surface = Surface,
        onBackground = TextMain,
        onSurface = TextMain
    )
    MaterialTheme(colorScheme = colors, content = content)
}

@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "CoinSphere",
                        color = TextMain,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Background
                )
            )
        },
        containerColor = Background
    ) { inner ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(inner)
                .padding(16.dp)
        ) {
            InfoCard("Global Market Cap", "$2.18T")
            Spacer(Modifier.height(12.dp))
            InfoCard("Fear & Greed", "Neutral (54)")
            Spacer(Modifier.height(12.dp))
            InfoCard("Altcoin Season", "No")
        }
    }
}

@Composable
fun InfoCard(title: String, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Surface)
            .padding(16.dp)
    ) {
        Column {
            Text(title, color = TextDim, fontSize = 14.sp)
            Spacer(Modifier.height(6.dp))
            Text(value, color = TextMain, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCommit2() {
    AppThemeCoinsphere {
        HomeScreen()
    }
}