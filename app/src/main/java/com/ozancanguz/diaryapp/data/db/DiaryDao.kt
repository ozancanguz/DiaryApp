package com.ozancanguz.diaryapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ozancanguz.diaryapp.data.model.Diary

@Dao
interface DiaryDao {


    // get all diary from db
    @Query("select * from diary_table order by id Asc")
     fun getAllData(): LiveData<List<Diary>>


    // insert diary
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(diary: Diary)

}