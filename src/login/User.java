package login;

import java.util.ArrayList;

public class User {
    private  long id;
    private String userName;
    private String password;
    private String role;
    private ArrayList<Role>allRoles;

    public User(){
        allRoles = new ArrayList<>();
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.allRoles = allRoles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ArrayList<Role> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(ArrayList<Role> allRoles) {
        this.allRoles = allRoles;
    }

    public void addRole(Role role){
        allRoles.add(role);
    }
}
