package com.example.testapp.ui.viewmodel;

import android.app.Application;

import com.example.testapp.data.model.Employee;
import com.example.testapp.data.model.EmployeeResponse;
import com.example.testapp.data.repository.EmployeeRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EmployeeViewModel extends AndroidViewModel {
    private EmployeeRepository employeeRepository;
    private LiveData<EmployeeResponse> responseLiveData;

    public EmployeeViewModel(@NonNull Application application) {
        super(application);
        employeeRepository = new EmployeeRepository(application);
    }

    public void init() {
        employeeRepository.getAllEmployees();
        responseLiveData = employeeRepository.getEmployeeResponseLiveData();
    }

    public LiveData<EmployeeResponse> getResponseLiveData() {
        return responseLiveData;
    }

    public void insertAll(List<Employee> employees) {
        employeeRepository.insertAll(employees);
    }

    public LiveData<List<Employee>> getEmployeesFromDb() {
        return employeeRepository.getEmployeesFromDb();
    }

    public LiveData<List<Employee>> searchEmployeesFromDb(String name, String email) {
        return employeeRepository.searchedEmployeesFromDb(name, email);
    }
}
