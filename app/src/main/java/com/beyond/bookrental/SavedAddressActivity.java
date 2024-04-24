package com.beyond.bookrental;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SavedAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_address);

        // Initialize EditText fields
        EditText shippingName = findViewById(R.id.shipping_name);
        EditText shippingContact = findViewById(R.id.shipping_contact);
        EditText shippingAddress = findViewById(R.id.shipping_address);
        EditText shippingCity = findViewById(R.id.shipping_city);
        EditText shippingState = findViewById(R.id.shipping_state);
        EditText shippingPincode = findViewById(R.id.shipping_pincode);

        // Inside your ShippingActivity or wherever you want to use the database
        AddressDatabaseHelper dbHelper = new AddressDatabaseHelper(this);

        Button button = findViewById(R.id.shipping_place_order);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move data retrieval inside the OnClickListener
                AddressModel newAddress = new AddressModel(
                        shippingName.getText().toString(),
                        shippingContact.getText().toString(),
                        shippingAddress.getText().toString(),
                        shippingCity.getText().toString(),
                        shippingState.getText().toString(),
                        shippingPincode.getText().toString()
                );

                // Insert data into the database
                long id = dbHelper.insertAddress(newAddress);
                Toast.makeText(SavedAddressActivity.this, "Address Saved", Toast.LENGTH_SHORT).show();

                // Retrieve and print all addresses
                List<AddressModel> addressList = dbHelper.getAllAddresses();
                for (AddressModel var : addressList) {
                    System.out.println("Juhi " + var.getFullName());
                }

                // Remember to close the database when you're done with it
                dbHelper.close();
            }
        });
    }
}