import java.util.Scanner;

class Product {
    private String name;
    private double price;
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class ShoppingCart {
    private static String name = "Macedonia Store";
    private static int n = 0;
    private static Product[] cart = new Product[100];
    private static Product[] products = {
            new Product("Coca-Cola", 20.0),
            new Product("Fanta", 30.5),
            new Product("Chips Ahoy Cookies", 50.2),
            new Product("Doritos", 40.3),
            new Product("Apple", 10.0),
            new Product("Banana", 5.9),
            new Product("Milk", 25.0),
            new Product("Bread", 15.5),
            new Product("Eggs", 30.5),
            new Product("Chicken Breast", 100.0)
    };
    public static void main(String[] args) {
        System.out.println("-----------------------------------");
        System.out.println("| Welcome to the " + getName() + "! |");
        System.out.println("-----------------------------------");

        displayMenu();
        boolean isShopping = true;

        while(isShopping) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("-----------------------------------");
                    System.out.println("We have the following products: ");
                    displayItems(products);
                    System.out.println("-----------------------------------");
                    System.out.print("Product name: ");
                    String productName = scanner.next();
                    System.out.print("Product price: ");
                    double productPrice = scanner.nextDouble();
                    Product p = new Product(productName, productPrice);
                    System.out.println("-----------------------------------");
                    addToCart(p);
                    break;
                case 2:
                    String prName = scanner.next();
                    removeFromCart(prName);
                    break;
                default:
                    break;
            }
            System.out.print("Will you continue with shopping?: ");
            String shoppingChoice = scanner.next();
            if(shoppingChoice.equals("no")) {
                checkout();
                isShopping = false;
            }
        }
    }
    public static void displayMenu() {
        System.out.println("1. Add to cart");
        System.out.println("-----------------------------------");
        System.out.println("2. Remove from cart");
        System.out.println("-----------------------------------");
    }
    public static String getName() {
        return name;
    }
    public static void displayItems(Product[] items) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                System.out.println("Product: " + items[i].getName() + ", $" + items[i].getPrice());
            }
        }
    }
    public static void addToCart(Product pr) {
        boolean flag = false;
        for (Product product : products) {
            if ((pr.getName()).equals(product.getName())) {
                flag = true;
                break;
            }
        }
        if(flag) {
            System.out.println("You added a " + pr.getName() + " to your cart!");
            cart[n] = pr;
            n++;
            System.out.println("-----------------------------------");
            System.out.println("You have " + n + " items in the cart: ");
            displayItems(cart);
            System.out.println("-----------------------------------");
        } else {
            System.out.println("We don't have that product!");
        }
    }
    public static void removeFromCart(String productName) {
        Product[] temp = new Product[cart.length - 1];
        int tempIndex = 0;
        for (Product product : cart) {
            if (product != null && !productName.equals(product.getName())) {
                temp[tempIndex++] = product;
            }
        }
        cart = temp;
        System.out.println("You have removed " + productName + " from your cart!");
        displayItems(cart);
    }

    public static void checkout() {
        double total = 0.0;
        for (Product pr : cart) {
            if (pr != null) {
                total += pr.getPrice();
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Your order costs: " + total + "\nEnter your cash: ");
        double cash = scanner.nextDouble();

        if (cash < total) {
            System.out.println("Sorry you don't have enough money!\nPlease make your order again!");
        } else {
            System.out.println("Thank you for your shopping!");
            if (cash > total) {
                System.out.println("Your change is: " + (cash - total));
            }
            System.out.println("Bye!");
        }
    }
}
