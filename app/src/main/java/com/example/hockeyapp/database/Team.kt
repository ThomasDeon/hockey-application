package com.example.hockeyapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teams")
data class Team(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "club_name") val clubName: String,
    @ColumnInfo(name = "club_contact_person") val clubContactPerson: String,
    @ColumnInfo(name = "contact_person_cell") val contactPersonCell: String,
    @ColumnInfo(name = "email") val email: String,

    @ColumnInfo(name = "nominated_umpire_name") val nominatedUmpireName: String,
    @ColumnInfo(name = "nominated_umpire_contact") val nominatedUmpireContact: String,
    @ColumnInfo(name = "nominated_umpire_email") val nominatedUmpireEmail: String,

    @ColumnInfo(name = "nominated_technical_official") val nominatedTechnicalOfficial: String,
    @ColumnInfo(name = "technical_official_contact") val technicalOfficialContact: String,
    @ColumnInfo(name = "technical_official_email") val technicalOfficialEmail: String,
    @ColumnInfo(name = "leagues") val leagues: String
)
