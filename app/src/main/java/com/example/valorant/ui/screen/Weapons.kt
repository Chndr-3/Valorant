package com.example.valorant.ui.screen

import android.util.Log
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.valorant.data.WeaponItems
import com.example.valorant.ui.screen.detail.DetailAgents
import com.example.valorant.ui.theme.blackV
import com.example.valorant.ui.theme.blueV
import com.example.valorant.ui.theme.tungstenFont
import com.example.valorant.ui.theme.valorantFont
import com.example.valorant.ui.viewmodel.HomeViewModel

@Composable
fun WeaponCards(dataItem: WeaponItems, navHostController: NavHostController, viewModel: HomeViewModel) {
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
                .clickable {
                    navHostController.navigate("detail_weapon")
                    viewModel.selectedWeapon(dataItem)
                }
        ) {
            Text(
                text = dataItem.category?.split("::")?.get(1) ?: "Error",
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
            contentScale = ContentScale.Inside,
            painter = rememberAsyncImagePainter(dataItem.displayIcon),
            contentDescription = null,
            modifier = Modifier

                .align(Alignment.Center).fillMaxWidth()
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
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
fun WeaponsList(navHostController: NavHostController, viewModel: HomeViewModel) {
    val weaponsData = viewModel.weaponsData.collectAsState()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .background(blackV)
            .fillMaxSize()
    ) {
        items(count = weaponsData.value.size) {

            WeaponCards(weaponsData.value[it], navHostController, viewModel)
        }

    }
}