package is.hi.hbv601g.hikers.Entities;

public class Item {
    private long id;
    private String name;
    private String description;
    private ItemType itemType;
    private String image;

    public Item(String name, String description, ItemType itemType, String image, Hike hike) {
        this.name = name;
        this.description = description;
        this.itemType = itemType;
        this.image = image;
    }

    public Item() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

}
