package com.example.booknumber;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PhonebookAdapter extends RecyclerView.Adapter<PhonebookAdapter.PersonViewHolder> {

    private List<PersonRecord> personList;

    public PhonebookAdapter(List<PersonRecord> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_2, parent, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        PersonRecord person = personList.get(position);
        holder.txtName.setText(person.getFull_name());
        holder.txtPhone.setText(person.getPhone_num());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void refreshList(List<PersonRecord> newPersons) {
        this.personList = newPersons;
        notifyDataSetChanged();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPhone;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(android.R.id.text1);
            txtPhone = itemView.findViewById(android.R.id.text2);
        }
    }
}
