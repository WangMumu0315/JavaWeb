package cdu.nls.app.model;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String name;
    private String password;
    private int isAdmin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin == 1 ? true : false;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return "User: " +
                id +
                ", " + name +
                ", " + password;
    }
}
