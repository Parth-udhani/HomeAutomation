package com.beyond.bookrental;

// AddressAdapter.java

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

    private List<AddressModel> addressList;
    SharedPreferences sp;
    private SharedPreferences sharedPreferences;


    public AddressAdapter(List<AddressModel> addressList, SharedPreferences sharedPreferences) {
        this.addressList = addressList;
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public AddressViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View addressView = inflater.inflate(R.layout.item_address, parent, false);

        return new AddressViewHolder(addressView);
    }

    @Override
    public void onBindViewHolder(AddressViewHolder holder, int position) {
        AddressModel address = addressList.get(position);

        // Bind data to the ViewHolder
        holder.fullNameTextView.setText(address.getFullName());
        holder.mobileNoTextView.setText(address.getMobileNo());
        holder.addressTextView.setText(address.getAddress());
        holder.cityTextView.setText(address.getCity());
        holder.stateTextView.setText(address.getState());
        holder.pinCodeTextView.setText(address.getPinCode());
        if (!sharedPreferences.getString("fullName", "").equals("") ){
            if( !sharedPreferences.getString("fullName", "").equals(address.getFullName())){
                holder.likebutton.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.heart));
            }else{
                holder.likebutton.setImageDrawable(holder.itemView.getContext().getResources().getDrawable(R.drawable.favoritered));

            }
        }
    }

    @Override
    public int getItemCount() {
        return addressList.size();
    }

    public class AddressViewHolder extends RecyclerView.ViewHolder {
        // Declare your TextViews here
        public TextView fullNameTextView;
        public TextView mobileNoTextView;
        public TextView addressTextView;
        public TextView cityTextView;
        public TextView stateTextView;
        public TextView pinCodeTextView;

        public ImageButton likebutton;

        public AddressViewHolder(View itemView) {
            super(itemView);

            // Initialize TextViews
            fullNameTextView = itemView.findViewById(R.id.fullNameTextView);
            mobileNoTextView = itemView.findViewById(R.id.mobileNoTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            stateTextView = itemView.findViewById(R.id.stateTextView);
            pinCodeTextView = itemView.findViewById(R.id.pinCodeTextView);
            likebutton = itemView.findViewById(R.id.likeButton);

            likebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Handle the like button click here
                    // You can perform any action or trigger any method you want
                    // For example, you can use the position parameter to get the clicked item
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // Access the address at this position using addressList.get(position)
                        AddressModel clickedAddress = addressList.get(position);
                        saveUserDetails(clickedAddress);
                        likebutton.setImageDrawable(itemView.getContext().getResources().getDrawable(R.drawable.favoritered));


                    }

                }
            });
        }
        private void saveUserDetails(AddressModel address) {
            // Use SharedPreferences.Editor to save the user details
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("fullName", address.getFullName());
            editor.putString("mobileNo", address.getMobileNo());
            editor.putString("address", address.getAddress());
            editor.putString("city", address.getCity());
            editor.putString("state", address.getState());
            editor.putString("pinCode", address.getPinCode());

            // Apply the changes
            editor.apply();

            Log.d("SharedPreferences", "User details saved: " +
                    "fullName: " + address.getFullName() +
                    ", mobileNo: " + address.getMobileNo() +
                    ", address: " + address.getAddress() +
                    ", city: " + address.getCity() +
                    ", state: " + address.getState() +
                    ", pinCode: " + address.getPinCode());
        }
        }
    }

