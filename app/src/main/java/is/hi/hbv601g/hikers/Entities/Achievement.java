package is.hi.hbv601g.hikers.Entities;

public class Achievement {
    private long id;
    private String name;
    private String description;
    private int difficulty;

    public Achievement(String name, String description, int difficulty, Hike hike) {
        this.name = name;
        this.description = description;
        this.difficulty = difficulty;
    }
    public Achievement() {
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

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

}
