package com.assessment.inventoryManager;

import com.assessment.inventoryManager.model.Product;
import com.assessment.inventoryManager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.Console;
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
        int quantity = console.nextInt();
        console.nextLine();

        System.out.println("Enter the price of the product: ");
        double price = Double.parseDouble(console.nextLine());

        Product newProduct = new Product();
        newProduct.setProductName(name);
        newProduct.setQuantity(quantity);
        newProduct.setPrice(price);
        productRepository.save(newProduct);
    }
}
