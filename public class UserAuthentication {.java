 class UserAuthentication {
    private static UserAuthentication instance;
    private boolean authenticated;
    private String userName;
    private UserAuthentication() {
        this.authenticated = false;
    }
    public static UserAuthentication getInstance() {
        if (instance == null) {
            synchronized (UserAuthentication.class) {
                if (instance == null) {
                    instance = new UserAuthentication();
                }
            }
        }
        return instance;
    }
    public void login (String userName) {
        if (!authenticated) {
            this.userName = userName;
            authenticated = true;
            System.out.println("User " + userName + " logged in.");
        } else {
            System.out.println("User " + this.userName + " is already logged in.");
        }
    }
    public void logout() {
        if (authenticated) {
            System.out.println("User " + userName + " logged out.");
            userName = null;
            authenticated = false;
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
    public boolean isAuthenticated() {
        return authenticated;
    }
    public String getUserName() {
        if (authenticated) {
            return userName;
        } else {
            return "No user is authenticated.";
        }
    }
}
abstract class Vehicle {
    public abstract void ride();
}

class Car extends Vehicle {
    public void ride() {
        System.out.println("Riding a car!");
    }
}

class Bike extends Vehicle {
    public void ride() {
        System.out.println("Riding a bike!");
    }
}
class Scooter extends Vehicle {
    public void ride() {
        System.out.println("Riding a scooter!");
    }
}
abstract class VehicleFactory {
    public abstract Vehicle createVehicle();
}
class CarFactory extends VehicleFactory {
    public Vehicle createVehicle() {
        return new Car();
    }
}
class BikeFactory extends VehicleFactory {
    public Vehicle createVehicle() {
        return new Bike();
    }
}
class ScooterFactory extends VehicleFactory {
    public Vehicle createVehicle() {
        return new Scooter();
    }
}
interface PaymentMethod {
    void pay(double amount);
}
class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}
class PayPalPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}
interface PaymentFactory {
    PaymentMethod createPaymentMethod();
}
class CreditCardPaymentFactory implements PaymentFactory {
    public PaymentMethod createPaymentMethod() {
        return new CreditCardPayment();
    }
}
class PayPalPaymentFactory implements PaymentFactory {
    public PaymentMethod createPaymentMethod() {
        return new PayPalPayment();
    }
}
 class Main {
    public static void main(String[] args) {
        UserAuthentication userAuth = UserAuthentication.getInstance();
        userAuth.login("Alice");
        if (userAuth.isAuthenticated()) {
            VehicleFactory carFactory = new CarFactory();
            Vehicle car = carFactory.createVehicle();
            car.ride();

            VehicleFactory bikeFactory = new BikeFactory();
            Vehicle bike = bikeFactory.createVehicle();
            bike.ride();
            PaymentFactory creditCardPaymentFactory = new CreditCardPaymentFactory();
            PaymentMethod creditCard = creditCardPaymentFactory.createPaymentMethod();
            creditCard.pay(20.00);

            PaymentFactory payPalPaymentFactory = new PayPalPaymentFactory();
            PaymentMethod payPal = payPalPaymentFactory.createPaymentMethod();
            payPal.pay(15.00);
        }
        userAuth.logout();
    }
}

