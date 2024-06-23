package com.geovnn.meteoapuane.domain.models

import android.graphics.Bitmap

data class ViabilitaPage(
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

    val urlVideoA15SantoStefano: String = "",
    val urlVideoA15Pontremoli: String = "",
    val urlVideoA15Montelungo: String = "",
    val urlVideoA15Tugo: String = "",

    val urlVideoA12Ceparana: String = "",
    val urlVideoA12Luni: String = "",
    val urlVideoA12Avenza: String = "",
    val urlVideoA12Cinquale: String = "",

)
