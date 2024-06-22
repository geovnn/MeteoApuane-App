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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.io.IOException
import java.io.InputStream
import java.net.URL

class MeteoapuaneScrape {

    private suspend fun getBitmapFromUrl(url: String): Bitmap? {
        println("getBitmap")
        return withContext(Dispatchers.IO) {
            try {
                val inputStream: InputStream = URL(url).openStream()
                BitmapFactory.decodeStream(inputStream)
            } catch (e: IOException) {
                e.printStackTrace()
                null
            }
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

            // Avviare il caricamento delle immagini in parallelo
            val deferredImgAllerta1 = async { getBitmapFromUrl(urlImgAllerta1) }
            val deferredImgAllerta2 = async { getBitmapFromUrl(urlImgAllerta2) }
            val deferredImgAllerta3 = async { getBitmapFromUrl(urlImgAllerta3) }
            val deferredImgAllerta4 = async { getBitmapFromUrl(urlImgAllerta4) }
            val deferredImgAllerta5 = async { getBitmapFromUrl(urlImgAllerta5) }
            val deferredImgAllerta6 = async { getBitmapFromUrl(urlImgAllerta6) }
            val deferredImgUltimora1 = async { getBitmapFromUrl(urlImgUltimora1) }
            val deferredImgUltimora2 = async { getBitmapFromUrl(urlImgUltimora2) }

            // Attendere il completamento di tutti i caricamenti
            val imgAllerta1 = deferredImgAllerta1.await()
            val imgAllerta2 = deferredImgAllerta2.await()
            val imgAllerta3 = deferredImgAllerta3.await()
            val imgAllerta4 = deferredImgAllerta4.await()
            val imgAllerta5 = deferredImgAllerta5.await()
            val imgAllerta6 = deferredImgAllerta6.await()
            val imgUltimora1 = deferredImgUltimora1.await()
            val imgUltimora2 = deferredImgUltimora2.await()

            HomePage(
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
    }

    suspend fun getProvinciaData(): ProvinciaPage {
        return withContext(Dispatchers.IO) {
            val url = "https://www.meteoapuane.it/previsioni.php"
            val document = Jsoup.connect(url).timeout(10 * 1000).get()
            val ultimaModifica = document.select(".aggiornamento")[0].text()
            val deferredSfondo = async { getBitmapFromUrl("https://www.meteoapuane.it/img/apuane_previ.jpg") }
            // OGGI
            val deferredTemperatureMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "temperature"
                    )[0].select("img").attr("src")
                )
            }
            val deferredIconaAggiunitivaMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "iconaaggiuntiva"
                    )[0].select("img").attr("src")
                )
            }
            val deferredAltaLunigianaMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "cisa"
                    )[0].select("img").attr("src")
                )
            }
            val deferredVersanteEmilianoMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "parma"
                    )[0].select("img").attr("src")
                )
            }
            val deferredMediaAltaLunigianaMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "pontremoli"
                    )[0].select("img").attr("src")
                )
            }
            val deferredLunigianaOccidentaleMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "zeri"
                    )[0].select("img").attr("src")
                )
            }
            val deferredAppenninoVersanteToscanoMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "cerreto"
                    )[0].select("img").attr("src")
                )
            }
            val deferredBassaLunigianaMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "aulla"
                    )[0].select("img").attr("src")
                )
            }
            val deferredLunigianaSudOrientaleMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "fivizzano"
                    )[0].select("img").attr("src")
                )
            }
            val deferredGolfoDeiPoetiMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "aggiuntiva2"
                    )[0].select("img").attr("src")
                )
            }
            val deferredBassaValDiMagraMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "sarzana"
                    )[0].select("img").attr("src")
                )
            }
            val deferredAlpiApuaneMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "apuane"
                    )[0].select("img").attr("src")
                )
            }
            val deferredMassaCarraraMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "massa"
                    )[0].select("img").attr("src")
                )
            }
            val deferredAltaVersiliaMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "aggiuntiva"
                    )[0].select("img").attr("src")
                )
            }
            val deferredVentoAppenninoMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "vento2"
                    )[0].select("img").attr("src")
                )
            }
            val deferredVentoLunigianaMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "vento1"
                    )[0].select("img").attr("src")
                )
            }
            val deferredVentoAlpiApuaneMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "vento3"
                    )[0].select("img").attr("src")
                )
            }
            val deferredVentoCostaMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "vento4"
                    )[0].select("img").attr("src")
                )
            }
            val deferredMareSottocostaMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "mare1"
                    )[0].select("img").attr("src")
                )
            }
            val deferredMareLargoMattinaOggi = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "mare2"
                    )[0].select("img").attr("src")
                )
            }

            val sfondo = deferredSfondo.await()
            val temperatureMattinaOggi = deferredTemperatureMattinaOggi.await()
            val iconaAggiunitivaMattinaOggi = deferredIconaAggiunitivaMattinaOggi.await()
            val altaLunigianaMattinaOggi = deferredAltaLunigianaMattinaOggi.await()
            val versanteEmilianoMattinaOggi = deferredVersanteEmilianoMattinaOggi.await()
            val mediaAltaLunigianaMattinaOggi = deferredMediaAltaLunigianaMattinaOggi.await()
            val lunigianaOccidentaleMattinaOggi = deferredLunigianaOccidentaleMattinaOggi.await()
            val appenninoVersanteToscanoMattinaOggi = deferredAppenninoVersanteToscanoMattinaOggi.await()
            val bassaLunigianaMattinaOggi = deferredBassaLunigianaMattinaOggi.await()
            val lunigianaSudOrientaleMattinaOggi = deferredLunigianaSudOrientaleMattinaOggi.await()
            val golfoDeiPoetiMattinaOggi = deferredGolfoDeiPoetiMattinaOggi.await()
            val bassaValDiMagraMattinaOggi = deferredBassaValDiMagraMattinaOggi.await()
            val alpiApuaneMattinaOggi = deferredAlpiApuaneMattinaOggi.await()
            val massaCarraraMattinaOggi = deferredMassaCarraraMattinaOggi.await()
            val altaVersiliaMattinaOggi = deferredAltaVersiliaMattinaOggi.await()
            val ventoAppenninoMattinaOggi = deferredVentoAppenninoMattinaOggi.await()
            val ventoLunigianaMattinaOggi = deferredVentoLunigianaMattinaOggi.await()
            val ventoAlpiApuaneMattinaOggi = deferredVentoAlpiApuaneMattinaOggi.await()
            val ventoCostaMattinaOggi = deferredVentoCostaMattinaOggi.await()
            val mareSottocostaMattinaOggi = deferredMareSottocostaMattinaOggi.await()
            val mareLargoMattinaOggi = deferredMareLargoMattinaOggi.await()

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
            val deferredTemperatureSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "temperature"
                    )[5].select("img").attr("src")
                )
            }
            val deferredIconaAggiunitivaSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "iconaaggiuntiva"
                    )[5].select("img").attr("src")
                )
            }
            val deferredAltaLunigianaSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "cisa"
                    )[5].select("img").attr("src")
                )
            }
            val deferredVersanteEmilianoSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "parma"
                    )[5].select("img").attr("src")
                )
            }
            val deferredMediaAltaLunigianaSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "pontremoli"
                    )[5].select("img").attr("src")
                )
            }
            val deferredLunigianaOccidentaleSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "zeri"
                    )[5].select("img").attr("src")
                )
            }
            val deferredAppenninoVersanteToscanoSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "cerreto"
                    )[5].select("img").attr("src")
                )
            }
            val deferredBassaLunigianaSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "aulla"
                    )[5].select("img").attr("src")
                )
            }
            val deferredLunigianaSudOrientaleSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "fivizzano"
                    )[5].select("img").attr("src")
                )
            }
            val deferredGolfoDeiPoetiSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "aggiuntiva2"
                    )[5].select("img").attr("src")
                )
            }
            val deferredBassaValDiMagraSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "sarzana"
                    )[5].select("img").attr("src")
                )
            }
            val deferredAlpiApuaneSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "apuane"
                    )[5].select("img").attr("src")
                )
            }
            val deferredMassaCarraraSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "massa"
                    )[5].select("img").attr("src")
                )
            }
            val deferredAltaVersiliaSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "aggiuntiva"
                    )[5].select("img").attr("src")
                )
            }
            val deferredVentoAppenninoSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "vento2"
                    )[5].select("img").attr("src")
                )
            }
            val deferredVentoLunigianaSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "vento1"
                    )[5].select("img").attr("src")
                )
            }
            val deferredVentoAlpiApuaneSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "vento3"
                    )[5].select("img").attr("src")
                )
            }
            val deferredVentoCostaSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "vento4"
                    )[5].select("img").attr("src")
                )
            }
            val deferredMareSottocostaSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "mare1"
                    )[5].select("img").attr("src")
                )
            }
            val deferredMareLargoSeraDopodomani = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "mare2"
                    )[5].select("img").attr("src")
                )
            }

            val temperatureSeraDopodomani = deferredTemperatureSeraDopodomani.await()
            val iconaAggiunitivaSeraDopodomani = deferredIconaAggiunitivaSeraDopodomani.await()
            val altaLunigianaSeraDopodomani = deferredAltaLunigianaSeraDopodomani.await()
            val versanteEmilianoSeraDopodomani = deferredVersanteEmilianoSeraDopodomani.await()
            val mediaAltaLunigianaSeraDopodomani = deferredMediaAltaLunigianaSeraDopodomani.await()
            val lunigianaOccidentaleSeraDopodomani = deferredLunigianaOccidentaleSeraDopodomani.await()
            val appenninoVersanteToscanoSeraDopodomani = deferredAppenninoVersanteToscanoSeraDopodomani.await()
            val bassaLunigianaSeraDopodomani = deferredBassaLunigianaSeraDopodomani.await()
            val lunigianaSudOrientaleSeraDopodomani = deferredLunigianaSudOrientaleSeraDopodomani.await()
            val golfoDeiPoetiSeraDopodomani = deferredGolfoDeiPoetiSeraDopodomani.await()
            val bassaValDiMagraSeraDopodomani = deferredBassaValDiMagraSeraDopodomani.await()
            val alpiApuaneSeraDopodomani = deferredAlpiApuaneSeraDopodomani.await()
            val massaCarraraSeraDopodomani = deferredMassaCarraraSeraDopodomani.await()
            val altaVersiliaSeraDopodomani = deferredAltaVersiliaSeraDopodomani.await()
            val ventoAppenninoSeraDopodomani = deferredVentoAppenninoSeraDopodomani.await()
            val ventoLunigianaSeraDopodomani = deferredVentoLunigianaSeraDopodomani.await()
            val ventoAlpiApuaneSeraDopodomani = deferredVentoAlpiApuaneSeraDopodomani.await()
            val ventoCostaSeraDopodomani = deferredVentoCostaSeraDopodomani.await()
            val mareSottocostaSeraDopodomani = deferredMareSottocostaSeraDopodomani.await()
            val mareLargoSeraDopodomani = deferredMareLargoSeraDopodomani.await()

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
            val deferredSuccessiviImgA1 = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.attr("src")
                )
            }
            val deferredSuccessiviImgB1 = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.attr("src")
                )
            }
            val deferredSuccessiviImgC1 = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.attr("src")
                )
            }
            val deferredSuccessiviImgA2 = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(6) > img:nth-child(1)")
                        .first()?.attr("src")
                )
            }
            val deferredSuccessiviImgB2 = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(6) > img:nth-child(1)")
                        .first()?.attr("src")
                )
            }
            val deferredSuccessiviImgC2 = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(4) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(6) > img:nth-child(1)")
                        .first()?.attr("src")
                )
            }

            val successiviImgA1 = deferredSuccessiviImgA1.await()
            val successiviImgB1 = deferredSuccessiviImgB1.await()
            val successiviImgC1 = deferredSuccessiviImgC1.await()
            val successiviImgA2 = deferredSuccessiviImgA2.await()
            val successiviImgB2 = deferredSuccessiviImgB2.await()
            val successiviImgC2 = deferredSuccessiviImgC2.await()
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
            val deferredImmagineSfondo = async {
                getBitmapFromUrl("https://www.meteoapuane.it/admin/3confini/prova_map.jpg")
            }
            val deferredImmagineOggiMare = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "m1"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiTemperaturaVersantePadano = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "t3"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiTemperaturaAppennino = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "t2"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiTemperaturaVersanteLigure = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "t1"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiVentoBassaPianura = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w4"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiVentoAppenninoLigurePiacentino = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w5"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiVentoPedemontana = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w3"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiVentoCostaLigure = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w2"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiVentoCrinale = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w1"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiValTrebbia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "bobbio"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiPianuraPiacentina = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "firenzuola"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiTerreVerdiane = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "castelnovo"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiBassaParmense = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "parma"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiAppenninoLigurePiacentino = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "ottone"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiValNure = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "morfasso"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiValTaro = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "borgotaro"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiPedemontanaParmense = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "langhirano"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiSpezzinoInterno = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "levanto"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiAltaValTaro = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "varese"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiCrinaleAppenninico = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "corniglio"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiAppenninoReggiano = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "cerreto"
                    )[0].select("img").attr("src")
                )
            }
            val deferredImmagineOggiCostaSpezzina = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "spezia"
                    )[0].select("img").attr("src")
                )
            }

            val immagineSfondo = deferredImmagineSfondo.await()
            val immagineOggiMare = deferredImmagineOggiMare.await()
            val immagineOggiTemperaturaVersantePadano = deferredImmagineOggiTemperaturaVersantePadano.await()
            val immagineOggiTemperaturaAppennino = deferredImmagineOggiTemperaturaAppennino.await()
            val immagineOggiTemperaturaVersanteLigure = deferredImmagineOggiTemperaturaVersanteLigure.await()
            val immagineOggiVentoBassaPianura = deferredImmagineOggiVentoBassaPianura.await()
            val immagineOggiVentoAppenninoLigurePiacentino = deferredImmagineOggiVentoAppenninoLigurePiacentino.await()
            val immagineOggiVentoPedemontana = deferredImmagineOggiVentoPedemontana.await()
            val immagineOggiVentoCostaLigure = deferredImmagineOggiVentoCostaLigure.await()
            val immagineOggiVentoCrinale = deferredImmagineOggiVentoCrinale.await()
            val immagineOggiValTrebbia = deferredImmagineOggiValTrebbia.await()
            val immagineOggiPianuraPiacentina = deferredImmagineOggiPianuraPiacentina.await()
            val immagineOggiTerreVerdiane = deferredImmagineOggiTerreVerdiane.await()
            val immagineOggiBassaParmense = deferredImmagineOggiBassaParmense.await()
            val immagineOggiAppenninoLigurePiacentino = deferredImmagineOggiAppenninoLigurePiacentino.await()
            val immagineOggiValNure = deferredImmagineOggiValNure.await()
            val immagineOggiValTaro = deferredImmagineOggiValTaro.await()
            val immagineOggiPedemontanaParmense = deferredImmagineOggiPedemontanaParmense.await()
            val immagineOggiSpezzinoInterno = deferredImmagineOggiSpezzinoInterno.await()
            val immagineOggiAltaValTaro = deferredImmagineOggiAltaValTaro.await()
            val immagineOggiCrinaleAppenninico = deferredImmagineOggiCrinaleAppenninico.await()
            val immagineOggiAppenninoReggiano = deferredImmagineOggiAppenninoReggiano.await()
            val immagineOggiCostaSpezzina = deferredImmagineOggiCostaSpezzina.await()

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
            val deferredImmagineDomaniMare = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "m1"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniTemperaturaVersantePadano = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "t3"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniTemperaturaAppennino = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "t2"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniTemperaturaVersanteLigure = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "t1"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniVentoBassaPianura = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w4"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniVentoAppenninoLigurePiacentino = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w5"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniVentoPedemontana = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w3"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniVentoCostaLigure = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w2"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniVentoCrinale = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w1"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniValTrebbia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "bobbio"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniPianuraPiacentina = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "firenzuola"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniTerreVerdiane = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "castelnovo"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniBassaParmense = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "parma"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniAppenninoLigurePiacentino = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "ottone"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniValNure = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "morfasso"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniValTaro = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "borgotaro"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniPedemontanaParmense = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "langhirano"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniSpezzinoInterno = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "levanto"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniAltaValTaro = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "varese"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniCrinaleAppenninico = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "corniglio"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniAppenninoReggiano = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "cerreto"
                    )[1].select("img").attr("src")
                )
            }
            val deferredImmagineDomaniCostaSpezzina = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "spezia"
                    )[1].select("img").attr("src")
                )
            }

            val immagineDomaniMare = deferredImmagineDomaniMare.await()
            val immagineDomaniTemperaturaVersantePadano = deferredImmagineDomaniTemperaturaVersantePadano.await()
            val immagineDomaniTemperaturaAppennino = deferredImmagineDomaniTemperaturaAppennino.await()
            val immagineDomaniTemperaturaVersanteLigure = deferredImmagineDomaniTemperaturaVersanteLigure.await()
            val immagineDomaniVentoBassaPianura = deferredImmagineDomaniVentoBassaPianura.await()
            val immagineDomaniVentoAppenninoLigurePiacentino = deferredImmagineDomaniVentoAppenninoLigurePiacentino.await()
            val immagineDomaniVentoPedemontana = deferredImmagineDomaniVentoPedemontana.await()
            val immagineDomaniVentoCostaLigure = deferredImmagineDomaniVentoCostaLigure.await()
            val immagineDomaniVentoCrinale = deferredImmagineDomaniVentoCrinale.await()
            val immagineDomaniValTrebbia = deferredImmagineDomaniValTrebbia.await()


            val immagineDomaniPianuraPiacentina = deferredImmagineDomaniPianuraPiacentina.await()
            val immagineDomaniTerreVerdiane = deferredImmagineDomaniTerreVerdiane.await()
            val immagineDomaniBassaParmense = deferredImmagineDomaniBassaParmense.await()
            val immagineDomaniValNure = deferredImmagineDomaniValNure.await()
            val immagineDomaniAppenninoLigurePiacentino = deferredImmagineDomaniAppenninoLigurePiacentino.await()
            val immagineDomaniValTaro = deferredImmagineDomaniValTaro.await()
            val immagineDomaniPedemontanaParmense = deferredImmagineDomaniPedemontanaParmense.await()
            val immagineDomaniAltaValTaro = deferredImmagineDomaniAltaValTaro.await()
            val immagineDomaniCrinaleAppenninico = deferredImmagineDomaniCrinaleAppenninico.await()
            val immagineDomaniSpezzinoInterno = deferredImmagineDomaniSpezzinoInterno.await()
            val immagineDomaniAppenninoReggiano = deferredImmagineDomaniAppenninoReggiano.await()
            val immagineDomaniCostaSpezzina = deferredImmagineDomaniCostaSpezzina.await()

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
            val deferredImmagineDopodomaniMare = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "m1"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniTemperaturaVersantePadano = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "t3"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniTemperaturaAppennino = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "t2"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniTemperaturaVersanteLigure = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "t1"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniVentoBassaPianura = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w4"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniVentoAppenninoLigurePiacentino = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w5"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniVentoPedemontana = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w3"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniVentoCostaLigure = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w2"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniVentoCrinale = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "w1"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniValTrebbia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "bobbio"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniPianuraPiacentina = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "firenzuola"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniTerreVerdiane = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "castelnovo"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniBassaParmense = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "parma"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniAppenninoLigurePiacentino = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "ottone"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniValNure = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "morfasso"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniValTaro = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "borgotaro"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniPedemontanaParmense = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "langhirano"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniSpezzinoInterno = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "levanto"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniAltaValTaro = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "varese"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniCrinaleAppenninico = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "corniglio"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniAppenninoReggiano = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "cerreto"
                    )[2].select("img").attr("src")
                )
            }
            val deferredImmagineDopodomaniCostaSpezzina = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.getElementsByAttributeValue(
                        "id",
                        "spezia"
                    )[2].select("img").attr("src")
                )
            }

            val immagineDopodomaniMare = deferredImmagineDopodomaniMare.await()
            val immagineDopodomaniTemperaturaVersantePadano = deferredImmagineDopodomaniTemperaturaVersantePadano.await()
            val immagineDopodomaniTemperaturaAppennino = deferredImmagineDopodomaniTemperaturaAppennino.await()
            val immagineDopodomaniTemperaturaVersanteLigure = deferredImmagineDopodomaniTemperaturaVersanteLigure.await()
            val immagineDopodomaniVentoBassaPianura = deferredImmagineDopodomaniVentoBassaPianura.await()
            val immagineDopodomaniVentoAppenninoLigurePiacentino = deferredImmagineDopodomaniVentoAppenninoLigurePiacentino.await()
            val immagineDopodomaniVentoPedemontana = deferredImmagineDopodomaniVentoPedemontana.await()
            val immagineDopodomaniVentoCostaLigure = deferredImmagineDopodomaniVentoCostaLigure.await()
            val immagineDopodomaniVentoCrinale = deferredImmagineDopodomaniVentoCrinale.await()
            val immagineDopodomaniValTrebbia = deferredImmagineDopodomaniValTrebbia.await()
            val immagineDopodomaniPianuraPiacentina = deferredImmagineDopodomaniPianuraPiacentina.await()
            val immagineDopodomaniTerreVerdiane = deferredImmagineDopodomaniTerreVerdiane.await()
            val immagineDopodomaniBassaParmense = deferredImmagineDopodomaniBassaParmense.await()
            val immagineDopodomaniAppenninoLigurePiacentino = deferredImmagineDopodomaniAppenninoLigurePiacentino.await()
            val immagineDopodomaniValNure = deferredImmagineDopodomaniValNure.await()
            val immagineDopodomaniValTaro = deferredImmagineDopodomaniValTaro.await()
            val immagineDopodomaniPedemontanaParmense = deferredImmagineDopodomaniPedemontanaParmense.await()
            val immagineDopodomaniSpezzinoInterno = deferredImmagineDopodomaniSpezzinoInterno.await()
            val immagineDopodomaniAltaValTaro = deferredImmagineDopodomaniAltaValTaro.await()
            val immagineDopodomaniCrinaleAppenninico = deferredImmagineDopodomaniCrinaleAppenninico.await()
            val immagineDopodomaniAppenninoReggiano = deferredImmagineDopodomaniAppenninoReggiano.await()
            val immagineDopodomaniCostaSpezzina = deferredImmagineDopodomaniCostaSpezzina.await()

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
            val deferredimmagineSfondo = async {
                getBitmapFromUrl("https://www.meteoapuane.it/img/montagna.jpg")
            }
            val deferredImmagineOggiZeri = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val deferredImmagineDomaniZeri = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val deferredImmagineDopodomaniZeri = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val deferredImmagineOggiCampocecina = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val deferredImmagineDomaniCampocecina = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val deferredImmagineDopodomaniCampocecina = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(4) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val deferredImmagineOggiPratospilla = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val deferredImmagineDomaniPratospilla = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val deferredImmagineDopodomaniPratospilla = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(3) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val deferredImmagineOggiCerreto = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val deferredImmagineDomaniCerreto = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val deferredImmagineDopodomaniCerreto = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2 > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(3) > strong:nth-child(1) > img:nth-child(2)")[0].select(
                        "img"
                    ).attr("src")
                )
            }
            val immagineSfondo = deferredimmagineSfondo.await()
            val immagineOggiZeri = deferredImmagineOggiZeri.await()
            val immagineDomaniZeri = deferredImmagineDomaniZeri.await()
            val immagineDopodomaniZeri = deferredImmagineDopodomaniZeri.await()
            val immagineOggiCampocecina = deferredImmagineOggiCampocecina.await()
            val immagineDomaniCampocecina = deferredImmagineDomaniCampocecina.await()
            val immagineDopodomaniCampocecina = deferredImmagineDopodomaniCampocecina.await()
            val immagineOggiPratospilla = deferredImmagineOggiPratospilla.await()
            val immagineDomaniPratospilla = deferredImmagineDomaniPratospilla.await()
            val immagineDopodomaniPratospilla = deferredImmagineDopodomaniPratospilla.await()
            val immagineOggiCerreto = deferredImmagineOggiCerreto.await()
            val immagineDomaniCerreto = deferredImmagineDomaniCerreto.await()
            val immagineDopodomaniCerreto = deferredImmagineDopodomaniCerreto.await()
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

            val document = Jsoup.connect("https://www.meteoapuane.it/montagna.php").timeout(10 * 1000).get()
            val documentPanel =
                Jsoup.connect("https://www.meteoapuane.it/ledpanel/prova.php").timeout(10 * 1000).get()
            val testoSegnalazione =
                documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > span:nth-child(1)")
                    .text()
            println("QUA INIZIA CARICAMENTO IMMAGINI")
