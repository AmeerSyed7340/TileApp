package edu.qc.seclass.fim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    FloorDB dbHelper;
    EditText uName;
    EditText pwrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new FloorDB(this);

        TextView textView = findViewById(R.id.signUp);
        TextView textViewGuest = findViewById(R.id.signInGuest);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class );
                startActivity(intent);
            }
        });
        textViewGuest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProductDisplayActivity.class );
                intent.putExtra("isEmployee", false);
                startActivity(intent);
            }
        });
      
    }

    public void checkLogInCredentials(View view){
        uName = findViewById(R.id.username);
        pwrd = findViewById(R.id.password);

        String userName = uName.getText().toString();
        String password = pwrd.getText().toString();
        boolean isEmployee = dbHelper.checkEmployeeCredentials(userName, password);
        boolean isGuest = dbHelper.checkGuestCredentials(userName, password);

        if(userName.equals("") || password.equals("")){
            Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
        } else if(isEmployee){
            Intent intent = new Intent(MainActivity.this, ProductDisplayActivity.class);
            intent.putExtra("isEmployee", true);
            startActivity(intent);
        } else if(isGuest){
            Intent intent = new Intent(MainActivity.this, ProductDisplayActivity.class);
            intent.putExtra("isEmployee", false);
            startActivity(intent);
        } else if(!isEmployee && !isGuest){
            Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_SHORT).show();
        }

    }

}