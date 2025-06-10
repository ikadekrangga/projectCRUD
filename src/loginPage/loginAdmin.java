package loginPage;

import parent.user;

public class loginAdmin extends user {
    private login Login;

    public loginAdmin(String name, String email, String password) {
        super(name, email, password);  // Calling constructor from superclass 'user'
        this.Login = new login();  // Creating an instance of login
    }

    // Method to verify admin login
    public boolean adminLogin() {
        var scanner = new java.util.Scanner(System.in);

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Using the login object to verify the admin credentials
        boolean isLoggedIn = Login.loginUser(email, password);

        if (isLoggedIn) {
            System.out.println("Welcome, " + getName() + " (Admin).");
            return true;  // Return true if login is successful
        } else {
            System.out.println("Login failed!");
            return false;  // Return false if login fails
        }
    }
}
