package is.hi.hbv601g.hikers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import is.hi.hbv601g.hikers.Entities.Profile;

public class ProfileActivity extends AppCompatActivity {
    private static final String TAG = "Profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        Profile selectedProfile = (Profile) intent.getSerializableExtra("profile");

        TextView profileNameText = findViewById(R.id.profile_name);
        TextView profileUsernameText = findViewById(R.id.profile_username);
        TextView profileAgeText = findViewById(R.id.profile_age);
//        TextView profileAchievementsText = findViewById(R.id.profile_achievements);

        profileNameText.setText(selectedProfile.getName());
        profileUsernameText.setText(selectedProfile.getUsername());
        profileAgeText.setText(String.valueOf(selectedProfile.getAge()));

        Button btn = (Button) findViewById(R.id.edit_profile);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("profile", selectedProfile);
                startActivity(intent);
            }
        });
    }
}