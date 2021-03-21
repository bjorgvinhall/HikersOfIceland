package is.hi.hbv601g.hikers.Entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Achievement implements Serializable {
    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("difficulty")
    private int mDifficulty;

    public Achievement(String name, String description, int difficulty, Hike hike) {
        this.mName = name;
        this.mDescription = description;
        this.mDifficulty = difficulty;
    }
    public Achievement() {
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

    public int getDifficulty() {
        return mDifficulty;
    }

    public void setDifficulty(int difficulty) {
        this.mDifficulty = difficulty;
    }

}
