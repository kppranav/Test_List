package com.example.testapp.data.repository;

import android.app.Application;
import android.util.Log;

import com.example.testapp.data.api.EmployeeApiService;
import com.example.testapp.data.model.Employee;
import com.example.testapp.data.model.EmployeeResponse;
import com.example.testapp.db.EmployeeDao;
import com.example.testapp.db.EmployeeDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmployeeRepository {
    private static final String BASE_URL = "http://www.mocky.io/v2/";

    private EmployeeApiService apiService;
    private MutableLiveData<EmployeeResponse> employeeMutableLiveData;

    private EmployeeDao employeeDao;

    public EmployeeRepository(Application application) {
        employeeMutableLiveData = new MutableLiveData<>();

        apiService = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
               // .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EmployeeApiService.class);

        EmployeeDatabase database = EmployeeDatabase.getDatabase(application);
        employeeDao = database.employeeDao();

    }

    public void getAllEmployees() {
        apiService.getAllEmployees().enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                Log.d("TAG", "size : " + response.body().size());
                employeeMutableLiveData.postValue(new EmployeeResponse(response.body()));
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("TAG", "err : " + t.getMessage());
                employeeMutableLiveData.postValue(null);
            }
        });
        /*apiService.getAllEmployees().enqueue(new Callback<EmployeeResponse>() {
            @Override
            public void onResponse(Call<EmployeeResponse> call, Response<EmployeeResponse> response) {
                Log.d("TAG", "ok");
                employeeMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<EmployeeResponse> call, Throwable t) {
                Log.d("TAG", ":: " + t.getMessage());
                employeeMutableLiveData.postValue(null);
            }
        });*/
    }

    public LiveData<EmployeeResponse> getEmployeeResponseLiveData() {
        return employeeMutableLiveData;
    }

    public void insertAll(List<Employee> employees) {
        for (Employee employee :
                employees) {
            EmployeeDatabase.databaseWriteExecutor.execute(() -> {
                employeeDao.insertAll(employee);
            });
        }

    }

    public LiveData<List<Employee>> getEmployeesFromDb() {
        return employeeDao.getAllEmployees();
    }

    public LiveData<List<Employee>> searchedEmployeesFromDb(String name, String email) {
        return employeeDao.getSearchedEmployees(name, email);
    }
}
