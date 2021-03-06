package com.example.retrofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserAdapterVH> {
    private List<UserResponse> userResponsesList;
    private ClickedItem clickedItem;

    public UsersAdapter(ClickedItem clickedItem) {
        this.clickedItem = clickedItem;
    }

    public UsersAdapter() {
    }
    public void setData(List<UserResponse> userResponsesList) {
        this.userResponsesList = userResponsesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new UsersAdapter.UserAdapterVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_users,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapterVH holder, int position) {
        // populate data
        UserResponse userResponse = userResponsesList.get(position);
        String username = userResponse.getUsername();
        char pre;
        String prefix;
        pre = Character.toUpperCase(username.charAt(0));
        prefix = String.valueOf(pre);
        holder.prefix.setText(prefix);
        holder.username.setText(username);
        holder.detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedItem.ClickedUser(userResponse);
            }
        });

    }
    public interface ClickedItem{
        public void ClickedUser(UserResponse userResponse);
    }




    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return userResponsesList.size();
    }

    public class UserAdapterVH extends RecyclerView.ViewHolder {
        TextView username;
        TextView prefix;
        ImageView detail;
        // sets adapter info
        public UserAdapterVH(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            prefix = itemView.findViewById(R.id.prefix);
            detail = itemView.findViewById(R.id.imageMore);

        }
    }
}
