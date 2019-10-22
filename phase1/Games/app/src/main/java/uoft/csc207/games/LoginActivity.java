package uoft.csc207.games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.etName);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               validate(username.getText().toString(), password.getText().toString());
           }
        });
    }

    private void validate(String userName, String passWord){
        if (userName.equals("1234") && passWord.equals("5678")){
            Intent myIntent = new Intent(LoginActivity.this, GameSelectActivity.class);
            startActivity(myIntent);
        }
    }
}
