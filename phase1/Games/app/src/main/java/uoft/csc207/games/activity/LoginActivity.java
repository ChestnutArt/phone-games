package uoft.csc207.games.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import uoft.csc207.games.model.PlayerProfile;
import uoft.csc207.games.controller.ProfileManager;
import uoft.csc207.games.R;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private Button create;
    private TextView errorMsg;
    private ProfileManager profileManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        profileManager = ProfileManager.getProfileManager(getApplicationContext());

        username = (EditText) findViewById(R.id.etName);
        password = (EditText) findViewById(R.id.etPassword);
        login = (Button) findViewById(R.id.btnLogin);
        create = (Button) findViewById(R.id.btnCreate);
        errorMsg = (TextView) findViewById(R.id.txtErrorMsg);

        login.setOnClickListener(new View.OnClickListener(){
           public void onClick(View view){
               validate(username.getText().toString(), password.getText().toString());
           }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProfile(username.getText().toString(), password.getText().toString());
            }
        });
    }

    private void validate(String userName, String passWord){
        if(null == userName || userName.equalsIgnoreCase("")){
            errorMsg.setText("Username is required");
            return;
        }
        if(null == passWord || passWord.equalsIgnoreCase("")){
            errorMsg.setText("Password is required");
            return;
        }
        PlayerProfile tmpProfile = profileManager.getProfileById(userName);
        if(tmpProfile==null || !tmpProfile.getId().equalsIgnoreCase(userName)){
            errorMsg.setText("User: " + userName + " doesn't exist!");
            return;
        }
        if(!tmpProfile.getPassword().equals(passWord)){
            errorMsg.setText("The password of user: " + userName + " is incorrect!");
            return;
        }
        profileManager.setCurrentPlayer(tmpProfile);
        moveToNextActivity();
    }

    private void createProfile(String userName, String passWord){
        PlayerProfile newProfile = new PlayerProfile(userName, passWord);
        if(profileManager.getProfileById(userName) != null){
            errorMsg.setText("User name: " + userName + " is already used by another player!");
            return;
        }
        profileManager.createProfile(newProfile);
        profileManager.setCurrentPlayer(newProfile);
        moveToNextActivity();

    }

    private void moveToNextActivity(){
        Intent intent = new Intent(LoginActivity.this, GameSelectActivity.class);
        //intent.putExtra(ProfileManager.CURRENT_PLAYER, profileManager.getCurrentPlayer());
        startActivity(intent);
    }
}
