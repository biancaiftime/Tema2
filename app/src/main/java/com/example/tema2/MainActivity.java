package com.example.tema2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema2.Adapters.ListOfUsersAdapter;
import com.example.tema2.Models.User;
import com.example.tema2.Repositories.OnUserRepositoryListener;
import com.example.tema2.Repositories.UserRepository;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends Activity {

    private RecyclerView ListOfUsers;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private UserRepository userRepository;
    private EditText fullName;
    private EditText mark;
    private Button addUser;
    private Button deleteUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListOfUsers =  findViewById(R.id.list_of_users);
        ListOfUsers.setHasFixedSize(true);
        userRepository = new UserRepository(getApplicationContext());

        layoutManager = new LinearLayoutManager(this);
        ListOfUsers.setLayoutManager((layoutManager));

        List<User> users = userRepository.getAll(new OnUserRepositoryListener());
        adapter= new ListOfUsersAdapter( users);
        ListOfUsers.setAdapter(adapter);


        fullName = findViewById(R.id.FullName);
        mark = findViewById(R.id.Mark);
        addUser = findViewById(R.id.ButtonAddUser);
        addUser.setOnClickListener(new OnClickListenerAddUser());

        deleteUser = findViewById(R.id.ButtonDeleteUser);
        deleteUser.setOnClickListener(new OnClickListenerDeleteUser());
    }

    private class OnClickListenerAddUser implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            User user = new User(fullName.getText().toString(), new Float(mark.getText().toString()));
            userRepository.insertTask(user, new OnUserRepositoryListener());
            ((ListOfUsersAdapter)adapter).addItem(user);
        }
    }

    private class OnClickListenerDeleteUser implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            User user = userRepository.findByNameTask(fullName.getText().toString(), new OnUserRepositoryListener());
            if(user == null) {
                Toast toast = Toast.makeText(getApplicationContext(), "Couldn't find user with name:" + fullName.getText().toString() + "!",Toast.LENGTH_SHORT);
                toast.show();
            }
            else {
                ((ListOfUsersAdapter) adapter).deleteItem(user);
                userRepository.deleteTask(user, new OnUserRepositoryListener());
            }
        }
    }
}
