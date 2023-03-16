package com.raxza.hitters.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "set")
data class Set(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @field:ColumnInfo(name = "name")
    val name: String,

    @field:ColumnInfo(name = "weight")
    val weight: Float,

    @field:ColumnInfo(name = "rep")
    val rep: Int,

    @field:ColumnInfo(name = "set")
    val set: Int
) : Parcelable

