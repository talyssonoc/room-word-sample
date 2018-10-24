package com.example.c_tcassiano.roomwordsample

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.content_main.recyclerview
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.*
import kotlin.coroutines.experimental.CoroutineContext

class MainActivity : AppCompatActivity() {
  companion object {
    const val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
  }

  private lateinit var wordViewModel: WordViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    setSupportActionBar(toolbar)

    val adapter = WordListAdapter(this)
    recyclerview.adapter = adapter
    recyclerview.layoutManager = LinearLayoutManager(this)

    wordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
    wordViewModel.allWords.observe(this, Observer { words ->
      adapter.words = words
    })

    fab.setOnClickListener { _ ->
      val intent = Intent(this, NewWordActivity::class.java)
      startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    // Inflate the menu; this adds items to the action bar if it is present.
    menuInflater.inflate(R.menu.menu_main, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    return when (item.itemId) {
      R.id.action_settings -> true
      else -> super.onOptionsItemSelected(item)
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)

    if(requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
      val word = Word(data!!.getStringExtra(NewWordActivity.EXTRA_REPLY))

      GlobalScope.launch {
        wordViewModel.insert(word)
      }

    } else {
      Toast.makeText(applicationContext,
        R.string.empty_not_saved,
        Toast.LENGTH_LONG
      ).show()
    }
  }
}
