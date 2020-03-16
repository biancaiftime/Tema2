package com.example.tema2.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tema2.Models.User;
import com.example.tema2.R;
import com.example.tema2.Repositories.UserRepository;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ListOfUsersAdapter extends RecyclerView.Adapter<ListOfUsersAdapter.ViewHolder> {

    public List<User> mDataset;

    public ListOfUsersAdapter(List<User> users)
    {
        mDataset = users;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_text_view, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.fullName.setText(mDataset.get(i).fullName);
        viewHolder.mark.setText(Float.toString(mDataset.get(i).mark));

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView fullName;
        public TextView mark;

        public ViewHolder(View vCard)
        {
            super(vCard);
            fullName = vCard.findViewById(R.id.List_FullName);
            mark = vCard.findViewById(R.id.List_Mark);
        }
    }
}
