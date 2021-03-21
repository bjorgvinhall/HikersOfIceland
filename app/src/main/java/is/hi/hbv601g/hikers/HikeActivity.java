package is.hi.hbv601g.hikers;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Hike;
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
    }


}
