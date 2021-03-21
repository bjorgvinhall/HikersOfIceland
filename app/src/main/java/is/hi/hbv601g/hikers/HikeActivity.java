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
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import is.hi.hbv601g.hikers.Entities.Hike;
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

        List<Review> reviews = new ArrayList<>();
        reviews = hike.getReviews();

        ListView lv = (ListView) findViewById(R.id.hike_listview);
        HikeActivity.ListAdapter listAdapter = new HikeActivity.ListAdapter(this, reviews);

        lv.setAdapter(listAdapter);
    }

    private class ListAdapter extends BaseAdapter {
        Activity context;
        List<Review> reviews;
        private LayoutInflater inflater = null;

        public ListAdapter(Activity context, List<Review> reviews) {
            this.context = context;
            this.reviews = reviews;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public void setData(List<Hike> hikes) {
            this.reviews = reviews;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return reviews.size();
        }

        @Override
        public Object getItem(int i) {
            return reviews.get(i);
        }

        @Override
        public long getItemId(int i) {
            return reviews.get(i).getId();
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View itemView = convertView;
            itemView = (itemView == null) ? inflater.inflate(R.layout.review_list_item, null): itemView;
            TextView textViewDesc = (TextView) itemView.findViewById(R.id.reviewlist_desc);
            TextView textViewRating = (TextView) itemView.findViewById(R.id.reviewlist_rating);

            Button btn = (Button) itemView.findViewById(R.id.reviewlist_button);

            Review selectedReview = reviews.get(position);
            textViewDesc.setText(selectedReview.getReviewText());
            textViewRating.setText(String.valueOf(selectedReview.getRating()));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //delete Review if userid = current useid
                    Toast.makeText(getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();

                }
            });
            return itemView;
        }


    }
}



