package com.ozancanguz.diaryapp.data.repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.ozancanguz.diaryapp.data.db.DiaryDao
import com.ozancanguz.diaryapp.data.model.Diary

class Repository(private val diaryDao: DiaryDao) {


    // get all data from dao
    val getAllData:LiveData<List<Diary>> =diaryDao.getAllData()

    // insert data to repository
    suspend fun insertData(diary: Diary){
        return diaryDao.insertData(diary)
    }



}