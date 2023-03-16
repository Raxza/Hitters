package com.raxza.hitters.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "menu")
data class Menu (

        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,

        @field:ColumnInfo(name = "name")
        val name: String
) : Parcelable
