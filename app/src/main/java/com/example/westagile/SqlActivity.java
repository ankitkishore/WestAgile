package com.example.westagile;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class SqlActivity extends AppCompatActivity {

    EditText nameInput,emailInput,dobInput,mobile_no;
    Button btnAdd, btnDelete;
    MyDBHandler dbHandler;
    Button dob;
    Spinner genderInput;

    String g;

    private int mYear, mMonth, mDay;

    String[] gender = { "Choose a Gender", "Male", "Female"};


    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        nameInput = findViewById(R.id.nameInput);
        emailInput = findViewById(R.id.emailInput);
        genderInput = findViewById(R.id.genderInput);
        dobInput = findViewById(R.id.dobInput);
        mobile_no = findViewById(R.id.mobile_no);
        dob = findViewById(R.id.dob);

        list = findViewById(R.id.list);

        dbHandler = new MyDBHandler(this, null, null, 1);
        try {
            printDatabase();
        } catch (Exception e) {
            Log.i("exxxx", e.toString());
        }

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderInput.setAdapter(aa);


        dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {

                    // Get Current Date
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);


                    DatePickerDialog datePickerDialog = new DatePickerDialog(SqlActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    dobInput.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                }
            }
        });




    }

    public void printDatabase() {
        ArrayList<StudentDetails> dbData = dbHandler.databaseToString();

        ListAdapter adapter = new ListAdapter(this,dbData);
        Log.i("yo",dbData.toString());
        list.setAdapter(adapter);
        }

    //Add a product to the database
    public void addButtonClicked(View view) {
        Log.i("exxxx", "CLİCKED ADD BUTTON");
        StudentDetails p = new StudentDetails(nameInput.getText().toString(),
                genderInput.getSelectedItem().toString(),emailInput.getText().toString(),
                dobInput.getText().toString(),mobile_no.getText().toString());
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