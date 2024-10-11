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
        ArrayList<Product> cart = cart();
        while (true){
            displayCommands();
            int command = Integer.parseInt(keyboard.nextLine());
            switch (command){
                case 1:
                    displayProducts(inventory,keyboard,cart);
                    break;
                case 2:
                    displayCart(cart,keyboard);
                    break;
                case 3:
                    System.exit(0);

            }
        }


    }
    public static void displayProducts(HashMap<String,Product> inventory,Scanner keyboard,ArrayList<Product> cart){

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
                    if (!userSearch.equalsIgnoreCase(product.getProductName()) && !userSearch.equalsIgnoreCase(product.getDepartment())) {
                        continue;
                    }

                    if (userSearch.equalsIgnoreCase(product.getProductName()) || userSearch.equalsIgnoreCase(product.getDepartment())) {
                        System.out.println("Name: " + product.getProductName());
                        System.out.println("Price: $" + product.getPrice());
                        System.out.println("Department: " + product.getDepartment());
                    } else {
                        System.out.println("Product not found");
                    }
                }
                break;
            case 2:
                int count =0;

                while (true) {
                    System.out.println("please enter product to add to cart: ");
                    String userCart = keyboard.nextLine();
                    for (Product product : inventory.values()) {
                        if (!userCart.equalsIgnoreCase(product.getProductName())) {
                            continue;
                        }
                        if (userCart.equalsIgnoreCase(product.getProductName())) {
                            cart.add(new Product(product.getSku(), product.getProductName(), product.getPrice(), product.getDepartment()));
                            System.out.println("the added item is: " + cart.get(count++).getProductName());
                            System.out.println("product added to cart successfully");

                        }

                    }
                    System.out.println("Do you want to add another product (Y or N)? ");
                    String addAnother = keyboard.nextLine();
                    if(addAnother.equalsIgnoreCase("y")){
                        continue;

                    } else if(addAnother.equalsIgnoreCase("N")) {
                        return;
                    }
                    }
            case 3:
                return;

                }
        }



    public static void displayCart (ArrayList<Product> cart, Scanner keyboard){
        for (Product product : cart){
        System.out.println(product.getProductName());
        System.out.println("$"+ product.getPrice());
        System.out.println(product.getDepartment());
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

    public static ArrayList<Product> cart (){
        ArrayList<Product> cart = new ArrayList<>();
        return cart;
    }


}

