package auth;

import java.io.*;
import java.util.ArrayList;

public class LoginSystem{
    private ArrayList<User> users = new ArrayList();
    private final static String userfilepath = "D:\\users.dat";
    private User loggedInUser;

    
    public LoginSystem() {
        this.users = new ArrayList<>();
        loadUsers();
    }
    
    public boolean register(String email, String username, String password) {
    if(!isValidEmail(email)){
        System.out.println("Invalid email format");
        return false;
    }
    
    for(User user: users){
        if(user.getEmail().equals(email) || user.getUsername().equals(username)){
            System.out.println("User already exists.");
            return false;
        }
    }
    
    users.add(new User(generateUserID(),email,username,password));
    saveUsers();
    return true;
        
    }
    
    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) || user.getEmail().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                return true;
            }
        }
        return false;
    }
    
    public boolean resetPassword(String email, String newPassword) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setPassword(newPassword);
                saveUsers();
                return true;
            }
        }
        return false;
    }
    
    public void logout() {
    if (loggedInUser != null) {
        System.out.println("User " + loggedInUser.getUsername() + " logged out successfully.");
        loggedInUser = null; // Clear the logged-in user
    } else {
        System.out.println("No user is currently logged in.");
    }
}
    
    
    
    
    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    private String generateUserID() {
        int userNumber = users.size() + 1; 
        return String.format("USR%03d", userNumber);
    }
    
    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userfilepath))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userfilepath))) {
            users = (ArrayList<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No user data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    
}
































/*package auth;

import java.io.*;
import java.util.ArrayList;

public class LoginSystem {
    private ArrayList<User> users;
    private final String FILE_NAME = "D:\\users.dat";

    public LoginSystem() {
        this.users = new ArrayList<>();
        loadUsers();
    }

    public boolean register(String userID, String username, String password, String email) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        users.add(new User(userID, username, password, email));
        saveUsers();
        return true;
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean resetPassword(String email, String newPassword) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                user.setPassword(newPassword);
                saveUsers();
                return true;
            }
        }
        return false;
    }

    public void logout() {
        System.out.println("User logged out.");
           }

    private void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            users = (ArrayList<User>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No user data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }
}*/