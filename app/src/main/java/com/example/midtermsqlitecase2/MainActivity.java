package com.example.midtermsqlitecase2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextStudentName, editTextStudentID;
    Spinner spinnerEnrolled;
    Button btnAdd, btnView;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextStudentName = findViewById(R.id.editTextStudentName);
        editTextStudentID = findViewById(R.id.editTextStudentID);
        spinnerEnrolled = findViewById(R.id.spinnerEnrolled);
        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        dbHelper = new DBHelper(this);

        String[] enrolledOptions = {
                "Enrolled",
                "Not Enrolled"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                enrolledOptions
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerEnrolled.setAdapter(adapter);

        btnAdd.setOnClickListener(view -> {

            String studentName = editTextStudentName
                    .getText()
                    .toString();

            String studentID = editTextStudentID
                    .getText()
                    .toString();

            String enrolled = spinnerEnrolled
                    .getSelectedItem()
                    .toString();

            if (studentName.isEmpty() || studentID.isEmpty()) {

                Toast.makeText(
                        this,
                        "Please fill all fields",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            dbHelper.addStudent(
                    new StudentModel(
                            studentName,
                            studentID,
                            enrolled
                    )
            );

            Toast.makeText(
                    this,
                    "Student added",
                    Toast.LENGTH_SHORT
            ).show();

            editTextStudentName.setText("");
            editTextStudentID.setText("");
        });

        btnView.setOnClickListener(view -> {

            Intent intent = new Intent(
                    MainActivity.this,
                    ViewStudent.class
            );

            startActivity(intent);
        });
    }
}