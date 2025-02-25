package com.example.jetpack_demo_test.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.jetpack_demo_test.domain.model.PetDetails
import com.example.jetpack_demo_test.ui.dimen.Dimensions

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPager(
    pagerState: PagerState,
    carousalList: PetDetails,
) {
    val localDim = compositionLocalOf { Dimensions() }

    Column(modifier = Modifier.wrapContentHeight()) {
        HorizontalPager(
            state = pagerState,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(localDim.current.pagerDimen)
                    .padding(top = localDim.current.marginLarge, start = localDim.current.marginLarge, end = localDim.current.marginLarge),
        ) { page ->
            Image(
                painter = painterResource(id = carousalList.speciesList[page].image),
                contentDescription = "Image ${page + 1}",
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .border(
                            shape =
                                RoundedCornerShape(localDim.current.spaceMedium),
                            border = BorderStroke(localDim.current.lineWidth, Color.Gray),
                        ).fillMaxSize()
                        .clip(
                            RoundedCornerShape(localDim.current.spaceMedium),
                        ),
            )
        }

        PageIndicators(pagerState)
    }
}
