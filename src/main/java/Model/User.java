package Model;

import java.io.Serializable;

/**
 * Creaaza un utilizator in fucntie de rankul acestuia
 */

public class User implements Serializable {
    private final int id;
    private String username;
    private String pass;
    private Rank rank;
    private static int i = 0;

    public User( String username, String pass, Rank rank) {

        this.id = i;
        i++;              //de cate ori se creaza un client ou, creste i-ul si o sa am un id unic pt fiecare;(in  limita de i)
        this.username = username;
        this.pass = pass;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }



}
