package com.example.c_tcassiano.roomwordsample

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import kotlinx.android.synthetic.main.activity_new_word.*

class NewWordActivity : AppCompatActivity() {
  companion object {
    const val EXTRA_REPLY = "com.example.c_tcassiano.roomwordsample.REPLY"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_new_word)

    buttonSave.setOnClickListener { _ ->
      val replyIntent = Intent()
      if (TextUtils.isEmpty(editWord.text)) {
        setResult(Activity.RESULT_CANCELED, replyIntent)
      } else {
        val word = editWord.text.toString()
        replyIntent.putExtra(EXTRA_REPLY, word)
        setResult(Activity.RESULT_OK, replyIntent)
      }

      finish()
    }
  }
}
