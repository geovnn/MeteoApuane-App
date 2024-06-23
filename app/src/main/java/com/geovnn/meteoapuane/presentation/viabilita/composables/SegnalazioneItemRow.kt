package com.geovnn.meteoapuane.presentation.viabilita.composables

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import com.geovnn.meteoapuane.presentation.utils.FontSizeRange
import com.geovnn.meteoapuane.presentation.utils.composables.AutoResizeText

@Composable
fun SegnalazioneItemRow(
    modifier : Modifier = Modifier,
    title: String = "",
    img1 : Bitmap? = null,
    img2 : Bitmap? = null,
    img3 : Bitmap? = null,
    img4 : Bitmap? = null,
    img5 : Bitmap? = null,
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
            if (img1!=null) {
                Image(bitmap = img1.asImageBitmap(), contentDescription = null)
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            if (img2!=null) {
                Image(bitmap = img2.asImageBitmap(), contentDescription = null)
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            if (img3!=null) {
                Image(bitmap = img3.asImageBitmap(), contentDescription = null)
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            if (img4!=null) {
                Image(bitmap = img4.asImageBitmap(), contentDescription = null)
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
        Box(modifier = Modifier.weight(1f)) {
            if (img5!=null) {
                Image(bitmap = img5.asImageBitmap(), contentDescription = null)
            } else {
                SegnalazioneItemText(modifier = Modifier.fillMaxSize(), text = "-")
            }
        }
    }
}