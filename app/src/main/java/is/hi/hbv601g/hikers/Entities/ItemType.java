package is.hi.hbv601g.hikers.Entities;

public enum ItemType {
    MOUNTAIN("mountain"),
    WILDLIFE("wildlife"),
    FLORA("flora");


    private final String displayValue;

    private ItemType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
