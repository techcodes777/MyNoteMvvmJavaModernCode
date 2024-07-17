package com.eclatsol.mynotemvvm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_notes")
public class Note {
    //Room ma je database bane che direct te note class name thi banshe khali

    //Getter-Setter and Constructor ma apne id ne nai leshu
    private String title;
    private String description;

    @PrimaryKey(autoGenerate = true)
    private int id;

    //id ne set automatically room kari deshe mare khali get jotu che


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
