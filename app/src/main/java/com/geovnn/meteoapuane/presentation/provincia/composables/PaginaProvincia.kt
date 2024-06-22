package com.geovnn.meteoapuane.presentation.provincia.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material.icons.outlined.Nightlight
import androidx.compose.material.icons.outlined.WbSunny
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.domain.models.ProvinciaPageTab
import com.geovnn.meteoapuane.presentation.utils.composables.BodyText
import com.geovnn.meteoapuane.presentation.utils.composables.TitleText
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaginaProvincia(
    modifier : Modifier = Modifier,
    uiState: ProvinciaPageTab
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        val pagerState = rememberPagerState(pageCount = { 2 })
        val coroutineScope = rememberCoroutineScope()
        TabRow(selectedTabIndex = pagerState.currentPage) {
//            Column(
//                modifier = Modifier
//                    .padding(2.dp)
//                    .clickable { coroutineScope.launch { pagerState.animateScrollToPage(0) } },
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                IconButton(
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally)
//                        .background(
//                            color = if (pagerState.currentPage == 0)
//                                MaterialTheme.colorScheme.primaryContainer else
//                                Color.Transparent,
//                            shape = MaterialTheme.shapes.large
//                        ),
//                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0)} }
//                ) {
//                    Icon(
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally),
//                        imageVector = if (pagerState.currentPage == 0)
//                            Icons.Filled.WbSunny else
//                            Icons.Outlined.WbSunny,
//                        contentDescription = "Localized description",
//                        tint = if (pagerState.currentPage == 0)
//                            MaterialTheme.colorScheme.onPrimaryContainer else
//                            MaterialTheme.colorScheme.onSurface,
//                    )
//                }
//                Text(
//                    text = "Mattina",
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally),
//                    color = MaterialTheme.colorScheme.onSurface,
//                    textAlign = TextAlign.Center,
//                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
//                    fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
//                    fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
//                    fontStyle = MaterialTheme.typography.labelLarge.fontStyle
//                )
//            }
//            Column(
//                modifier = Modifier
//                    .padding(2.dp)
//                    .clickable { coroutineScope.launch { pagerState.animateScrollToPage(1) } },
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                IconButton(
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally)
//                        .background(
//                            color = if (pagerState.currentPage == 1)
//                                MaterialTheme.colorScheme.primaryContainer else
//                                Color.Transparent,
//                            shape = MaterialTheme.shapes.large
//                        ),
//                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1)} }
//                ) {
//                    Icon(
//                        modifier = Modifier
//                            .align(Alignment.CenterHorizontally),
//                        imageVector = if (pagerState.currentPage == 1)
//                            Icons.Filled.Nightlight else
//                            Icons.Outlined.Nightlight,
//                        contentDescription = "Localized description",
//                        tint = if (pagerState.currentPage == 1)
//                            MaterialTheme.colorScheme.onPrimaryContainer else
//                            MaterialTheme.colorScheme.onSurface,
//                    )
//                }
//                Text(
//                    text = "Sera",
//                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally),
//                    color = MaterialTheme.colorScheme.onSurface,
//                    textAlign = TextAlign.Center,
//                    fontSize = MaterialTheme.typography.labelLarge.fontSize,
//                    fontWeight = MaterialTheme.typography.labelLarge.fontWeight,
//                    fontFamily = MaterialTheme.typography.labelLarge.fontFamily,
//                    fontStyle = MaterialTheme.typography.labelLarge.fontStyle
//                )
//            }
            Tab(
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (pagerState.currentPage == 0)
                                Icons.Filled.WbSunny else
                                Icons.Outlined.WbSunny,
                            contentDescription = "Mattina")
                        Spacer(modifier = modifier.width(3.dp))
                        Text(
                            text = "Mattina",
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                            fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                            fontStyle = MaterialTheme.typography.titleSmall.fontStyle
                        ) }
                },
                selected = pagerState.currentPage == 0,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } }
            )
            Tab(
                text = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = if (pagerState.currentPage == 1)
                                Icons.Filled.Nightlight else
                                Icons.Outlined.Nightlight,
                            contentDescription = "Sera")
                        Spacer(modifier = modifier.width(3.dp))
                        Text(text = "Sera",
                            fontSize = MaterialTheme.typography.titleSmall.fontSize,
                            fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                            fontFamily = MaterialTheme.typography.titleSmall.fontFamily,
                            fontStyle = MaterialTheme.typography.titleSmall.fontStyle) }
                },
                selected = pagerState.currentPage == 1,
                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } }
            )
//            Tab(
//                text = { Text(text = "Sera") },
//                selected = pagerState.currentPage == 1,
//                onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } }
//            )
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
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
                )
        ) {
            BodyText(modifier = Modifier.padding(5.dp),text = uiState.testo)
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
            )        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                TitleText(modifier = Modifier.fillMaxWidth(), text = "TEMPERATURE")
                BodyText(text = uiState.testoTemperature)
                HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

//                Divider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.secondary)
                TitleText(modifier = Modifier.fillMaxWidth(), text = "VENTI")
                BodyText(text = uiState.testoVenti)
                HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)

//                Divider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.secondary)
                TitleText(modifier = Modifier.fillMaxWidth(), text = "MARE")
                BodyText(text = uiState.testoMare)
            }
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
            )        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                TitleText(modifier = Modifier.fillMaxWidth(), text = "TEMPERATURE (°C)")
                Row {
                    BodyText(modifier = Modifier.weight(4f),text = "")
                    BodyText(modifier = Modifier.weight(1f),text = "MIN")
                    BodyText(modifier = Modifier.weight(1f),text = "MAX")
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)
                Row {
                    BodyText(modifier = Modifier.weight(4f),text = "MASSA")
                    BodyText(modifier = Modifier.weight(1f),text = uiState.temperatureMassa.first.toString())
                    BodyText(modifier = Modifier.weight(1f),text = uiState.temperatureMassa.second.toString())
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)
                Row {
                    BodyText(modifier = Modifier.weight(4f),text = "CARRARA")
                    BodyText(modifier = Modifier.weight(1f),text = uiState.temperatureCarrara.first.toString())
                    BodyText(modifier = Modifier.weight(1f),text = uiState.temperatureCarrara.second.toString())
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)
                Row {
                    BodyText(modifier = Modifier.weight(4f),text = "AULLA")
                    BodyText(modifier = Modifier.weight(1f),text = uiState.temperatureAulla.first.toString())
                    BodyText(modifier = Modifier.weight(1f),text = uiState.temperatureAulla.second.toString())
                }
                HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)
//                Row {
//                    Text(modifier = Modifier.weight(4f),text = "FIVIZZANO")
//                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureFivizzano.first.toString())
//                    Text(modifier = Modifier.weight(1f),text = uiState.temperatureFivizzano.second.toString())
//                }
//                Divider(color = MaterialTheme.colorScheme.secondary)
                Row {
                    BodyText(modifier = Modifier.weight(4f),text = "PONTREMOLI")
                    BodyText(modifier = Modifier.weight(1f),text = uiState.temperaturePontremoli.first.toString())
                    BodyText(modifier = Modifier.weight(1f),text = uiState.temperaturePontremoli.second.toString())
                }
            }
        }
    }
}