package com.example.booklist.BooksRecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booklist.BookDoc
import com.example.booklist.R


class BooksRecyclerAdapter(private val dataSet: List<BookDoc>, private val onItemClick: (BookDoc) -> Unit = {}) :
    RecyclerView.Adapter<BooksRecyclerAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val bookTitleTextView: TextView

        init {
            // Define click listener for the ViewHolder's View
            bookTitleTextView = view.findViewById(R.id.book_title)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.books_recycler_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.setOnClickListener {
            onItemClick(dataSet[position])
        }
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bookTitleTextView.text = dataSet[position].title
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

