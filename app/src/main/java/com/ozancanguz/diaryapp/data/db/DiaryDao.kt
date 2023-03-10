package com.ozancanguz.diaryapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ozancanguz.diaryapp.data.model.Diary

@Dao
interface DiaryDao {


    // get all diary from db
    @Query("select * from diary_table order by id Asc")
     fun getAllData(): LiveData<List<Diary>>


    // insert diary
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(diary: Diary)

    // update
    @Update
    suspend fun updateData(diary: Diary)

    // delete single item
    @Delete
    suspend fun deleteSingleItem(diary: Diary)

    @Query("select * from diary_table where title like  :searchQuery")
    fun searchDatabase(searchQuery:String):LiveData<List<Diary>>



}