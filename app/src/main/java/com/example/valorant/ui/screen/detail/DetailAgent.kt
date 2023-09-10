package com.example.valorant.ui.screen.detail

import android.media.MediaPlayer
import android.net.Uri
import android.widget.Space
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.valorant.data.DataItem
import com.example.valorant.ui.theme.blackV
import com.example.valorant.ui.theme.redV
import com.example.valorant.ui.theme.tungstenFont
import com.example.valorant.ui.viewmodel.HomeViewModel
import okhttp3.internal.wait

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AgentsHeader(
    viewModel: HomeViewModel,
    pagerIndex: Int,
    pagerState: PagerState,
    navHostController: NavHostController
) {
    val data = viewModel.selectedAgents
    val mContext = LocalContext.current
    val mMediaPlayer =
        MediaPlayer.create(mContext, Uri.parse(data?.voiceLine?.mediaList?.get(0)?.wave))

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
                                .fillMaxHeight(0.6f)
                                .fillMaxWidth(1f)
                                .background(
                                    redV,
                                    RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                                )
                                .paint(
                                    rememberAsyncImagePainter(model = data?.background),
                                    contentScale = ContentScale.Crop
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
                            contentScale = ContentScale.Crop,
                            painter = rememberAsyncImagePainter(model = data?.fullPortrait),
                            contentDescription = "Agents Image",
                            modifier = Modifier
                                .fillMaxHeight(0.7f)

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
                                    ),
                                    modifier = Modifier.padding(0.dp)
                                )
                                Text(
                                    "(${data?.role?.displayName})" ?: "",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 30.sp,
                                        fontFamily = tungstenFont
                                    )
                                )
                            }
                            Image(
                                painter = rememberAsyncImagePainter(model = data?.role?.displayIcon),
                                contentDescription = "Role Icon",
                                Modifier.height(40.dp)
                            )
                        }

                        Text(
                            data?.description ?: "",
                            style = TextStyle(color = Color.White),
                            modifier = Modifier.padding(vertical = 10.dp)
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
                                "Abilities & Voice Line",
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
            ) {
                Column(Modifier.fillMaxHeight(0.6f)) {
                    Text(
                        "Abilities",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 30.sp,
                            fontFamily = tungstenFont
                        )
                    )
                    AbilitiesList(dataItems = data!!)
                }


                Column {
                    Text(
                        "Voice Line",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 30.sp,
                            fontFamily = tungstenFont
                        ),
                    )
                    Box() {

                        Image(
                            painter = rememberAsyncImagePainter(model = data?.displayIcon),
                            contentDescription = "Display Icon",
                            modifier = Modifier
                                .size(90.dp)
                                .align(Alignment.Center)
                        )
                        IconButton(
                            onClick = { mMediaPlayer.start() }, modifier = Modifier.align(
                                Alignment.Center
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.fillMaxSize(1f)
                            )
                        }
                    }
                }


            }
        }
    }


}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AbilitiesList(dataItems: DataItem) {
    var chosenAbilities by remember { mutableStateOf(0) }
    Column() {
        Row(
            Modifier
                .background(blackV)
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            dataItems.abilities?.forEachIndexed() { index, agent ->
                agent?.displayIcon?.let {
                    agent.description?.let {

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painter = rememberAsyncImagePainter(model = dataItems.abilities[index]?.displayIcon),
                                contentDescription = "Abilities Icon",
                                contentScale = ContentScale.FillWidth,
                                modifier = Modifier
                                    .alpha(
                                        if (index != chosenAbilities) {
                                            0.3f
                                        } else {
                                            1f
                                        }
                                    )
                                    .size(
                                        if (index != chosenAbilities) {
                                            50.dp
                                        } else {
                                            80.dp
                                        }
                                    )
                                    .animateContentSize(
                                        spring(
                                            dampingRatio = Spring.DampingRatioHighBouncy,
                                            stiffness = Spring.StiffnessMedium
                                        )
                                    )
                                    .clickable { chosenAbilities = index }

                            )
                            Text(
                                if (index == chosenAbilities) {
                                    dataItems.abilities[index]?.displayName!!
                                } else {
                                    ""
                                },
                                style = TextStyle(fontSize = 20.sp, fontFamily = tungstenFont),
                                color = Color.White
                            )
                        }

                    }
                }
            }
        }
        AnimatedContent(targetState = chosenAbilities, label = "Text") {
            Text(
                dataItems.abilities?.get(chosenAbilities)?.description!!,
                color = Color.White,
                style = TextStyle(fontSize = 16.sp),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .border(width = 2.dp, color = Color.White)
                    .padding(10.dp)
            )
        }
    }

}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailAgents(viewModel: HomeViewModel, navHostController: NavHostController) {
    val pagerState = rememberPagerState(initialPage = 0)
    VerticalPager(pageCount = 2, state = pagerState) {
        AgentsHeader(viewModel = viewModel, it, pagerState, navHostController)


    }
}

