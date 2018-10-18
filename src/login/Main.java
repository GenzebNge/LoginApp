package login;

import com.sun.org.apache.xpath.internal.SourceTree;

import javax.jws.soap.SOAPBinding;
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

           System.out.println("Select from the options " +
                   "\n1. Sign Up \n2. List Users \n3. Add or Create New role \n4. Liste Roles \n5. Add Role to Users \n6. Login \n7. List Users with their role ");

            userOptions = keyBord.nextInt();
            keyBord.nextLine();
            String yesOrNo ="";

        switch (userOptions){
            case 1: //Sign up or create a  new user. This loops as far as uer need to create a new user.

                do{
                    User newUser = new User();
                    System.out.println("Create Users");

                    System.out.println("Create an id");
                    long newIdNumber = keyBord.nextLong();
                    keyBord.nextLine();

                    newUser.setId(newIdNumber);

                    System.out.println("Create user name");
                    String newUserName = keyBord.nextLine();

                    newUser.setUserName(newUserName);

                    System.out.println("Create Password");
                    String newPassword = keyBord.nextLine();

                    newUser.setPassword(newPassword);
                    System.out.println("Do you want to Enter Role Here");
                    String needRole = keyBord.nextLine();
                    if (needRole.equalsIgnoreCase("yes")){
                        addRoleHere(keyBord, allRole);
                    }
                    allUsers.add(newUser);

                    System.out.println("Do you want to create an other user?");
                    yesOrNo = keyBord.nextLine();

                }while (yesOrNo.equalsIgnoreCase("yes"));
                break;

            case 2: //list users which are created at case 1. i need to print this in table format??

                for (User newUsers : allUsers){
                    System.out.println("\nUser Id: " + newUsers.getId() + "\nUserName: " + newUsers.getUserName() + "\nPassword: " + newUsers.getPassword());
                }
                break;

            case 3: //Add new roles (Admin, Editor, Contributor or other
                System.out.println("Add A role");
                do{
                    Role newRole = new Role();

                    String tryAgain ="";
                    do{
                        System.out.println("Enter Role Id");
                        long id  = keyBord.nextLong();
                        keyBord.nextLine();

                        newRole.setId(id);

                        System.out.println("Enter Role Name");
                        String name = keyBord.nextLine();

                        newRole.setName(name);

                        Role value  = checkRoleIdAndRoleName(id,name, allRole, keyBord);
                        if (value == null){
                            System.out.println(" Role does not exist and is added to the role list ");
                            allRole.add(newRole);
                        }else {
                            System.out.println("Role exist, do you want to try again ?");

                         tryAgain = keyBord.nextLine();

                    }
                    }while (tryAgain.equalsIgnoreCase("yes"));

                    System.out.println("Do you want to add more role? Yes/No");
                    yesOrNo = keyBord.nextLine();
                 }  while (yesOrNo.equalsIgnoreCase("yes"));

                break;
            case 4: // List role added

                for (Role rooleAdded : allRole){
                    System.out.println("\n Role ID: " + rooleAdded.getId() + "\nRole Name: " + rooleAdded.getName());
                }
                break;

            case 5://Add role to user

                System.out.println("Enter role id you want to add to the user");
                long roleId = keyBord.nextLong();
                keyBord.nextLine();
                System.out.println("Enter User Id you want to add Role ");
                long uId = keyBord.nextLong();
                keyBord.nextLine();

                Role role = findRoleBy(roleId, allRole);
                User user1 = findUserBy(uId, allUsers);


                    if (user1.getAllRoles().contains(role)){
                        System.out.println("User already has role and the role is : " + user1.getAllRoles());
                        return;
                    }else {
                        user1.getAllRoles().add(role);
                    }



//                Role role = findRoleBy(roleId, allRole);
//                for (User eachUser: allUsers){
//                    if (eachUser.getAllRoles().contains(role)){
//                        eachUser.getAllRoles().add(role);
//                    }
//                }
                break;

            case 6 : // Login

                System.out.println("Login");
                System.out.println("Enter User name ");
                String userName = keyBord.nextLine();
                System.out.println("Enter your Password ");
                String userPassword = keyBord.nextLine();

               if (checkUserNameAndPassword(userName, userPassword,allUsers)!=null) {

                   for (Role eachRole : allRole) {
                       System.out.println(" RoleId: " + eachRole.getId() + " \nRoleNAme: " + eachRole.getName());
                   }
               }else{
                   System.out.println("You used Wrong user name and password");
               }
                break;

            case 7 : // Show list of Users with Their role

                for (User allMyUsers :allUsers){
                    System.out.println("Users: " + allMyUsers.getId() + " ThisUserRole " + allMyUsers.getAllRoles());
                    for (Role myRoles :allRole){
                        System.out.println("Role Id: " + myRoles.getId() + " Role Name: " + myRoles.getName());
                    }
                }
                break;

        }

        }while(userOptions!=8);
    }

    private static Role checkRoleIdAndRoleName(long newId,String roleName, ArrayList<Role>allRoles, Scanner keyBord) {

            Role roleIdAndName = null;
            for (Role existingRole : allRoles) {
                if (existingRole.getId() == newId && existingRole.getName().equalsIgnoreCase(roleName)) {
                    roleIdAndName = existingRole;
                   return roleIdAndName;
                }
            }
            return roleIdAndName;
    }

    private static void addRoleHere(Scanner keyBord, ArrayList<Role>allRole) {
        String yesOrNo="";
        do {
            Role newRole = new Role();

            System.out.println("Enter Role Id");
            long id = keyBord.nextLong();
            keyBord.nextLine();

            newRole.setId(id);

            System.out.println("Enter Role Name");
            String name = keyBord.nextLine();

            newRole.setName(name);

            allRole.add(newRole);
            System.out.println("Do you want to add more role? Yes/No");
            yesOrNo = keyBord.nextLine();
        }while(yesOrNo.equalsIgnoreCase("yes"));
    }

//    private static String checkPassword(String userPassword, ArrayList<User>allUsers) {
//        String foundPassword ="";
//
//        for (User myPassword : allUsers){
//            if (myPassword.getPassword().contains(userPassword)) {
//                foundPassword = myPassword.getPassword();
//            }
//        }
//        return foundPassword;
//    }

    private static User checkUserNameAndPassword(String userName,String password, ArrayList<User>allUsers ) {
        User userFound = null;

        for (User myUsers : allUsers)
        if (myUsers.getUserName().contains(userName) && myUsers.getPassword().contains(password) ){
            userFound = myUsers;

        }
        return userFound;

    }

    private static Role findRoleBy(long roleId, ArrayList<Role>lookHere) {
        Role foundRole = null;
        for (Role role : lookHere){
            if (role.getId().equals(roleId)){
                foundRole = role;
            }
        }
        return foundRole;
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
