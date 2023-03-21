package edu.qc.seclass.fim;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShoppingCartActivity extends AppCompatActivity {
    public static ArrayList<ShoppingCart> shopFloor;
    //    closeMyKeyboard();
    FloorDB dbHelper;
    public ShoppingCart sc = ShoppingCart.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppingcart);
        RecyclerView shop= findViewById(R.id.shopping_rv);
        shopFloor= new ArrayList<ShoppingCart>();
        dbHelper = new FloorDB(this);

        shoppingAdapter adapter = new shoppingAdapter(this,shopFloor);
        shop.setAdapter(adapter);
        shop.setLayoutManager(new LinearLayoutManager((this)));



    }
    public void checkOut(View view) {

        double[] productOneQuantity = sc.cart.get("Product 1");

        Context context = getApplicationContext();
        CharSequence text = "Checking Out" + Double.toString(productOneQuantity[0]);

        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
    }


//    private void closeMyKeyboard() {
//        View myView = this.getCurrentFocus();
//        if(myView != null){
//            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(myView.getWindowToken(), 0);
//        }
//    }

}