package com.example.testapp.ui.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.testapp.R;
import com.example.testapp.data.model.Employee;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeListHolder> {

    private List<Employee> employees = new ArrayList<>();

    OnItemClickListener listener;

    public EmployeeListAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public EmployeeListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent,
                false);
        return new EmployeeListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeListHolder holder, int position) {
        Employee employee = employees.get(position);

        holder.nameTextView.setText(employee.getName());
        if (employee.getCompany() != null) {
            holder.companyTextView.setText(employee.getCompany().getName());
        } else {
            holder.companyTextView.setText("");
        }
        Glide.with(holder.itemView)
                .load(employee.getProfileImage())
                .placeholder(R.drawable.profile_placeholder)
                .into(holder.employeeImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(employee);
            }
        });

    }

    @Override
    public int getItemCount() {
        return employees.size();
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
        notifyDataSetChanged();
    }

    class EmployeeListHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView companyTextView;
        ImageView employeeImageView;

        public EmployeeListHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.employee_item_name);
            companyTextView = itemView.findViewById(R.id.employee_item_company);
            employeeImageView = itemView.findViewById(R.id.employee_item_smallThumbnail);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Employee employee);
    }
}
