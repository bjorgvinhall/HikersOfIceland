package is.hi.hbv601g.hikers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
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
        getSupportActionBar().setTitle("Umsagnir");

        // Get the selected hike
        Intent intent = getIntent();
        Hike hike = (Hike) intent.getSerializableExtra("selectedHike");
        Profile selectedProfile = (Profile) intent.getSerializableExtra("profile");

        Button addReviewButton = findViewById(R.id.addReviewButton);
        // Update view
        EditText newReview = findViewById(R.id.newReviewField);
        RatingBar reviewRating = findViewById(R.id.reviewRating);


        List<Review> reviews = new ArrayList<>();
        reviews = hike.getReviews();
        Collections.reverse(reviews);

        ListView lv = (ListView) findViewById(R.id.review_listview);
        ReviewActivity.ListAdapter listAdapter = new ReviewActivity.ListAdapter(this, reviews, hike, selectedProfile);

        lv.setAdapter(listAdapter);

        addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hike updatedHike = null;
                try {
                    newReview(selectedProfile.getName(), newReview.getText(), reviewRating.getProgress(), hike.getId(), listAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private class ListAdapter extends BaseAdapter {
        Activity context;
        List<Review> reviews;
        Hike hike;
        Profile selectedProfile;
        private LayoutInflater inflater = null;

        public ListAdapter(Activity context, List<Review> reviews, Hike hike, Profile selectedProfile) {
            this.context = context;
            this.reviews = reviews;
            this.hike = hike;
            this.selectedProfile = selectedProfile;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        public void setData(List<Review> reviews) {
            Collections.reverse(reviews);
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
            Review selectedReview = reviews.get(position);

            itemView = (itemView == null) ? inflater.inflate(R.layout.review_list_item, null) : itemView;
            TextView reviewListDesc = (TextView) itemView.findViewById(R.id.reviewlist_desc);
            TextView reviewListRating = (TextView) itemView.findViewById(R.id.reviewlist_user);

            Button btn = (Button) itemView.findViewById(R.id.reviewlist_button);
            if(!selectedProfile.getName().equals(selectedReview.getuserId())) {
                btn.setVisibility(View.INVISIBLE);
            }

            int drawableInt = 0;
            int rating = selectedReview.getRating();

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
            ImageView imageRatingView = (ImageView) itemView.findViewById(R.id.reviewListRating);
            imageRatingView.setImageResource(drawableInt);

            reviewListDesc.setText(selectedReview.getReviewText());
            reviewListRating.setText(selectedReview.getuserId());
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Service service = new Service(context);
                    service.deleteReview(String.valueOf(hike.getId()), String.valueOf(selectedReview.getId()), new NetworkCallback<String>() {
                        @Override
                        public void onSuccess(String result) {
                            reviews.remove(selectedReview);
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(String error) {
                            Toast.makeText(getApplicationContext(), "Network error", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, error);
                        }

                    });
                }
            });
            return itemView;
        }


    }

    private void newReview(String userId, Editable newReviewText, Integer newReviewRating, long selectedHike, ListAdapter listAdapter) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", userId);
        jsonObject.put("reviewText", newReviewText);
        jsonObject.put("rating",newReviewRating);

        Service service = new Service(this);
        service.postReview(jsonObject, selectedHike, new NetworkCallback<Hike>() {
            @Override
            public void onSuccess(Hike hike) {
                Toast.makeText(getApplicationContext(), "Succesful", Toast.LENGTH_SHORT).show();
                listAdapter.setData(hike.getReviews());
                listAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String error) {
                Log.e(TAG, "onFailure: failed making a new review >>> " + error);
            }
        });
    }
}