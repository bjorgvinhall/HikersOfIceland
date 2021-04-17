package is.hi.hbv601g.hikers;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import is.hi.hbv601g.hikers.Entities.Profile;
import is.hi.hbv601g.hikers.Networking.NetworkCallback;
import is.hi.hbv601g.hikers.Networking.Service;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signInButton = findViewById(R.id.login);
        EditText userNameField = findViewById(R.id.username);
        Editable userName = userNameField.getText();

        EditText userPassField = findViewById(R.id.password);
        Editable userPass = userPassField.getText();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    signIn(userName.toString(), userPass.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        Button signUpButton = findViewById(R.id.signup);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    signUp("kalli", "bimbo");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void signIn(String userName, String userPass) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", userName);
        jsonObject.put("password", userPass);

        Service service = new Service(this);
        service.postLogin(jsonObject, new NetworkCallback<Profile>() {
            @Override
            public void onSuccess(Profile result) {
                Intent intent;
                intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("profile", result); // Pass the selected profile to next Activity
                startActivity(intent);
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getApplicationContext(), R.string.login_wrong, Toast.LENGTH_SHORT).show();
                Log.e(TAG, error);
            }
        });
    }

    private void signUp(String userName, String userPass) throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", userName);
        jsonObject.put("password", userPass);

        Service service = new Service(this);
        service.postSignup(jsonObject, new NetworkCallback<Profile>() {
            @Override
            public void onSuccess(Profile result) {
                Toast.makeText(getApplicationContext(), "Profile created", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(String error) {
                Toast.makeText(getApplicationContext(), R.string.signup_wrong, Toast.LENGTH_SHORT).show();
                Log.e(TAG, error);
            }
        });
    }

}
