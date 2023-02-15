package com.ozancanguz.diaryapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ozancanguz.diaryapp.data.model.Diary

@Database(entities = [Diary::class], version = 1,
    exportSchema = false,)

@TypeConverters(TypeConverter::class)
abstract class DiaryDatabase:RoomDatabase(){
    abstract fun diaryDao():DiaryDao
}
