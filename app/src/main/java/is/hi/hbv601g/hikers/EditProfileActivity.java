package is.hi.hbv601g.hikers;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import is.hi.hbv601g.hikers.Entities.Profile;
import is.hi.hbv601g.hikers.Networking.NetworkCallback;
import is.hi.hbv601g.hikers.Networking.Service;

public class EditProfileActivity extends AppCompatActivity {
    private static final String TAG = "EditProfile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent = getIntent();
        Profile selectedProfile = (Profile) intent.getSerializableExtra("profile");

        EditText userNameField = findViewById(R.id.editprofile_username);
        userNameField.setHint(selectedProfile.getUsername());
        Editable userName = userNameField.getText();

        EditText passwordField = findViewById(R.id.editprofile_password);
        passwordField.setHint("****");
        Editable password = passwordField.getText();

        EditText nameField = findViewById(R.id.editprofile_name);
        nameField.setHint(selectedProfile.getName());
        Editable name = nameField.getText();

        EditText ageField = findViewById(R.id.editprofile_age);
        ageField.setHint(String.valueOf(selectedProfile.getAge()));
        Editable age = ageField.getText();

        Button confirmChangesButton = findViewById(R.id.editprofile_confirm_button);

        confirmChangesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    editProfile(userName.toString() ,password.toString(), name.toString(), age.toString());
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void editProfile(String username, String password, String name, String age) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", username);
        jsonObject.put("password", password);
        jsonObject.put("name", name);
        jsonObject.put("age", age);

        Service service = new Service(this);
        service.postProfile(jsonObject, new NetworkCallback<Profile>() {
            @Override
            public void onSuccess(Profile result) {
                Intent intent;
                intent = new Intent(EditProfileActivity.this, LoginActivity.class);
                intent.putExtra("profile", result);
                startActivity(intent);
            }

            @Override
            public void onFailure(String error) {
                Log.e(TAG, "onFailure: did not work to edit profile >>> " + error);
            }
        });


    }
}

