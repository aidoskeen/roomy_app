package com.aidos.roomy_app.data.remote_data_source

import android.util.Log
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class HostConnection {

    fun sendGetRequest(requestUrl: String): String {
        val url = URL(requestUrl)
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        try {
            val code: Int = httpURLConnection.responseCode
            val responseMessage = httpURLConnection.responseMessage
            Log.d(this.javaClass.name, "Response : $responseMessage")

            if (code == HttpURLConnection.HTTP_OK) {
                return getResponseFromInputStream(InputStreamReader(httpURLConnection.inputStream))
            } else {
                Log.e(tag,"Connection error")
                return ""
            }
        } catch (ex: Exception) {
            Log.e(tag, ex.toString())
            return ""
        }
    }

    private fun getResponseFromInputStream(reader: InputStreamReader): String {
        val bufferedReader = BufferedReader(reader)
        val response = StringBuffer()

        bufferedReader.forEachLine { line: String? ->
            if (line != null)
                response.append(line)
        }

        bufferedReader.close()
        return response.toString()
    }

    fun sendPost(urlPost: String?, postDataParams: String?): String {
        val url = URL(urlPost)
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        println(postDataParams)
        httpURLConnection.requestMethod = "POST"

        val outputStream = httpURLConnection.getOutputStream()
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
        bufferedWriter.write(postDataParams)
        bufferedWriter.close()
        outputStream!!.close()
        val code: Int = httpURLConnection.responseCode
        Log.d("HostConnection","Response code: $code")
        return if (code == HttpURLConnection.HTTP_OK) {
            val `in` = BufferedReader(InputStreamReader(httpURLConnection.getInputStream()))
            var line: String?
            val response = StringBuffer()
            while (`in`.readLine().also { line = it } != null) {
                response.append(line)
                break
            }
            `in`.close()
            response.toString()
        } else {
            "Error"
        }
    }

    fun sendPut(urlPost: String?, postDataParams: String?): String {
        val url = URL(urlPost)
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.setRequestProperty("Accept", "application/json")
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        httpURLConnection.requestMethod = "PUT"
        httpURLConnection.doInput = true
        httpURLConnection.doOutput = false
        println(postDataParams)
        val outputStream = httpURLConnection.outputStream
        val bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
        bufferedWriter.write(postDataParams)
        bufferedWriter.close()
        outputStream.flush()
        val code: Int = httpURLConnection.responseCode
        println("Response code: $code")
        return if (code == HttpURLConnection.HTTP_OK) {
            getResponseFromInputStream(InputStreamReader(httpURLConnection.inputStream))
        } else {
            "Error"
        }
    }

    @Throws(IOException::class)
    fun sendDelete(urlDelete: String?): String? { //http://192.168.1.225:8080/PM/sers/getUser/1
        val url = URL(urlDelete)
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "DELETE"
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        val code: Int = httpURLConnection.getResponseCode()
        println("Response code: $code")
        return if (code == HttpURLConnection.HTTP_OK) {
            val `in` = BufferedReader(InputStreamReader(httpURLConnection.getInputStream()))
            var line: String?
            val response = StringBuffer()
            while (`in`.readLine().also { line = it } != null) {
                response.append(line)
                break
            }
            `in`.close()
            response.toString()
        } else {
            ""
        }
    }

    private fun setConnectionParameters(httpURLConnection: HttpURLConnection, type: String) {
        httpURLConnection.setRequestMethod(type)
        httpURLConnection.setReadTimeout(20000)
        httpURLConnection.setConnectTimeout(20000)
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        httpURLConnection.setRequestProperty("Accept", "application/json")
        httpURLConnection.setDoInput(true)
        httpURLConnection.setDoOutput(true)
    }

    companion object {
        val tag = Companion::class.java.name
    }
}