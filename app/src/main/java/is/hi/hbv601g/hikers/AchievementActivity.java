package is.hi.hbv601g.hikers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Achievement;
import is.hi.hbv601g.hikers.Entities.Hike;
import is.hi.hbv601g.hikers.Entities.Profile;
import is.hi.hbv601g.hikers.Entities.Review;

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
        AchievementActivity.ListAdapter listAdapter = new AchievementActivity.ListAdapter(this, achievs);

        lv.setAdapter(listAdapter);
    }

    private class ListAdapter extends BaseAdapter {
        Activity context;
        List<Achievement> achievs;
        private LayoutInflater inflater = null;

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
            achievlistdiff.setText(selectedAchiev.getDescription());
            achievlistdesc.setText(String.valueOf(selectedAchiev.getDifficulty()));

            return itemView;
        }

    }
}

