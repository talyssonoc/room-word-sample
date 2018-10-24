package com.example.c_tcassiano.roomwordsample

import android.app.Application
import android.arch.lifecycle.LiveData
import kotlinx.coroutines.experimental.coroutineScope

class WordRepository(application: Application) {
  private var wordDao: WordDao
  private var allWords: LiveData<List<Word>>

  init {
    val db = WordRoomDatabase.getDatabase(application)
    wordDao = db.wordDao()
    allWords = wordDao.getAllWords()
  }

  fun getAllWorlds(): LiveData<List<Word>> {
    return allWords
  }

  suspend fun insert(word: Word) = coroutineScope {
    wordDao.insert(word)
  }
}