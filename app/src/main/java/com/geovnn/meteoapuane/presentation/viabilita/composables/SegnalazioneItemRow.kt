package com.geovnn.meteoapuane.presentation.viabilita.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(2f),
            text = title,
            maxLines = 2
            )
//        AutoResizeText(modifier = Modifier.weight(2f),
//            text = title,fontSizeRange = FontSizeRange(
//            min = MaterialTheme.typography.labelSmall.fontSize,
//            max = MaterialTheme.typography.titleLarge.fontSize,),
//            maxLines = 2
//            )
        Box(modifier = Modifier
            .weight(1f)
            .height(30.dp)) {
            if (img1!="") {
                ImageCoil(modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center),url = img1, contentDescription = "")
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier
            .weight(1f)
            .height(30.dp)) {
            if (img2!="") {
                ImageCoil(modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center),url = img2, contentDescription = "")
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier
            .weight(1f)
            .height(30.dp)) {
            if (img3!="") {
                ImageCoil(modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center),url = img3, contentDescription = "")
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier
            .weight(1f)
            .height(30.dp)) {
            if (img4!="") {
                ImageCoil(modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center),url = img4, contentDescription = "")
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier
            .weight(1f)
            .height(30.dp)) {
            if (img5!="") {
                ImageCoil(modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.Center),url = img5, contentDescription = "")
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
    }
}