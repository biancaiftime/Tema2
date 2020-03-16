package com.example.tema2.Models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name="FullName")
    public String fullName;

    @ColumnInfo(name = "Mark")
    public float mark;

    public User(String fullName, float mark)
    {
        this.fullName = fullName;
        this.mark = mark;
    }

}
