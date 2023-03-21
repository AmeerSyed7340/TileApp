package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.List;

public class AddNewProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FloorDB dbHelper;
    EditText enterPrice, enterBrand, enterColor, enterSize, enterQuantity;
    String selectedStore;
    String chosenCategory;
    String chosenType;
    String chosenSpecies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_product);
        dbHelper = new FloorDB(this);

        enterPrice = findViewById(R.id.enterPrice);
        enterBrand = findViewById(R.id.enterBrand);
        enterColor = findViewById(R.id.enterColor);
        enterSize = findViewById(R.id.enterSize);
        enterQuantity = findViewById(R.id.enterQuantity);

        setUpSpinner();

        Spinner typeDropdown = findViewById(R.id.typeDropdown);
        Spinner speciesDropdown = findViewById(R.id.speciesDropdown);
        typeDropdown.setEnabled(false);
        speciesDropdown.setEnabled(false);
        setUpCategorySpinner();
    }

    public void setUpSpinner(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.stores, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner storeSpinner = findViewById(R.id.spinner);
        storeSpinner.setAdapter(adapter);
        storeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedStore = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    public void addNewProductToDB(View view) {
        if(selectedStore.equals("Select a Store")){
            Toast toast = Toast.makeText(getApplicationContext(), "Empty store", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        if(enterPrice.getText() == null || enterPrice.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if(enterColor.getText() == null || enterColor.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if(enterBrand.getText() == null || enterBrand.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if(enterSize.getText() == null || enterSize.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if(enterQuantity.getText() == null || enterQuantity.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Empty fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String price = enterPrice.getText().toString();
        String color = enterColor.getText().toString();
        String brand = enterBrand.getText().toString();
        String size = enterSize.getText().toString();
        int quantity = Integer.parseInt(enterQuantity.getText().toString());

        if(!chosenCategory.equals("Wood")) chosenSpecies = "null";
        boolean isAdded = dbHelper.addNewFloorToInv(selectedStore, chosenCategory, chosenType, chosenSpecies, brand, size, color, price, quantity);
        if(isAdded) {
            Toast.makeText(getApplicationContext(), "Product Successfully Added", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddNewProductActivity.this, ProductDisplayActivity.class);
            intent.putExtra("isEmployee", true);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Failed to Add Product", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if(adapterView.getId() == R.id.categoryDropdown) {
//            enterColor.setText(adapterView.getItemAtPosition(i).toString());
            chosenCategory = adapterView.getItemAtPosition(i).toString();
            setUpTypeSpinner();
        }

        if(adapterView.getId() == R.id.typeDropdown) {
//            enterBrand.setText(adapterView.getItemAtPosition(i).toString());
            chosenType = adapterView.getItemAtPosition(i).toString();
        }

        if(adapterView.getId() == R.id.speciesDropdown) {
//            enterPrice.setText(adapterView.getItemAtPosition(i).toString());
            chosenSpecies = adapterView.getItemAtPosition(i).toString();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        if(adapterView.getId() == R.id.speciesDropdown) {
//            enterPrice.setText(adapterView.getItemAtPosition(i).toString());
            chosenSpecies = "null";
        }
    }

    public void setUpCategorySpinner() {
        Spinner categoryDropdown = findViewById(R.id.categoryDropdown);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categoryDropdown.setAdapter(adapter);
        categoryDropdown.setOnItemSelectedListener(this);
    }

    public void setUpTypeSpinner() {
        Spinner typeDropdown = findViewById(R.id.typeDropdown);
        Spinner speciesDropdown = findViewById(R.id.speciesDropdown);

        if(chosenCategory.equals("Wood")) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.woodTypes, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeDropdown.setAdapter(adapter);
            typeDropdown.setOnItemSelectedListener(this);

            // Set up the species spinner only when wood was chosen.
            ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.species, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            speciesDropdown.setAdapter(adapter2);
            speciesDropdown.setOnItemSelectedListener(this);
            speciesDropdown.setEnabled(true);
        }
        if(chosenCategory.equals("Tile")) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tileTypes, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeDropdown.setAdapter(adapter);
            typeDropdown.setOnItemSelectedListener(this);

            speciesDropdown.setEnabled(false);
        }
        if(chosenCategory.equals("Stone")) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.stoneTypes, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeDropdown.setAdapter(adapter);
            typeDropdown.setOnItemSelectedListener(this);

            speciesDropdown.setEnabled(false);
        }
        if(chosenCategory.equals("Laminate")) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.laminateTypes, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeDropdown.setAdapter(adapter);
            typeDropdown.setOnItemSelectedListener(this);

            speciesDropdown.setEnabled(false);
        }
        if(chosenCategory.equals("Vinyl")) {
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.vinylTypes, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            typeDropdown.setAdapter(adapter);
            typeDropdown.setOnItemSelectedListener(this);
            speciesDropdown.setEnabled(false);
        }
        typeDropdown.setEnabled(true);
    }
}