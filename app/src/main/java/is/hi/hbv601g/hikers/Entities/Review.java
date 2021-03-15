package is.hi.hbv601g.hikers.Entities;

public class Review {
    private long id;
    private long userId;
    private String reviewText;
    private int rating;

    public Review() { }

    public Review(String reviewText, int rating, Hike hike, long userId) {
        this.userId = userId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getuserId() {
        return userId;
    }

    public void setuserId(long userId) {
        this.userId = userId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
