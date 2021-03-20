package is.hi.hbv601g.hikers.Entities;

public enum ItemType {
    MOUNTAIN("mountain"),
    WILDLIFE("wildlife"),
    FLORA("flora");


    private final String mDisplayValue;

    private ItemType(String displayValue) {
        this.mDisplayValue = displayValue;
    }

    public String getDisplayValue() {
        return mDisplayValue;
    }

}
