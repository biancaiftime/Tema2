package com.example.tema2.DataBase;

import com.example.tema2.DAO.UserDAO;
import com.example.tema2.Models.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
}
