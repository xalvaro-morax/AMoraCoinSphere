@file:OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)

package com.example.coinsphere

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest

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
    val top10 = remember {
        listOf(
            Crypto(1, "Bitcoin",   "$109,797.37", cmcIcon(1)),
            Crypto(2, "Ethereum",  "$4,321.21",   cmcIcon(1027)),
            Crypto(3, "Tether",    "$1.0000",     cmcIcon(825)),
            Crypto(4, "XRP",       "$2.8100",     cmcIcon(52)),
            Crypto(5, "BNB",       "$845.0000",   cmcIcon(1839)),
            Crypto(6, "Solana",    "$201.8500",   cmcIcon(5426)),
            Crypto(7, "USDC",      "$0.9998",     cmcIcon(3408)),
            Crypto(8, "Dogecoin",  "$0.1320",     cmcIcon(74)),
            Crypto(9, "TRON",      "$0.3630",     cmcIcon(1958)),
            Crypto(10,"Cardano",   "$0.5120",     cmcIcon(2010))
        )
    }

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
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Background)
                .padding(inner)
        ) {
            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    InfoCard("Global Market Cap", "$2.18T")
                    Spacer(Modifier.height(12.dp))
                    InfoCard("Fear & Greed", "Neutral (54)")
                    Spacer(Modifier.height(12.dp))
                    InfoCard("Altcoin Season", "No")
                    Spacer(Modifier.height(16.dp))
                    HeaderRow("#", "Name", "Price")
                }
            }
            items(top10) { coin ->
                CryptoRow(coin)
            }
            item { Spacer(Modifier.height(24.dp)) }
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

@Composable
fun HeaderRow(col1: String, col2: String, col3: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(col1, color = TextDim, modifier = Modifier.width(28.dp))
        Text(col2, color = TextDim, modifier = Modifier.weight(1f))
        Text(col3, color = TextDim)
    }
}

data class Crypto(val rank: Int, val name: String, val price: String, val iconUrl: String)
fun cmcIcon(id: Int) = "https://s2.coinmarketcap.com/static/img/coins/64x64/$id.png"

@Composable
fun CryptoRow(c: Crypto) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Surface)
            .padding(horizontal = 14.dp, vertical = 12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("${c.rank}", color = TextMain, modifier = Modifier.width(28.dp), fontWeight = FontWeight.SemiBold)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(c.iconUrl).crossfade(true).build(),
                contentDescription = c.name,
                modifier = Modifier.size(28.dp).clip(CircleShape)
            )
            Spacer(Modifier.width(12.dp))
            Text(
                c.name,
                color = TextMain,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(c.price, color = TextMain, fontSize = 15.sp, fontWeight = FontWeight.Medium)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCommit3() {
    AppThemeCoinsphere {
        HomeScreen()
    }
}