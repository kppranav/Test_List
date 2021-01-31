package com.example.testapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testapp.R;
import com.example.testapp.data.model.Employee;
import com.example.testapp.databinding.ActivityEmployeeDetailsBinding;

import java.io.Serializable;

public class EmployeeDetailsActivity extends AppCompatActivity {

    Employee employee;


    private ActivityEmployeeDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View Binding
        binding = ActivityEmployeeDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        employee = (Employee) getIntent().getExtras().getSerializable("employee");
        //For data binding
        //binding.setEmployee(employee);

        initUI();

    }

    private void initUI() {

        Glide.with(this).load(employee.getProfileImage()).into(binding.employeeImage);
        binding.employeeName.setText("Name :" + getDisplayString(employee.getName()));
        binding.employeeUsername.setText("Username : " + getDisplayString(employee.getUsername()));
        binding.employeeEmail.setText("Email : " + getDisplayString(employee.getEmail()));
        binding.employeePhone.setText("Phone : " + getDisplayString(employee.getPhone()));
    }

    private String getDisplayString(String val) {
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }
}