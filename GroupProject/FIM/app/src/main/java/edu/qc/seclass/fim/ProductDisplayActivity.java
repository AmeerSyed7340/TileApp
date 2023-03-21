package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductDisplayActivity extends AppCompatActivity {

    //ListView listView;
    public static boolean isEmployee;
    FloorDB dbHelper;
    RecyclerView rvPrograms;
    FloorsAdapter floorsAdapter;
    RecyclerView.LayoutManager layoutManager;
    List<Floors> floorsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display2);

        dbHelper = new FloorDB(this);
        Intent intent = getIntent();
        isEmployee = intent.getBooleanExtra("isEmployee", false);
        checkIsEmployee();

        floorsList = dbHelper.getALlFloors();
        rvPrograms = findViewById(R.id.FloorRecycler);
        rvPrograms.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvPrograms.setLayoutManager(layoutManager);
        floorsAdapter = new FloorsAdapter(this, floorsList, rvPrograms, isEmployee);
        rvPrograms.setAdapter(floorsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_menu,menu);
        // MenuItem menuItem = menu.findItem(R.id.nav_search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = (SearchView) menu.findItem(R.id.nav_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setQueryHint("Search by type, brand, or color");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

                                              public boolean onQueryTextSubmit(String query){
                                                  return false;
                                              }
                                              public boolean onQueryTextChange(String newText){
                                                  return false;
                                              }
                                          }
        );

        return super.onCreateOptionsMenu(menu);
    }

    public void openShoppingCart(View view) {
        Intent intent = new Intent(ProductDisplayActivity.this, ShoppingCartActivity.class );
        startActivity(intent);
    }

    public void openProductInfo(View view, int id) {
        Intent intent = new Intent(ProductDisplayActivity.this, ProductInfoActivity.class );
        intent.putExtra("isEmployee", isEmployee);
        intent.putExtra("floorID", id);
        startActivity(intent);
    }

    public void openAddNewProduct(View view) {
        Intent intent = new Intent(ProductDisplayActivity.this, AddNewProductActivity.class );
        startActivity(intent);
    }

    public void checkIsEmployee() {
        if(!isEmployee) {
            FloatingActionButton addNewProductBtn = findViewById(R.id.addNewProductBtn);
            addNewProductBtn.setVisibility(View.INVISIBLE);
        }
    }

}