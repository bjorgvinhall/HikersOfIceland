package is.hi.hbv601g.hikers.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Review implements Serializable {
    @SerializedName("id")
    private long mId;
    @SerializedName("userId")
    private String mUserId;
    @SerializedName("reviewText")
    private String mReviewText;
    @SerializedName("rating")
    private int mRating;

    public Review() { }

    public Review(String reviewText, int rating, Hike hike, String userId) {
        this.mUserId = userId;
        this.mReviewText = reviewText;
        this.mRating = rating;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getuserId() {
        return mUserId;
    }

    public void setuserId(String userId) {
        this.mUserId = userId;
    }

    public String getReviewText() {
        return mReviewText;
    }

    public void setReviewText(String reviewText) {
        this.mReviewText = reviewText;
    }

    public int getRating() {
        return mRating;
    }

    public void setRating(int rating) {
        this.mRating = rating;
    }

}
