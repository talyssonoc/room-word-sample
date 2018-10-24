package com.example.c_tcassiano.roomwordsample

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

class WordViewModel(application: Application): AndroidViewModel(application) {
  private val repository = WordRepository(application)
  val allWords = repository.getAllWorlds()

  suspend fun insert(word: Word) {
    repository.insert(word)
  }
}