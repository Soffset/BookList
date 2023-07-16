package com.example.booklist

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


object ApiClient {
    private lateinit var queue : RequestQueue

    fun init(context: Context) {
        queue = Volley.newRequestQueue(context)
    }

    private fun clearQueue() {
        queue.cancelAll { true }
    }

    fun sendApiRequest(url: String, onSuccess: (String) -> Unit = {}, onFail: (String) -> Unit = {}) {
        if (url.isEmpty()) {
            return
        }
        if (!::queue.isInitialized) {
            throw Exception("ApiClient is not initialized")
        }
        if (queue.sequenceNumber > 0) {
            clearQueue()
            Log.d("APIREQUEST", "Cleared queue")
        }

        val jsonRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                run {
                    onSuccess(response)
                }
            },
            { error ->
                run {
                    onFail(error.toString())
                }
            })

        // Add the request to the RequestQueue.
        queue.add(jsonRequest)

    }
}