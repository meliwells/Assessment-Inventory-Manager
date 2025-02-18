package com.assessment.inventoryManager;

import com.assessment.inventoryManager.model.Product;
import com.assessment.inventoryManager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;
import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication
public class InventoryManagerApplicationCLI implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(InventoryManagerApplicationCLI.class, args);}

    public void run(String[] args) {
        //test add product
        System.out.println("Add product");
        addProduct();
    }

    public void addProduct() {
        Scanner console = new Scanner(System.in);
        System.out.println("Enter the product name: ");
        String name = console.nextLine();

        System.out.println("Enter the product quantity: ");
        String input = console.nextLine();
        int quantity = 0;
        try {
            quantity = Integer.parseInt(input);
            System.out.println("Valid quantity: " + quantity);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a number: ");
        }
        console.nextLine();

        System.out.println("Enter the price of the product: ");
        String inputPrice =  console.nextLine();
        double price = 0;
        try {
            price = Double.parseDouble(inputPrice);
            System.out.println("Valid price: " + price);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid price: ");
        }
        console.nextLine();

        System.out.println("Enter the expiration date: ");
        LocalDate expirationDate = LocalDate.parse(console.nextLine());

        Product newProduct = new Product();
        newProduct.setProductName(name);
        newProduct.setQuantity(quantity);
        newProduct.setPrice(price);
        productRepository.save(newProduct);
    }

    public void updateProduct() {

    }
}
