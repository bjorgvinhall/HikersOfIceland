package is.hi.hbv601g.hikers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Hike;
import is.hi.hbv601g.hikers.Entities.Item;
import is.hi.hbv601g.hikers.Entities.Profile;
import is.hi.hbv601g.hikers.Entities.Review;
import is.hi.hbv601g.hikers.Networking.NetworkCallback;
import is.hi.hbv601g.hikers.Networking.Service;

public class HikeActivity extends AppCompatActivity {
    private static final String TAG = "HikeActivity";
    private static Hike hike;
    Service service = new Service(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike);
        TextView hikeName = (TextView) findViewById(R.id.hike_name);
        TextView hikeDesc = (TextView) findViewById(R.id.hike_desc);
        ImageView imageView = (ImageView) findViewById(R.id.hike_image);

        // Get the selected hike and profile
        Intent intent = getIntent();
        hike = (Hike) intent.getSerializableExtra("selectedHike");
        Profile selectedProfile = (Profile) intent.getSerializableExtra("profile");

        // Update view
        hikeName.setText(hike.getName());
        hikeDesc.setText(hike.getDescription());
        hikeDesc.setMovementMethod(new ScrollingMovementMethod());
        Picasso.get()
                .load( "https://hikers-of-iceland.herokuapp.com/rest/hikes/" + hike.getId() + "/image")
                .resize(205, 100)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.placeholder)
                .into(imageView);


        Button btn = (Button) findViewById(R.id.reviewButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(HikeActivity.this, ReviewActivity.class);
                intent.putExtra("selectedHike", hike); // Pass the selected hike to next Activity
                intent.putExtra("profile", selectedProfile);
                startActivity(intent);

            }

        });
        Button btn2 = (Button) findViewById(R.id.achievButton);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(HikeActivity.this, AchievementActivity.class);
                intent.putExtra("selectedHike", hike); // Pass the selected hike to next Activity
                intent.putExtra("profile", selectedProfile);
                startActivity(intent);

            }
        });
        Button btn3 = (Button) findViewById(R.id.itemButton);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(HikeActivity.this, ItemActivity.class);
                intent.putExtra("selectedHike", hike); // Pass the selected hike to next Activity
                intent.putExtra("profile", selectedProfile);
                startActivity(intent);

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        // update the data
        service.getHikeById(hike.getId(), new NetworkCallback<Hike>() {
            @Override
            public void onSuccess(Hike result) {
                hike = result;
            }

            @Override
            public void onFailure(String error) {
                Log.e(TAG, "Request failed: "  + error);
                Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



