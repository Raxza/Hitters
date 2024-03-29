package com.raxza.hitters.data

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "menu")
data class Menu (

        @PrimaryKey(autoGenerate = true)
        val menuId: Int = 0,

        @field:ColumnInfo(name = "name")
        val name: String
) : Parcelable

@Entity(tableName = "sets")
data class Sets (

        @PrimaryKey(autoGenerate = true)
        val setId: Int = 0,

        @field:ColumnInfo(name = "menuSetId")
        val menuSetId: Int,

        @field:ColumnInfo(name = "name")
        val name: String,

        @field:ColumnInfo(name = "weight")
        val weight: String,

        @field:ColumnInfo(name = "rep")
        val rep: String,

        @field:ColumnInfo(name = "set")
        val set: String
)

data class MenuAndSet(
        @Embedded
        val menu: Menu,

        @Relation(
                parentColumn = "menuId",
                entityColumn = "menuSetId"
        )
        val set: List<Sets>
)
