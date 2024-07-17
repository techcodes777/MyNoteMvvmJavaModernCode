package com.eclatsol.mynotemvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    //Dao interface hoi che ka to abstract hoi che

    @Insert
    public void insert(Note note);

    @Update
    public void update(Note note);

    @Delete
    public void delete(Note note);

    @Query("SELECT * FROM my_notes")
    public LiveData<List<Note>> getAllData();


    //live data basically linklist ni jem thay gayu
    //livedata male che room database ni sathe
}
