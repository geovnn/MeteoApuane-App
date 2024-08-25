package com.geovnn.meteoapuane.presentation.viabilita.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.geovnn.meteoapuane.presentation.utils.FontSizeRange
import com.geovnn.meteoapuane.presentation.utils.composables.AutoResizeText
import com.geovnn.meteoapuane.presentation.utils.composables.ImageCoil

@Composable
fun SegnalazioneItemRow(
    modifier : Modifier = Modifier,
    title: String = "",
    img1 : String = "",
    img2 : String = "",
    img3 : String = "",
    img4 : String = "",
    img5 : String = "",
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        AutoResizeText(modifier = Modifier.weight(2f),
            text = title,fontSizeRange = FontSizeRange(
            min = MaterialTheme.typography.labelSmall.fontSize,
            max = MaterialTheme.typography.titleLarge.fontSize,),
            maxLines = 2
            )
        Box(modifier = Modifier.weight(1f)) {
            if (img1!="") {
                ImageCoil(url = img1, contentDescription = "")
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            if (img2!="") {
                ImageCoil(url = img2, contentDescription = "")
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            if (img3!="") {
                ImageCoil(url = img3, contentDescription = "")
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            if (img4!="") {
                ImageCoil(url = img4, contentDescription = "")
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            if (img5!="") {
                ImageCoil(url = img5, contentDescription = "")
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
    }
}