package com.example.westagile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SqlActivity extends AppCompatActivity {

    EditText nameInput,emailInput,genderInput;
    Button btnAdd, btnDelete;
    MyDBHandler dbHandler;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        nameInput = (EditText) findViewById(R.id.nameInput);
        emailInput = (EditText) findViewById(R.id.emailInput);
        genderInput = (EditText) findViewById(R.id.genderInput);
        list = findViewById(R.id.list);

        dbHandler = new MyDBHandler(this, null, null, 1);
        try {
            printDatabase();
        } catch (Exception e) {
            Log.i("exxxx", e.toString());
        }



    }

    public void printDatabase() {
        ArrayList<User> dbData = dbHandler.databaseToString();

        ListAdapter adapter = new ListAdapter(this,dbData);
        Log.i("yo",dbData.toString());
        list.setAdapter(adapter);
        }

    //Add a product to the database
    public void addButtonClicked(View view) {
        Log.i("exxxx", "CLİCKED ADD BUTTON");
        User p = new User(nameInput.getText().toString(),genderInput.getText().toString(),emailInput.getText().toString());
        dbHandler.addUser(p);
        printDatabase();
    }

    //Delete a product to the database
    public void deleteButtonClicked(View view) {
        Log.i("exxxx", "CLİCKED DELETE BUTTON");
        String inputText = nameInput.getText().toString();
        dbHandler.deleteUser(inputText);
        printDatabase();
    }
}