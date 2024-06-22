package com.geovnn.meteoapuane.presentation.viabilita

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.presentation.utils.composables.AutoResizeText
import com.geovnn.meteoapuane.presentation.utils.FontSizeRange
import com.geovnn.meteoapuane.presentation.utils.composables.TitleText
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ViabilitaScreen(
    uiState: ViabilitaUiState,
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

//    LaunchedEffect(Unit) {
//        refreshData()
//    }


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
                color = MaterialTheme.colorScheme.secondary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant,
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
            Column(
                modifier= Modifier.verticalScroll(rememberScrollState())
            ) {

                ElevatedCard(
                    modifier = Modifier.padding(5.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    TitleText(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        text = uiState.viabilitaPage.testoSegnalazione)

                }

                ElevatedCard(
                    modifier = Modifier.padding(5.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(5.dp)
                    ) {
                        TitleText(text = "A15 PARMA - LA SPEZIA")
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(modifier = Modifier.weight(2f), fontSize = 9.sp,text = "", maxLines = 1)
                            Text(modifier = Modifier.weight(1f), fontSize = 9.sp,text = "VENTO", maxLines = 1)
                            Text(modifier = Modifier.weight(1f), fontSize = 9.sp,text = "PIOGGIA", maxLines = 1)
                            Text(modifier = Modifier.weight(1f), fontSize = 9.sp,text = "NEBBIA", maxLines = 1)
                            Text(modifier = Modifier.weight(1f), fontSize = 9.sp,text = "NEVE", maxLines = 1)
                            Text(modifier = Modifier.weight(1f), fontSize = 9.sp,text = "GHIACCIO", maxLines = 1)
                        }
                    }
                }

            }
        }
        PullRefreshIndicator(isRefreshing, state, Modifier.align(Alignment.TopCenter))

    }
}