package com.example.booklist

data class BookApiResponse (
    val start: Int,
    val num_found: Int,
    val docs: List<BookDoc>
)

data class BookDoc (
    val cover_i: Int,
    val has_fulltext: Boolean,
    val edition_count: Int,
    val title: String,
    val author_name: List<String>,
    val first_publish_year: Int,
    val key: String,
    val ia: List<String>,
    val author_key: List<String>,
    val public_scan_b: Boolean,
)