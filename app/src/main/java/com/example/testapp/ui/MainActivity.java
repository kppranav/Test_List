package com.example.testapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.testapp.R;
import com.example.testapp.data.model.Employee;
import com.example.testapp.data.model.EmployeeResponse;
import com.example.testapp.ui.adapter.EmployeeListAdapter;
import com.example.testapp.ui.viewmodel.EmployeeViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements EmployeeListAdapter.OnItemClickListener {

    EmployeeViewModel employeeViewModel;
    EmployeeListAdapter adapter;

    TextInputEditText nameEditText, emailEditText;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new EmployeeListAdapter(this::onItemClick);
        initList();
        initUI();

        employeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        employeeViewModel.init();
        employeeViewModel.getResponseLiveData().observe(this, new Observer<EmployeeResponse>() {
            @Override
            public void onChanged(EmployeeResponse employeeResponse) {
                if (employeeResponse != null) {

                    employeeViewModel.insertAll(employeeResponse.getEmployees());

                } else {
                    Log.d("TAG", "::null");
                }

            }
        });

        employeeViewModel.getEmployeesFromDb().observe(this, employees -> {
            Log.d("TAG", "List : " + employees.size());
            if (adapter != null) {
                //Log.d("TAG", "Adapter not null");
                adapter.setEmployees(employees);
            }
        });

    }

    private void initUI() {

        nameEditText = findViewById(R.id.employeeSearch_name);
        emailEditText = findViewById(R.id.employeeSearch_email);
        searchButton = findViewById(R.id.employee_search_btn);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performSearch();
            }
        });

    }

    private void performSearch() {
        String name = nameEditText.getEditableText().toString();
        String email = emailEditText.getEditableText().toString();

        employeeViewModel.searchEmployeesFromDb(name, email).observe(this, employees -> {
            adapter.setEmployees(employees);
        });


    }

    private void initList() {
        RecyclerView recyclerView = findViewById(R.id.fragment_employeeSearch_searchResultsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


    }


    @Override
    public void onItemClick(Employee employee) {
        Intent intent = new Intent(MainActivity.this, EmployeeDetailsActivity.class);
        intent.putExtra("employee", employee);
        startActivity(intent);
    }
}