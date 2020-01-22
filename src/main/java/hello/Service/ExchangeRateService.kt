package hello.Service

import hello.Service.Model.ExchangeRate
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.*

class ExchangeRateService {
    val ratesViaOkHttp: List<ExchangeRate>
        get() {
            val result: List<ExchangeRate> = ArrayList()
            val url = "https://api.exchangeratesapi.io/latest"
            val client = OkHttpClient()
            val request = Request.Builder()
                    .url(url)
                    .build()
            try {
                client.newCall(request).execute().use { response -> return jsonToModels(response.body!!.string()) }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return result
        }

    val ratesViaURLConnection: List<ExchangeRate>
        get() {
            val result: List<ExchangeRate> = ArrayList()
            try {
                val response = StringBuilder()
                val url = URL("https://api.exchangeratesapi.io/latest")
                val con = url.openConnection() as HttpURLConnection
                val reader = BufferedReader(InputStreamReader(con.inputStream))
                var readLine: String?
                while (reader.readLine().also { readLine = it } != null) {
                    response.append(readLine)
                }
                reader.close()
                return jsonToModels(response.toString())
            } catch (e: Exception) {
                println(e)
            }
            return result
        }

    private fun jsonToModels(response: String): List<ExchangeRate> {
        val result: MutableList<ExchangeRate> = ArrayList()
        val json = JSONObject(response)
        val rates = json.getJSONObject("rates")
        for (key in rates.keySet()) {
            val currency = key as String
            val ratio = rates[currency] as Double
            val rate = ExchangeRate()
            rate.currency = currency
            rate.rate = ratio
            result.add(rate)
        }
        return result
    }
}