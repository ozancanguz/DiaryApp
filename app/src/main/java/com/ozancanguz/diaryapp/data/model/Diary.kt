package com.ozancanguz.diaryapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName ="Diary_table")
data class Diary(
    var title:String,
    var description:String

) {

    @PrimaryKey(autoGenerate = true)
    var id:Int=0


}