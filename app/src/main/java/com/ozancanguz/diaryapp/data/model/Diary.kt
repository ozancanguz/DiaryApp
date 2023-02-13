package com.ozancanguz.diaryapp.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName ="Diary_table")
data class Diary(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    var title:String,
    var description:String

):Parcelable {



}