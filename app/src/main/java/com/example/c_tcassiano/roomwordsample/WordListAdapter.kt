package com.example.c_tcassiano.roomwordsample

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class WordListAdapter(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
  inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val wordItemView = itemView.findViewById<TextView>(R.id.textView)!!

    var word: String = "No word"
      set(word) {
        wordItemView.text = word
      }
  }

  private val inflater = LayoutInflater.from(context)

  var words: List<Word>? = null
    set(words) {
      field = words
      notifyDataSetChanged()
    }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
    val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
    return WordViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
    words?.let {
      val current = it[position]
      holder.word = current.word
      return
    }

    holder.word = "No word"
  }

  override fun getItemCount(): Int {
    words?.let {
      return it.size
    }

    return 0
  }

}