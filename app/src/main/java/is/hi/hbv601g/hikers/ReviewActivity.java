package is.hi.hbv601g.hikers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import is.hi.hbv601g.hikers.Entities.Review;

public class ReviewActivity extends AppCompatActivity {
    private static final String TAG = "ReviewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        // Get the selected hike
        Intent intent = getIntent();
        Hike hike = (Hike) intent.getSerializableExtra("selectedHike");
        Button addReviewButton = findViewById(R.id.addReviewButton);
        // Update view
        TextView hikeName = (TextView) findViewById(R.id.hike_name);

        List<Review> reviews = new ArrayList<>();
        reviews = hike.getReviews();

        ListView lv = (ListView) findViewById(R.id.review_listview);
        ReviewActivity.ListAdapter listAdapter = new ReviewActivity.ListAdapter(this, reviews);

        lv.setAdapter(listAdapter);

        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();

            }
         });
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

                    Toast.makeText(getApplicationContext(), "Not implemented", Toast.LENGTH_SHORT).show();

                }
            });
            return itemView;
        }


    }
}