package com.ozancanguz.diaryapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ozancanguz.diaryapp.data.db.DiaryDatabase
import com.ozancanguz.diaryapp.data.model.Diary
import com.ozancanguz.diaryapp.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel@Inject constructor(private val repository: Repository,application: Application):AndroidViewModel(application) {


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

    // update data
    fun updateData(diary: Diary){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateData(diary)
        }
    }

    // delete single item
    fun deleteSingleItem(diary: Diary){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteSingleItem(diary)
        }
    }

    // search database with query
    fun searchDatabase(searchQuery:String):LiveData<List<Diary>>{

        return repository.searchQuery(searchQuery)

    }

}