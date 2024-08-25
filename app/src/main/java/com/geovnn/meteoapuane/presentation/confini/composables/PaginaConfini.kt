package com.geovnn.meteoapuane.presentation.confini.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geovnn.meteoapuane.domain.models.ConfiniPageTab
import com.geovnn.meteoapuane.presentation.utils.composables.BodyText
import com.geovnn.meteoapuane.presentation.utils.composables.TitleText

@Composable
fun PaginaConfini(
    modifier : Modifier = Modifier,
    uiState: ConfiniPageTab
) {
    Column(
        modifier = modifier.fillMaxSize()//.verticalScroll(rememberScrollState())
    ) {
        if (uiState.mappa!=null) {
            MappaConfini(mappa = uiState.mappa)
        }
        ElevatedCard(
            modifier = Modifier.padding(5.dp),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor =  MaterialTheme.colorScheme.onPrimaryContainer,
            )        ) {
            Column(modifier = Modifier.padding(5.dp)) {
                TitleText(modifier = Modifier.fillMaxWidth(), text = "CIELO")
                BodyText(text = uiState.testoCielo)
                HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)
                TitleText(modifier = Modifier.fillMaxWidth(), text = "FENOMENI")
                BodyText(text = uiState.testoFenomeni)
                HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)
                TitleText(modifier = Modifier.fillMaxWidth(), text = "TEMPERATURE")
                BodyText(text = uiState.testoTemperature)
                HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)
                TitleText(modifier = Modifier.fillMaxWidth(), text = "VENTI")
                BodyText(text = uiState.testoVenti)
                HorizontalDivider(modifier = Modifier.padding(vertical = 2.dp),color = MaterialTheme.colorScheme.onPrimaryContainer)
                TitleText(modifier = Modifier.fillMaxWidth(), text = "MARE")
                BodyText(text = uiState.testoMare)
            }
        }
    }
}