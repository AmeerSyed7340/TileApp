package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ModifyShoppingCart extends AppCompatActivity {

    public ArrayList<ShoppingCart> listOfProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_shopping_cart);
        //getting passed in data
        Intent intent = getIntent();
        listOfProd = (ArrayList<ShoppingCart>) intent.getSerializableExtra("LIST_PRODUCTS");


        Button cancelButton1= findViewById(R.id.button3);
        Button crossButton= findViewById(R.id.newbutton1);
        cancelButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        crossButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView myProduct2 = (TextView) findViewById(R.id.newproduct1);
                String productidUi= myProduct2.getText().toString();
                myProduct2.setText("");
                EditText myQuantity2= (EditText) findViewById(R.id.newquatityinput2);
                String quantityUi=myQuantity2.getText().toString();
                myQuantity2.setText("");
                for(ShoppingCart x: listOfProd){
                    if(x.getProId() == productidUi){
                        listOfProd.remove(x);
                    }
                }

            }
        });
    }
}
