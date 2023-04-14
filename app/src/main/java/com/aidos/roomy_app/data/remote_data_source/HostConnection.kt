package com.aidos.roomy_app.data.remote_data_source

import java.io.*
import java.net.HttpURLConnection
import java.net.URL

class HostConnection {
    private var outputStream: OutputStream? = null
    private var bufferedWriter: BufferedWriter? = null

    fun sendGetRequest(requestUrl: String): String {
        val url = URL(requestUrl)
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.requestMethod = "GET"
        httpURLConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        val code: Int = httpURLConnection.responseCode
        val responseMessage = httpURLConnection.responseMessage
        println("Response : $responseMessage")
        return if (code == HttpURLConnection.HTTP_OK) {
            val bufferedReader = BufferedReader(InputStreamReader(httpURLConnection.inputStream))
            val response = StringBuffer()
            if (bufferedReader.readLine() != null)
                bufferedReader.forEachLine { line ->
                    response.append(line)
                }
            else println("Response has empty body")
            bufferedReader.close()
            response.toString()
        } else ""
    }

    fun sendPost(urlPost: String?, postDataParams: String?): String? {
        val url = URL(urlPost)
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        println(postDataParams)

        setConnectionParameters(httpURLConnection, "POST")
        outputStream = httpURLConnection.getOutputStream()
        bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
        bufferedWriter.write(postDataParams)
        bufferedWriter.close()
        outputStream!!.close()
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
            "Error"
        }
    }

    @Throws(IOException::class)
    fun sendPut(urlPost: String?, postDataParams: String?): String {
        val url = URL(urlPost)
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        println(postDataParams)
        //Reikia papildomu connectiono nustatymu, jie bus bendri su Put metodu
        setConnectionParameters(httpURLConnection, "PUT")
        outputStream = httpURLConnection.getOutputStream()
        bufferedWriter = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
        bufferedWriter.write(postDataParams)
        //? bufferedWriter.flush();
        bufferedWriter.close()
        outputStream!!.close()
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
            "Error"
        }
    }

    @Throws(IOException::class)
    fun sendDelete(urlDelete: String?): String? { //http://192.168.1.225:8080/PM/sers/getUser/1
        val url = URL(urlDelete)
        val httpURLConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        httpURLConnection.setRequestMethod("DELETE")
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
}