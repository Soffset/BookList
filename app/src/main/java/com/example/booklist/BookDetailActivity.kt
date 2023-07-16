package com.example.booklist

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BookDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val bookName = intent.getStringExtra("book_title")
        val bookTitleView = findViewById<TextView>(R.id.book_title_text)

        bookTitleView.text = bookName
    }
}