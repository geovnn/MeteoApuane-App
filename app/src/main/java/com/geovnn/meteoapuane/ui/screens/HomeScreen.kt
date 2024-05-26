package com.geovnn.meteoapuane.ui.screens

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.model.HomeUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onMenuClick: () -> Unit,
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

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = { Text(text = "MeteoApuane - Home",
                    color = MaterialTheme.colorScheme.onPrimaryContainer) },
                navigationIcon = {
                    IconButton(onClick = { onMenuClick() }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        },

    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .pullRefresh(state)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(64.dp)
                        .align(Alignment.Center),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            } else if (uiState.error!="") {
                AlertDialog(
                    onDismissRequest = { },
                    confirmButton = {
                        TextButton(onClick = { refreshData() }) {
                            Text(text = "Riprova")
                        }
                    },
                    title = { Text(text = "Errore") },
                    text = { Text(text = uiState.error) }
                )
            } else {
                val allertaList= listOf(
                    uiState.imgAllerta1,
                    uiState.imgAllerta2,
                    uiState.imgAllerta3,
                    uiState.imgAllerta4,
                    uiState.imgAllerta5,
                    uiState.imgAllerta6
                )
                val cardOuterPadding=10.dp
                val cardInnerPadding=5.dp
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .fillMaxSize()
                ) {
                    Text(
                        text = uiState.txtUltimaModifia,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(3.dp),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    ElevatedCard(
                        modifier = Modifier.padding(cardOuterPadding),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        TitleText(text = "ALLERTA METEO")
                        AllertaCard(list = allertaList)
                    }

                    ElevatedCard(
                        modifier = Modifier.padding(cardOuterPadding),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        TitleText(
                            text = "BACHECA AVVISI",
                        )
                        BodyText(text = uiState.txtAvviso)
                    }

                    ElevatedCard(
                        modifier = Modifier.padding(cardOuterPadding),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
                        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                    ) {
                        TitleText(text = "ULTIM'ORA DALLA PROVINCIA")
                        UltimoraCard(
                            image = uiState.imgUltimora1,
                            title = uiState.txtUltimoraTitle1,
                            body = uiState.txtUltimoraBody1,
                        )
                        UltimoraCard(
                            image = uiState.imgUltimora2,
                            title = uiState.txtUltimoraTitle2,
                            body = uiState.txtUltimoraBody2,
                        )
                    }

                }

            }
            PullRefreshIndicator(isRefreshing, state, Modifier.align(Alignment.TopCenter))

        }



    }
}

@Composable
fun TitleText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSecondaryContainer
    )
}

@Composable
fun BodyText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        text = text,
//        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        color = MaterialTheme.colorScheme.onSecondaryContainer

    )
}

@Composable
fun AllertaCard(
    list: List<Bitmap?>,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.White,
        ),
    ) {
        Row {
            repeat(list.size) { index ->
                if (list[index]!=null) {
                    list[index]?.let { image ->
                        Image(
                            bitmap = image.asImageBitmap(),
                            contentDescription = "",
                            modifier = Modifier
                                .weight(1f)
                                .height(50.dp)
                                .padding(2.dp)
                                .fillMaxSize(),
                            contentScale = ContentScale.Fit

                        )
                    }
                } else {
                    Box(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
fun UltimoraCard(
    image: Bitmap?,
    title: String,
    body: String,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.outlinedCardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .height(30.dp)
                    .fillMaxWidth()
            ) {
                if (image != null) {
                    Image(
                        bitmap = image.asImageBitmap(),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxHeight(),
                            //                        .weight(1f) ,
                        contentScale = ContentScale.FillHeight
                    )
                }
                Text(
                    text = title,
                    modifier= Modifier
                        .align(Alignment.CenterVertically)
                        .fillMaxHeight(),
                    color = MaterialTheme.colorScheme.onTertiaryContainer

                )
            }
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                text = body,
//        fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onTertiaryContainer

            )
        }
    }

}