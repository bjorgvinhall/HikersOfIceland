package is.hi.hbv601g.hikers.Entities;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private long id;
    private String password;
    private String username;
    private String name;
    private int age;
    private boolean admin;
    private List<Achievement> completedAchievements = new ArrayList<>();

    public Profile(){ }

    public Profile(String username, String password, String name, int age, boolean admin, List<Achievement> completedAchievements){
        this.username = username;
        this.password = password;
        this.name = name;
        this.age= age;
        this.admin = admin;
        this.completedAchievements = completedAchievements;
    }

    public long getId(){return id;}

    public void setId(long id) {this.id = id;}

    public String getPassword(){return password;}

    public void setPassword(String password) {this.password = password;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public boolean getAdmin() {return admin;}

    public void setAdmin(boolean priv) {this.admin = priv;}

    public boolean isAdmin() {
        return admin;
    }

    public List<Achievement> getCompletedAchievements() {
        return completedAchievements;
    }

}
