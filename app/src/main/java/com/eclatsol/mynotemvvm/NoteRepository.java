package com.eclatsol.mynotemvvm;

import static com.eclatsol.mynotemvvm.BackgroundTask.backgroundTask;

import android.app.Application;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    //Jyare abstract class hoi tyare objct create karvani jarur nathi
    //Class na name thi variable ,function access kari shako shavo
    //Application context ni sub class hoi che
    //Repository class ni andar apne badha function  bind karvi shavi

    private NoteDao noteDao;
    private LiveData<List<Note>> listLiveData;

    public NoteRepository(Application application) {
        noteDao = NoteDataBase.getInstance(application).getNoteDao();
        listLiveData = noteDao.getAllData();
    }

    public void insertData(Note note) {
        backgroundTask(noteDao,note,1);
    }

    public void updateData(Note note) {
        backgroundTask(noteDao,note,2);
    }

    public void deleteData(Note note) {
        backgroundTask(noteDao,note,3);
    }

    public LiveData<List<Note>> getListLiveData() {
        return listLiveData;
    }

}
