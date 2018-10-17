package login;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<User>allUsers = new ArrayList<>();
        ArrayList<Role>allRole = new ArrayList<>();
        Scanner keyBord = new Scanner(System.in);

	// write your code here
        int userOptions =0;
        do{

        System.out.println("Select from the options \n1. Sign Up \n2. Add new Role \n3. Add Role to User \n4. Login \n5. List Users with their role");
            userOptions = keyBord.nextInt();
            keyBord.nextLine();
            String yesOrNo ="";
        switch (userOptions){
            case 1: //Sign up/ create users

                do{
                    User newUser = new User();
                    System.out.println("Create Users");
                    System.out.println("Create an id");
                    long idNumber = keyBord.nextLong();
                    keyBord.nextLine();
                    System.out.println("Create user name");
                    String name = keyBord.nextLine();
                    System.out.println("Create Password");
                    String password = keyBord.nextLine();
                    allUsers.add(newUser);
                    System.out.println("Do you wan to create an other user?");
                    yesOrNo = keyBord.nextLine();

                }while (yesOrNo.equalsIgnoreCase("yes"));
                break;

            case 2: //Add new roles

                do{
                    Role newRole = new Role();
                    System.out.println("Add A role");
                    System.out.println("Enter Role Id");
                    long id  = keyBord.nextLong();
                    keyBord.nextLine();
                    newRole.setId(id);
                    System.out.println("Enter Role Name");
                    String name = keyBord.nextLine();
                    newRole.setName(name);
                    allRole.add(newRole);
                    System.out.println("Do you want to add more role? Yes/No");
                    yesOrNo = keyBord.nextLine();
                 }  while (yesOrNo.equalsIgnoreCase("yes"));

                break;

            case 3://Add role to user

                System.out.println("Enter role id you want to add to the user");
                long roleId = keyBord.nextLong();
                keyBord.nextLine();

                Role role = findRoleBy(roleId, allRole);
                for (User eachUser: allUsers){
                    if (eachUser.getAllRoles().contains(role)){
                        eachUser.getAllRoles().add(role);
                    }
                }



                break;

            case 4 : // Login

                System.out.println("Login");
                System.out.println("Enter User name ");
                String userName = keyBord.nextLine();
                System.out.println("Enter your Password ");
                String userPassword = keyBord.nextLine();

                for ( Role eachrole : allRole){
                    System.out.println(" RoleId: " + eachrole.getId() + " \nRoleNAme: " +eachrole.getName());
                }
                break;

            case 5 : // Show list of Users with Their role

                for (User allMyUsers :allUsers){
                    System.out.println("Users: " + allMyUsers.getId() + allMyUsers.getAllRoles());
                    for (Role myRoles :allRole){
                        System.out.println("Role Id: " + myRoles.getId() + " Role Name: " + myRoles.getName());
                    }
                }
                break;

        }

        }while(userOptions!=6);
    }

    private static Role findRoleBy(long roleId, ArrayList<Role>lookHere) {
        Role foundRoleId = null;
        for (Role role : lookHere){
            if (role.getId().equals(roleId)){
                foundRoleId = role;
            }
        }
        return foundRoleId;
    }

    private static void addRoleToUser(Scanner keyBord,ArrayList<User>allUsers, User users) {
        int roleChoice = 0;
        System.out.println("Do you like to add a new Role or take from the existing roles?  \n1. Take From Existing \n2. Add a new Role");
        roleChoice = keyBord.nextInt();
        keyBord.nextLine();

        switch (roleChoice){
            case 1:
                System.out.println("Enter role Id ");
                long roleIdIn = keyBord.nextLong();
               for (User user1 : allUsers){
                   if(roleIdIn == user1.getId()){

                      //users.addRole(role);
                   }
               }
                break;

        }
    }

    private static User findUserBy(long userId, ArrayList<User>lookHere) {
        User foudUserId = null;
        for (User eachUserId :lookHere)
            if (userId == eachUserId.getId())
                foudUserId = eachUserId;
        return foudUserId;
    }
}
