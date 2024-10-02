import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class cafeMocha {
    public static final String users = "user.txt";
    public static final String itemMenu = "itemmenu.txt";
    public static final String menuItem = "menuItem.txt";
    public static final String orderDetailsFile = "orderDetails.txt";
    public static final String ordernumbers = "OrderNumber.txt";
    public static final String displayBill = "displayBill.txt";

// ==================================================================================================================
public static void loginUser(ArrayList<String> userNameAndPassword){ // Login the admin 
    Scanner input = new Scanner(System.in); 
    boolean loginSucc = false;

// Loop until a successful login
    while (!loginSucc) {
        System.out.print("Enter your UserName :"); // Getting the username from the user
        String UserName = input.nextLine();
    
        System.out.print("Enter Your Password :");
        String Password = input.nextLine(); 

        try{
            BufferedReader reader = new BufferedReader(new FileReader(users));
            String users;
            while ((users = reader.readLine()) != null) { // Read lines until the null
                userNameAndPassword.add(users); // Add each user entry to the user list
            }
            reader.close(); 

                // boolean loginSucc = false;
        for(String Login : userNameAndPassword){
            String [] userNamePassword = Login.split(" ");  // Check each stored username and password

            // Validate the entered username and password
            if(userNamePassword.length == 2 && userNamePassword[0].equals(UserName) && userNamePassword[1].equals(Password)){
                System.out.println("Login successfully");
                loginSucc = true;
                displayMenu(); // call the method to displyMenu
                break;
            }
        
        }if(!loginSucc){
            System.out.println("invalid Username Or Password !!");
            
        }
    }catch(Exception e){
        System.out.println(e); // Catching errors and showing the message
    }    
    
    }
    input.close();
}
// =============================================================================================================
public static void displayMenu(){ // after Login success display the menu
    ArrayList<Integer> Numbers = new ArrayList<>(); // Create a new ArrayList to store integer values
    ArrayList<Integer> Ordernum = new ArrayList<>(); // Creating an ArrayList to store order numbers as integers
    ArrayList<String> orderDetails = new ArrayList<>();
    ArrayList<Integer> quntity  = new ArrayList<>();
    ArrayList<Integer> itemPrice  = new ArrayList<>();
    ArrayList<String> ItemName = new ArrayList<>();
    ArrayList<Integer> numberItem =new ArrayList<>();

    // ArrayList<String> userNameAndPassword = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    boolean running = true;
  while (running) {
        System.out.println("Menu:");
        System.out.println("1. Add Order");
        System.out.println("2. View Order Details");
        System.out.println("3. Calculate and print bill");
        System.out.println("4. Add Item for Menu");
        System.out.println("5. Update Item for Menu");
        System.out.println("6. Delete Item for Menu");
        System.out.println("7. Help..");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: "); // Ask user for input
        int choice = input.nextInt();

        switch (choice) {   // Execute code based on user choice
            case 1:
                System.out.println("....Seleted the Add Order.......");
                addOrder(Numbers, orderDetails, Ordernum, quntity, itemPrice, ItemName); // Calls the addOrder method with the provided parameters
                break;
            case 2:
                System.out.println("....Seleted the Add Order Details.......");
                OrderDetails(Numbers, orderDetails, Ordernum, quntity, itemPrice, ItemName); // Calls the OrderDetails method with the provided parameters
                break;
                
            case 3:
                System.out.println("....Seleted the Add Calculate and display.......");
                calculate(Numbers, orderDetails, Ordernum, quntity, itemPrice, ItemName);// Calls the calculate method with the provided parameters
                break;
            case 4:
                System.out.println("....Seleted the Add New Item.......");
                addItem(numberItem);
                break;
            case 5:
                System.out.println("....Seleted the Update Item.......");
                updateItem(numberItem);
                break;
            case 6:
                System.out.println("....Seleted the Delete Order.......");
                deleteOrder(Numbers, orderDetails, Ordernum, quntity, itemPrice, ItemName);
                break;
            case 7:
                System.out.println("click help....");
                help();// Calls the help method
                break;
            case 8:
                running = false;
                System.out.println("Exiting...");
                // deleteOrder();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
// input.close();
}
// ============================================================================================================
//add a new order
 public static void addOrder(ArrayList<Integer> Numbers, ArrayList<String> orderDetails,ArrayList<Integer> Ordernum, ArrayList<Integer> quntity,ArrayList<Integer> itemPrice,ArrayList<String> ItemName ) {
        Scanner input = new Scanner(System.in); 
        int orderNumber = 1000 + Numbers.size(); // Increment order number based on the size of the list
        boolean moreItem = true;

        System.out.print("Enter Order Details: ");
            
        System.out.println("Your Order Number :" + orderNumber);

        System.out.print("Enter Your Name :");
        String inputCustomerName = input.nextLine(); // Get the customer's name
        orderDetails.add(inputCustomerName);
        // Adds the inputCustomerName to the orderDetails list

        System.out.print("Enter Your Address :");
        String inputCustomerAddress = input.nextLine();
        
        orderDetails.add(inputCustomerAddress);
        
        System.out.print("Enter Your Phone Number :");
        int inputCustomerNumber = input.nextInt();
        Ordernum.add(inputCustomerNumber);
        // Adds the inputCustomerNumber to the Ordernum list

        try{
            BufferedReader reader = new BufferedReader(new FileReader(itemMenu));
            String itme;
            while ((itme = reader.readLine()) != null) { // Read lines until the null
                System.out.println(itme);
                
            }
        reader.close();

        }catch(Exception e){
            System.out.println(e); // Handle any file reading errors
        }
         // Loop to allow the user to add multiple items to the order
        while (moreItem) {
            System.out.print("Enter A Order Details :");

            System.out.print("Enter a Item Name : ");
            String inputItem = input.next();
            input.nextLine(); // Getting the Item Name from the user

            
            ItemName.add(inputItem); // Adds the inputItem to the ItemName list
            
    
            System.out.print("Enter a Quantity :");
            int inputQuantity = input.nextInt();
            
            quntity.add(inputQuantity);
            
            System.out.print("Enter A Price :");
            int inputPrice = input.nextInt();

            itemPrice.add(inputPrice);
            System.out.print("Do You Want to Add More Item :");  // Ask the user if they want to add more items
            String No = input.next();
            if(No.equals("no")){ // If the user types 'no', stop adding items
                moreItem = false;
            }
 // Save the order details to a file
        try{
            // Creates a BufferedWriter to write to the orderDetailsFile in append mode
            BufferedWriter writer = new BufferedWriter(new FileWriter(orderDetailsFile , true));
            writer.write("=".repeat(60) + "\n");
            writer.write("Café Mocha," +"\n");
            writer.write(" You Order Details : " +"\n");
            writer.write("Order Number :" + orderNumber + "\n");
            writer.write("Customer Name :" + inputCustomerName + "\n");
            writer.write("Customer Address : " + inputCustomerAddress + "\n");
            writer.write("Phone Number : " + inputCustomerNumber + "\n");
            writer.append("=".repeat(60) + "\n");
            writer.append("Order Item Name : " + inputItem + "\n");
            writer.append("Quntity : " + inputQuantity + "\n");
            writer.append("Item Price : "  + inputPrice + "\n");
            writer.append("=".repeat(60) + "\n");
            writer.close();

        

        }catch(Exception e){
            System.out.println(e); // Handle any file writing errors
        }
        }
        Numbers.add(orderNumber); // Adds the orderNumber to the Number list
        
        System.out.println("Name :" + inputCustomerName);
        System.out.println("Address : " + inputCustomerAddress);
        System.out.println("Number : " + inputCustomerNumber);
        
        System.out.println("Order added successfully.Your Order Number: " + orderNumber);
    }
// ==============================================================================================================================
//get a order details
public static void OrderDetails(ArrayList<Integer> Numbers, ArrayList<String> orderDetails,ArrayList<Integer> Ordernum,ArrayList<Integer> quntity,ArrayList<Integer> itemPrice,ArrayList<String> ItemName) {
    Scanner input = new Scanner(System.in);
    System.out.print("Enter Your Order Number: ");
    int inputOrderNumber = input.nextInt();  // Get the order number from the user
    boolean same = false; // To check if the order number matches
    
    for(Integer number : Numbers){// Loop through each number in the Numbers list
        if(number == inputOrderNumber){// Check if the current number matches the inputOrderNumber
            System.out.println("vaild Order Number " + number);
            same = true;  

             // Display customer and order details
        for(int i = 0; i< ItemName.size(); i++){ 
    
            String itemName = ItemName.get(i);  // Get the item name at position i
            String customerName = orderDetails.get(0);
            String customerAddrss = orderDetails.get(1);
            int customernumber = Ordernum.get(0);

            System.out.print("=".repeat(60) + "\n");
            System.out.println("Customer Name : " + customerName );
            System.out.println("Customer Adress : " + customerAddrss);
            System.out.println("Customer Phone Number : " + customernumber);
            System.out.print("=".repeat(60) + "\n");
            System.out.print("Item Name : " + itemName + "\n");
            System.out.println("Quntity : " + quntity.get(i));
            System.out.println("Item Price : " + itemPrice.get(i));
            System.out.print("=".repeat(60) + "\n");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(orderDetailsFile));   // Read and display the order details from the file
            String details;
            while ((details = reader.readLine()) != null) { // Read lines until the null
                System.out.println(details);
                
            }
        reader.close();
        }catch(Exception e){
            System.out.println(e); // Handle any errors
        }
    }
        
        }else{
            System.out.println("Check Your Order Number Again!!");
            OrderDetails(Numbers, orderDetails, Ordernum, quntity, itemPrice, ItemName);
            // Call the OrderDetails method again, passing the relevant parameters to allow the user to re-enter or view order details.

        }
        
    }
    // input.close();
}
// ===============================================================================================================================
// Calculate and print the bill
public static int calculate(ArrayList<Integer> Numbers, ArrayList<String> orderDetails,ArrayList<Integer> Ordernum,ArrayList<Integer> quntity,ArrayList<Integer> itemPrice,ArrayList<String> ItemName ){
    int totalPrice = 0;
    Scanner input = new Scanner(System.in);
    System.out.print("Enter Your Order Number :");
    int inputOrderNumber = input.nextInt(); // Get the order number from the user

    boolean same = false;  // To check if the order number is valid

    for(Integer number : Numbers){
        if(number == inputOrderNumber){  // Check if the current 'number' matches the 'inputOrderNumber' provided by the user.
            System.out.println("vaild Order Number " + number);
            same = true;

            for(int i = 0; i< ItemName.size(); i++){ 
        // Loop through each element in the 'ItemName' list or array using an index 'i'.
                String itemName = ItemName.get(i);
                String customerName = orderDetails.get(0);
                String customerAddrss = orderDetails.get(1);

                System.out.print("=".repeat(60) + "\n");
                System.out.println("Customer Name : " + customerName );
                System.out.println("Customer Adress : " + customerAddrss);
                System.out.print("=".repeat(60) + "\n");
                System.out.print("Item Name : " + itemName + "\n");
                System.out.println("Quntity : " + quntity.get(i));
                System.out.println("Item Price : " + itemPrice.get(i));
                totalPrice += quntity.get(i) * itemPrice.get(i); // Calculate total price
                System.out.print("=".repeat(60) + "\n");
                System.out.println("Your Total Amount : " + totalPrice);
                System.out.print("=".repeat(60) + "\n");

                String welcome =
                ">>===============================================================<<\n" +
                "||        ___       __                          _                ||\n" +
                "||       / __\\__ _ / _| ___    /\\/\\   ___   ___| |__   __ _      ||\n" +
                "||      / /  / _` | |_ / _ \\  /    \\ / _ \\ / __| '_ \\ / _` |     ||\n" +
                "||     / /__| (_| |  _|  __/ / /\\/\\ \\ (_) | (__| | | | (_| |     ||\n" +
                "||     \\____/\\__,_|_|  \\___| \\/    \\/\\___/ \\___|_| |_|\\__,_|     ||\n" +
                ">>===============================================================<<\n";
                // Define a welcome message with a border and Café Mocha logo

                String thanks = 
                "+-+-+-+-+-+ +-+-+-+ +-+-+-+-+ +-+-+-+-+-+\n" +
                "|T|H|A|N|K| |Y|O|U| |C|O|M|E| |A|G|A|I|N|\n" +
                "+-+-+-+-+-+ +-+-+-+ +-+-+-+-+ +-+-+-+-+-+\n";
                
                // Write order details to the bill file
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(displayBill));
                    writer.write(welcome);
                    writer.write("=".repeat(60) + "\n");
                    writer.write(".............CAFE MOCHA........." + "\n");
                    writer.write("=".repeat(60) + "\n");
                    writer.write("=".repeat(60) + "\n");
                    writer.write("Order Number : " + number + "\n");
                    writer.write("Customer Name : " + customerName + "\n");
                    writer.write("Customer Address : " + customerAddrss + "\n");
                    writer.write("=".repeat(60) + "\n");
                    writer.write("Item Name : " + itemName + "\n");
                    writer.write("Item Price : Rs" + itemPrice.get(i) + "\n");
                    writer.write("Total Price : Rs" + totalPrice + "\n");
                    writer.write("=".repeat(60) + "\n");
                    writer.write("!!THANK YOU COME AGAIN!!!! \n ");
                    writer.write(thanks);
                    writer.close();
    
                }catch(IOException e){
                    System.out.println(e);  // Handle file write errors
                }   
            }
            
        }else{
            System.out.println("Check Your Order Number Again!!");   // If order number is invalid,user to try again
            calculate(Numbers, orderDetails, Ordernum, quntity, itemPrice, ItemName);
        }
        
    }return totalPrice;
}
// ==========================================================================================================================
//Add a new Item 
public static void addItem(ArrayList<Integer> numberItem){
    Scanner input = new Scanner(System.in);
    int itemNumber = 1 + numberItem.size(); // Calculate the item number by adding 1 to the current size of the 'numberItem' list or array.

                System.out.print("Enter a New Item Name : ");
                String newItemInput = input.nextLine(); // Get the item name

        
                System.out.print("Enter a Description :");
                String itemDescription = input.nextLine();
        
                System.out.print("Enter a Price : ");
                String itemPrice = input.nextLine();

            // Write the new item details to the file
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(menuItem,true)); // add a ,ture 
            
                        // int itemnumber = numberItem.get(i);
                        writer.append(itemNumber + "\n"); // Write item number with new line 
                        writer.append("Item Name : " + newItemInput + "\n");  // add a writer.apend 
                        writer.append("Item Description : " + itemDescription + "\n");
                        writer.append("item Price : " + itemPrice + "\n");
                        writer.close();
                        System.out.println("........Successful Add a Item.........");
    
            
    
                }catch(Exception e){
                    System.out.println(e);  // Handle file write errors
                }
        numberItem.add(itemNumber);
        itemNumber++;  //incrment the itemNumber 
}

