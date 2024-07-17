package com.eclatsol.mynotemvvm;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BackgroundTask {

    public static void backgroundTask(NoteDao noteDao,Note note,int type){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //Do Background work here
                if (type == 1){
                    noteDao.insert(note);
                } else if (type == 2) {
                    noteDao.update(note);
                }else {
                    noteDao.delete(note);
                }
            }
        });

        handler.post(new Runnable() {
            @Override
            public void run() {
                //Do UI Thread work here
            }
        });
    }
}
