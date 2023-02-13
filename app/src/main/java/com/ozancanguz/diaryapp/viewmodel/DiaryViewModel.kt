package com.ozancanguz.diaryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ozancanguz.diaryapp.data.db.DiaryDatabase
import com.ozancanguz.diaryapp.data.model.Diary
import com.ozancanguz.diaryapp.data.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiaryViewModel(application: Application):AndroidViewModel(application) {

    // get dao ref
    val diaryDao=DiaryDatabase.getDatabase(application).diaryDao()

    //get repository ref
    private val repository = Repository(diaryDao)


    //get all data list
    val getAllData: LiveData<List<Diary>>

    // init get all data
    init {
        getAllData=repository.getAllData
    }

    // inserting data with background thread
    fun insertData(diary: Diary){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertData(diary)
        }
    }



}