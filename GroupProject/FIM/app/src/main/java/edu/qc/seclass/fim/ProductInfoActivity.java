package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProductInfoActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    FloorDB dbHelper;
    private boolean clicked = false;
    private boolean isEmployee;
    public ShoppingCart sc = ShoppingCart.getInstance();
    private EditText category;
    private EditText type;
    private EditText species;
    private EditText color;
    private EditText size;
    private EditText brand;
    private EditText price;
    private int floorID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_info);

        dbHelper = new FloorDB(this);
        Intent intent = getIntent();
        isEmployee = intent.getBooleanExtra("isEmployee", false);
        floorID = intent.getIntExtra("id", 0);
        instantiateAllInputFields();
        checkIsEmployee();
        setUpSpinner();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String amountString = adapterView.getItemAtPosition(i).toString();
        int amount = Integer.parseInt(amountString);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void instantiateAllInputFields() {
        this.category = findViewById(R.id.category);
        this.type = findViewById(R.id.type);
        this.species = findViewById(R.id.species);
        this.color = findViewById(R.id.color);
        this.size = findViewById(R.id.size);
        this.brand = findViewById(R.id.brand);
        this.price = findViewById(R.id.price);


        category.setText(dbHelper.getCatName(floorID));
        type.setText(dbHelper.getTypeName(floorID));
        String speciesName = dbHelper.getSpeciesName(floorID);
        species.setText(speciesName);
        color.setText(dbHelper.getColor(floorID));
        size.setText(dbHelper.getSize(floorID));
        brand.setText(dbHelper.getBrand(floorID));
        price.setText(dbHelper.getPrice(floorID));

    }

    public void checkIsEmployee() {
        if(!isEmployee) {
            FloatingActionButton dropDownBtn = findViewById(R.id.dropdown_menu);
            dropDownBtn.setVisibility(View.INVISIBLE);
            disableEditFields();
        }
    }

    public void disableEditFields() {
        // Disable all text fields when it's a customer.
        category.setEnabled(false);
        category.setFocusable(false);

        type.setEnabled(false);
        type.setFocusable(false);

        species.setEnabled(false);
        species.setFocusable(false);

        color.setEnabled(false);
        color.setFocusable(false);

        size.setEnabled(false);
        size.setFocusable(false);

        brand.setEnabled(false);
        brand.setFocusable(false);

        price.setEnabled(false);
        price.setFocusable(false);
    }

    public void setUpSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.amount, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner quantitySpinner = findViewById(R.id.quantity);
        quantitySpinner.setAdapter(adapter);
        quantitySpinner.setOnItemSelectedListener(this);
    }

    public void showDropDown(View view) {
        FloatingActionButton editBtn = findViewById(R.id.editBtn);
        FloatingActionButton deleteBtn = findViewById(R.id.deleteBtn);

        if(!clicked) {
            editBtn.setVisibility(View.VISIBLE);
            deleteBtn.setVisibility(View.VISIBLE);
        } else {
            editBtn.setVisibility(View.INVISIBLE);
            deleteBtn.setVisibility(View.INVISIBLE);
        }

        clicked = !clicked;
    }

    public void editProduct(View view) {
        // Change the btn text to save changes when the edit btn is clicked.
        Button btn = findViewById(R.id.btn);
        btn.setText("Save changes");
    }

    public void deleteProduct(View view) {
        boolean isFloorDeleted = dbHelper.deleteFloor(floorID);
        if(isFloorDeleted){
            Toast.makeText(getApplicationContext(), "Floor has been Deleted", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProductInfoActivity.this, ProductDisplayActivity.class);
            intent.putExtra("isEmployee", true);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Floor could not be Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public void addToCartOrSaveEdit(View view) {
        String butText = ((TextView)view).getText().toString();

        Toast toast;
        if(butText.equals("Add To Cart")) {
            toast = Toast.makeText(getApplicationContext(), "Items have been added to cart.", Toast.LENGTH_SHORT);
        } else {
            saveChanges();

            // Change the btn text back to add to cart.
            Button btn = findViewById(R.id.btn);
            btn.setText("Add To Cart");
        }
    }

    public void saveChanges() {
        // Get all the new values.
        String categoryText = category.getText().toString();
        String typeText = type.getText().toString();
        String speciesText = species.getText().toString();
        String colorText = color.getText().toString();
        String sizeText = size.getText().toString();
        String brandText = brand.getText().toString();
        String priceText = price.getText().toString();

        boolean isUpdated = dbHelper.updateFloor(floorID, categoryText, typeText, speciesText, colorText, sizeText, brandText, priceText);
        if(isUpdated){
            Toast.makeText(getApplicationContext(), "Floor has been Updated", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(ProductInfoActivity.this, ProductDisplayActivity.class);
            intent.putExtra("isEmployee", true);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Floor could not be Updated", Toast.LENGTH_SHORT).show();

        }




    }

    public void openShoppingCart(View view) {
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        startActivity(intent);
    }
}