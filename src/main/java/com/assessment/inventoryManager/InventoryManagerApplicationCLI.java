package com.assessment.inventoryManager;

import com.assessment.inventoryManager.model.Product;
import com.assessment.inventoryManager.repository.ProductRepository;
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
        //test add product
        //addProduct();

        //test update product
        updateProduct();

    }

    public void addProduct() {
        Scanner console = new Scanner(System.in);
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
        System.out.println("Press enter to return to the main menu...");
        console.nextLine();

    }

    public void updateProduct() {
        Scanner console = new Scanner(System.in);
        System.out.println("Update a Product");
        System.out.println("Enter the product Id: ");
        int productId = console.nextInt();
        console.nextLine();


            Optional<Product> updateProduct = productRepository.findById(productId);
            if (updateProduct.isPresent()) {
                Product product = updateProduct.get();
                System.out.println("Current Details: " + product);

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

            System.out.println("Press enter to return to the main menu...");
            console.nextLine();
    }


}
