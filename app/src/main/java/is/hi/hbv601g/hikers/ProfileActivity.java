package is.hi.hbv601g.hikers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Achievement;
import is.hi.hbv601g.hikers.Entities.Hike;
import is.hi.hbv601g.hikers.Entities.Profile;
import is.hi.hbv601g.hikers.Networking.NetworkCallback;
import is.hi.hbv601g.hikers.Networking.Service;

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

        Button changeProfileButton = (Button) findViewById(R.id.edit_profile);
        changeProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                intent.putExtra("profile", selectedProfile);
                startActivity(intent);
            }
        });

        ImageButton logOutButton = (ImageButton) findViewById(R.id.log_out_button);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        // Makes a list of achievements from the selected hike
        List<Achievement> achievs = new ArrayList<>();
        achievs = selectedProfile.getCompletedAchievements();

        ListView lv = (ListView) findViewById(R.id.profile_achievements);
        ProfileActivity.ListAdapter listAdapter = new ProfileActivity.ListAdapter(this, selectedProfile.getCompletedAchievements());

        lv.setAdapter(listAdapter);
    }
    private class ListAdapter extends BaseAdapter {
        Activity context;
        List<Achievement> achievs;
        private LayoutInflater inflater = null;
        Service service = new Service(context);

        public ListAdapter(Activity context, List<Achievement> achievs) {
            this.context = context;
            this.achievs = achievs;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


        @Override
        public int getCount() {
            return achievs.size();
        }

        @Override
        public Object getItem(int i) {
            return achievs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return achievs.get(i).getId();
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            itemView = (itemView == null) ? inflater.inflate(R.layout.achiev_list_item, null) : itemView;
            TextView achievlistname = (TextView) itemView.findViewById(R.id.achievlistname);
            TextView achievlistdiff = (TextView) itemView.findViewById(R.id.achievlistdiff);
            TextView achievlistdesc = (TextView) itemView.findViewById(R.id.achievlistdesc);

            Achievement selectedAchiev = achievs.get(position);

            achievlistname.setText(selectedAchiev.getName());
            achievlistdesc.setText(selectedAchiev.getDescription());
            achievlistdiff.setText("Erfiðleikastig: " + String.valueOf(selectedAchiev.getDifficulty()));

            // Image
            int drawableInt = 0;
            int difficulty = selectedAchiev.getDifficulty();

            switch(difficulty){
                case 1:
                    drawableInt = R.drawable.difficulty1;
                    break;
                case 2:
                    drawableInt = R.drawable.difficulty1;
                    break;
                case 3:
                    drawableInt = R.drawable.difficulty2;
                    break;
                case 4:
                    drawableInt = R.drawable.difficulty3;
                    break;
                case 5:
                    drawableInt = R.drawable.difficulty3;
                    break;
                default:
                    drawableInt = R.drawable.difficulty2;
            }
            ImageView imageRatingView = (ImageView) itemView.findViewById(R.id.difficultyImage);
            imageRatingView.setImageResource(drawableInt);

            // Checkbox
            CheckBox checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            checkBox.setVisibility(View.INVISIBLE);

            TextView tv = (TextView) itemView.findViewById(R.id.achiev_label);
            tv.setVisibility(View.INVISIBLE);
//            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////
////                @Override
////                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
////                    checkBox.setEnabled(false);
////                    // post rest/hikes/{hikeid}/achievements/{achievementid}
//////                    JSONObject requestBody = new JSONObject();
//////                    try {
//////                        requestBody.put("username", profile.getUsername());
//////                    } catch (JSONException e) {
//////
//////                    }
////                }
//            });

            return itemView;
        }

    }
}