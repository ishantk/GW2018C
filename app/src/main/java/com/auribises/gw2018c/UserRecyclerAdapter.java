package com.auribises.gw2018c;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.auribises.gw2018c.model.User;

import java.util.ArrayList;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.ViewHolder>{


    Context context;
    int resource;
    ArrayList<User> objects;

    public UserRecyclerAdapter(Context context, int resource, ArrayList<User> objects) {
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(resource,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = objects.get(position);

        holder.imageView.setBackgroundResource(user.image);
        holder.txtName.setText(user.name);
        holder.txtEmail.setText(user.email);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    // ViewHolder will hold the views for list item
    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView txtName;
        TextView txtEmail;

        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            txtName = itemView.findViewById(R.id.textViewName);
            txtEmail = itemView.findViewById(R.id.textViewEmail);
        }
    }
}
