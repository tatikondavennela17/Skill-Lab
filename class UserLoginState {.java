class UserLoginState {
    private static UserLoginState instance;
    private boolean loggedIn;
    private String username;
    private UserLoginState() {
        this.loggedIn = false;
        this.username = null;
    }
    public static UserLoginState getInstance() {
        if (instance == null) {
            instance = new UserLoginState();
        }
        return instance;
    }
    public void login (String username) {
        if (!loggedIn) {
            this.username = username;
            this.loggedIn = true;
            System.out.println("User " + username + " logged in.");
        } else {
            System.out.println("User " + this.username + " is already logged in.");
        }
    }
    public void logout() {
        if (loggedIn) {
            System.out.println("User " + this.username + " logged out.");
            this.username = null;
            this.loggedIn = false;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void viewBalance() {
        if (loggedIn) {
            System.out.println("Displaying balance for " + username);
        } else {
            System.out.println("Please log in to view balance.");
        }
    }
    public void deposit (double amount) {
        if (loggedIn) {
            System.out.println("Depositing $" + amount + " for " + username);
        } else {
            System.out.println("Please log in to deposit money.");
        }
    }
    public void withdraw (double amount) {
        if (loggedIn) {
            System.out.println("Withdrawing $" + amount + " for " + username);
            } else {
            System.out.println("Please log in to withdraw money.");
        }
    }
}
class BankingApp {
    public static void main (String [] args) {
        UserLoginState loginState = UserLoginState.getInstance();
        loginState.viewBalance();
        loginState.viewBalance();
        loginState.logout();
    }
}

