package uoft.csc207.games.model;

import java.io.Serializable;
import java.util.TreeMap;

public class PlayerProfile implements Serializable {
    public PlayerProfile(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String id;
    private String password;
    private TreeMap games;

}
