package is.hi.hbv601g.hikers.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@SuppressWarnings("serial")
public class Hike implements Serializable {
    @SerializedName("id")
    private long mId;
    @SerializedName("achievements")
    private List<Achievement> mAchievements;
    @SerializedName("items")
    private List<Item> mItems;
    @SerializedName("reviews")
    private List<Review> mReviews;
    @SerializedName("name")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("location")
    private String mLocation;
    @SerializedName("image")
    private String mImage;

    public Hike() {

    }

    public Hike(long id, String name, String description, String location, String image, List<Achievement> achievements){
        this.mId = id;
        this.mName = name;
        this.mDescription = description;
        this.mLocation = location;
        this.mImage = image;
        this.mAchievements = achievements;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }

    public List<Achievement> getAchievements() {
        Collections.reverse(mAchievements);
        return mAchievements;
    }

    public void setAchievements(List<Achievement> achievements) {
        this.mAchievements = achievements;
    }

    public List<Review> getReviews() {
        Collections.reverse(mReviews);
        return mReviews;
    }

    public void setReviews(List<Review> reviews) {
        this.mReviews = reviews;
    }

    public List<Item> getItems() {
        return mItems;
    }

    public void setItems(List<Item> items) {
        this.mItems = items;
    }
}
