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
import is.hi.hbv601g.hikers.Entities.Profile;
import is.hi.hbv601g.hikers.Entities.Review;
import is.hi.hbv601g.hikers.Networking.NetworkCallback;
import is.hi.hbv601g.hikers.Networking.Service;

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
        TextView hikeName = (TextView) findViewById(R.id.hikeName);
        hikeName.setText(hike.getName());
        List<Review> reviews = new ArrayList<>();
        reviews = hike.getReviews();

        ListView lv = (ListView) findViewById(R.id.review_listview);
        ReviewActivity.ListAdapter listAdapter = new ReviewActivity.ListAdapter(this, reviews, hike);

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
        Hike hike;
        private LayoutInflater inflater = null;

        public ListAdapter(Activity context, List<Review> reviews, Hike hike) {
            this.context = context;
            this.reviews = reviews;
            this.hike = hike;

            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }
        public void setData(List<Review> reviews) {
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
            TextView reviewListDesc = (TextView) itemView.findViewById(R.id.reviewlist_desc);
            TextView reviewListRating = (TextView) itemView.findViewById(R.id.reviewlist_rating);

            Button btn = (Button) itemView.findViewById(R.id.reviewlist_button);

            Review selectedReview = reviews.get(position);
            reviewListDesc.setText(selectedReview.getReviewText());
            reviewListRating.setText(String.valueOf(selectedReview.getRating()));
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Service service = new Service(context);
                    service.deleteReview( String.valueOf(hike.getId()), String.valueOf(selectedReview.getId()),new NetworkCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(getApplicationContext(),"Succesful",Toast.LENGTH_SHORT).show();
                            hike.getReviews().remove(selectedReview);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(String error) {
                            Toast.makeText(getApplicationContext(), "Network error",Toast.LENGTH_SHORT).show();
                            Log.e(TAG, error);
                        }

                    });
                }
            });
            return itemView;
        }


    }
}