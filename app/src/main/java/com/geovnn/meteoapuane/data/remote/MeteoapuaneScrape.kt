package com.geovnn.meteoapuane.data.remote

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.geovnn.meteoapuane.domain.models.ConfiniPage
import com.geovnn.meteoapuane.domain.models.ConfiniPageMap
import com.geovnn.meteoapuane.domain.models.HomePage
import com.geovnn.meteoapuane.domain.models.MontagnaPage
import com.geovnn.meteoapuane.domain.models.ProvinciaPage
import com.geovnn.meteoapuane.domain.models.ProvinciaPageMap
import com.geovnn.meteoapuane.domain.models.ProvinciaPageTab
import com.geovnn.meteoapuane.domain.models.ProvinciaPageSuccessivi
import com.geovnn.meteoapuane.domain.models.ViabilitaPage
import com.geovnn.meteoapuane.domain.models.ConfiniPageTab
import com.geovnn.meteoapuane.domain.models.IncendiPage
import com.geovnn.meteoapuane.domain.models.NowcastingPage
import com.geovnn.meteoapuane.domain.models.WebcamPage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MeteoapuaneScrape {

    private val client = OkHttpClient()

    private suspend fun getBitmapFromUrl(url: String): Bitmap? {
        println("getBitmap")
        return withContext(Dispatchers.IO) {
            var inputStream: InputStream? = null
            val maxRetries = 3
            var attempts = 0

            while (attempts < maxRetries) {
                try {
                    // Ensure HTTPS protocol
                    val formattedUrl = if (url.startsWith("http://")) {
                        url.replace("http://", "https://")
                    } else {
                        url
                    }

                    val request = Request.Builder()
                        .url(formattedUrl)
                        .build()

                    client.newCall(request).execute().use { response ->
                        if (!response.isSuccessful) {
                            println("HTTP error code: ${response.code}")
                            return@withContext null
                        }

                        inputStream = response.body?.byteStream()
                        return@withContext BitmapFactory.decodeStream(inputStream)
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                    attempts++
                    if (attempts >= maxRetries) {
                        println("Failed after $attempts attempts")
                        return@withContext null
                    }
                } finally {
                    inputStream?.close()
                }
            }
            null
        }
    }

    suspend fun getHomeData(): HomePage {
        return withContext(Dispatchers.IO) {
            val document = Jsoup.connect("https://www.meteoapuane.it/").timeout(10 * 1000).get()
            val txtUltimaModifica = document.select(".aggiornamento")[1].text()
            val urlImgAllerta1 = "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(1) > img:nth-child(1)").attr("src")
            val urlImgAllerta2 = "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(2) > img:nth-child(1)").attr("src")
            val urlImgAllerta3 = "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val urlImgAllerta4 = "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(4) > img:nth-child(1)").attr("src")
            val urlImgAllerta5 = "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(5) > img:nth-child(1)").attr("src")
            val urlImgAllerta6 = "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(6) > img:nth-child(1)").attr("src")
            val urlImgUltimora1 = document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > img:nth-child(2)").attr("src")
            val urlImgUltimora2 = document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > img:nth-child(7)").attr("src")
            val txtAvviso = document.select("div.scrittepiccole:nth-child(11)").text()
            val txtUltimoraTitle1 = document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > strong:nth-child(3)").text()
            val txtUltimoraTitle2 = document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > strong:nth-child(8)").text()
            val txtUltimoraBody1 = document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)").first()?.text().toString().substringAfter(txtUltimoraTitle1).substringBefore(txtUltimoraTitle2)
            val txtUltimoraBody2 = document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)").first()?.text().toString().substringAfter(txtUltimoraTitle2).substringBefore(" Vedi tutte le segnalazioni")

            HomePage(
                imgAllerta1 = urlImgAllerta1,
                imgAllerta2 = urlImgAllerta2,
                imgAllerta3 = urlImgAllerta3,
                imgAllerta4 = urlImgAllerta4,
                imgAllerta5 = urlImgAllerta5,
                imgAllerta6 = urlImgAllerta6,
                txtAvviso = txtAvviso,
                imgUltimora1 = urlImgUltimora1,
                txtUltimoraTitle1 = txtUltimoraTitle1,
                txtUltimoraBody1 = txtUltimoraBody1,
                imgUltimora2 = urlImgUltimora2,
                txtUltimoraTitle2 = txtUltimoraTitle2,
                txtUltimoraBody2 = txtUltimoraBody2,
                txtUltimaModifia = txtUltimaModifica
            )
        }
    }

    suspend fun getProvinciaData(): ProvinciaPage {
        return withContext(Dispatchers.IO) {
            val url = "https://www.meteoapuane.it/previsioni.php"
            val document = Jsoup.connect(url).timeout(10 * 1000).get()
            val ultimaModifica = document.select(".aggiornamento")[0].text()
            val sfondo = "https://www.meteoapuane.it/img/apuane_previ.jpg"
            // OGGI
            val temperatureMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[0].select("img").attr("src")
            val iconaAggiunitivaMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[0].select("img").attr("src")
            val altaLunigianaMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[0].select("img").attr("src")
            val versanteEmilianoMattinaOggi =                     "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[0].select("img").attr("src")
            val mediaAltaLunigianaMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[0].select("img").attr("src")
            val lunigianaOccidentaleMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[0].select("img").attr("src")
            val appenninoVersanteToscanoMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[0].select("img").attr("src")
            val bassaLunigianaMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[0].select("img").attr("src")
            val lunigianaSudOrientaleMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[0].select("img").attr("src")
            val golfoDeiPoetiMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[0].select("img").attr("src")
            val bassaValDiMagraMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "sarzana"
            )[0].select("img").attr("src")
            val alpiApuaneMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[0].select("img").attr("src")
            val massaCarraraMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[0].select("img").attr("src")
            val altaVersiliaMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[0].select("img").attr("src")
            val ventoAppenninoMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[0].select("img").attr("src")
            val ventoLunigianaMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[0].select("img").attr("src")
            val ventoAlpiApuaneMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[0].select("img").attr("src")
            val ventoCostaMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[0].select("img").attr("src")
            val mareSottocostaMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare1"
            )[0].select("img").attr("src")
            val mareLargoMattinaOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[0].select("img").attr("src")

            val mappaMattinaOggi = ProvinciaPageMap(
                sfondo,
                temperatureMattinaOggi,
                iconaAggiunitivaMattinaOggi,
                altaLunigianaMattinaOggi,
                versanteEmilianoMattinaOggi,
                mediaAltaLunigianaMattinaOggi,
                lunigianaOccidentaleMattinaOggi,
                appenninoVersanteToscanoMattinaOggi,
                bassaLunigianaMattinaOggi,
                lunigianaSudOrientaleMattinaOggi,
                golfoDeiPoetiMattinaOggi,
                bassaValDiMagraMattinaOggi,
                alpiApuaneMattinaOggi,
                massaCarraraMattinaOggi,
                altaVersiliaMattinaOggi,
                ventoAppenninoMattinaOggi,
                ventoLunigianaMattinaOggi,
                ventoAlpiApuaneMattinaOggi,
                ventoCostaMattinaOggi,
                mareSottocostaMattinaOggi,
                mareLargoMattinaOggi
            )
            val temperatureSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[1].select("img").attr("src")
            val iconaAggiunitivaSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[1].select("img").attr("src")
            val altaLunigianaSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[1].select("img").attr("src")
            val versanteEmilianoSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[1].select("img").attr("src")
            val mediaAltaLunigianaSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[1].select("img").attr("src")
            val lunigianaOccidentaleSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[1].select("img").attr("src")
            val appenninoVersanteToscanoSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[1].select("img").attr("src")
            val bassaLunigianaSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[1].select("img").attr("src")
            val lunigianaSudOrientaleSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[1].select("img").attr("src")
            val golfoDeiPoetiSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[1].select("img").attr("src")
            val bassaValDiMagraSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "sarzana"
            )[1].select("img").attr("src")
            val alpiApuaneSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[1].select("img").attr("src")
            val massaCarraraSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[1].select("img").attr("src")
            val altaVersiliaSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[1].select("img").attr("src")
            val ventoAppenninoSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[1].select("img").attr("src")
            val ventoLunigianaSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[1].select("img").attr("src")
            val ventoAlpiApuaneSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[1].select("img").attr("src")
            val ventoCostaSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[1].select("img").attr("src")
            val mareSottocostaSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare1"
            )[1].select("img").attr("src")
            val mareLargoSeraOggi = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[1].select("img").attr("src")
            val mappaSeraOggi = ProvinciaPageMap(
                sfondo,
                temperatureSeraOggi,
                iconaAggiunitivaSeraOggi,
                altaLunigianaSeraOggi,
                versanteEmilianoSeraOggi,
                mediaAltaLunigianaSeraOggi,
                lunigianaOccidentaleSeraOggi,
                appenninoVersanteToscanoSeraOggi,
                bassaLunigianaSeraOggi,
                lunigianaSudOrientaleSeraOggi,
                golfoDeiPoetiSeraOggi,
                bassaValDiMagraSeraOggi,
                alpiApuaneSeraOggi,
                massaCarraraSeraOggi,
                altaVersiliaSeraOggi,
                ventoAppenninoSeraOggi,
                ventoLunigianaSeraOggi,
                ventoAlpiApuaneSeraOggi,
                ventoCostaSeraOggi,
                mareSottocostaSeraOggi,
                mareLargoSeraOggi
            )
            val oggiData = document.select(".testo2")[1].text().toString()
            val oggiTestoPrevisione = document.select(".testo2")[2].text()
            val oggiTemperatura = document.select(".testo2")[3].text()
            val oggiTestoVento = document.select(".testo2")[4].text()
            val oggiTestoMare = document.select(".testo2")[5].text()
            val oggiMinimoMassa = document.select(".testoBLU")[1].text()
            val oggiMassimoMassa = document.select(".testoROSSO")[1].text()
            val oggiMinimoCarrara = document.select(".testoBLU")[2].text()
            val oggiMassimoCarrara = document.select(".testoROSSO")[2].text()
            val oggiMinimoAulla = document.select(".testoBLU")[3].text()
            val oggiMassimoAulla = document.select(".testoROSSO")[3].text()
            val oggiMinimoPontremoli = document.select(".testoBLU")[4].text()
            val oggiMassimoPontremoli = document.select(".testoROSSO")[4].text()
            val previsioniOggi = ProvinciaPageTab(
                data = oggiData,
                mappaMattina = mappaMattinaOggi,
                mappaSera = mappaSeraOggi,
                testo = oggiTestoPrevisione,
                testoTemperature = oggiTemperatura,
                testoVenti = oggiTestoVento,
                testoMare = oggiTestoMare,
                temperatureMassa = Pair(oggiMinimoMassa.toInt(), oggiMassimoMassa.toInt()),
                temperatureCarrara = Pair(
                    oggiMinimoCarrara.toInt(),
                    oggiMassimoCarrara.toInt()
                ),
                temperatureAulla = Pair(oggiMinimoAulla.toInt(), oggiMassimoAulla.toInt()),
                temperatureFivizzano = Pair(0, 0),
                temperaturePontremoli = Pair(
                    oggiMinimoPontremoli.toInt(),
                    oggiMassimoPontremoli.toInt()
                )
            )
            //DOMANI
            val temperatureMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[2].select("img").attr("src")
            val iconaAggiunitivaMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[2].select("img").attr("src")
            val altaLunigianaMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[2].select("img").attr("src")
            val versanteEmilianoMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[2].select("img").attr("src")
            val mediaAltaLunigianaMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[2].select("img").attr("src")
            val lunigianaOccidentaleMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[2].select("img").attr("src")
            val appenninoVersanteToscanoMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[2].select("img").attr("src")
            val bassaLunigianaMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[2].select("img").attr("src")
            val lunigianaSudOrientaleMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[2].select("img").attr("src")
            val golfoDeiPoetiMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[2].select("img").attr("src")
            val bassaValDiMagraMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "sarzana"
            )[2].select("img").attr("src")
            val alpiApuaneMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[2].select("img").attr("src")
            val massaCarraraMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[2].select("img").attr("src")
            val altaVersiliaMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[2].select("img").attr("src")
            val ventoAppenninoMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[2].select("img").attr("src")
            val ventoLunigianaMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[2].select("img").attr("src")
            val ventoAlpiApuaneMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[2].select("img").attr("src")
            val ventoCostaMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[2].select("img").attr("src")
            val mareSottocostaMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[2].select("img").attr("src")
            val mareLargoMattinaDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[2].select("img").attr("src")
            val mappaMattinaDomani = ProvinciaPageMap(
                sfondo,
                temperatureMattinaDomani,
                iconaAggiunitivaMattinaDomani,
                altaLunigianaMattinaDomani,
                versanteEmilianoMattinaDomani,
                mediaAltaLunigianaMattinaDomani,
                lunigianaOccidentaleMattinaDomani,
                appenninoVersanteToscanoMattinaDomani,
                bassaLunigianaMattinaDomani,
                lunigianaSudOrientaleMattinaDomani,
                golfoDeiPoetiMattinaDomani,
                bassaValDiMagraMattinaDomani,
                alpiApuaneMattinaDomani,
                massaCarraraMattinaDomani,
                altaVersiliaMattinaDomani,
                ventoAppenninoMattinaDomani,
                ventoLunigianaMattinaDomani,
                ventoAlpiApuaneMattinaDomani,
                ventoCostaMattinaDomani,
                mareSottocostaMattinaDomani,
                mareLargoMattinaDomani
            )
            val temperatureSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[3].select("img").attr("src")
            val iconaAggiunitivaSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[3].select("img").attr("src")
            val altaLunigianaSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[3].select("img").attr("src")
            val versanteEmilianoSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[3].select("img").attr("src")
            val mediaAltaLunigianaSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[3].select("img").attr("src")
            val lunigianaOccidentaleSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[3].select("img").attr("src")
            val appenninoVersanteToscanoSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[3].select("img").attr("src")
            val bassaLunigianaSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[3].select("img").attr("src")
            val lunigianaSudOrientaleSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[3].select("img").attr("src")
            val golfoDeiPoetiSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[3].select("img").attr("src")
            val bassaValDiMagraSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "sarzana"
            )[3].select("img").attr("src")
            val alpiApuaneSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[3].select("img").attr("src")
            val massaCarraraSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[3].select("img").attr("src")
            val altaVersiliaSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[3].select("img").attr("src")
            val ventoAppenninoSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[3].select("img").attr("src")
            val ventoLunigianaSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[3].select("img").attr("src")
            val ventoAlpiApuaneSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[3].select("img").attr("src")
            val ventoCostaSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[3].select("img").attr("src")
            val mareSottocostaSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare1"
            )[3].select("img").attr("src")
            val mareLargoSeraDomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[3].select("img").attr("src")
            val mappaSeraDomani = ProvinciaPageMap(
                sfondo,
                temperatureSeraDomani,
                iconaAggiunitivaSeraDomani,
                altaLunigianaSeraDomani,
                versanteEmilianoSeraDomani,
                mediaAltaLunigianaSeraDomani,
                lunigianaOccidentaleSeraDomani,
                appenninoVersanteToscanoSeraDomani,
                bassaLunigianaSeraDomani,
                lunigianaSudOrientaleSeraDomani,
                golfoDeiPoetiSeraDomani,
                bassaValDiMagraSeraDomani,
                alpiApuaneSeraDomani,
                massaCarraraSeraDomani,
                altaVersiliaSeraDomani,
                ventoAppenninoSeraDomani,
                ventoLunigianaSeraDomani,
                ventoAlpiApuaneSeraDomani,
                ventoCostaSeraDomani,
                mareSottocostaSeraDomani,
                mareLargoSeraDomani
            )
            val domaniData = document.select(".testo2")[7].text().toString()
            val domaniTestoPrevisione = document.select(".testo2")[8].text()
            val domaniTemperatura = document.select(".testo2")[9].text()
            val domaniTestoVento = document.select(".testo2")[10].text()
            val domaniTestoMare = document.select(".testo2")[11].text()
            val domaniMinimoMassa = document.select(".testoBLU")[6].text()
            val domaniMassimoMassa = document.select(".testoROSSO")[6].text()
            val domaniMinimoCarrara = document.select(".testoBLU")[7].text()
            val domaniMassimoCarrara = document.select(".testoROSSO")[7].text()
            val domaniMinimoAulla = document.select(".testoBLU")[8].text()
            val domaniMassimoAulla = document.select(".testoROSSO")[8].text()
            val domaniMinimoPontremoli = document.select(".testoBLU")[9].text()
            val domaniMassimoPontremoli = document.select(".testoROSSO")[9].text()
            val previsioniDomani = ProvinciaPageTab(
                data = domaniData,
                mappaMattina = mappaMattinaDomani,
                mappaSera = mappaSeraDomani,
                testo = domaniTestoPrevisione,
                testoTemperature = domaniTemperatura,
                testoVenti = domaniTestoVento,
                testoMare = domaniTestoMare,
                temperatureMassa = Pair(domaniMinimoMassa.toInt(), domaniMassimoMassa.toInt()),
                temperatureCarrara = Pair(
                    domaniMinimoCarrara.toInt(),
                    domaniMassimoCarrara.toInt()
                ),
                temperatureAulla = Pair(domaniMinimoAulla.toInt(), domaniMassimoAulla.toInt()),
                temperatureFivizzano = Pair(0, 0),
                temperaturePontremoli = Pair(
                    domaniMinimoPontremoli.toInt(),
                    domaniMassimoPontremoli.toInt()
                )
            )
            //DOPODOMANI
            val temperatureMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[4].select("img").attr("src")
            val iconaAggiunitivaMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[4].select("img").attr("src")
            val altaLunigianaMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[4].select("img").attr("src")
            val versanteEmilianoMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[4].select("img").attr("src")
            val mediaAltaLunigianaMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[4].select("img").attr("src")
            val lunigianaOccidentaleMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[4].select("img").attr("src")
            val appenninoVersanteToscanoMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[4].select("img").attr("src")
            val bassaLunigianaMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[4].select("img").attr("src")
            val lunigianaSudOrientaleMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[4].select("img").attr("src")
            val golfoDeiPoetiMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[4].select("img").attr("src")
            val bassaValDiMagraMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "sarzana"
            )[4].select("img").attr("src")
            val alpiApuaneMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[4].select("img").attr("src")
            val massaCarraraMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[4].select("img").attr("src")
            val altaVersiliaMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[4].select("img").attr("src")
            val ventoAppenninoMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[4].select("img").attr("src")
            val ventoLunigianaMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[4].select("img").attr("src")
            val ventoAlpiApuaneMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[4].select("img").attr("src")
            val ventoCostaMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[4].select("img").attr("src")
            val mareSottocostaMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[4].select("img").attr("src")
            val mareLargoMattinaDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[4].select("img").attr("src")
            val mappaMattinaDopodomani = ProvinciaPageMap(
                sfondo,
                temperatureMattinaDopodomani,
                iconaAggiunitivaMattinaDopodomani,
                altaLunigianaMattinaDopodomani,
                versanteEmilianoMattinaDopodomani,
                mediaAltaLunigianaMattinaDopodomani,
                lunigianaOccidentaleMattinaDopodomani,
                appenninoVersanteToscanoMattinaDopodomani,
                bassaLunigianaMattinaDopodomani,
                lunigianaSudOrientaleMattinaDopodomani,
                golfoDeiPoetiMattinaDopodomani,
                bassaValDiMagraMattinaDopodomani,
                alpiApuaneMattinaDopodomani,
                massaCarraraMattinaDopodomani,
                altaVersiliaMattinaDopodomani,
                ventoAppenninoMattinaDopodomani,
                ventoLunigianaMattinaDopodomani,
                ventoAlpiApuaneMattinaDopodomani,
                ventoCostaMattinaDopodomani,
                mareSottocostaMattinaDopodomani,
                mareLargoMattinaDopodomani
            )
            val temperatureSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[5].select("img").attr("src")
            val iconaAggiunitivaSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[5].select("img").attr("src")
            val altaLunigianaSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[5].select("img").attr("src")
            val versanteEmilianoSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[5].select("img").attr("src")
            val mediaAltaLunigianaSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[5].select("img").attr("src")
            val lunigianaOccidentaleSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[5].select("img").attr("src")
            val appenninoVersanteToscanoSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[5].select("img").attr("src")
            val bassaLunigianaSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[5].select("img").attr("src")
            val lunigianaSudOrientaleSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[5].select("img").attr("src")
            val golfoDeiPoetiSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[5].select("img").attr("src")
            val bassaValDiMagraSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[5].select("img").attr("src")
            val alpiApuaneSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[5].select("img").attr("src")
            val massaCarraraSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[5].select("img").attr("src")
            val altaVersiliaSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[5].select("img").attr("src")
            val ventoAppenninoSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[5].select("img").attr("src")
            val ventoLunigianaSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[5].select("img").attr("src")
            val ventoAlpiApuaneSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[5].select("img").attr("src")
            val ventoCostaSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[5].select("img").attr("src")
            val mareSottocostaSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare1"
            )[5].select("img").attr("src")
            val mareLargoSeraDopodomani = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[5].select("img").attr("src")

            val mappaSeraDopodomani = ProvinciaPageMap(
                sfondo,
                temperatureSeraDopodomani,
                iconaAggiunitivaSeraDopodomani,
                altaLunigianaSeraDopodomani,
                versanteEmilianoSeraDopodomani,
                mediaAltaLunigianaSeraDopodomani,
                lunigianaOccidentaleSeraDopodomani,
                appenninoVersanteToscanoSeraDopodomani,
                bassaLunigianaSeraDopodomani,
                lunigianaSudOrientaleSeraDopodomani,
                golfoDeiPoetiSeraDopodomani,
                bassaValDiMagraSeraDopodomani,
                alpiApuaneSeraDopodomani,
                massaCarraraSeraDopodomani,
                altaVersiliaSeraDopodomani,
                ventoAppenninoSeraDopodomani,
                ventoLunigianaSeraDopodomani,
                ventoAlpiApuaneSeraDopodomani,
                ventoCostaSeraDopodomani,
                mareSottocostaSeraDopodomani,
                mareLargoSeraDopodomani
            )
            val dopodomaniData = document.select(".testo2")[13].text().toString()
            val dopodomaniTestoPrevisione = document.select(".testo2")[14].text()
            val dopodomaniTemperatura = document.select(".testo2")[15].text()
            val dopodomaniTestoVento = document.select(".testo2")[16].text()
            val dopodomaniTestoMare = document.select(".testo2")[17].text()
            val dopodomaniMinimoMassa = document.select(".testoBLU")[11].text()
            val dopodomaniMassimoMassa = document.select(".testoROSSO")[11].text()
            val dopodomaniMinimoCarrara = document.select(".testoBLU")[12].text()
            val dopodomaniMassimoCarrara = document.select(".testoROSSO")[12].text()
            val dopodomaniMinimoAulla = document.select(".testoBLU")[13].text()
            val dopodomaniMassimoAulla = document.select(".testoROSSO")[13].text()
            val dopodomaniMinimoPontremoli = document.select(".testoBLU")[14].text()
            val dopodomaniMassimoPontremoli = document.select(".testoROSSO")[14].text()
            val previsioniDopodomani = ProvinciaPageTab(
                data = dopodomaniData,
                mappaMattina = mappaMattinaDopodomani,
                mappaSera = mappaSeraDopodomani,
                testo = dopodomaniTestoPrevisione,
                testoTemperature = dopodomaniTemperatura,
                testoVenti = dopodomaniTestoVento,
                testoMare = dopodomaniTestoMare,
                temperatureMassa = Pair(
                    dopodomaniMinimoMassa.toInt(),
                    dopodomaniMassimoMassa.toInt()
                ),
                temperatureCarrara = Pair(
                    dopodomaniMinimoCarrara.toInt(),
                    dopodomaniMassimoCarrara.toInt()
                ),
                temperatureAulla = Pair(
                    dopodomaniMinimoAulla.toInt(),
                    dopodomaniMassimoAulla.toInt()
                ),
                temperatureFivizzano = Pair(0, 0),
                temperaturePontremoli = Pair(
                    dopodomaniMinimoPontremoli.toInt(),
                    dopodomaniMassimoPontremoli.toInt()
                )
            )
            //PROSSIMI
            val prossimiTesto = document.select(".testo2")[20].text()
            val prossimiData1 =
                document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > strong:nth-child(1)")
                    .first()?.text().toString()
            val prossimiAttendibilita1 =
                document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2)")
                    .first()?.text().toString()
            val prossimiData2 =
                document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(4) > strong:nth-child(1)")
                    .first()?.text().toString()
            val prossimiAttendibilita2 =
                document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(6)")
                    .first()?.text().toString()
            val successiviImgA1 = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > img:nth-child(1)")
                .first()?.attr("src")
            val successiviImgB1 = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > img:nth-child(1)")
                .first()?.attr("src")
            val successiviImgC1 = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > img:nth-child(1)")
                .first()?.attr("src")
            val successiviImgA2 = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(6) > img:nth-child(1)")
                .first()?.attr("src")
            val successiviImgB2 = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(6) > img:nth-child(1)")
                .first()?.attr("src")
            val successiviImgC2 = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(6) > img:nth-child(1)")
                .first()?.attr("src")

            val previsioniSuccessivi = ProvinciaPageSuccessivi(
                label = "Giorni Successivi",
                testo = prossimiTesto,
                data1 = prossimiData1,
                imgA1 = successiviImgA1,
                imgB1 = successiviImgB1,
                imgC1 = successiviImgC1,
                attendibilita1 = prossimiAttendibilita1,
                data2 = prossimiData2,
                imgA2 = successiviImgA2,
                imgB2 = successiviImgB2,
                imgC2 = successiviImgC2,
                attendibilita2 = prossimiAttendibilita2
            )
            ProvinciaPage(
                testoUltimoAggiornamento = ultimaModifica,
                paginaOggi = previsioniOggi,
                paginaDomani = previsioniDomani,
                paginaDopodomani = previsioniDopodomani,
                paginaSuccessivi = previsioniSuccessivi,
            )

        }

    }

    suspend fun getConfiniData(): ConfiniPage {
        return withContext(Dispatchers.IO) {
            val url = "https://www.meteoapuane.it/3confini.php"
            val document = Jsoup.connect(url).timeout(10 * 1000).get()

            val testoUltimaModifica =
                "Aggiornamento del: " + document.select("div.aggiornamento > strong:nth-child(1)")
                    .text() + " (aggiornato 3 volte la settimana)"
            val testoPrevisione =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > span:nth-child(1)")
                    .text()
            val testoOggiData =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1) > strong:nth-child(1)")
                    .text()
            val testoOggiCielo =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Cielo: ").substringBefore("Fenomeni")
            val testoOggiFenomeni =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Fenomeni: ").substringBefore("Temperature")
            val testoOggiTemperature =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Temperature: ").substringBefore("Venti")
            val testoOggiVenti =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Venti: ").substringBefore("Mare")
            val testoOggiMare =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Mare: ")
            val immagineSfondo = "https://www.meteoapuane.it/admin/3confini/prova_map.jpg"
            val immagineOggiMare = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "m1"
            )[0].select("img").attr("src")
            val immagineOggiTemperaturaVersantePadano = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t3"
            )[0].select("img").attr("src")
            val immagineOggiTemperaturaAppennino = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t2"
            )[0].select("img").attr("src")
            val immagineOggiTemperaturaVersanteLigure = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t1"
            )[0].select("img").attr("src")
            val immagineOggiVentoBassaPianura = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w4"
            )[0].select("img").attr("src")
            val immagineOggiVentoAppenninoLigurePiacentino = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w5"
            )[0].select("img").attr("src")
            val immagineOggiVentoPedemontana = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w3"
            )[0].select("img").attr("src")
            val immagineOggiVentoCostaLigure = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w2"
            )[0].select("img").attr("src")
            val immagineOggiVentoCrinale = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w1"
            )[0].select("img").attr("src")
            val immagineOggiValTrebbia = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "bobbio"
            )[0].select("img").attr("src")
            val immagineOggiPianuraPiacentina = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "firenzuola"
            )[0].select("img").attr("src")
            val immagineOggiTerreVerdiane = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "castelnovo"
            )[0].select("img").attr("src")
            val immagineOggiBassaParmense = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[0].select("img").attr("src")
            val immagineOggiAppenninoLigurePiacentino = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "ottone"
            )[0].select("img").attr("src")
            val immagineOggiValNure = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "morfasso"
            )[0].select("img").attr("src")
            val immagineOggiValTaro = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "borgotaro"
            )[0].select("img").attr("src")
            val immagineOggiPedemontanaParmense = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "langhirano"
            )[0].select("img").attr("src")
            val immagineOggiSpezzinoInterno = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "levanto"
            )[0].select("img").attr("src")
            val immagineOggiAltaValTaro = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "varese"
            )[0].select("img").attr("src")
            val immagineOggiCrinaleAppenninico = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "corniglio"
            )[0].select("img").attr("src")
            val immagineOggiAppenninoReggiano = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[0].select("img").attr("src")
            val immagineOggiCostaSpezzina = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "spezia"
            )[0].select("img").attr("src")

            val mappaOggi = ConfiniPageMap(
                sfondo = immagineSfondo,
                temperaturaVersantePadano = immagineOggiTemperaturaVersantePadano,
                temperaturaAppennino = immagineOggiTemperaturaAppennino,
                temperaturaVersanteLigure = immagineOggiTemperaturaVersanteLigure,
                ventoBassaPianura = immagineOggiVentoBassaPianura,
                ventoAppenninoLigurePiacentino = immagineOggiVentoAppenninoLigurePiacentino,
                ventoPedemontana = immagineOggiVentoPedemontana,
                ventoCostaLigure = immagineOggiVentoCostaLigure,
                ventoCrinale = immagineOggiVentoCrinale,
                mare = immagineOggiMare,
                previsioneValTrebbia = immagineOggiValTrebbia,
                previsionePianuraPiacentina = immagineOggiPianuraPiacentina,
                previsioneTerreVerdiane = immagineOggiTerreVerdiane,
                previsioneBassaParmense = immagineOggiBassaParmense,
                previsioneValNure = immagineOggiValNure,
                previsioneAppenninoLigurePiacentino = immagineOggiAppenninoLigurePiacentino,
                previsioneValTaro = immagineOggiValTaro,
                previsionePedemontanaParmense = immagineOggiPedemontanaParmense,
                previsioneAltaValTaro = immagineOggiAltaValTaro,
                previsioneCrinaleAppenninico = immagineOggiCrinaleAppenninico,
                previsioneSpezzinoInterno = immagineOggiSpezzinoInterno,
                previsioneAppenninoReggiano = immagineOggiAppenninoReggiano,
                previsioneCostaSpezzina = immagineOggiCostaSpezzina
            )
            val paginaOggi = ConfiniPageTab(
                data = testoOggiData,
                testoCielo = testoOggiCielo,
                testoFenomeni = testoOggiFenomeni,
                testoTemperature = testoOggiTemperature,
                testoVenti = testoOggiVenti,
                testoMare = testoOggiMare,
                mappa = mappaOggi
            )
            val testoDomaniData =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1) > strong:nth-child(1)")
                    .text()
            val testoDomaniCielo =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Cielo: ").substringBefore("Fenomeni")
            val testoDomaniFenomeni =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Fenomeni: ").substringBefore("Temperature")
            val testoDomaniTemperature =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Temperature: ").substringBefore("Venti")
            val testoDomaniVenti =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Venti: ").substringBefore("Mare")
            val testoDomaniMare =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Mare: ")
            val immagineDomaniMare ="https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "m1"
            )[1].select("img").attr("src")
            val immagineDomaniTemperaturaVersantePadano = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t3"
            )[1].select("img").attr("src")
            val immagineDomaniTemperaturaAppennino = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t2"
            )[1].select("img").attr("src")
            val immagineDomaniTemperaturaVersanteLigure = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t1"
            )[1].select("img").attr("src")
            val immagineDomaniVentoBassaPianura = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w4"
            )[1].select("img").attr("src")
            val immagineDomaniVentoAppenninoLigurePiacentino = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w5"
            )[1].select("img").attr("src")
            val immagineDomaniVentoPedemontana = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w3"
            )[1].select("img").attr("src")
            val immagineDomaniVentoCostaLigure = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w2"
            )[1].select("img").attr("src")
            val immagineDomaniVentoCrinale = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w1"
            )[1].select("img").attr("src")
            val immagineDomaniValTrebbia = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "bobbio"
            )[1].select("img").attr("src")
            val immagineDomaniPianuraPiacentina = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "firenzuola"
            )[1].select("img").attr("src")
            val immagineDomaniTerreVerdiane = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "castelnovo"
            )[1].select("img").attr("src")
            val immagineDomaniBassaParmense = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[1].select("img").attr("src")
            val immagineDomaniAppenninoLigurePiacentino = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "ottone"
            )[1].select("img").attr("src")
            val immagineDomaniValNure = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "morfasso"
            )[1].select("img").attr("src")
            val immagineDomaniValTaro = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "borgotaro"
            )[1].select("img").attr("src")
            val immagineDomaniPedemontanaParmense = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "langhirano"
            )[1].select("img").attr("src")
            val immagineDomaniSpezzinoInterno = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "levanto"
            )[1].select("img").attr("src")
            val immagineDomaniAltaValTaro = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "varese"
            )[1].select("img").attr("src")
            val immagineDomaniCrinaleAppenninico = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "corniglio"
            )[1].select("img").attr("src")
            val immagineDomaniAppenninoReggiano = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[1].select("img").attr("src")
            val immagineDomaniCostaSpezzina = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "spezia"
            )[1].select("img").attr("src")


            val mappaDomani = ConfiniPageMap(
                sfondo = immagineSfondo,
                temperaturaVersantePadano = immagineDomaniTemperaturaVersantePadano,
                temperaturaAppennino = immagineDomaniTemperaturaAppennino,
                temperaturaVersanteLigure = immagineDomaniTemperaturaVersanteLigure,
                ventoBassaPianura = immagineDomaniVentoBassaPianura,
                ventoAppenninoLigurePiacentino = immagineDomaniVentoAppenninoLigurePiacentino,
                ventoPedemontana = immagineDomaniVentoPedemontana,
                ventoCostaLigure = immagineDomaniVentoCostaLigure,
                ventoCrinale = immagineDomaniVentoCrinale,
                mare = immagineDomaniMare,
                previsioneValTrebbia = immagineDomaniValTrebbia,
                previsionePianuraPiacentina = immagineDomaniPianuraPiacentina,
                previsioneTerreVerdiane = immagineDomaniTerreVerdiane,
                previsioneBassaParmense = immagineDomaniBassaParmense,
                previsioneValNure = immagineDomaniValNure,
                previsioneAppenninoLigurePiacentino = immagineDomaniAppenninoLigurePiacentino,
                previsioneValTaro = immagineDomaniValTaro,
                previsionePedemontanaParmense = immagineDomaniPedemontanaParmense,
                previsioneAltaValTaro = immagineDomaniAltaValTaro,
                previsioneCrinaleAppenninico = immagineDomaniCrinaleAppenninico,
                previsioneSpezzinoInterno = immagineDomaniSpezzinoInterno,
                previsioneAppenninoReggiano = immagineDomaniAppenninoReggiano,
                previsioneCostaSpezzina = immagineDomaniCostaSpezzina
            )
            val paginaDomani = ConfiniPageTab(
                data = testoDomaniData,
                testoCielo = testoDomaniCielo,
                testoFenomeni = testoDomaniFenomeni,
                testoTemperature = testoDomaniTemperature,
                testoVenti = testoDomaniVenti,
                testoMare = testoDomaniMare,
                mappa = mappaDomani
            )
            val testoDopodomaniData =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1) > strong:nth-child(1)")
                    .text()
            val testoDopodomaniCielo =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Cielo: ").substringBefore("Fenomeni")
            val testoDopodomaniFenomeni =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Fenomeni: ").substringBefore("Temperature")
            val testoDopodomaniTemperature =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Temperature: ").substringBefore("Venti")
            val testoDopodomaniVenti =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Venti: ").substringBefore("Mare")
            val testoDopodomaniMare =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                    .text().substringAfter("Mare: ")
            val immagineDopodomaniMare = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "m1"
            )[2].select("img").attr("src")
            val immagineDopodomaniTemperaturaVersantePadano = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t3"
            )[2].select("img").attr("src")
            val immagineDopodomaniTemperaturaAppennino = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t2"
            )[2].select("img").attr("src")
            val immagineDopodomaniTemperaturaVersanteLigure = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t1"
            )[2].select("img").attr("src")
            val immagineDopodomaniVentoBassaPianura = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w4"
            )[2].select("img").attr("src")
            val immagineDopodomaniVentoAppenninoLigurePiacentino = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w5"
            )[2].select("img").attr("src")
            val immagineDopodomaniVentoPedemontana = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w3"
            )[2].select("img").attr("src")
            val immagineDopodomaniVentoCostaLigure = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w2"
            )[2].select("img").attr("src")
            val immagineDopodomaniVentoCrinale = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w1"
            )[2].select("img").attr("src")
            val immagineDopodomaniValTrebbia = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "bobbio"
            )[2].select("img").attr("src")
            val immagineDopodomaniPianuraPiacentina = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "firenzuola"
            )[2].select("img").attr("src")
            val immagineDopodomaniTerreVerdiane = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "castelnovo"
            )[2].select("img").attr("src")
            val immagineDopodomaniBassaParmense = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[2].select("img").attr("src")
            val immagineDopodomaniAppenninoLigurePiacentino = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "ottone"
            )[2].select("img").attr("src")
            val immagineDopodomaniValNure = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "morfasso"
            )[2].select("img").attr("src")
            val immagineDopodomaniValTaro = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "borgotaro"
            )[2].select("img").attr("src")
            val immagineDopodomaniPedemontanaParmense = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "langhirano"
            )[2].select("img").attr("src")
            val immagineDopodomaniSpezzinoInterno = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "levanto"
            )[2].select("img").attr("src")
            val immagineDopodomaniAltaValTaro = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "varese"
            )[2].select("img").attr("src")
            val immagineDopodomaniCrinaleAppenninico = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "corniglio"
            )[2].select("img").attr("src")
            val immagineDopodomaniAppenninoReggiano = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[2].select("img").attr("src")
            val immagineDopodomaniCostaSpezzina = "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "spezia"
            )[2].select("img").attr("src")

            val mappaDopodomani = ConfiniPageMap(
                sfondo = immagineSfondo,
                temperaturaVersantePadano = immagineDopodomaniTemperaturaVersantePadano,
                temperaturaAppennino = immagineDopodomaniTemperaturaAppennino,
                temperaturaVersanteLigure = immagineDopodomaniTemperaturaVersanteLigure,
                ventoBassaPianura = immagineDopodomaniVentoBassaPianura,
                ventoAppenninoLigurePiacentino = immagineDopodomaniVentoAppenninoLigurePiacentino,
                ventoPedemontana = immagineDopodomaniVentoPedemontana,
                ventoCostaLigure = immagineDopodomaniVentoCostaLigure,
                ventoCrinale = immagineDopodomaniVentoCrinale,
                mare = immagineDopodomaniMare,
                previsioneValTrebbia = immagineDopodomaniValTrebbia,
                previsionePianuraPiacentina = immagineDopodomaniPianuraPiacentina,
                previsioneTerreVerdiane = immagineDopodomaniTerreVerdiane,
                previsioneBassaParmense = immagineDopodomaniBassaParmense,
                previsioneValNure = immagineDopodomaniValNure,
                previsioneAppenninoLigurePiacentino = immagineDopodomaniAppenninoLigurePiacentino,
                previsioneValTaro = immagineDopodomaniValTaro,
                previsionePedemontanaParmense = immagineDopodomaniPedemontanaParmense,
                previsioneAltaValTaro = immagineDopodomaniAltaValTaro,
                previsioneCrinaleAppenninico = immagineDopodomaniCrinaleAppenninico,
                previsioneSpezzinoInterno = immagineDopodomaniSpezzinoInterno,
                previsioneAppenninoReggiano = immagineDopodomaniAppenninoReggiano,
                previsioneCostaSpezzina = immagineDopodomaniCostaSpezzina
            )
            val paginaDopodomani = ConfiniPageTab(
                data = testoDopodomaniData,
                testoCielo = testoDopodomaniCielo,
                testoFenomeni = testoDopodomaniFenomeni,
                testoTemperature = testoDopodomaniTemperature,
                testoVenti = testoDopodomaniVenti,
                testoMare = testoDopodomaniMare,
                mappa = mappaDopodomani
            )
            ConfiniPage(
                testoUltimoAggiornamento = testoUltimaModifica,
                testoPrevisione = testoPrevisione,
                paginaOggi = paginaOggi,
                paginaDomani = paginaDomani,
                paginaDopodomani = paginaDopodomani,
            )
        }
    }

    suspend fun getMontagnaData(): MontagnaPage {
        return withContext(Dispatchers.IO) {
            val document = Jsoup.connect("https://www.meteoapuane.it/montagna.php").timeout(10 * 1000).get()
            val testo = document.select("td.testo2 > span:nth-child(5)").text()
            val testoUltimaModifica =
                "Aggiornamento del " + document.select(".Stile3 > strong:nth-child(1)")
                    .text() + " a cura di " + document.select(".Stile3 > strong:nth-child(2)")
                    .text()
            val testoOggiZeri =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1)")
                    .text()
            val testoDomaniZeri =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1)")
                    .text()
            val testoDopodomaniZeri =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3)")
                    .text()
            val testoZeroZeri =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(1)")
                    .text()
            val testoNeveZeri =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(2)")
                    .text()
            val testoZeroCerreto =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(1)")
                    .text()
            val testoNeveCerreto =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(2)")
                    .text()
            val testoZeroCampocecina =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(1)")
                    .text()
            val testoNeveCampocecina =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(2)")
                    .text()
            val testoZeroPratospilla =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(1)")
                    .text()
            val testoNevePratospilla =
                document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(2)")
                    .text()
            val immagineSfondo = "https://www.meteoapuane.it/img/montagna.jpg"
            val immagineOggiZeri = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            val immagineDomaniZeri = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            val immagineDopodomaniZeri = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            val immagineOggiCampocecina = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            val immagineDomaniCampocecina = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            val immagineDopodomaniCampocecina = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            val immagineOggiPratospilla = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            val immagineDomaniPratospilla = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            val immagineDopodomaniPratospilla = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            val immagineOggiCerreto = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            val immagineDomaniCerreto = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            val immagineDopodomaniCerreto = "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
            MontagnaPage(
                testoUltimoAggiornamento = testoUltimaModifica,
                testo = testo,
                testoDataOggi = testoOggiZeri,
                testoDataDomani = testoDomaniZeri,
                testoDataDopodomani = testoDopodomaniZeri,
                testoZeroZeri = testoZeroZeri,
                testoNeveZeri = testoNeveZeri,
                immagineSfondo = immagineSfondo,
                immagineOggiZeri = immagineOggiZeri,
                immagineDomaniZeri = immagineDomaniZeri,
                immagineDopodomaniZeri = immagineDopodomaniZeri,
                testoZeroPratospilla = testoZeroPratospilla,
                testoNevePratospilla = testoNevePratospilla,
                immagineOggiPratospilla = immagineOggiPratospilla,
                immagineDomaniPratospilla = immagineDomaniPratospilla,
                immagineDopodomaniPratospilla = immagineDopodomaniPratospilla,
                testoZeroCampocecina = testoZeroCampocecina,
                testoNeveCampocecina = testoNeveCampocecina,
                immagineOggiCampocecina = immagineOggiCampocecina,
                immagineDomaniCampocecina = immagineDomaniCampocecina,
                immagineDopodomaniCampocecina = immagineDopodomaniCampocecina,
                testoZeroCerreto = testoZeroCerreto,
                testoNeveCerreto = testoNeveCerreto,
                immagineOggiCerreto = immagineOggiCerreto,
                immagineDomaniCerreto = immagineDomaniCerreto,
                immagineDopodomaniCerreto = immagineDopodomaniCerreto
            )
        }
    }

    suspend fun getViabilitaData(): ViabilitaPage {
        return withContext(Dispatchers.IO) {
            println("QUA INIZIA CHIAMATA")

            val document = Jsoup.connect("https://www.meteoapuane.it/viabilita.php").timeout(10 * 1000).get()
            val documentPanel =
                Jsoup.connect("https://www.meteoapuane.it/ledpanel/prova.php").timeout(10 * 1000).get()
            val testoSegnalazione =
                documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > span:nth-child(1)")
                    .text()
//                val imgSegnalazioneGrande = getBitmapFromUrl("https://www.meteoapuane.it/"+documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > img:nth-child(2)").first()?.select("img")?.attr("src"))
            val imgSegnalazionePiccola1 = "https://www.meteoapuane.it/ledpanel/" + documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
            val imgSegnalazionePiccola2 = "https://www.meteoapuane.it/ledpanel/" + documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
            val imgA15LaspeziaVento = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15LaspeziaPioggia = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15LaspeziaNebbia = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15LaspeziaNeve = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15LaspeziaGhiaccio = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15SantostefanoVento = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15SantostefanoPioggia = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15SantostefanoNebbia = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15SantostefanoNeve = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15SantostefanoGhiaccio = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15AullaVento = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15AullaPioggia = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15AullaNebbia = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15AullaNeve = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15AullaGhiaccio = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15PontremoliVento = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15PontremoliPioggia = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15PontremoliNebbia = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15PontremoliNeve = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA15PontremoliGhiaccio = document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12VersiliaVento = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12VersiliaPioggia = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12VersiliaNebbia = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12VersiliaNeve = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12VersiliaGhiaccio = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12MassaVento = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12MassaPioggia = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12MassaNebbia = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12MassaNeve = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12MassaGhiaccio = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12CarraraVento = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12CarraraPioggia = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12CarraraNebbia = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12CarraraNeve = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12CarraraGhiaccio = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12SarzanaVento = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12SarzanaPioggia = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12SarzanaNebbia = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12SarzanaNeve = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""
            val imgA12SarzanaGhiaccio = document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")?.let {
                    "https://www.meteoapuane.it/$it"
                } ?: ""

            val urlVideoA15SantoStefano = document.select("table.testo2:nth-child(10) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > span:nth-child(1) > a:nth-child(3)")[0].attr("href")
            val urlVideoA15Pontremoli = document.select("table.testo2:nth-child(10) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1) > span:nth-child(1) > a:nth-child(3)")[0].attr("href")
            val urlVideoA15Montelungo = document.select("table.testo2:nth-child(10) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(3) > div:nth-child(1) > span:nth-child(1) > a:nth-child(3)")[0].attr("href")
            val urlVideoA15Tugo = document.select("table.testo2:nth-child(10) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(4) > div:nth-child(1) > span:nth-child(1) > a:nth-child(3)")[0].attr("href")

            val urlVideoA12Ceparana = document.select("table.testo2:nth-child(11) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > span:nth-child(1) > a:nth-child(3)")[0].attr("href")
            val urlVideoA12Luni = document.select("table.testo2:nth-child(11) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > div:nth-child(1) > span:nth-child(1) > a:nth-child(3)")[0].attr("href")
            val urlVideoA12Avenza = document.select("table.testo2:nth-child(11) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(3) > div:nth-child(1) > span:nth-child(1) > a:nth-child(3)")[0].attr("href")
            val urlVideoA12Cinquale = document.select("table.testo2:nth-child(11) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(4) > div:nth-child(1) > span:nth-child(1) > a:nth-child(3)")[0].attr("href")

            ViabilitaPage(
                testoSegnalazione = testoSegnalazione,
                imgSegnalazioneGrande = "",
                imgSegnalazionePiccola1 = imgSegnalazionePiccola1,
                imgSegnalazionePiccola2 = imgSegnalazionePiccola2,
                imgA15LaspeziaVento = imgA15LaspeziaVento,
                imgA15LaspeziaPioggia = imgA15LaspeziaPioggia,
                imgA15LaspeziaNebbia = imgA15LaspeziaNebbia,
                imgA15LaspeziaNeve = imgA15LaspeziaNeve,
                imgA15LaspeziaGhiaccio = imgA15LaspeziaGhiaccio,
                imgA15SantostefanoVento = imgA15SantostefanoVento,
                imgA15SantostefanoPioggia = imgA15SantostefanoPioggia,
                imgA15SantostefanoNebbia = imgA15SantostefanoNebbia,
                imgA15SantostefanoNeve = imgA15SantostefanoNeve,
                imgA15SantostefanoGhiaccio = imgA15SantostefanoGhiaccio,
                imgA15AullaVento = imgA15AullaVento,
                imgA15AullaPioggia = imgA15AullaPioggia,
                imgA15AullaNebbia = imgA15AullaNebbia,
                imgA15AullaNeve = imgA15AullaNeve,
                imgA15AullaGhiaccio = imgA15AullaGhiaccio,
                imgA15PontremoliVento = imgA15PontremoliVento,
                imgA15PontremoliPioggia = imgA15PontremoliPioggia,
                imgA15PontremoliNebbia = imgA15PontremoliNebbia,
                imgA15PontremoliNeve = imgA15PontremoliNeve,
                imgA15PontremoliGhiaccio = imgA15PontremoliGhiaccio,
                imgA12VersiliaVento = imgA12VersiliaVento,
                imgA12VersiliaPioggia = imgA12VersiliaPioggia,
                imgA12VersiliaNebbia = imgA12VersiliaNebbia,
                imgA12VersiliaNeve = imgA12VersiliaNeve,
                imgA12VersiliaGhiaccio = imgA12VersiliaGhiaccio,
                imgA12MassaVento = imgA12MassaVento,
                imgA12MassaPioggia = imgA12MassaPioggia,
                imgA12MassaNebbia = imgA12MassaNebbia,
                imgA12MassaNeve = imgA12MassaNeve,
                imgA12MassaGhiaccio = imgA12MassaGhiaccio,
                imgA12CarraraVento = imgA12CarraraVento,
                imgA12CarraraPioggia = imgA12CarraraPioggia,
                imgA12CarraraNebbia = imgA12CarraraNebbia,
                imgA12CarraraNeve = imgA12CarraraNeve,
                imgA12CarraraGhiaccio = imgA12CarraraGhiaccio,
                imgA12SarzanaVento = imgA12SarzanaVento,
                imgA12SarzanaPioggia = imgA12SarzanaPioggia,
                imgA12SarzanaNebbia = imgA12SarzanaNebbia,
                imgA12SarzanaNeve = imgA12SarzanaNeve,
                imgA12SarzanaGhiaccio = imgA12SarzanaGhiaccio,
                urlVideoA15SantoStefano = urlVideoA15SantoStefano,
                urlVideoA15Pontremoli = urlVideoA15Pontremoli,
                urlVideoA15Montelungo = urlVideoA15Montelungo,
                urlVideoA15Tugo = urlVideoA15Tugo,
                urlVideoA12Ceparana = urlVideoA12Ceparana,
                urlVideoA12Luni = urlVideoA12Luni,
                urlVideoA12Avenza = urlVideoA12Avenza,
                urlVideoA12Cinquale = urlVideoA12Cinquale,

            )
        }
    }

    suspend fun getIncendiData(): IncendiPage {
        return withContext(Dispatchers.IO) {
            val document = Jsoup.connect("https://www.meteoapuane.it/incendi.php").timeout(10 * 1000).get()
            val txtDataOggi = document.select("td.testo2 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > strong:nth-child(1)")[0].text()
            val imgCostaOggi = "https://www.meteoapuane.it/" + document.select("td.testo2 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(3) > img:nth-child(1)").attr("src")
            val imgLunigianaOggi = "https://www.meteoapuane.it/" + document.select("td.testo2 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(5) > img:nth-child(1)").attr("src")
            val txtDataDomani = document.select("td.testo2 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > strong:nth-child(1)")[0].text()
            val imgCostaDomani = "https://www.meteoapuane.it/" + document.select("td.testo2 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > img:nth-child(1)").attr("src")
            val imgLunigianaDomani = "https://www.meteoapuane.it/" + document.select("td.testo2 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(5) > img:nth-child(1)").attr("src")
            val txtDataDopodomani = document.select("td.testo2 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(1) > strong:nth-child(1)")[0].text()
            val imgCostaDopodomani = "https://www.meteoapuane.it/" + document.select("td.testo2 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(7) > td:nth-child(3) > img:nth-child(1)").attr("src")
            val imgLunigianaDopodomani = "https://www.meteoapuane.it/" + document.select("td.testo2 > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(7) > td:nth-child(5) > img:nth-child(1)").attr("src")

            IncendiPage(
                dataOggi = txtDataOggi,
                costaOggi = imgCostaOggi,
                lunigianaOggi = imgLunigianaOggi,
                dataDomani = txtDataDomani,
                costaDomani = imgCostaDomani,
                lunigianaDomani = imgLunigianaDomani,
                costaDopodomani = imgCostaDopodomani,
                lunigianaDopodomani = imgLunigianaDopodomani,
                dataDopodomani = txtDataDopodomani
            )
        }
    }

    suspend fun getWebcamData(): WebcamPage {
        return withContext(Dispatchers.IO) {
            val document = Jsoup.connect("https://www.meteoapuane.it/webcam.php").timeout(10 * 1000).get()
            val imgMassaCentro = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgMoncigoli = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgCanevara = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgMonteBorla = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgVinca = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgMarinaDiMassa = document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgMarinaDiCarrara = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > div:nth-child(2) > a:nth-child(2) > img:nth-child(1)").attr("src")
            val imgAvenza = document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgFivizzano = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgVillafrancaLunigiana = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgBagnone = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgSassalbo = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgMonteBosta = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgZumZeri = document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgPassoDelCerreto1 = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgPassoDelCerreto2 = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgCerretoLaghi = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgRigoso = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgPratospilla1 = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgPratospilla2 = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgMonteCusna = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgLagoSanto = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgValditacca = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgTrefiumi = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgBorgoValDiTaro = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > a:nth-child(3)").attr("src")
            val imgGhiareDiBerceto = document.select("table.testo2:nth-child(6) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgPonzanoMagra = document.select("table.testo2:nth-child(8) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > a:nth-child(3)").attr("src")
            val imgBoccaDiMagra = document.select("table.testo2:nth-child(8) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgLerici = document.select("table.testo2:nth-child(8) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgPortovenere = document.select("table.testo2:nth-child(8) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgSestaGodano = document.select("table.testo2:nth-child(8) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgVareseLigure = document.select("table.testo2:nth-child(8) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgPietrasanta = document.select("table.testo2:nth-child(10) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgLidoDiCamaiore1 = document.select("table.testo2:nth-child(10) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgLidoDiCamaiore2 = document.select("table.testo2:nth-child(10) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgViareggio = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(10) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgCapanneDiCareggine = "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(10) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > a:nth-child(3) > img:nth-child(1)").attr("src")
            val imgMonteArgegna = document.select("table.testo2:nth-child(10) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > a:nth-child(3) > img:nth-child(1)").attr("src")

            WebcamPage(
                massaCentro = imgMassaCentro,
                moncigoli = imgMoncigoli,
                canevara = imgCanevara,
                monteBorla = imgMonteBorla,
                vinca = imgVinca,
                marinaDiMassa = imgMarinaDiMassa,
                marinaDiCarrara = imgMarinaDiCarrara,
                avenza = imgAvenza,
                fivizzano = imgFivizzano,
                villafrancaLunigiana = imgVillafrancaLunigiana,
                bagnone = imgBagnone,
                sassalbo = imgSassalbo,
                monteBosta = imgMonteBosta,
                zumZeri = imgZumZeri,
                passoDelCerreto1 = imgPassoDelCerreto1,
                passoDelCerreto2 = imgPassoDelCerreto2,
                cerretoLaghi = imgCerretoLaghi,
                rigoso = imgRigoso,
                pratospilla1 = imgPratospilla1,
                pratospilla2 = imgPratospilla2,
                monteCusna = imgMonteCusna,
                lagoSanto = imgLagoSanto,
                valditacca = imgValditacca,
                trefiumi = imgTrefiumi,
                borgoValDiTaro = imgBorgoValDiTaro,
                ghiareDiBerceto = imgGhiareDiBerceto,
                ponzanoMagra = imgPonzanoMagra,
                boccaDiMagra = imgBoccaDiMagra,
                lerici = imgLerici,
                portovenere = imgPortovenere,
                sestaGodano = imgSestaGodano,
                vareseLigure = imgVareseLigure,
                pietrasanta = imgPietrasanta,
                lidoDiCamaiore1 = imgLidoDiCamaiore1,
                lidoDiCamaiore2 = imgLidoDiCamaiore2,
                viareggio = imgViareggio,
                capanneDiCareggine = imgCapanneDiCareggine,
                monteArgegna = imgMonteArgegna,

            )
        }
    }

    suspend fun getNowcastingData(): NowcastingPage {
        return withContext(Dispatchers.IO) {
            val document = Jsoup.connect("https://www.meteoapuane.it/nowcasting.php").timeout(10 * 1000).get()
            val imgMeteoSatInfrarosso = document.select("td.testo2 > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)").attr("src")
            val imgMeteosatInfrarossoAnimazione = document.select("td.testo2 > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)").attr("src")
            val imgTemperaturaNubi = document.select("td.testo2 > table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)").attr("src")
            val imgMeteosatVisibileAnimazione = document.select("td.testo2 > table:nth-child(5) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)").attr("src")
            val imgFulminazioniAnimazioneGolfoLigure = document.select("td.testo2 > table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)").attr("src")
            val imgFulminazioniAnimazioneItalia = document.select("td.testo2 > table:nth-child(7) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)").attr("src")
            val imgRadarPrecipitazioniSettepani = "https://www.meteoapuane.it/" + document.select("td.testo2 > table:nth-child(9) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)").attr("src")
            val imgRadarPrecipitazioniMeteoFrance = document.select("td.testo2 > table:nth-child(9) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)").attr("src")
            val imgCartaSinotticaEuropa = document.select("td.testo2 > table:nth-child(11) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)").attr("src")
            val imgCartaSinotticaItalia = document.select("td.testo2 > table:nth-child(11) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > div:nth-child(1) > a:nth-child(1) > img:nth-child(1)").attr("src")

            NowcastingPage(
                meteosatInfrarosso = imgMeteoSatInfrarosso,
                meteosatInfrarossoAnimazione = imgMeteosatInfrarossoAnimazione,
                temperaturaNubi = imgTemperaturaNubi,
                meteosatVisibileAnimazione = imgMeteosatVisibileAnimazione,
                fulminazioniAnimazioneGolfoLigure = imgFulminazioniAnimazioneGolfoLigure,
                fulminazioniAnimazioneItalia = imgFulminazioniAnimazioneItalia,
                radarPrecipitazioniSettepani = imgRadarPrecipitazioniSettepani,
                radarPrecipitazioniMeteoFrance = imgRadarPrecipitazioniMeteoFrance,
                cartaSinotticaEuropa = imgCartaSinotticaEuropa,
                cartaSinotticaItalia = imgCartaSinotticaItalia
            )
        }
    }
}