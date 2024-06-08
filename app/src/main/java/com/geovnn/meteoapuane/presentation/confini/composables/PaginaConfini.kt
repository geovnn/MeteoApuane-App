package com.geovnn.meteoapuane.presentation.confini.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.geovnn.meteoapuane.domain.models.ConfiniPageTab

@Composable
fun PaginaConfini(
    modifier : Modifier = Modifier,
    uiState: ConfiniPageTab
) {
    Column(
        modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        if (uiState.mappa!=null) {
            MappaConfini(mappa = uiState.mappa)
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                Text(modifier = Modifier.fillMaxWidth(), text = "CIELO", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Text(text = uiState.testoCielo)
                Divider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.secondary)
                Text(modifier = Modifier.fillMaxWidth(), text = "FENOMENI", fontWeight = FontWeight.Bold, fontSize = 18.sp, textAlign = TextAlign.Center)
                Text(text = uiState.testoFenomeni)
                Divider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.secondary)
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
    }
}