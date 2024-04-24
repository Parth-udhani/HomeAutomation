package com.beyond.bookrental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UserAddressActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AddressAdapter addressAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_address);

        recyclerView = findViewById(R.id.recyclerview_address);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ImageButton button_savedaddress = findViewById(R.id.addaddressbutton);
        SharedPreferences sharedPreferences = getSharedPreferences("address_pref",MODE_PRIVATE);
        addressAdapter = new AddressAdapter(new ArrayList<>(), sharedPreferences );
        recyclerView.setAdapter(addressAdapter);
        AddressDatabaseHelper dbHelper = new AddressDatabaseHelper(this);
        button_savedaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserAddressActivity.this, SavedAddressActivity.class);
                startActivity(intent);
            }
        });
        // Retrieve and display existing addresses
        updateRecyclerView();
        dbHelper.close();

    }
    private void updateRecyclerView() {
        AddressDatabaseHelper dbHelper = new AddressDatabaseHelper(this);
        List<AddressModel> addressList = dbHelper.getAllAddresses();
        Collections.reverse(addressList);
        SharedPreferences sharedPreferences = getSharedPreferences("address_pref",MODE_PRIVATE);
        addressAdapter = new AddressAdapter(addressList, sharedPreferences);
        recyclerView.setAdapter(addressAdapter);
        dbHelper.close();
    }
}