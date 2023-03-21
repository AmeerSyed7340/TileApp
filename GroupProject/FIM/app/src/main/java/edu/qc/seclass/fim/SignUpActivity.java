package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    FloorDB dbHelper;
    EditText nameField, emailField, passField;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        dbHelper = new FloorDB(this);
        nameField = findViewById(R.id.name);
        emailField = findViewById(R.id.email);
        passField = findViewById(R.id.passwordActivity2);
        nameField.setText("");
        emailField.setText("");
        passField.setText("");


    }

    public void guestSignUp(View view) {

        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String password = passField.getText().toString();

        if(name.equals("") || email.equals("") || password.equals("")){
            Toast.makeText(getApplicationContext(), "Empty fields", Toast.LENGTH_SHORT).show();
        } else {
            boolean isSignedUp = dbHelper.newGuestSignUp(name, email, password);
            if(isSignedUp){
                Toast.makeText(getApplicationContext(), "Account Successfully Created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignUpActivity.this, ProductDisplayActivity.class);
                startActivity(intent);
            }

        }
    }
}