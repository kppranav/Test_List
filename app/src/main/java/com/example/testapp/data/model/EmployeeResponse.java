package com.example.testapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeResponse {
    @SerializedName("employees")
    @Expose
    List<Employee> employees = null;
    @SerializedName("count")
    @Expose
    int count;

    public EmployeeResponse(List<Employee> employees) {
        this.employees = employees;
        this.count = employees.size();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public int getCount() {
        return count;
    }
}
