package com.example.data.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ActivityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(activityStudent: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg activityStudents: User)

    @Update
    suspend fun updateActivityStudent(activityStudent: User)

    @Query("Select * from  User")
    suspend fun getAllActivityStudent(): Flow<List<User>>


}