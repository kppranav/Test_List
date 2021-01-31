package com.example.testapp.db;

import com.example.testapp.data.model.Employee;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Employee...employees);

    @Query("SELECT * FROM employee_table")
    LiveData<List<Employee>> getAllEmployees();

    @Query("SELECT * FROM employee_table WHERE name = :name OR email = :email")
    LiveData<List<Employee>> getSearchedEmployees(String name, String email);
}
