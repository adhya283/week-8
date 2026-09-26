interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Processing Credit Card payment...");
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        System.out.println("Processing PayPal payment...");
        return false;
    }
}

class Product {
    String name;
    double price;
    int quantity;

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    double getTotal() {
        return price * quantity;
    }
}

class Order {
    String customer;
    Product[] products;
    int count = 0;
    String status = "Pending";

    Order(String customer) {
        this.customer = customer;
        products = new Product[10];
    }

    void addProduct(Product p) {
        products[count++] = p;
    }

    double getTotal() {
        double total = 0;

        for (int i = 0; i < count; i++) {
            total += products[i].getTotal();
        }

        return total;
    }

    void pay(PaymentMethod method) {

        if (count == 0) {
            System.out.println(
                "Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated via "
                + method.getClass().getSimpleName()
                + " for Order " + customer);

        boolean success = method.processPayment(getTotal());

        if (success) {
            status = "Paid";
            System.out.println("Payment for Order "
                    + customer + " successful.");
        } else {
            System.out.println("Payment for Order "
                    + customer + " failed.");
        }

        System.out.println("Order status: " + status);
    }
}

public class PaymentSystem {
    public static void main(String[] args) {

        Order orderX = new Order("X");

        orderX.addProduct(new Product("Product A", 100, 2));
        orderX.addProduct(new Product("Product B", 50, 1));

        System.out.println("Order created for Customer X.");

        orderX.pay(new CreditCardPayment());

        Order orderY = new Order("Y");

        System.out.println("Order created for Customer Y.");
        orderY.pay(new CreditCardPayment());

        Order orderZ = new Order("Z");

        orderZ.addProduct(new Product("Product C", 200, 1));

        System.out.println("Order created for Customer Z.");

        orderZ.pay(new PayPalPayment());
    }
}