//                val imgSegnalazioneGrande = getBitmapFromUrl("https://www.meteoapuane.it/"+documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > img:nth-child(2)").first()?.select("img")?.attr("src"))
            val deferredImgSegnalazionePiccola1 = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/ledpanel/" + documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(1) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgSegnalazionePiccola2 = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/ledpanel/" + documentPanel.select("body > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(1) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(1) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15LaspeziaVento = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15LaspeziaPioggia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(3) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15LaspeziaNebbia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(4) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15LaspeziaNeve = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(5) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15LaspeziaGhiaccio = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(6) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15SantostefanoVento = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15SantostefanoPioggia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(3) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15SantostefanoNebbia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(4) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15SantostefanoNeve = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(5) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15SantostefanoGhiaccio = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(6) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15AullaVento = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15AullaPioggia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15AullaNebbia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(4) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15AullaNeve = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(5) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15AullaGhiaccio = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(6) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15PontremoliVento = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15PontremoliPioggia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(3) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15PontremoliNebbia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(4) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15PontremoliNeve = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(5) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA15PontremoliGhiaccio = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(5) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(6) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12VersiliaVento = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12VersiliaPioggia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(3) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12VersiliaNebbia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(4) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12VersiliaNeve = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(5) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12VersiliaGhiaccio = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(3) > td:nth-child(6) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12MassaVento = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12MassaPioggia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(3) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12MassaNebbia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(4) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12MassaNeve = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(5) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12MassaGhiaccio = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(4) > td:nth-child(6) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12CarraraVento = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12CarraraPioggia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(3) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12CarraraNebbia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(4) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12CarraraNeve = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(5) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12CarraraGhiaccio = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(5) > td:nth-child(6) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12SarzanaVento = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(2) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12SarzanaPioggia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(3) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12SarzanaNebbia = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(4) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12SarzanaNeve = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(5) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            val deferredImgA12SarzanaGhiaccio = async {
                getBitmapFromUrl(
                    "https://www.meteoapuane.it/" + document.select("table.testo2:nth-child(7) > tbody:nth-child(1) > tr:nth-child(6) > td:nth-child(6) > img:nth-child(1)")
                        .first()?.select("img")?.attr("src")
                )
            }
            println("QUA COMINCIA AD ASPETTARE IL CARICAMENTO")
            val imgSegnalazionePiccola1 = deferredImgSegnalazionePiccola1.await()
            val imgSegnalazionePiccola2 = deferredImgSegnalazionePiccola2.await()
            val imgA15LaspeziaVento = deferredImgA15LaspeziaVento.await()
            val imgA15LaspeziaPioggia = deferredImgA15LaspeziaPioggia.await()
            val imgA15LaspeziaNebbia = deferredImgA15LaspeziaNebbia.await()
            val imgA15LaspeziaNeve = deferredImgA15LaspeziaNeve.await()
            val imgA15LaspeziaGhiaccio = deferredImgA15LaspeziaGhiaccio.await()
            val imgA15SantostefanoVento = deferredImgA15SantostefanoVento.await()
            val imgA15SantostefanoPioggia = deferredImgA15SantostefanoPioggia.await()
            val imgA15SantostefanoNebbia = deferredImgA15SantostefanoNebbia.await()
            val imgA15SantostefanoNeve = deferredImgA15SantostefanoNeve.await()
            val imgA15SantostefanoGhiaccio = deferredImgA15SantostefanoGhiaccio.await()
            val imgA15AullaVento = deferredImgA15AullaVento.await()
            val imgA15AullaPioggia = deferredImgA15AullaPioggia.await()
            val imgA15AullaNebbia = deferredImgA15AullaNebbia.await()
            val imgA15AullaNeve = deferredImgA15AullaNeve.await()
            val imgA15AullaGhiaccio = deferredImgA15AullaGhiaccio.await()
            val imgA15PontremoliVento = deferredImgA15PontremoliVento.await()
            val imgA15PontremoliPioggia = deferredImgA15PontremoliPioggia.await()
            val imgA15PontremoliNebbia = deferredImgA15PontremoliNebbia.await()
            val imgA15PontremoliNeve = deferredImgA15PontremoliNeve.await()
            val imgA15PontremoliGhiaccio = deferredImgA15PontremoliGhiaccio.await()
            val imgA12VersiliaVento = deferredImgA12VersiliaVento.await()
            val imgA12VersiliaPioggia = deferredImgA12VersiliaPioggia.await()
            val imgA12VersiliaNebbia = deferredImgA12VersiliaNebbia.await()
            val imgA12VersiliaNeve = deferredImgA12VersiliaNeve.await()
            val imgA12VersiliaGhiaccio = deferredImgA12VersiliaGhiaccio.await()
            val imgA12MassaVento = deferredImgA12MassaVento.await()
            val imgA12MassaPioggia = deferredImgA12MassaPioggia.await()
            val imgA12MassaNebbia = deferredImgA12MassaNebbia.await()
            val imgA12MassaNeve = deferredImgA12MassaNeve.await()
            val imgA12MassaGhiaccio = deferredImgA12MassaGhiaccio.await()
            val imgA12CarraraVento = deferredImgA12CarraraVento.await()
            val imgA12CarraraPioggia = deferredImgA12CarraraPioggia.await()
            val imgA12CarraraNebbia = deferredImgA12CarraraNebbia.await()
            val imgA12CarraraNeve = deferredImgA12CarraraNeve.await()
            val imgA12CarraraGhiaccio = deferredImgA12CarraraGhiaccio.await()
            val imgA12SarzanaVento = deferredImgA12SarzanaVento.await()
            val imgA12SarzanaPioggia = deferredImgA12SarzanaPioggia.await()
            val imgA12SarzanaNebbia = deferredImgA12SarzanaNebbia.await()
            val imgA12SarzanaNeve = deferredImgA12SarzanaNeve.await()
            val imgA12SarzanaGhiaccio = deferredImgA12SarzanaGhiaccio.await()
            println("QUA CI SIAMO E RITORNA UI")
            ViabilitaPage(
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
}