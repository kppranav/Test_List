package com.example.testapp.data.api;

import com.example.testapp.data.model.Employee;
import com.example.testapp.data.model.EmployeeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeApiService {
    @GET("5d565297300000680030a986")
    Call<List<Employee>> getAllEmployees();
}
