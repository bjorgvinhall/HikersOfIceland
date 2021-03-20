package is.hi.hbv601g.hikers.Entities;

import com.google.gson.annotations.SerializedName;

public class Item {
    @SerializedName("id")
    private long mId;
    @SerializedName("name")
    private String mName;
    @SerializedName("description")
    private String mDescription;
    @SerializedName("itemType")
    private ItemType mItemType;
    @SerializedName("image")
    private String mImage;

    public Item(String name, String description, ItemType itemType, String image, Hike hike) {
        this.mName = name;
        this.mDescription = description;
        this.mItemType = itemType;
        this.mImage = image;
    }

    public Item() {
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

    public String getImage() {
        return mImage;
    }

    public void setImage(String image) {
        this.mImage = image;
    }

    public ItemType getItemType() {
        return mItemType;
    }

    public void setItemType(ItemType itemType) {
        this.mItemType = itemType;
    }

}
