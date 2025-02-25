package com.example.jetpack_demo_test.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetpack_demo_test.R
import com.example.jetpack_demo_test.ui.dimen.Dimensions
import com.example.jetpack_demo_test.ui.viewModel.PetViewModel
import java.util.Locale
import com.example.jetpack_demo_test.ui.theme.Typography
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PetBreedScreen(showBottomSheet: MutableState<Boolean>) {
    val viewModel = hiltViewModel<PetViewModel>()
    val searchText = rememberSaveable { mutableStateOf("") }
    val carousalList by viewModel.petData.collectAsState()
    val pagerState = rememberPagerState(pageCount = { carousalList.speciesList.size })
    val localDim = compositionLocalOf { Dimensions() }

    LaunchedEffect(pagerState.currentPage) {
        searchText.value = ""
        viewModel.getPetList("")
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (carousalList.speciesList.isNotEmpty()) {
            LazyColumn(
                modifier =
                    Modifier
                        .padding(top = localDim.current.default)
                        .fillMaxSize(),
            ) {
                item {
                    ViewPager(pagerState, carousalList)
                }
                stickyHeader {
                    SearchWidget(searchText) { input ->
                        viewModel.getFilteredData(pagerState.currentPage, input)
                    }
                }
                items(carousalList.speciesList[pagerState.currentPage].breedsList) { item ->
                    BreedCard(item)
                }
            }
        }

        if (showBottomSheet.value) {
            StatisticBottomSheet(
                onDismissRequest = { showBottomSheet.value = false },
            ) {
                LaunchedEffect(Unit) {
                    viewModel.getStatisticCount(carousalList.speciesList[pagerState.currentPage].breedsList)
                }
                val charStatistics by viewModel.topCharCount.collectAsState(initial = listOf())


                Column(modifier = Modifier.padding(localDim.current.spaceMedium)) {
                    Text(
                        text =
                            LocalContext.current.getString(
                                R.string.total_count,
                                carousalList.speciesList[pagerState.currentPage]
                                    .breedsList.size
                                    .toString(),
                            ),
                        style = Typography.titleLarge,
                        textAlign = TextAlign.Center,
                        modifier =
                            Modifier
                                .padding(bottom = localDim.current.marginMedium)
                                .fillMaxWidth(),
                    )
                    if (charStatistics.isNotEmpty()) {
                        charStatistics.map { (char, count) ->
                            Row(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .padding(localDim.current.marginSmall)
                                        .background(
                                            color = MaterialTheme.colorScheme.inversePrimary.copy(alpha = 0.4f),
                                            shape = RoundedCornerShape(localDim.current.marginSmall),
                                        ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                Text(
                                    text = char.toString().uppercase(Locale.getDefault()),
                                    textAlign = TextAlign.Center,
                                    style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                                    modifier = Modifier.weight(1f),
                                )
                                Text(
                                    text = count.toString(),
                                    style = Typography.bodyLarge,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.weight(1f),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
