package com.assessment.inventoryManager;

import com.assessment.inventoryManager.model.Product;
import com.assessment.inventoryManager.repository.ProductRepository;
import com.assessment.inventoryManager.ui.MenuOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class InventoryManagerApplicationCLI implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagerApplicationCLI.class, args);}

    public void run(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuOption choice;
        

        do {
            displayMenu();
            choice = getUserChoice(scanner);

            switch (choice) {
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case VIEW_PRODUCTS:
                    viewProducts();
                    break;
                case SEARCH_PRODUCT:
                    searchProduct();
                    break;
                case UPDATE_PRODUCT:
                    updateProduct();
                    break;
                case DELETE_PRODUCT:
                    deleteProduct();
                    break;
                case EXIT:
                    System.out.println("Exiting the Program");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != MenuOption.EXIT);
    }

    public void addProduct() {
        Scanner console = new Scanner(System.in);
        System.out.println("==== Add Product ====");
        System.out.println("Enter product Name: ");
        String name = console.nextLine();

        int quantity;
        do {
            System.out.println("Enter Quantity: ");
            while (!console.hasNextInt()) {
                System.out.println("Please enter a valid quantity: ");
                console.next();
            }
            quantity = console.nextInt();
            console.nextLine();
        } while (quantity <= 0);
        System.out.println("Quantity :" + quantity);

        double price;
        do {
            System.out.println("Enter Price: ");
            while (!console.hasNextDouble()) {
                System.out.println("Please enter a valid price: ");
                console.next();
            }
            price = console.nextDouble();
            console.nextLine();
        } while (price <= 0);
        System.out.println("Price: " + price);

        //System.out.println("Enter the expiration date: ");
        //LocalDate expirationDate = LocalDate.parse(console.nextLine());

        Product newProduct = new Product();
        newProduct.setProductName(name);
        newProduct.setQuantity(quantity);
        newProduct.setPrice(price);
        productRepository.save(newProduct);

        System.out.println("Product added successfully!");
        System.out.println("Press Enter to return to the main menu...");
        console.nextLine();
        displayMenu();
    }

    public void viewProducts() {
        Scanner console = new Scanner(System.in);
        System.out.println("==== Inventory List ====");
        List<Product> product = productRepository.findAll();
        for (Product p : product)
        {
            System.out.println(p);
        }
        System.out.println("Press Enter to return to the main menu...");
        console.nextLine();
        displayMenu();
    }

    public void searchProduct() {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter Product ID or Name: ");

        String input = console.nextLine();
        Optional<Product> searchProduct = Optional.empty();

        try {
            int productId = Integer.parseInt(input);
            searchProduct = productRepository.findById(productId);
        } catch (NumberFormatException e) {
            searchProduct = productRepository.findByProductName(input);
        }

        if (searchProduct.isPresent()) {
            Product product = searchProduct.get();
            System.out.println("Product Found: \n"
                    + "ID: " + product.getProductId() + "\n"
                    + "Name: " + product.getProductName() + "\n"
                    + "Quantity: " + product.getQuantity() + "\n"
                    + "Price: " + product.getPrice());
        } else {
            System.out.println("Product not found.");
        }
        System.out.println("Press Enter to return to the main menu...");
        console.nextLine();
        displayMenu();
    }

    public void updateProduct() {
        Scanner console = new Scanner(System.in);
        System.out.println("==== Update Product ====");
        System.out.println("Enter Product Id: ");
        int productId = console.nextInt();
        console.nextLine();


            Optional<Product> updateProduct = productRepository.findById(productId);
            if (updateProduct.isPresent()) {
                Product product = updateProduct.get();
                System.out.println("Current Details: " + "\n Name: " + product.getProductName() + "\n Quantity: " + product.getQuantity() + "\n Price: " + product.getPrice() );

                System.out.println("Enter New Quantity (or press Enter to skip): ");
                String quantityInput = console.nextLine();
                Integer quantity = quantityInput.isEmpty() ? null : Integer.parseInt(quantityInput);

                if (quantity != null) {
                    product.setQuantity(quantity);
                }

                System.out.println("Enter New Price (or press Enter to skip):");
                String priceInput = console.nextLine();
                Double price = priceInput.isEmpty() ? null : Double.parseDouble(priceInput);

                if (price != null) {
                    product.setPrice(price);
                }

                productRepository.save(product);
                System.out.println("Product updated successfully!");

            } else {
                System.out.println("Product Id not found. Please enter a different Id.");
            }

            System.out.println("Press Enter to return to the main menu...");
            console.nextLine();
            displayMenu();
    }

    public void deleteProduct() {
        Scanner console = new Scanner(System.in);
        while (true) {
            System.out.println("==== Delete Product ====");
            System.out.println("Enter Product ID:");
            int productId = console.nextInt();
            console.nextLine();

            Optional<Product> deleteProduct = productRepository.findById(productId);
            if (deleteProduct.isPresent()) {
                Product product = deleteProduct.get();
                System.out.println("Are you sure you want to delete: " + product.getProductName() + "," + " Quantity: " + product.getQuantity() + " (Y/N)");
                String confirm = console.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    productRepository.delete(product);
                    System.out.println("Product deleted successfully!");
                    break;
                } else {
                    System.out.println("Product not deleted.");
                }
            } else {
                System.out.println("Product not found!");
            }
        }

        System.out.println("Press Enter to return to the main menu...");
        console.nextLine();
        displayMenu();
    }

    public void displayMenu() {
        System.out.println("==== Inventory Manager ====");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Exit");
        System.out.println("Enter your choice: ");
    }

    public MenuOption getUserChoice(Scanner scanner) {
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                return MenuOption.ADD_PRODUCT;
            case 2:
                return MenuOption.VIEW_PRODUCTS;
            case 3:
                return MenuOption.SEARCH_PRODUCT;
            case 4:
                return MenuOption.UPDATE_PRODUCT;
            case 5:
                return MenuOption.DELETE_PRODUCT;
            case 6:
                return MenuOption.EXIT;
            default:
                return null;
        }
    }



}
