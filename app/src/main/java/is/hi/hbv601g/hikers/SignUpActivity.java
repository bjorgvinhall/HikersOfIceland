package is.hi.hbv601g.hikers;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import is.hi.hbv601g.hikers.Entities.Profile;
import is.hi.hbv601g.hikers.Networking.NetworkCallback;
import is.hi.hbv601g.hikers.Networking.Service;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "SignUp";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EditText userNameField = findViewById(R.id.signup_username);
        Editable userName = userNameField.getText();
        EditText passwordField = findViewById(R.id.signup_password);
        Editable password = passwordField.getText();
        EditText nameField = findViewById(R.id.signup_name);
        Editable name = nameField.getText();
        EditText ageField = findViewById(R.id.signup_age);
        Editable age = ageField.getText();
        Button createAccountButton = findViewById(R.id.signup_button);

        createAccountButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    signUp(userName.toString(), password.toString(), name.toString(), age.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void signUp(String userName, String password, String name, String age) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", userName);
        jsonObject.put("password", password);
        jsonObject.put("name", name);
        jsonObject.put("age", age);

        Service service = new Service(this);
        service.postSignup(jsonObject, new NetworkCallback<Profile>() {
            @Override
            public void onSuccess(Profile result) {
                Intent intent;
                intent = new Intent(SignUpActivity.this,LoginActivity.class);
                intent.putExtra("profile", result);
                startActivity(intent);
            }

            @Override
            public void onFailure(String error) {
                Log.e(TAG, "onFailure: did not work to sign up >>> " + error);
            }
        });
    }
}
