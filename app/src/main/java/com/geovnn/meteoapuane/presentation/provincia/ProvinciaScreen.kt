package com.geovnn.meteoapuane.presentation.provincia

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.presentation.provincia.composables.PaginaProvincia
import com.geovnn.meteoapuane.presentation.provincia.composables.PaginaSuccessivi
import com.geovnn.meteoapuane.presentation.utils.FontSizeRange
import com.geovnn.meteoapuane.presentation.utils.composables.AutoResizeText
import com.geovnn.meteoapuane.presentation.utils.composables.UltimoAggiornamentoText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class,
    ExperimentalFoundationApi::class
)
@Composable
fun ProvinciaScreen(
    uiState: ProvinciaUiState,
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

//    LaunchedEffect(Unit) {
//        refreshData()
//    }
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
                text = { Text(text = uiState.error) },
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        } else {
            Column {
                val pagerState = rememberPagerState(pageCount = { 4 })
                val tabRowItems = listOf(
                    uiState.provinciaPage.paginaOggi.data,
                    uiState.provinciaPage.paginaDomani.data,
                    uiState.provinciaPage.paginaDopodomani.data,
                    uiState.provinciaPage.paginaSuccessivi.label
                )
                UltimoAggiornamentoText(text = uiState.provinciaPage.testoUltimoAggiornamento)
//                Text(
//                    text = uiState.provinciaPage.testoUltimoAggiornamento,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(3.dp),
//                    fontSize = 14.sp,
//                    textAlign = TextAlign.Center
//                )
                TabRow(selectedTabIndex = pagerState.currentPage) {
                    tabRowItems.forEachIndexed { index, item ->//iterate over TabItem List
                        Tab(//Create tab for each item
                            text = { Text(text = item,
                                fontSize = MaterialTheme.typography.titleSmall.fontSize,
                                fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                                fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                                fontStyle = MaterialTheme.typography.titleSmall.fontStyle)},
                            selected = pagerState.currentPage == index,//select only when current index is stored page
                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } }//animate scroll onClick
                        )
                    }
                }

                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    state = pagerState,
                    beyondBoundsPageCount = 3
                ) { page ->
                    when(page) {
                        0 -> PaginaProvincia( uiState = uiState.provinciaPage.paginaOggi)
                        1 -> PaginaProvincia(uiState = uiState.provinciaPage.paginaDomani)
                        2 -> PaginaProvincia(uiState = uiState.provinciaPage.paginaDopodomani)
                        3 -> PaginaSuccessivi(uiState = uiState.provinciaPage.paginaSuccessivi)
                    }
//                        Column(
//                            modifier = Modifier
//                                .verticalScroll(rememberScrollState())
//                                .fillMaxSize()
//                        ) {
//
//
//
//                        }

                }

            }

        }
        PullRefreshIndicator(isRefreshing, state, Modifier.align(Alignment.TopCenter))

    }
}




