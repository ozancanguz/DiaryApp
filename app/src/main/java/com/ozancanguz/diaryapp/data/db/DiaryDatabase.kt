package com.ozancanguz.diaryapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ozancanguz.diaryapp.data.model.Diary

@Database(entities = [Diary::class], version = 1, exportSchema = true)
abstract class DiaryDatabase:RoomDatabase(){

    // get reference for dao
    abstract fun diaryDao():DiaryDao

    companion object{
        @Volatile
        private var INSTANCE: DiaryDatabase? = null


        fun getDatabase(context: Context): DiaryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DiaryDatabase::class.java,
                    "Diary_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }





}