package is.hi.hbv601g.hikers.Entities;

public class Review {
    private long mId;
    private long mUserId;
    private String mReviewText;
    private int mRating;

    public Review() { }

    public Review(String reviewText, int rating, Hike hike, long userId) {
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

    public long getuserId() {
        return mUserId;
    }

    public void setuserId(long userId) {
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
