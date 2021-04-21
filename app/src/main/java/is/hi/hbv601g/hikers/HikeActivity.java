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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Hike;
import is.hi.hbv601g.hikers.Entities.Item;
import is.hi.hbv601g.hikers.Entities.Review;
import is.hi.hbv601g.hikers.Networking.NetworkCallback;
import is.hi.hbv601g.hikers.Networking.Service;

public class HikeActivity extends AppCompatActivity {
    private static final String TAG = "HikeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike);
        TextView hikeName = (TextView) findViewById(R.id.hike_name);
        TextView hikeDesc = (TextView) findViewById(R.id.hike_desc);

        // Get the selected hike
        Intent intent = getIntent();
        Hike hike = (Hike) intent.getSerializableExtra("selectedHike");

        // Update view
        hikeName.setText(hike.getName());
        hikeDesc.setText(hike.getDescription());

        Button btn = (Button) findViewById(R.id.reviewButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(HikeActivity.this, ReviewActivity.class);
                intent.putExtra("selectedHike", hike); // Pass the selected hike to next Activity
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
                startActivity(intent);

            }
        });
    }
}



