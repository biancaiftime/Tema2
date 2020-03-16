package com.example.tema2.Repositories;

import android.content.Context;
import android.os.AsyncTask;

import com.example.tema2.Controllers.ApplicationController;
import com.example.tema2.DataBase.AppDatabase;
import com.example.tema2.Models.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserRepository {

    private AppDatabase appDatabase;

    public UserRepository(Context context)
    {
        appDatabase = ApplicationController.getAppDatabase();
    }

    public void insertTask(final User user, final OnUserRepositoryActionListener listener)
    {
        new InsertTask(listener).execute(user);
    }
    public void deleteTask(final User user, final OnUserRepositoryActionListener listener)
    {
        new DeleteTask(listener).execute(user);
    }

    public List<User> getAll(final OnUserRepositoryActionListener listener){
        try {
            return new GetAllTask(listener).execute((Void[]) null).get();
        }
         catch (ExecutionException e) {
            e.printStackTrace();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    public User findByNameTask(final String name, final OnUserRepositoryActionListener listener){
        try {
            return new GetAllByNameTask(listener).execute(name).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    //public void deleteTask(final User user);

    private class InsertTask extends AsyncTask<User, Void, Void>{
        OnUserRepositoryActionListener listener;

        InsertTask(OnUserRepositoryActionListener listener)
        {
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(User... users) {
            appDatabase.userDAO().insertAll((users));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }
    private class GetAllTask extends AsyncTask<Void, Void, List<User>> {
        OnUserRepositoryActionListener listener;

        GetAllTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<User> doInBackground(Void... voids) {
            return appDatabase.userDAO().getAll();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            listener.actionSuccess();
        }
    }

    private class DeleteTask extends AsyncTask<User, Void, Void>{
        OnUserRepositoryActionListener listener;

        DeleteTask(OnUserRepositoryActionListener listener)
        {
            this.listener = listener;
        }

        @Override
        protected Void doInBackground(User... users) {
            appDatabase.userDAO().delete(users[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
            listener.actionSuccess();
        }
    }

    private class GetAllByNameTask extends AsyncTask<String, Void, User> {
        OnUserRepositoryActionListener listener;

        GetAllByNameTask(OnUserRepositoryActionListener listener) {
            this.listener = listener;
        }

        @Override
        protected User doInBackground(String... names) {
            return appDatabase.userDAO().findByName(names[0]);
        }

        @Override
        protected void onPostExecute(User users) {
            super.onPostExecute(users);
            listener.actionSuccess();
        }
    }
}



