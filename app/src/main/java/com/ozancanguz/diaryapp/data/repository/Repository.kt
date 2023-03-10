package com.ozancanguz.diaryapp.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ozancanguz.diaryapp.data.db.DiaryDao
import com.ozancanguz.diaryapp.data.model.Diary
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class Repository@Inject constructor(private val diaryDao: DiaryDao) {


    // get all data from dao
    val getAllData:LiveData<List<Diary>> =diaryDao.getAllData()

    // insert data to repository
    suspend fun insertData(diary: Diary){
        return diaryDao.insertData(diary)
    }

    // update data
    suspend fun updateData(diary: Diary){
        return diaryDao.updateData(diary)
    }

    // delete single item
    suspend fun deleteSingleItem(diary: Diary){
        return diaryDao.deleteSingleItem(diary)
    }


    // search data
    fun searchQuery(searchQuery:String):LiveData<List<Diary>>{
        return diaryDao.searchDatabase(searchQuery)
    }


}