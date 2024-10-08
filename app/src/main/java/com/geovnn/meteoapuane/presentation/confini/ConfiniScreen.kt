package com.geovnn.meteoapuane.presentation.confini

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextButton
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geovnn.meteoapuane.presentation.confini.composables.PaginaConfini
import com.geovnn.meteoapuane.presentation.utils.composables.UltimoAggiornamentoText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun ConfiniScreen(
    uiState: ConfiniUiState,
    refreshData: () -> Unit
) {
    val refreshScope = rememberCoroutineScope()
    var isRefreshing by remember { mutableStateOf(false) }
    fun refresh() = refreshScope.launch {
        isRefreshing = true
        refreshData()
        delay(1000)
        isRefreshing = false
    }
    val state = rememberPullRefreshState(isRefreshing, ::refresh)
    val coroutineScope = rememberCoroutineScope()//will use for animation

    LaunchedEffect(Unit) {
        refreshData()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(state)
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(64.dp)
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.onPrimary,
            )
        } else if (uiState.error!="") {
            AlertDialog(
                onDismissRequest = {  },
                confirmButton = { TextButton(onClick = { refreshData() }) {
                    Text(text = "Riprova")
                } },
                title = { Text(text = "Errore") },
                text = { Text(text = uiState.error) }
            )
        } else {
            val pages = remember { listOf(
                uiState.confiniPage.paginaOggi,
                uiState.confiniPage.paginaDomani,
                uiState.confiniPage.paginaDopodomani
            )}
            val pagerState = rememberPagerState(pageCount = { 3 })
            LazyColumn(
                modifier = Modifier,//.verticalScroll(rememberScrollState())
                verticalArrangement = Arrangement.Top
            ) {
                item {
                    UltimoAggiornamentoText(text=uiState.confiniPage.testoUltimoAggiornamento)
                    Text(
                        text = uiState.confiniPage.testoPrevisione,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                    )
                }
                stickyHeader {
                    val tabRowItems = listOf(
                        uiState.confiniPage.paginaOggi.data,
                        uiState.confiniPage.paginaDomani.data,
                        uiState.confiniPage.paginaDopodomani.data,
                    )
                    TabRow(selectedTabIndex = pagerState.currentPage) {
                        tabRowItems.forEachIndexed { index, item ->//iterate over TabItem List
                            Tab(//Create tab for each item
                                text = { Text(text = item) },
                                selected = pagerState.currentPage == index,//select only when current index is stored page
                                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } }//animate scroll onClick
                            )
                        }
                    }
                }
                item {
                    HorizontalPager(
                        userScrollEnabled = false,
                        modifier = Modifier
                            .fillMaxWidth(),
                        state = pagerState,
                        pageSize = PageSize.Fill
                    ) { page ->
                        Box(modifier = Modifier.fillMaxSize()) {
                            PaginaConfini(uiState = pages[page])
                        }
                    }
                }
            }
        }
        PullRefreshIndicator(isRefreshing, state, Modifier.align(Alignment.TopCenter))
    }
}