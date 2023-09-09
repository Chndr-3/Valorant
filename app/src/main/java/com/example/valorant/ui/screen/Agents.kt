package com.example.valorant.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.valorant.data.Agents
import com.example.valorant.data.DataItem
import com.example.valorant.ui.theme.blackV
import com.example.valorant.ui.theme.blueV
import com.example.valorant.ui.theme.tungstenFont
import com.example.valorant.ui.theme.valorantFont
import com.example.valorant.ui.viewmodel.HomeViewModel

@Composable
fun AgentsCard(dataItem: DataItem, navHostController: NavHostController, viewModel: HomeViewModel) {
    Box(
        Modifier
            .height(200.dp)
            .background(Color.Transparent)
            .padding(horizontal = 6.dp)
            .clickable {
                viewModel.selectedAgents(dataItem)
                navHostController.navigate("detail_agent")

            }
    ) {
        Box(
            modifier = Modifier
                .background(blueV, shape = RoundedCornerShape(20.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .align(Alignment.BottomCenter)

        ) {
            Text(
                text = dataItem.role?.displayName ?: "Error",
                maxLines = 1,
                style = TextStyle(

                    fontSize = if (dataItem.role?.displayName == "Controller") {
                        60.sp
                    } else {
                        70.sp
                    },
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
            contentScale = ContentScale.Fit,
            painter = rememberAsyncImagePainter(dataItem.fullPortrait),
            contentDescription = null,
            modifier = Modifier

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
                dataItem.displayName ?: "Error",
                fontSize = 30.sp,
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
fun AgentsList(navHostController: NavHostController, viewModel: HomeViewModel) {
    val agentsData = viewModel.agentsData.collectAsState()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .background(blackV)
            .fillMaxSize()
    ) {
        items(count = agentsData.value.size) {
            AgentsCard(agentsData.value[it], navHostController, viewModel)
        }

    }
}