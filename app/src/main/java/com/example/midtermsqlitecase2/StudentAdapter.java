package com.example.midtermsqlitecase2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private List<StudentModel> studentModels;
    private Context context;
    private DBHelper dbHelper;

    public StudentAdapter(List<StudentModel> studentModels, Context context) {
        this.studentModels = studentModels;
        this.context = context;
        this.dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        StudentModel student = studentModels.get(position);

        holder.textViewID.setText(String.valueOf(student.getId()));
        holder.editTextStudentName.setText(student.getStudentName());
        holder.editTextStudentID.setText(student.getStudentID());

        String[] enrolledOptions = {
                "Enrolled",
                "Not Enrolled"
        };

        ArrayAdapter<String> enrolledAdapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                enrolledOptions
        );

        enrolledAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        holder.spinnerEnrolled.setAdapter(enrolledAdapter);

        if (student.getEnrolled().equals("Not Enrolled")) {
            holder.spinnerEnrolled.setSelection(1);
        } else {
            holder.spinnerEnrolled.setSelection(0);
        }

        holder.btnEdit.setOnClickListener(view -> {

            String studentName = holder.editTextStudentName
                    .getText()
                    .toString();

            String studentID = holder.editTextStudentID
                    .getText()
                    .toString();

            String enrolled = holder.spinnerEnrolled
                    .getSelectedItem()
                    .toString();

            StudentModel updatedStudent = new StudentModel(
                    student.getId(),
                    studentName,
                    studentID,
                    enrolled
            );

            dbHelper.updateStudent(updatedStudent);

            Intent intent = new Intent(
                    context,
                    ViewStudent.class
            );

            context.startActivity(intent);

            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        });

        holder.btnDelete.setOnClickListener(view -> {

            dbHelper.deleteStudent(student.getId());

            studentModels.remove(position);

            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return studentModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewID;
        EditText editTextStudentName;
        EditText editTextStudentID;
        Spinner spinnerEnrolled;
        Button btnEdit;
        Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.textViewID);

            editTextStudentName =
                    itemView.findViewById(R.id.editTextStudentName);

            editTextStudentID =
                    itemView.findViewById(R.id.editTextStudentID);

            spinnerEnrolled =
                    itemView.findViewById(R.id.spinnerEnrolled);

            btnEdit =
                    itemView.findViewById(R.id.btnEdit);

            btnDelete =
                    itemView.findViewById(R.id.btnDelete);
        }
    }
}