package com.example.valorant.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.valorant.data.DataItem
import com.example.valorant.ui.theme.blackV
import com.example.valorant.ui.theme.blueV
import com.example.valorant.ui.theme.redV
import com.example.valorant.ui.theme.tungstenFont
import com.example.valorant.ui.theme.valorantFont
import com.example.valorant.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: HomeViewModel, navHostController: NavHostController){
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
        TabScreen(modifier = Modifier.padding(it), navHostController, viewModel)
    }
}



@Composable
fun TabScreen(modifier: Modifier, navHostController: NavHostController, viewModel: HomeViewModel) {
    val tabIndex= viewModel.tabIndex

    val tabs = listOf("Agents", "Weapons")

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
                    onClick = { viewModel.tabIndex(index) }
                )
            }
        }
        when (tabIndex) {
            0 -> AgentsList(navHostController, viewModel)
            1 -> WeaponsList(navHostController,viewModel)
        }
    }
}
