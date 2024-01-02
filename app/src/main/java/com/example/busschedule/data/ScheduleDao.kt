package com.example.busschedule.data

import android.media.RouteListingPreference
import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {

    @Query("SELECT * from Schedule ORDER BY arrival_time ASC")
    fun getAllItems(): Flow<List<BusSchedule>>

    @Query("SELECT * from Schedule WHERE stop_name = :busStop ORDER BY arrival_time ASC")
    fun getItem(busStop: String): Flow<List<BusSchedule>>

}