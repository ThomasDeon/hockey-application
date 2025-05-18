package com.example.hockeyapp.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coaches")
data class Coach(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "first_name") val firstName: String,
    @ColumnInfo(name = "last_name") val lastName: String,
    @ColumnInfo(name = "contact_number") val contactNumber: String,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "region") val region: String,
    @ColumnInfo(name = "city_or_town") val cityOrTown: String,
    @ColumnInfo(name = "associated_club") val associatedClub: String,
    @ColumnInfo(name = "years_in_coaching") val yearsInCoaching: Int,
    @ColumnInfo(name = "highest_qualification") val highestQualification: String
)