// ==========================================================================================================================
//Update the itme 
public static void updateItem(ArrayList<Integer>numberItem){
    Scanner input = new Scanner(System.in);
    System.out.println(numberItem.get(0)); // Display the first item number
    System.out.print("Enter Your Item Number :");
    int inputItemNumber = input.nextInt();
    
    boolean same = false;

     // Loop through the numberItem list to find a matching item number
    for(Integer Number : numberItem){
        if(Number.equals(inputItemNumber)){ // Check if the current 'Number' matches the 'inputItemNumber' provided by the user.
            System.out.println("vaild Order Number " + Number);
            same = true;

            System.out.print("Enter a New Item Name : ");
            String newItemInput = input.next();  // Get new item name
    
            System.out.print("Enter a Description :");
            String itemDescription = input.next();
            input.nextLine();
        
    
            System.out.print("Enter a Price : ");
            int itemPrice = input.nextInt();

           
         // Write updated item details to the file
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(menuItem,true)); // add a ,ture 
                    // int itemnumber = numberItem.get(i);
                    writer.append("..........update a Item........" + "\n");
                    writer.append("Item Name : " + newItemInput + "\n");  // add a writer.apend 
                    writer.append("Item Description : " + itemDescription + "\n");
                    writer.append("item Price : " + itemPrice + "\n");
                    writer.close();
                    System.out.println(".............Successful Update the Item..........");

            }catch(IOException e){
                System.out.println(e);  // Handle any file writing errors
            }
            
        }else{
            System.out.println(Number);
        }
    }
}
// ==========================================================================================================================
//Delete the item
public static void deleteOrder(ArrayList<Integer> Numbers,ArrayList<String> orderDetails,ArrayList<Integer> Ordernum,ArrayList<Integer> quntity,ArrayList<Integer> itemPrice,ArrayList<String> ItemName) {
    
    Scanner input = new Scanner(System.in);
    System.out.print("Enter the Order Number to Delete: ");
    int inputOrderNumber = input.nextInt(); // Get the order number to delete
    boolean orderFound = false;
    
    // Find the index of the order number to be deleted
    int indexToDelete = -1;
    
    for (int i = 0; i < Numbers.size(); i++) {
        if (Numbers.get(i) == inputOrderNumber) { // Check if the order number matches
            indexToDelete = i;
            orderFound = true;
            break; 
        }
    }
    
    if (orderFound && indexToDelete != -1) {   // Remove the order details from all lists at the specified index
        Numbers.remove(indexToDelete);
        orderDetails.remove(indexToDelete * 2); // Assuming each order has two details: name and address
        orderDetails.remove(indexToDelete * 2); 
        Ordernum.remove(indexToDelete);
        quntity.remove(indexToDelete);
        itemPrice.remove(indexToDelete);
        ItemName.remove(indexToDelete);
        
        System.out.println("Order Number " + inputOrderNumber + " has been successfully deleted.");
    } else {
        System.out.println("Order Number not found. Please check and try again.");
    }
}
// ==========================================================================================================================
// ==========================================================================================================================
//user guidlines
public static void help(){
    System.out.println("......Help.......");
    System.out.println("______Guidlines to Use the System_______");

    System.out.println("____Guidline 01____ "+  "\n" + 
                        "Open the Café Mocha on your computer." + "\n" +
                        "field and type your assigned username And field and type your password \n");

    System.out.println("_____Guidlines 02____" + "\n" +
                        "select the option Add New Customer Order."+ "\n" +
                        "Order Number:This number will be used to track the order. \n" + 
                        "Customer Name:customer's full name.\n" +
                        "Address: Enter the customer's address \n" +
                        "Telephone Number:customer contact number \n" +
                        "Enter Order Details \n");

    System.out.println("____Guidlines 03____ \n" +
                        "Main menu, Select Calculate and Print Bill.\n" + 
                        "Enter the Order Number for the order you to bill \n" +
                        "Automatic Calculation the bill and Print \n");

    System.out.println("______Guidlines 04_____ \n" + 
                    "Main Menu, Select Add New Menu Item \n" + 
                    "Enter Item Details \n" +
                    "Removing a Menu Item \n" );

    System.out.println("_____Guidlines 05_____ \n" + "Main Menu, Select Exit the System");

    System.out.println("_____Have Any Problem ?____ \n" + "Call the Hotline Number....");
}
// =============================================================================================================================
// =============================================================================================================================
    public static void main(String[] args) throws Exception {
        ArrayList<String> userNameAndPassword = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader("welcomeMass.txt")); // Read welcome message from file
            // FileReader reader = new FileReader("welcome.txt");
            int data = reader.read();
            while (data != -1) {
                System.out.print((char) data); // Print each character from the file
                data = reader.read();
                
            }
            reader.close();
        }catch(IOException e){
            System.out.println(e); // Handle file reading errors
        }
        System.out.println("\n");
        loginUser(userNameAndPassword); // Call loginUser method to handle user login
        
    }
}

