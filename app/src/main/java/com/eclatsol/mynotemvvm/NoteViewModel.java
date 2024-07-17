package com.eclatsol.mynotemvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    public NoteRepository noteRepository;
    public LiveData<List<Note>> listLiveData;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        listLiveData = noteRepository.getListLiveData();
    }

    public void insert(Note note) {
        noteRepository.insertData(note);
    }

    public void update(Note note) {
        noteRepository.updateData(note);
    }

    public void delete(Note note) {
        noteRepository.deleteData(note);
    }

    public LiveData<List<Note>> getListLiveData() {
        return listLiveData;
    }

}
