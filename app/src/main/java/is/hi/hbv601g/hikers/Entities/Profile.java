package is.hi.hbv601g.hikers.Entities;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private long mId;
    private String mPassword;
    private String mUsername;
    private String mName;
    private int mAge;
    private boolean mAdmin;
    private List<Achievement> mCompletedAchievements = new ArrayList<>();

    public Profile(){ }

    public Profile(String username, String password, String name, int age, boolean admin, List<Achievement> completedAchievements){
        this.mUsername = username;
        this.mPassword = password;
        this.mName = name;
        this.mAge = age;
        this.mAdmin = admin;
        this.mCompletedAchievements = completedAchievements;
    }

    public long getId(){return mId;}

    public void setId(long id) {this.mId = id;}

    public String getPassword(){return mPassword;}

    public void setPassword(String password) {this.mPassword = password;}

    public String getUsername() {return mUsername;}

    public void setUsername(String username) {this.mUsername = username;}

    public String getName() {return mName;}

    public void setName(String name) {this.mName = name;}

    public int getAge() {return mAge;}

    public void setAge(int age) {this.mAge = age;}

    public boolean getAdmin() {return mAdmin;}

    public void setAdmin(boolean priv) {this.mAdmin = priv;}

    public boolean isAdmin() {
        return mAdmin;
    }

    public List<Achievement> getCompletedAchievements() {
        return mCompletedAchievements;
    }

}
