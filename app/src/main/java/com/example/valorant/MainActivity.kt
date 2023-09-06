package com.example.valorant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.valorant.ui.theme.ValorantTheme
import com.example.valorant.ui.theme.blackV
import com.example.valorant.ui.theme.blueV
import com.example.valorant.ui.theme.redV
import com.example.valorant.ui.theme.tungstenFont
import com.example.valorant.ui.theme.valorantFont

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ValorantTheme {
                Scaffold(topBar = {
                    CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        blackV
                    ),
                        title = {
                            Text(
                                "VALORANT",
                                style = TextStyle(
                                    fontFamily = valorantFont,
                                    color = Color.White,
                                    fontSize = 30.sp
                                )
                            )
                        })
                }) {
                    TabScreen(modifier = Modifier.padding(it))
                }
            }
        }
    }
}

@Composable
fun HeroesCard() {
    Box(
        Modifier
            .height(200.dp)
            .background(Color.Transparent)
            .padding(horizontal = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .background(blueV, shape = RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .align(Alignment.BottomCenter)
        ) {
            Text(
                text = "SENTINEL",
                maxLines = 1,
                style = TextStyle(

                    fontSize = 70.sp,
                    letterSpacing = 20.sp,
                    color = Color.White.copy(alpha = 0.1f),
                    fontFamily = tungstenFont
                ),
                modifier = Modifier.align(
                    Alignment.Center
                )

            )

        }
        Image(
            contentScale = ContentScale.FillHeight,
            painter = rememberAsyncImagePainter("https://media.valorant-api.com/agents/569fdd95-4d10-43ab-ca70-79becc718b46/fullportrait.png"),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterStart)
                .graphicsLayer {
                    translationY = (-50).toFloat()
                    translationX = (-100).toFloat()
                }
                .padding(start = 10.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 40.dp)
        ) {
            Text(
                "SAGE",
                fontSize = 40.sp,
                color = Color.White,
                fontFamily = valorantFont,

                )
            Row {
                Text("")
            }
        }
    }
}

@Composable
fun AgentsList(){
    LazyColumn(
        Modifier
            .background(blackV)
            .fillMaxSize()) {
        item{
            HeroesCard()
        }

    }
}

@Composable
fun TabScreen(modifier: Modifier) {
    var tabIndex by remember { mutableStateOf(0) }

    val tabs = listOf("Agents", "Weapon")

    Column(modifier = modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            containerColor = blackV,
            contentColor = redV,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[tabIndex]),
                    content = {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(redV)
                                .height(5.dp)
                                .align(Alignment.BottomCenter) // Adjust the height of the indicator
                        )
                    }
                )
            }) {
            tabs.forEachIndexed { index, title ->
                Tab(selectedContentColor = redV,
                    text = {
                        Text(
                            title,
                            style = TextStyle(fontSize = 20.sp, fontFamily = tungstenFont)
                        )
                    },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> AgentsList()
        }
    }
}

@Preview
@Composable
fun AgentsListPreview() {
    AgentsList()
}