import java.util.*;

class Product {
    int id;
    String name;
    double price;
    int quantity;

    Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    void display() {
        System.out.println("ID: " + id + " | Name: " + name + " | Price: $" + price + " | Qty: " + quantity);
    }
}

class CartItem {
    Product product;
    int qty;

    CartItem(Product product, int qty) {
        this.product = product;
        this.qty = qty;
    }
}

public class ECommerceApp {
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<CartItem> cart = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static int nextId = 1;

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n======== E-COMMERCE MENU ========");
            System.out.println("1. Insert Product");
            System.out.println("2. Display All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Add to Cart");
            System.out.println("6. View Cart");
            System.out.println("7. Cashout");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> insertProduct();
                case 2 -> displayProducts();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> addToCart();
                case 6 -> viewCart();
                case 7 -> cashout();
                case 8 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 8);
    }

    static void insertProduct() {
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        products.add(new Product(nextId++, name, price, qty));
        System.out.println("Product added successfully!");
    }

    static void displayProducts() {
        if (products.isEmpty()) {
            System.out.println("No products in the store.");
            return;
        }
        System.out.println("\n--- Product List ---");
        for (Product p : products) {
            p.display();
        }
    }

    static void updateProduct() {
        System.out.print("Enter product ID to update: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Product p : products) {
            if (p.id == id) {
                System.out.print("Enter new name: ");
                p.name = sc.nextLine();
                System.out.print("Enter new price: ");
                p.price = sc.nextDouble();
                System.out.print("Enter new quantity: ");
                p.quantity = sc.nextInt();
                sc.nextLine();
                System.out.println("Product updated!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    static void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).id == id) {
                products.remove(i);
                System.out.println("Product deleted!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    static void addToCart() {
        if (products.isEmpty()) {
            System.out.println("No products available to buy.");
            return;
        }

        displayProducts();
        System.out.print("Enter product ID to add to cart: ");
        int id = sc.nextInt();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        for (Product p : products) {
            if (p.id == id) {
                if (qty > p.quantity) {
                    System.out.println("Only " + p.quantity + " in stock!");
                    return;
                }
                cart.add(new CartItem(p, qty));
                System.out.println(qty + " x " + p.name + " added to cart!");
                return;
            }
        }
        System.out.println("Product not found!");
    }

    static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        System.out.println("\n--- Your Cart ---");
        double total = 0;
        for (CartItem ci : cart) {
            double sub = ci.product.price * ci.qty;
            System.out.println(ci.product.name + " x " + ci.qty + " = $" + sub);
            total += sub;
        }
        System.out.println("Total: $" + total);
    }

    static void cashout() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }

        System.out.println("\n--- INVOICE ---");
        double grandTotal = 0;
        for (CartItem ci : cart) {
            double sub = ci.product.price * ci.qty;
            System.out.println(ci.product.name + " x " + ci.qty + " = $" + sub);
            grandTotal += sub;
            ci.product.quantity -= ci.qty;
        }
        System.out.println("---------------------");
        System.out.println("GRAND TOTAL: $" + grandTotal);
        System.out.println("Thank you for your purchase!");

        cart.clear();
    }
}
