
package is.hi.hbv601g.hikers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Hike;
import is.hi.hbv601g.hikers.Entities.Profile;
import is.hi.hbv601g.hikers.Entities.Review;
import is.hi.hbv601g.hikers.Networking.NetworkCallback;
import is.hi.hbv601g.hikers.Networking.Service;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    boolean doubleBackToExitPressedOnce = false;
    private static Profile selectedProfile;
    Service service = new Service(this);
    boolean isFiltered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Hike> hikes;
        Intent intent = getIntent();
        ListView lv;
        ListAdapter listAdapter;
        selectedProfile = (Profile) intent.getSerializableExtra("profile");

        if (intent.getSerializableExtra("filteredHikes") != null) {
            isFiltered = true;
            hikes = (ArrayList<Hike>) intent.getSerializableExtra("filteredHikes");
            lv = (ListView) findViewById(R.id.main_listview);
            listAdapter = new ListAdapter(this, hikes,selectedProfile);
            List<Hike> trails = hikes;
            listAdapter.setData(trails);

        } else {
            isFiltered = false;
            hikes = new ArrayList<>();
            lv = (ListView) findViewById(R.id.main_listview);
            listAdapter = new ListAdapter(this, hikes,selectedProfile);

            Service service = new Service(this);
            service.getHikes(new NetworkCallback<List<Hike>>() {
                @Override
                public void onSuccess(List<Hike> result) {
                    listAdapter.setData(result);
                    hikes.addAll(result);
                }

                @Override
                public void onFailure(String error) {
                    Log.e(TAG, "Request failed: " + error);
                    Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                }
            });
        }

        lv.setAdapter(listAdapter);

        Button filterButton = findViewById(R.id.filterButton);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFiltered) {
                    // Go back to last filter
                    onBackPressed();
                    return;
                }
                Intent intent;
                intent = new Intent(MainActivity.this, FilterActivity.class);
                intent.putExtra("hikes", hikes); // Pass the selected hike to next Activity
                intent.putExtra("profile", selectedProfile); // Pass the selected profile to next Activity

                startActivity(intent);
            }
        });

        /* profile
        Intent profileIntent = getIntent();
        final Profile selectedProfiler = (Profile) profileIntent.getSerializableExtra("profile");

        Log.d(TAG, "onCreate: "+selectedProfile.getName());
        */
        ImageButton iBtn = (ImageButton) findViewById(R.id.profile_image_button);

        iBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent;
                intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("profile", selectedProfile);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        if(selectedProfile == null){
            super.onResume();
            return;
        }
        super.onResume();
//        isFiltered = getIntent().getBooleanExtra("isFiltered", false);
        // update the profile
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", selectedProfile.getUsername());
            jsonObject.put("password", selectedProfile.getPassword());
        } catch (JSONException e) { }

        service.postLogin(jsonObject, new NetworkCallback<Profile>() {
            @Override
            public void onSuccess(Profile profile) {
                selectedProfile = profile;
            }

            @Override
            public void onFailure(String error) {
                Log.e(TAG, "Request failed: "  + error);
                Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }

/*
    @Override
    public void onBackPressed() {
        ActivityManager mngr = (ActivityManager) getSystemService( ACTIVITY_SERVICE );

        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);

        Toast.makeText(this, "Last: " + taskList.get(0).topActivity.getClassName(), Toast.LENGTH_SHORT).show();

        if(taskList.get(0).numActivities == 1 &&
                taskList.get(0).topActivity.getClassName().equals(this.getClass().getName())) {
        }

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
*/

    private class ListAdapter extends BaseAdapter {
        Activity context;
        List<Hike> hikes;
        Profile selectedProfile;
        private LayoutInflater inflater = null;

        public ListAdapter(Activity context, List<Hike> hikes, Profile selectedProfile) {
            this.context = context;
            this.hikes = hikes;
            this.selectedProfile = selectedProfile;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<Hike> hikes) {
            this.hikes = hikes;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return hikes.size();
        }

        @Override
        public Object getItem(int i) {
            return hikes.get(i);
        }

        @Override
        public long getItemId(int i) {
            return hikes.get(i).getId();
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            Hike selectedHike = hikes.get(position);
            View itemView = convertView;
            itemView = (itemView == null) ? inflater.inflate(R.layout.hike_list_item, null): itemView;

            int drawableInt = 0;
            int rating = 0;
            if(selectedHike.getReviews().size() > 0) {
                double totalRating = 0.0;
                for (Review review: selectedHike.getReviews()) {
                    totalRating += review.getRating();
                }
                rating = (int) Math.round(totalRating / selectedHike.getReviews().size());
            }
            switch(rating){
                case 1:
                    drawableInt = R.drawable.star1;
                    break;
                case 2:
                    drawableInt = R.drawable.star2;
                    break;
                case 3:
                    drawableInt = R.drawable.star3;
                    break;
                case 4:
                    drawableInt = R.drawable.star4;
                    break;
                case 5:
                    drawableInt = R.drawable.star5;
                    break;
                default:
                    drawableInt = R.drawable.star0;
            }

            ImageView imageRatingView = (ImageView) itemView.findViewById(R.id.hikelist_ratingImage);
            imageRatingView.setImageResource(drawableInt);

            TextView textViewName = (TextView) itemView.findViewById(R.id.hikelist_name);
            textViewName.setText(selectedHike.getName());

            TextView textViewLocation = (TextView) itemView.findViewById(R.id.hikelist_location);
            textViewLocation.setText(selectedHike.getLocation());

            ImageView imageView = (ImageView) itemView.findViewById(R.id.hikelist_image);
            Picasso.get()
                    .load( "https://hikers-of-iceland.herokuapp.com/rest/hikes/" + selectedHike.getId() + "/image")
                    .resize(120, 90)
                    .centerCrop()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .into(imageView);

            Button btn = (Button) itemView.findViewById(R.id.hikelist_button);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent;
                    intent = new Intent(MainActivity.this, HikeActivity.class);
                    intent.putExtra("selectedHike", selectedHike); // Pass the selected hike to next Activity
                    intent.putExtra("profile", selectedProfile); // Pass the selected hike to next Activity
                    startActivity(intent);

                }
            });
            return itemView;
        }

    }
}
