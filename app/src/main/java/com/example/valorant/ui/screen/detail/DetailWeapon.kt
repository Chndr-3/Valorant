package com.example.valorant.ui.screen.detail

import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.valorant.data.WeaponItems
import com.example.valorant.ui.screen.WeaponCards
import com.example.valorant.ui.theme.blackV
import com.example.valorant.ui.theme.blueV
import com.example.valorant.ui.theme.redV
import com.example.valorant.ui.theme.tungstenFont
import com.example.valorant.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailWeapon(viewModel: HomeViewModel, navHostController: NavHostController) {
    val pagerState = rememberPagerState(initialPage = 0)
    VerticalPager(pageCount = 2, state = pagerState) {
        WeaponHeader(viewModel = viewModel, it, pagerState, navHostController)


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeaponHeader(
    viewModel: HomeViewModel,
    pagerIndex: Int,
    pagerState: PagerState,
    navHostController: NavHostController
) {
    val data = viewModel.selectedWeapon
    val mContext = LocalContext.current
    when (pagerIndex) {
        0 -> {
            BoxWithConstraints() {
                Box(
                    Modifier
                        .background(blackV)


                ) {

                    Column(Modifier.height(this@BoxWithConstraints.maxHeight)) {

                        Box(
                            Modifier
                                .fillMaxHeight(0.5f)
                                .fillMaxWidth(1f)
                                .background(
                                    redV,
                                    RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                                )

                        )
                        Box(
                            Modifier
                                .fillMaxHeight(0.4f)
                                .fillMaxWidth(1f)
                                .background(blackV)
                        )
                    }
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                            .height(this@BoxWithConstraints.maxHeight)
                    ) {
                        Image(
                            contentScale = ContentScale.Fit,
                            painter = rememberAsyncImagePainter(model = data?.displayIcon),
                            contentDescription = "Agents Image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.55f)

                        )
                        Row(
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(verticalArrangement = Arrangement.Center) {
                                Text(
                                    data?.displayName ?: "",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 50.sp,
                                        fontFamily = tungstenFont
                                    )
                                )
                                Text(
                                    "(${data?.shopData?.category})" ?: "",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 30.sp,
                                        fontFamily = tungstenFont
                                    )
                                )

                            }
                        }

                        Text(
                            ("Fire Rate   : " + data?.weaponStats?.fireRate.toString()) ?: "",
                            style = TextStyle(color = Color.White),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Text(
                            ("Magazine  : " + data?.weaponStats?.magazineSize.toString()) ?: "",
                            style = TextStyle(color = Color.White)
                        )
                        Text(
                            ("Reload      : " + data?.weaponStats?.reloadTimeSeconds.toString()) ?: "",
                            style = TextStyle(color = Color.White)
                        )
                        Text(
                            ("Wall Pen     : " + data?.weaponStats?.wallPenetration?.split("::")?.get(1)) ?: "",
                            style = TextStyle(color = Color.White)
                        )

                    }
                    AnimatedVisibility(
                        visible = !pagerState.isScrollInProgress,
                        enter = fadeIn(),
                        exit = fadeOut(),
                        modifier = Modifier.align(
                            Alignment.BottomCenter
                        )
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Skins",
                                style = TextStyle(fontSize = 15.sp),
                                color = Color.White
                            )
                            Icon(
                                Icons.Default.KeyboardArrowDown,
                                contentDescription = "Arrow Down",
                                tint = Color.White
                            )
                        }
                    }
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Arrow Back",
                            modifier = Modifier.size(50.dp)
                        )
                    }

                }
            }
        }

        1 -> {

            Column(
                Modifier
                    .background(blackV)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "Skins",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 40.sp,
                        fontFamily = tungstenFont
                    ),
                    modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
                    textAlign = TextAlign.Center
                )
                SkinsList(weaponItems = data!!)
            }
        }
    }


}

@Composable
fun SkinsList(weaponItems: WeaponItems) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(count = weaponItems.skins?.size!!) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.background(
                    blueV, RoundedCornerShape(10.dp)
                ).fillMaxSize()
            ) {
                Image(
                    contentScale = ContentScale.FillWidth,
                    painter = rememberAsyncImagePainter(model = weaponItems.skins[it]?.displayIcon),
                    contentDescription = "Agents Image",
                    modifier = Modifier.size(128.dp)


                )
                Text(
                    weaponItems.skins[it]?.displayName!!,
                    color = Color.White,
                    style = TextStyle(fontSize = 20.sp, fontFamily = tungstenFont),
                    modifier = Modifier.padding(vertical = 10.dp)
                )
            }

        }
    }
}