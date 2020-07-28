package com.example.traveljournal.Trip;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TripDao {

   // allowing the insert of the same word multiple times by passing a 
   // conflict resolution strategy
   @Insert(onConflict = OnConflictStrategy.IGNORE)
   void insert(Trip trip);

   @Query("DELETE FROM trip_table")
   void deleteAll();

   @Query("SELECT * from trip_table ORDER BY name ASC")
   LiveData<List<Trip>> getAlphabetizedWords();
}