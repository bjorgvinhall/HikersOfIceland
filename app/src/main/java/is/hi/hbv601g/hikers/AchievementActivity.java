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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Achievement;
import is.hi.hbv601g.hikers.Entities.Hike;
import is.hi.hbv601g.hikers.Entities.Profile;
import is.hi.hbv601g.hikers.Entities.Review;
import is.hi.hbv601g.hikers.Networking.NetworkCallback;
import is.hi.hbv601g.hikers.Networking.Service;

public class AchievementActivity extends AppCompatActivity {
    private static final String TAG = "ReviewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);
        // Get the selected hike
        Intent intent = getIntent();
        Hike hike = (Hike) intent.getSerializableExtra("selectedHike");
        Profile selectedProfile = (Profile) intent.getSerializableExtra("profile");

        // Makes a list of achievements from the selected hike
        List<Achievement> achievs = new ArrayList<>();
        achievs = hike.getAchievements();

        ListView lv = (ListView) findViewById(R.id.achiev_listview);
        AchievementActivity.ListAdapter listAdapter = new AchievementActivity.ListAdapter(this, achievs, hike, selectedProfile);

        lv.setAdapter(listAdapter);
    }

    private class ListAdapter extends BaseAdapter {
        Activity context;
        List<Achievement> achievs;
        Hike hike;
        Profile profile;
        private LayoutInflater inflater = null;
        Service service = new Service(context);

        public ListAdapter(Activity context, List<Achievement> achievs, Hike hike, Profile profile) {
            this.context = context;
            this.achievs = achievs;
            this.hike = hike;
            this.profile = profile;
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
            for (int i = 0; i < profile.getCompletedAchievements().size(); i++) {
                if (profile.getCompletedAchievements().get(i).getId() == selectedAchiev.getId())
                    checkBox.setChecked(true);
            }
//            if(selectedAchiev.getId() == )
//            if(profile.getCompletedAchievements().contains(selectedAchiev)) {
//                checkBox.setChecked(true);
//            }
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkBox.setEnabled(false);
                    // post rest/hikes/{hikeid}/achievements/{achievementid}
                    JSONObject requestBody = new JSONObject();
                    try {
                        requestBody.put("username", profile.getUsername());
                    } catch (JSONException e) {

                    }

                    service.postAchievement(hike.getId(), selectedAchiev.getId(), requestBody, new NetworkCallback<Profile>() {
                        @Override
                        public void onSuccess(Profile newProfile) {
                            checkBox.setEnabled(true);
                            if (isChecked){
                                Toast.makeText(getApplicationContext(), "Achievement completed!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Achievement removed!", Toast.LENGTH_SHORT).show();
                            }
                            profile = newProfile;
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(String error) {
                            Log.e(TAG, "Network error!");
                        }
                    });
                }
            });

            return itemView;
        }

    }
}

