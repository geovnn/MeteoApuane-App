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
import org.jsoup.Jsoup
import java.io.IOException
import java.io.InputStream
import java.net.URL

class MeteoapuaneApi {

    private fun getBitmapFromUrl(url: String) : Bitmap? {
        return try {
            val inputStream: InputStream = URL(url).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun getHomeData(): HomePage {
        val document = Jsoup.connect("https://www.meteoapuane.it/").get()
        val txtUltimaModifica = document.select(".aggiornamento")[1].text()
        val urlImgAllerta1 =
            "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(1) > img:nth-child(1)")
                .attr("src")
        val urlImgAllerta2 =
            "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(2) > img:nth-child(1)")
                .attr("src")
        val urlImgAllerta3 =
            "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(3) > img:nth-child(1)")
                .attr("src")
        val urlImgAllerta4 =
            "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(4) > img:nth-child(1)")
                .attr("src")
        val urlImgAllerta5 =
            "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(5) > img:nth-child(1)")
                .attr("src")
        val urlImgAllerta6 =
            "https://www.meteoapuane.it/" + document.select(".tabellaprincipale > tbody:nth-child(3) > tr:nth-child(6) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > div:nth-child(3) > a:nth-child(6) > img:nth-child(1)")
                .attr("src")
        val urlImgUltimora1 =
            document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > img:nth-child(2)")
                .attr("src")
        val urlImgUltimora2 =
            document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > img:nth-child(7)")
                .attr("src")
        val txtAvviso = document.select("div.scrittepiccole:nth-child(11)").text()
        val txtUltimoraTitle1 =
            document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > strong:nth-child(3)")
                .text()
        val txtUltimoraTitle2 =
            document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > strong:nth-child(8)")
                .text()
        val txtUltimoraBody1 =
            document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)")
                .first()?.text().toString().substringAfter(txtUltimoraTitle1)
                .substringBefore(txtUltimoraTitle2)
        val txtUltimoraBody2 =
            document.select("table.testo2:nth-child(14) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1)")
                .first()?.text().toString().substringAfter(txtUltimoraTitle2)
                .substringBefore(" Vedi tutte le segnalazioni")
        val imgAllerta1: Bitmap? = getBitmapFromUrl(urlImgAllerta1)
        val imgAllerta2: Bitmap? = getBitmapFromUrl(urlImgAllerta2)
        val imgAllerta3: Bitmap? = getBitmapFromUrl(urlImgAllerta3)
        val imgAllerta4: Bitmap? = getBitmapFromUrl(urlImgAllerta4)
        val imgAllerta5: Bitmap? = getBitmapFromUrl(urlImgAllerta5)
        val imgAllerta6: Bitmap? = getBitmapFromUrl(urlImgAllerta6)
        val imgUltimora1: Bitmap? = getBitmapFromUrl(urlImgUltimora1)
        val imgUltimora2: Bitmap? = getBitmapFromUrl(urlImgUltimora2)
         return HomePage(
            imgAllerta1 = imgAllerta1,
            imgAllerta2 = imgAllerta2,
            imgAllerta3 = imgAllerta3,
            imgAllerta4 = imgAllerta4,
            imgAllerta5 = imgAllerta5,
            imgAllerta6 = imgAllerta6,
            txtAvviso = txtAvviso,
            imgUltimora1 = imgUltimora1,
            txtUltimoraTitle1 = txtUltimoraTitle1,
            txtUltimoraBody1 = txtUltimoraBody1,
            imgUltimora2 = imgUltimora2,
            txtUltimoraTitle2 = txtUltimoraTitle2,
            txtUltimoraBody2 = txtUltimoraBody2,
            txtUltimaModifia = txtUltimaModifica
        )

    }

    fun getProvinciaData(): ProvinciaPage {
        val url = "https://www.meteoapuane.it/previsioni.php"
        val document = Jsoup.connect(url).get()
        val ultimaModifica = document.select(".aggiornamento")[0].text()
        val sfondo = getBitmapFromUrl("https://www.meteoapuane.it/img/apuane_previ.jpg")
        // OGGI
        val temperatureMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[0].select("img").attr("src")
        )
        val iconaAggiunitivaMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[0].select("img").attr("src")
        )
        val altaLunigianaMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[0].select("img").attr("src")
        )
        val versanteEmilianoMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[0].select("img").attr("src")
        )
        val mediaAltaLunigianaMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[0].select("img").attr("src")
        )
        val lunigianaOccidentaleMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[0].select("img").attr("src")
        )
        val appenninoVersanteToscanoMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[0].select("img").attr("src")
        )
        val bassaLunigianaMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[0].select("img").attr("src")
        )
        val lunigianaSudOrientaleMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[0].select("img").attr("src")
        )
        val golfoDeiPoetiMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[0].select("img").attr("src")
        )
        val bassaValDiMagraMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "sarzana"
            )[0].select("img").attr("src")
        )
        val alpiApuaneMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[0].select("img").attr("src")
        )
        val massaCarraraMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[0].select("img").attr("src")
        )
        val altaVersiliaMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[0].select("img").attr("src")
        )
        val ventoAppenninoMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[0].select("img").attr("src")
        )
        val ventoLunigianaMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[0].select("img").attr("src")
        )
        val ventoAlpiApuaneMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[0].select("img").attr("src")
        )
        val ventoCostaMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[0].select("img").attr("src")
        )
        val mareSottocostaMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare1"
            )[0].select("img").attr("src")
        )
        val mareLargoMattinaOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[0].select("img").attr("src")
        )
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
        val temperatureSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[1].select("img").attr("src")
        )
        val iconaAggiunitivaSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[1].select("img").attr("src")
        )
        val altaLunigianaSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[1].select("img").attr("src")
        )
        val versanteEmilianoSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[1].select("img").attr("src")
        )
        val mediaAltaLunigianaSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[1].select("img").attr("src")
        )
        val lunigianaOccidentaleSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[1].select("img").attr("src")
        )
        val appenninoVersanteToscanoSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[1].select("img").attr("src")
        )
        val bassaLunigianaSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[1].select("img").attr("src")
        )
        val lunigianaSudOrientaleSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[1].select("img").attr("src")
        )
        val golfoDeiPoetiSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[1].select("img").attr("src")
        )
        val bassaValDiMagraSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "sarzana"
            )[1].select("img").attr("src")
        )
        val alpiApuaneSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[1].select("img").attr("src")
        )
        val massaCarraraSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[1].select("img").attr("src")
        )
        val altaVersiliaSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[1].select("img").attr("src")
        )
        val ventoAppenninoSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[1].select("img").attr("src")
        )
        val ventoLunigianaSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[1].select("img").attr("src")
        )
        val ventoAlpiApuaneSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[1].select("img").attr("src")
        )
        val ventoCostaSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[1].select("img").attr("src")
        )
        val mareSottocostaSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare1"
            )[1].select("img").attr("src")
        )
        val mareLargoSeraOggi = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[1].select("img").attr("src")
        )
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
        val temperatureMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[2].select("img").attr("src")
        )
        val iconaAggiunitivaMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[2].select("img").attr("src")
        )
        val altaLunigianaMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[2].select("img").attr("src")
        )
        val versanteEmilianoMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[2].select("img").attr("src")
        )
        val mediaAltaLunigianaMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[2].select("img").attr("src")
        )
        val lunigianaOccidentaleMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[2].select("img").attr("src")
        )
        val appenninoVersanteToscanoMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[2].select("img").attr("src")
        )
        val bassaLunigianaMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[2].select("img").attr("src")
        )
        val lunigianaSudOrientaleMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[2].select("img").attr("src")
        )
        val golfoDeiPoetiMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[2].select("img").attr("src")
        )
        val bassaValDiMagraMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "sarzana"
            )[2].select("img").attr("src")
        )
        val alpiApuaneMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[2].select("img").attr("src")
        )
        val massaCarraraMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[2].select("img").attr("src")
        )
        val altaVersiliaMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[2].select("img").attr("src")
        )
        val ventoAppenninoMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[2].select("img").attr("src")
        )
        val ventoLunigianaMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[2].select("img").attr("src")
        )
        val ventoAlpiApuaneMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[2].select("img").attr("src")
        )
        val ventoCostaMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[2].select("img").attr("src")
        )
        val mareSottocostaMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[2].select("img").attr("src")
        )
        val mareLargoMattinaDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[2].select("img").attr("src")
        )
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
        val temperatureSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[3].select("img").attr("src")
        )
        val iconaAggiunitivaSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[3].select("img").attr("src")
        )
        val altaLunigianaSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[3].select("img").attr("src")
        )
        val versanteEmilianoSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[3].select("img").attr("src")
        )
        val mediaAltaLunigianaSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[3].select("img").attr("src")
        )
        val lunigianaOccidentaleSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[3].select("img").attr("src")
        )
        val appenninoVersanteToscanoSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[3].select("img").attr("src")
        )
        val bassaLunigianaSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[3].select("img").attr("src")
        )
        val lunigianaSudOrientaleSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[3].select("img").attr("src")
        )
        val golfoDeiPoetiSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[3].select("img").attr("src")
        )
        val bassaValDiMagraSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "sarzana"
            )[3].select("img").attr("src")
        )
        val alpiApuaneSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[3].select("img").attr("src")
        )
        val massaCarraraSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[3].select("img").attr("src")
        )
        val altaVersiliaSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[3].select("img").attr("src")
        )
        val ventoAppenninoSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[3].select("img").attr("src")
        )
        val ventoLunigianaSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[3].select("img").attr("src")
        )
        val ventoAlpiApuaneSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[3].select("img").attr("src")
        )
        val ventoCostaSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[3].select("img").attr("src")
        )
        val mareSottocostaSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare1"
            )[3].select("img").attr("src")
        )
        val mareLargoSeraDomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[3].select("img").attr("src")
        )
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
        val temperatureMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[4].select("img").attr("src")
        )
        val iconaAggiunitivaMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[4].select("img").attr("src")
        )
        val altaLunigianaMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[4].select("img").attr("src")
        )
        val versanteEmilianoMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[4].select("img").attr("src")
        )
        val mediaAltaLunigianaMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[4].select("img").attr("src")
        )
        val lunigianaOccidentaleMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[4].select("img").attr("src")
        )
        val appenninoVersanteToscanoMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[4].select("img").attr("src")
        )
        val bassaLunigianaMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[4].select("img").attr("src")
        )
        val lunigianaSudOrientaleMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[4].select("img").attr("src")
        )
        val golfoDeiPoetiMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[4].select("img").attr("src")
        )
        val bassaValDiMagraMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "sarzana"
            )[4].select("img").attr("src")
        )
        val alpiApuaneMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[4].select("img").attr("src")
        )
        val massaCarraraMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[4].select("img").attr("src")
        )
        val altaVersiliaMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[4].select("img").attr("src")
        )
        val ventoAppenninoMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[4].select("img").attr("src")
        )
        val ventoLunigianaMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[4].select("img").attr("src")
        )
        val ventoAlpiApuaneMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[4].select("img").attr("src")
        )
        val ventoCostaMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[4].select("img").attr("src")
        )
        val mareSottocostaMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[4].select("img").attr("src")
        )
        val mareLargoMattinaDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[4].select("img").attr("src")
        )
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
        val temperatureSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "temperature"
            )[5].select("img").attr("src")
        )
        val iconaAggiunitivaSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "iconaaggiuntiva"
            )[5].select("img").attr("src")
        )
        val altaLunigianaSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cisa"
            )[5].select("img").attr("src")
        )
        val versanteEmilianoSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[5].select("img").attr("src")
        )
        val mediaAltaLunigianaSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "pontremoli"
            )[5].select("img").attr("src")
        )
        val lunigianaOccidentaleSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "zeri"
            )[5].select("img").attr("src")
        )
        val appenninoVersanteToscanoSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[5].select("img").attr("src")
        )
        val bassaLunigianaSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aulla"
            )[5].select("img").attr("src")
        )
        val lunigianaSudOrientaleSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "fivizzano"
            )[5].select("img").attr("src")
        )
        val golfoDeiPoetiSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva2"
            )[5].select("img").attr("src")
        )
        val bassaValDiMagraSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "sarzana"
            )[5].select("img").attr("src")
        )
        val alpiApuaneSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "apuane"
            )[5].select("img").attr("src")
        )
        val massaCarraraSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "massa"
            )[5].select("img").attr("src")
        )
        val altaVersiliaSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "aggiuntiva"
            )[5].select("img").attr("src")
        )
        val ventoAppenninoSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento2"
            )[5].select("img").attr("src")
        )
        val ventoLunigianaSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento1"
            )[5].select("img").attr("src")
        )
        val ventoAlpiApuaneSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento3"
            )[5].select("img").attr("src")
        )
        val ventoCostaSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "vento4"
            )[5].select("img").attr("src")
        )
        val mareSottocostaSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare1"
            )[5].select("img").attr("src")
        )
        val mareLargoSeraDopodomani = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "mare2"
            )[5].select("img").attr("src")
        )
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
        val successiviImgA1 = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > img:nth-child(1)")
                .first()?.attr("src")
        )
        val successiviImgB1 = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > img:nth-child(1)")
                .first()?.attr("src")
        )
        val successiviImgC1 = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > img:nth-child(1)")
                .first()?.attr("src")
        )
        val successiviImgA2 = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(6) > img:nth-child(1)")
                .first()?.attr("src")
        )
        val successiviImgB2 = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(6) > img:nth-child(1)")
                .first()?.attr("src")
        )
        val successiviImgC2 = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(6) > img:nth-child(1)")
                .first()?.attr("src")
        )
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
        return ProvinciaPage(
            testoUltimoAggiornamento = ultimaModifica,
            paginaOggi = previsioniOggi,
            paginaDomani = previsioniDomani,
            paginaDopodomani = previsioniDopodomani,
            paginaSuccessivi = previsioniSuccessivi,
        )

    }

    fun getConfiniData(): ConfiniPage {
        val url = "https://www.meteoapuane.it/3confini.php"
        val document = Jsoup.connect(url).get()
        val immagineSfondo =
            getBitmapFromUrl("https://www.meteoapuane.it/admin/3confini/prova_map.jpg")
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
            "Cielo: " + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                .text().substringAfter("Cielo: ").substringBefore("Fenomeni")
        val testoOggiFenomeni =
            "Fenomeni: " + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
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
        val immagineOggiMare = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "m1"
            )[0].select("img").attr("src")
        )
        val immagineOggiTemperaturaVersantePadano = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t3"
            )[0].select("img").attr("src")
        )
        val immagineOggiTemperaturaAppennino = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t2"
            )[0].select("img").attr("src")
        )
        val immagineOggiTemperaturaVersanteLigure = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t1"
            )[0].select("img").attr("src")
        )
        val immagineOggiVentoBassaPianura = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w4"
            )[0].select("img").attr("src")
        )
        val immagineOggiVentoAppenninoLigurePiacentino = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w5"
            )[0].select("img").attr("src")
        )
        val immagineOggiVentoPedemontana = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w3"
            )[0].select("img").attr("src")
        )
        val immagineOggiVentoCostaLigure = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w2"
            )[0].select("img").attr("src")
        )
        val immagineOggiVentoCrinale = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w1"
            )[0].select("img").attr("src")
        )
        val immagineOggiValTrebbia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "bobbio"
            )[0].select("img").attr("src")
        )
        val immagineOggiPianuraPiacentina = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "firenzuola"
            )[0].select("img").attr("src")
        )
        val immagineOggiTerreVerdiane = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "castelnovo"
            )[0].select("img").attr("src")
        )
        val immagineOggiBassaParmense = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[0].select("img").attr("src")
        )
        val immagineOggiAppenninoLigurePiacentino = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "ottone"
            )[0].select("img").attr("src")
        )
        val immagineOggiValNure = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "morfasso"
            )[0].select("img").attr("src")
        )
        val immagineOggiValTaro = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "borgotaro"
            )[0].select("img").attr("src")
        )
        val immagineOggiPedemontanaParmense = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "langhirano"
            )[0].select("img").attr("src")
        )
        val immagineOggiSpezzinoInterno = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "levanto"
            )[0].select("img").attr("src")
        )
        val immagineOggiAltaValTaro = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "varese"
            )[0].select("img").attr("src")
        )
        val immagineOggiCrinaleAppenninico = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "corniglio"
            )[0].select("img").attr("src")
        )
        val immagineOggiAppenninoReggiano = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[0].select("img").attr("src")
        )
        val immagineOggiCostaSpezzina = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "spezia"
            )[0].select("img").attr("src")
        )
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
            "Cielo: " + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                .text().substringAfter("Cielo: ").substringBefore("Fenomeni")
        val testoDomaniFenomeni =
            "Fenomeni: " + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
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
        val immagineDomaniMare = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "m1"
            )[1].select("img").attr("src")
        )
        val immagineDomaniTemperaturaVersantePadano = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t3"
            )[1].select("img").attr("src")
        )
        val immagineDomaniTemperaturaAppennino = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t2"
            )[1].select("img").attr("src")
        )
        val immagineDomaniTemperaturaVersanteLigure = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t1"
            )[1].select("img").attr("src")
        )
        val immagineDomaniVentoBassaPianura = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w4"
            )[1].select("img").attr("src")
        )
        val immagineDomaniVentoAppenninoLigurePiacentino = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w5"
            )[1].select("img").attr("src")
        )
        val immagineDomaniVentoPedemontana = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w3"
            )[1].select("img").attr("src")
        )
        val immagineDomaniVentoCostaLigure = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w2"
            )[1].select("img").attr("src")
        )
        val immagineDomaniVentoCrinale = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w1"
            )[1].select("img").attr("src")
        )
        val immagineDomaniValTrebbia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "bobbio"
            )[1].select("img").attr("src")
        )
        val immagineDomaniPianuraPiacentina = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "firenzuola"
            )[1].select("img").attr("src")
        )
        val immagineDomaniTerreVerdiane = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "castelnovo"
            )[1].select("img").attr("src")
        )
        val immagineDomaniBassaParmense = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[1].select("img").attr("src")
        )
        val immagineDomaniAppenninoLigurePiacentino = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "ottone"
            )[1].select("img").attr("src")
        )
        val immagineDomaniValNure = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "morfasso"
            )[1].select("img").attr("src")
        )
        val immagineDomaniValTaro = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "borgotaro"
            )[1].select("img").attr("src")
        )
        val immagineDomaniPedemontanaParmense = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "langhirano"
            )[1].select("img").attr("src")
        )
        val immagineDomaniSpezzinoInterno = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "levanto"
            )[1].select("img").attr("src")
        )
        val immagineDomaniAltaValTaro = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "varese"
            )[1].select("img").attr("src")
        )
        val immagineDomaniCrinaleAppenninico = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "corniglio"
            )[1].select("img").attr("src")
        )
        val immagineDomaniAppenninoReggiano = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[1].select("img").attr("src")
        )
        val immagineDomaniCostaSpezzina = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "spezia"
            )[1].select("img").attr("src")
        )
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
            "Cielo: " + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
                .text().substringAfter("Cielo: ").substringBefore("Fenomeni")
        val testoDopodomaniFenomeni =
            "Fenomeni: " + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2)")
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
        val immagineDopodomaniMare = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "m1"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniTemperaturaVersantePadano = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t3"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniTemperaturaAppennino = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t2"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniTemperaturaVersanteLigure = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "t1"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniVentoBassaPianura = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w4"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniVentoAppenninoLigurePiacentino = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w5"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniVentoPedemontana = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w3"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniVentoCostaLigure = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w2"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniVentoCrinale = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "w1"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniValTrebbia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "bobbio"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniPianuraPiacentina = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "firenzuola"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniTerreVerdiane = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "castelnovo"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniBassaParmense = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "parma"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniAppenninoLigurePiacentino = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "ottone"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniValNure = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "morfasso"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniValTaro = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "borgotaro"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniPedemontanaParmense = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "langhirano"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniSpezzinoInterno = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "levanto"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniAltaValTaro = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "varese"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniCrinaleAppenninico = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "corniglio"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniAppenninoReggiano = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "cerreto"
            )[2].select("img").attr("src")
        )
        val immagineDopodomaniCostaSpezzina = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                "id",
                "spezia"
            )[2].select("img").attr("src")
        )
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
        return ConfiniPage(
            testoUltimoAggiornamento = testoUltimaModifica,
            testoPrevisione = testoPrevisione,
            paginaOggi = paginaOggi,
            paginaDomani = paginaDomani,
            paginaDopodomani = paginaDopodomani,
        )

    }

    fun getMontagnaData(): MontagnaPage {
        val document = Jsoup.connect("https://www.meteoapuane.it/montagna.php").get()
        val immagineSfondo = getBitmapFromUrl("https://www.meteoapuane.it/img/montagna.jpg")
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
        val immagineOggiZeri = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        val immagineDomaniZeri = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        val immagineDopodomaniZeri = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        val testoZeroCampocecina =
            document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(1)")
                .text()
        val testoNeveCampocecina =
            document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(2)")
                .text()
        val immagineOggiCampocecina = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        val immagineDomaniCampocecina = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        val immagineDopodomaniCampocecina = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        val testoZeroPratospilla =
            document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(1)")
                .text()
        val testoNevePratospilla =
            document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(2)")
                .text()
        val immagineOggiPratospilla = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        val immagineDomaniPratospilla = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        val immagineDopodomaniPratospilla = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        val testoZeroCerreto =
            document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(1)")
                .text()
        val testoNeveCerreto =
            document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(1) > strong:nth-child(2)")
                .text()
        val immagineOggiCerreto = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        val immagineDomaniCerreto = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        val immagineDopodomaniCerreto = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                "img"
            ).attr("src")
        )
        return MontagnaPage(
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

    fun getViabilitaData(): ViabilitaPage {
        val document = Jsoup.connect("https://www.meteoapuane.it/montagna.php").get()
        val documentPanel =
            Jsoup.connect("https://www.meteoapuane.it/ledpanel/prova.php").get()
//                val imgSegnalazioneGrande = getBitmapFromUrl("https://www.meteoapuane.it/"+documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > img:nth-child(2)").first()?.select("img")?.attr("src"))
        val imgSegnalazionePiccola1 = getBitmapFromUrl(
            "https://www.meteoapuane.it/ledpanel/" + documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgSegnalazionePiccola2 = getBitmapFromUrl(
            "https://www.meteoapuane.it/ledpanel/" + documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val testoSegnalazione =
            documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > span:nth-child(1)")
                .text()
        val imgA15LaspeziaVento = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15LaspeziaPioggia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15LaspeziaNebbia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15LaspeziaNeve = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15LaspeziaGhiaccio = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15SantostefanoVento = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15SantostefanoPioggia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15SantostefanoNebbia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15SantostefanoNeve = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15SantostefanoGhiaccio = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15AullaVento = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15AullaPioggia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15AullaNebbia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15AullaNeve = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15AullaGhiaccio = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15PontremoliVento = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15PontremoliPioggia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15PontremoliNebbia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15PontremoliNeve = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA15PontremoliGhiaccio = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12VersiliaVento = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12VersiliaPioggia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12VersiliaNebbia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12VersiliaNeve = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12VersiliaGhiaccio = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12MassaVento = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12MassaPioggia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12MassaNebbia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12MassaNeve = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12MassaGhiaccio = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12CarraraVento = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12CarraraPioggia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12CarraraNebbia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12CarraraNeve = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12CarraraGhiaccio = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12SarzanaVento = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(2) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12SarzanaPioggia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(3) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12SarzanaNebbia = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(4) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12SarzanaNeve = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(5) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        val imgA12SarzanaGhiaccio = getBitmapFromUrl(
            "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(6) > img:nth-child(1)")
                .first()?.select("img")?.attr("src")
        )
        return ViabilitaPage(
            testoSegnalazione = testoSegnalazione,
            imgSegnalazioneGrande = null,
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
            imgA12SarzanaGhiaccio = imgA12SarzanaGhiaccio
        )

    }
}
