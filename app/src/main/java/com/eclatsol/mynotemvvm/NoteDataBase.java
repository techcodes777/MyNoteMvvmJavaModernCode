package com.eclatsol.mynotemvvm;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Note.class, version = 1, exportSchema = false)
public abstract class NoteDataBase extends RoomDatabase {

    //Room database ne abstract banavo jaruri che
    //abstract ma apne data object banavishu
    //static atle banavu shavu varm var object banavani jarur no pade //Vadhare Memory consume no kare
//synchronized architecture no component che
    //synchronized basically main thread ne block nathi kartu
    //app tamari single thread per chale che , multiple thread per panchale che
    //synchronized block no use karvi shavi main thread per kam kare che

    public abstract NoteDao getNoteDao();
    private static NoteDataBase INTANCE;

    public static synchronized NoteDataBase getInstance(Context context) {
        if (INTANCE == null) {
            INTANCE = Room.databaseBuilder(context.getApplicationContext(), NoteDataBase.class, "NoteDatabase").fallbackToDestructiveMigration().build();
        }
        return INTANCE;
    }
}
