package com.example.busschedule.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BusSchedule::class], version = 1, exportSchema = false)
abstract class ScheduleDatabase : RoomDatabase() {

    abstract fun itemDao(): ScheduleDao

    companion object {
        @Volatile
        private var Instance: ScheduleDatabase? = null

        fun getDatabase(context: Context): ScheduleDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, ScheduleDatabase::class.java, "item_database")
                    .createFromAsset("database/bus_schedule.db")
                    // Wipes and rebuilds instead of migrating if no Migration object.
                    // Migration is not part of this codelab.
                    .fallbackToDestructiveMigration()
                    .build()
                    .also {
                        Instance = it
                    }
            }
        }
//        fun getDatabase(context: Context): AppDatabase {
//            return INSTANCE ?: synchronized(this) {
//                Room.databaseBuilder(
//                    context,
//                    AppDatabase::class.java,
//                    "app_database"
//                )
//                    .createFromAsset("database/bus_schedule.db")
//                    // Wipes and rebuilds instead of migrating if no Migration object.
//                    // Migration is not part of this codelab.
//                    .fallbackToDestructiveMigration()
//                    .build()
//                    .also {
//                        INSTANCE = it
//                    }
//            }
//        }
    }
}