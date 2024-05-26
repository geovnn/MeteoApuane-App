package com.geovnn.meteoapuane.model

import android.graphics.Bitmap

data class ViabilitaUiState(
    val error: String = "",
    val isLoading: Boolean = true,
    val testoSegnalazione: String = "",
    val imgSegnalazioneGrande: Bitmap? = null,
    val imgSegnalazionePiccola1: Bitmap? = null,
    val imgSegnalazionePiccola2: Bitmap? = null,

    val imgA15LaspeziaVento: Bitmap? = null,
    val imgA15LaspeziaPioggia: Bitmap? = null,
    val imgA15LaspeziaNebbia: Bitmap? = null,
    val imgA15LaspeziaNeve: Bitmap? = null,
    val imgA15LaspeziaGhiaccio: Bitmap? = null,

    val imgA15SantostefanoVento: Bitmap? = null,
    val imgA15SantostefanoPioggia: Bitmap? = null,
    val imgA15SantostefanoNebbia: Bitmap? = null,
    val imgA15SantostefanoNeve: Bitmap? = null,
    val imgA15SantostefanoGhiaccio: Bitmap? = null,

    val imgA15AullaVento: Bitmap? = null,
    val imgA15AullaPioggia: Bitmap? = null,
    val imgA15AullaNebbia: Bitmap? = null,
    val imgA15AullaNeve: Bitmap? = null,
    val imgA15AullaGhiaccio: Bitmap? = null,

    val imgA15PontremoliVento: Bitmap? = null,
    val imgA15PontremoliPioggia: Bitmap? = null,
    val imgA15PontremoliNebbia: Bitmap? = null,
    val imgA15PontremoliNeve: Bitmap? = null,
    val imgA15PontremoliGhiaccio: Bitmap? = null,

    val imgA12VersiliaVento: Bitmap? = null,
    val imgA12VersiliaPioggia: Bitmap? = null,
    val imgA12VersiliaNebbia: Bitmap? = null,
    val imgA12VersiliaNeve: Bitmap? = null,
    val imgA12VersiliaGhiaccio: Bitmap? = null,

    val imgA12MassaVento: Bitmap? = null,
    val imgA12MassaPioggia: Bitmap? = null,
    val imgA12MassaNebbia: Bitmap? = null,
    val imgA12MassaNeve: Bitmap? = null,
    val imgA12MassaGhiaccio: Bitmap? = null,

    val imgA12CarraraVento: Bitmap? = null,
    val imgA12CarraraPioggia: Bitmap? = null,
    val imgA12CarraraNebbia: Bitmap? = null,
    val imgA12CarraraNeve: Bitmap? = null,
    val imgA12CarraraGhiaccio: Bitmap? = null,

    val imgA12SarzanaVento: Bitmap? = null,
    val imgA12SarzanaPioggia: Bitmap? = null,
    val imgA12SarzanaNebbia: Bitmap? = null,
    val imgA12SarzanaNeve: Bitmap? = null,
    val imgA12SarzanaGhiaccio: Bitmap? = null,
    )