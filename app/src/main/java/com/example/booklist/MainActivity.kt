package com.example.booklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.net.toUri
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.booklist.BooksRecyclerView.BooksRecyclerAdapter
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiClient.init(this)

        val searchTextInput = findViewById<EditText>(R.id.search_input)
        val booksRecyclerView = findViewById<RecyclerView>(R.id.books_list)

        booksRecyclerView.layoutManager = LinearLayoutManager(this)
        booksRecyclerView.adapter = BooksRecyclerAdapter(listOf())

        searchTextInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId != EditorInfo.IME_ACTION_SEARCH) {
                return@setOnEditorActionListener false
            }
            fetchBook(
                name = searchTextInput.text.toString(),
                onSuccess = { response ->
                    run {
                        Log.d("APIREQUESTSUCCES", response.toString())
                        booksRecyclerView.adapter = BooksRecyclerAdapter(response.docs.subList(0, 10))
                    }
                },
                onFail = { error ->
                    run {
                        Log.d("APIREQUESTERROR", error)
                    }
                }
            )

            true
        }
    }

    private fun fetchBook(name: String, onSuccess: (BookApiResponse) -> Unit = {}, onFail: (String) -> Unit = {}) {
        val url = "https://openlibrary.org/search.json?q=${name.replace(" ", "+")})}"


        ApiClient.sendApiRequest(url,
            onSuccess = { response ->
                run {
                    try {
                        val responseJson: BookApiResponse =
                            Gson().fromJson(response, BookApiResponse::class.java) ?: throw Exception("Response is null")
                        onSuccess(responseJson)
                    } catch (e: Exception) {
                        onFail(e.toString())
                    }

                }
            },
            onFail = { error ->
                run {
                    onFail(error)
                }
            }
        )
    }
}