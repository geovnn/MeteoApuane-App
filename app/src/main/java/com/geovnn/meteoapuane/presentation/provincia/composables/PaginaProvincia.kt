package com.geovnn.meteoapuane.presentation.provincia.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.domain.models.ProvinciaPageTab
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaginaProvincia(
    modifier : Modifier = Modifier,
    uiState: ProvinciaPageTab
) {
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        val pagerState = rememberPagerState(pageCount = { 2 })
        val coroutineScope = rememberCoroutineScope()
        TabRow(selectedTabIndex = pagerState.currentPage) {
            Tab(
                text = { Text(text = "Mattina") },
                selected = pagerState.currentPage == 0,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } }
            )
            Tab(
                text = { Text(text = "Sera") },
                selected = pagerState.currentPage == 1,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } }
            )
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            beyondBoundsPageCount = 1
        ) { page ->
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                when(page) {
                    0 -> {
                        if (uiState.mappaMattina!=null) {
                            MappaProvincia(mappa = uiState.mappaMattina)
                        }
                    }
                    1 -> {
                        if (uiState.mappaSera!=null) {
                            MappaProvincia(mappa = uiState.mappaSera)
                        }
                    }
                }
            }
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(modifier = Modifier.padding(5.dp),text = uiState.testo)
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(modifier = Modifier.fillMaxWidth(), text = "TEMPERATURE", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Text(text = uiState.testoTemperature)
                Divider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.secondary)
                Text(modifier = Modifier.fillMaxWidth(), text = "VENTI", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Text(text = uiState.testoVenti)
                Divider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.secondary)
                Text(modifier = Modifier.fillMaxWidth(), text = "MARE", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Text(text = uiState.testoMare)
            }
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(modifier = Modifier.fillMaxWidth(), text = "TEMPERATURE (°C)", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Row {
                    Text(modifier = Modifier.weight(4f),text = "")
                    Text(modifier = Modifier.weight(1f),text = "MIN")
                    Text(modifier = Modifier.weight(1f),text = "MAX")
                }
                Divider(color = MaterialTheme.colorScheme.secondary)
                Row {
                    Text(modifier = Modifier.weight(4f),text = "MASSA")
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureMassa.first.toString())
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureMassa.second.toString())
                }
                Divider(color = MaterialTheme.colorScheme.secondary)
                Row {
                    Text(modifier = Modifier.weight(4f),text = "CARRARA")
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureCarrara.first.toString())
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureCarrara.second.toString())
                }
                Divider(color = MaterialTheme.colorScheme.secondary)
                Row {
                    Text(modifier = Modifier.weight(4f),text = "AULLA")
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureAulla.first.toString())
                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureAulla.second.toString())
                }
                Divider(color = MaterialTheme.colorScheme.secondary)
//                Row {
//                    Text(modifier = Modifier.weight(4f),text = "FIVIZZANO")
//                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureFivizzano.first.toString())
//                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureFivizzano.second.toString())
//                }
//                Divider(color = MaterialTheme.colorScheme.secondary)
                Row {
                    Text(modifier = Modifier.weight(4f),text = "PONTREMOLI")
                    Text(modifier = Modifier.weight(1f),text = uiState.temperaturePontremoli.first.toString())
                    Text(modifier = Modifier.weight(1f),text = uiState.temperaturePontremoli.second.toString())
                }
            }
        }
    }
}