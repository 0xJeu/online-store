package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        HashMap<String,Product> inventory = getinventory();
        displayProducts(inventory,keyboard);



    }
    public static void displayProducts(HashMap<String,Product> inventory,Scanner keyboard){

        for ( String k: inventory.keySet()) {
            Product product = inventory.get(k);
            //System.out.println("SKU: " + product.getSku()); until needed
            System.out.println("Name: " + product.getProductName());
            System.out.println("Price: $" + product.getPrice());
            System.out.println("Department: " + product.getDepartment());

      }

        System.out.print("""            
            What do you want to do?                        
            1 - Search for products         
            2 - Add a product            
            3 - Return to main menu                        
            Enter your command: """);
        int options= Integer.parseInt(keyboard.nextLine());

        switch (options) {
            case 1:
                System.out.println("Please enter product name or department: ");
                String userSearch = keyboard.nextLine();
                for (Product product : inventory.values()) {
                    if (!userSearch.equalsIgnoreCase(product.getProductName())&& !userSearch.equalsIgnoreCase(product.getDepartment())) {
                        continue;
                    }

                    if (userSearch.equalsIgnoreCase(product.getProductName()) || userSearch.equalsIgnoreCase(product.getDepartment())) {
                        System.out.println("Name: " + product.getProductName());
                        System.out.println("Price: $" + product.getPrice());
                        System.out.println("Department: " + product.getDepartment());
                    }
                    else{
                            System.out.println("product not found");
                        }
                    }

                }
        }



    public static  HashMap<String,Product> getinventory(){
        HashMap<String, Product> inventory = new HashMap<>();
        try {
            FileReader fr = new FileReader("src/main/resources/products.csv");
            // create a BufferedReader to manage input stream
            BufferedReader br = new BufferedReader(fr);
            String input;
            // read until there is no more data
            while ((input = br.readLine()) != null) {
                if (input.startsWith("SKU")){
                    continue;
                }

                String[] lineSplit = input.split(Pattern.quote("|"));
                String sku= lineSplit[0];

                String productName = lineSplit[1];
                String price = lineSplit[2];
                String department = lineSplit[3];

                inventory.put(productName,new Product(sku,productName,price,department));
            }

            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
         return inventory;
    }
    public static void displayCommands(){
        System.out.print("""            
            What do you want to do?                        
            1 - Display Products            
            2 - Display Cart            
            3 - Quit                        
            Enter your command: """);
    }


}

