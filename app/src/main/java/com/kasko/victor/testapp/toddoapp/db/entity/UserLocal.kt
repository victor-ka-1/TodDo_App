package com.kasko.victor.testapp.toddoapp.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "users")
data class UserLocal (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id : Int,
    val name : String,
    val username : String,
    val email :String,
    @Embedded(prefix = "address")
    val address: AddressLocal,
    val phone :String,
    val website : String,
    @Embedded(prefix = "company")
    val company : CompanyLocal
) :Serializable
