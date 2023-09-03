import java.util.*;
import java.io.*;
import java.time.LocalDate;


/**
 *
 */
public class BookstoreDriver {

    /**
     * Main method, executes Book Store functions/flow
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Bookstore bookstore1 = new Bookstore();
        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> cart = new ArrayList<String>();
        int num = 1;

        try {
        // new file and printwriter declaration for EndOfDayReport.txt   
        FileOutputStream fs = new FileOutputStream("EndOfDayReport.txt");
        PrintWriter outFS = new PrintWriter(fs);

        //new file and printwriter declaration for ProductInventoryDay2.csv
        FileOutputStream fs2 = new FileOutputStream("ProductInventoryDay2.csv");
        PrintWriter outFS2 = new PrintWriter(fs2);

        //Local Date class to print the local date under header for end of day report
        LocalDate LD = LocalDate.now();

        outFS.println("\t**** End of Day Report ****");
        outFS.println("\t        " + LD);

        
        // while loop to keep program running until exit    
        while(num > 0 && num < 10){

        try {
        // see what the user wants to do
        System.out.println("Please select from the following menu of options, by typing a number:");
        System.out.println("\t 1. Check Member Status");
        System.out.println("\t 2. Add Something to your Cart");
        System.out.println("\t 3. Get Total Purchase Value");
        System.out.println("\t 4. Checkout");
        System.out.println("\t 5. Register a New Member");
        System.out.println("\t 6. Compare Prices of Two Items");
        System.out.println("\t 7. Display Inventory Total");
        System.out.println("\t 8. Restock Items");
        System.out.println("\t 9. Exit");
 
        num = keyboard.nextInt();
        keyboard.nextLine();

        switch (num) {
            case 1:
                checkMemberStatus(bookstore1, keyboard);
                break;
            case 2:
                purchase(bookstore1, keyboard, cart);
                break;
            case 3:
                System.out.println("Your total purchase is : " + getTotalPurchase(cart));
                break;
            case 4:
                checkout(cart, bookstore1, keyboard);
                break;
            case 5:
                System.out.println("Premium member? (Y/N)");
                char letter = keyboard.next().charAt(0);
                keyboard.nextLine();
                if (letter == 'Y' || letter == 'y') {
                    registerNewMember(bookstore1, keyboard, true);
                } else {
                    registerNewMember(bookstore1, keyboard, false);
                }
                break;
            case 6:
                compareProducts(bookstore1, keyboard);
                break;
            case 7:
                bookstoreInventory(bookstore1);
                break;
            case 8:
                restockItems(bookstore1, keyboard);
                break;            
            case 9:
                closingTime(cart, bookstore1, fs, outFS, fs2, outFS2);
                System.exit(0);
                break;            
            }
        } catch (InputMismatchException ex){
            System.out.println("Sorry, but you need to enter a 1, 2, 3, 4, 5, 6, 7, 8, or 9");
            
            // making a new input if num becomes invalid
            // lets the while loop continue without ending
            String newInput = keyboard.next();
            continue;
        }
        }// end while loop
            
        } catch (FileNotFoundException ex){
            System.out.println("File not found. Please check file name again.");
        
        } catch (Exception ex){
             System.out.println("Exception occurred");
        }
        
    } // end main method
    
    /**
     * Method prints all members and user selects one to check status
     * @param bookstore the bookstore being examined
     * @param scan a scanner
     */
    private static void checkMemberStatus(Bookstore bookstore, Scanner scan){
        int memberNumber = 1;
        System.out.println("Select the member.");
        System.out.println("Members: ");
        for(int i = 0; i < bookstore.getMembersList().size(); i++){
            System.out.println(memberNumber + ". " + bookstore.getMembersList().get(i).getName());
            memberNumber++;
        }
        System.out.println("Premium Members: ");
        for(int i = 0; i < bookstore.getPremiumMembersList().size(); i++){
            System.out.println(memberNumber + ". " + bookstore.getPremiumMembersList().get(i).getName());
            memberNumber++;
        }

        try {
        System.out.println("Input the member number of the selected member: ");

        int selection = scan.nextInt();
        scan.nextLine();
        bookstore.displayMemberStatus(selection);

        } catch (InputMismatchException ex){
            System.out.println("Invalid input. Try again.");
        } catch (Exception ex){
            System.out.println("Exception occurred");
        }
    }  
    
    /**
     * Prints the total amount in the cart
     * @param cart list of Strings representing purchases
     * @return sum of all doubles in cart
     */
    private static double getTotalPurchase(ArrayList<String> cart){
        double sum = 0;
        for(int i = 0; i<cart.size(); i++){
            String[] expressions = cart.get(i).split("-");
            double price = Double.parseDouble(expressions[1]);
            //System.out.println(String.format("%.2f", price));
            sum = sum + price;
        }
        return sum;
    }
    
    /**
     * Decrements inventory that is in the cart
     * @param cart list of items in cart
     * @param bookstore bookstore selected
     * @param scan a scanner
     */  
    private static void checkout(ArrayList<String> cart, Bookstore bookstore,
            Scanner scan){
        System.out.println("Your total is " + getTotalPurchase(cart));
        System.out.println("Thank you!");
        System.out.println("Please come again");
    }  
    
    /**
     * Adds an item to the cart
     * @param bookstore the bookstore
     * @param scan scanner
     * @param cart list of purchases
     */
    private static void purchase(Bookstore bookstore, Scanner scan, 
            ArrayList<String> cart){

        int id = 1;
        System.out.println("Select the item you wish to add to your cart.");
        System.out.println("Books: ");

        for(int i = 0; i < bookstore.getBookInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getBookInventory().get(i).getName());
            id++;
        }
        System.out.println("CDs: ");
        for(int i = 0; i < bookstore.getCdInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getCdInventory().get(i).getName());
            id++;
        }
        System.out.println("DVDs: ");
        for(int i = 0; i < bookstore.getDvdInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getDvdInventory().get(i).getName());
            id++;
        }

        try {
        System.out.println("Input the id number of the selected item: ");

        int selection = scan.nextInt();
        scan.nextLine();
        System.out.println(bookstore.getCartItem(selection));
        
        bookstore.decrementInventory(selection);
        cart.add(bookstore.getCartItem(selection));
        
        } catch (InputMismatchException ex){
            System.out.println("Invalid input. Please try again.");
        } catch (Exception ex){
            System.out.println("Exception occurred");
        }
    } 
    
    /**
     * Adds a new member
     * @param bookstore the bookstore
     * @param scan the scanner
     * @param premium whether we are registering a premium or standard member
     * @return true if everything was input correctly
     */
    private static void registerNewMember(Bookstore bookstore, Scanner scan, boolean premium){
       
        try {     

        System.out.println("Input the name of the new member: ");
        String name = scan.nextLine();
        
        System.out.println("How many purchases have been made?");
        int purchases = scan.nextInt();
        scan.nextLine();
        bookstore.addNewMember(name, premium, purchases);
        
        } catch (InputMismatchException ex){
            System.out.println("Invalid input. Please try again.");
        } catch (Exception ex){
            System.out.println("Exception occurred");
        }
    }
    
    /*
     * Compares the prices of two chosen products
     * Prints all products of bookstore for user to choose two products
     * @param bookstore the bookstore
     * @param scan the scanner
    */
    private static double compareProducts(Bookstore bookstore, Scanner scan){
        try {
        int id = 1;
        int firstItemId;
        int secondItemId;

        Product firstItem;
        Product secondItem;
        
        System.out.println("What is the first item you would like to compare?");
        System.out.println("");

        System.out.println("***Books***");
        for(int i = 0; i < bookstore.getBookInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getBookInventory().get(i).getName());
            id++;
        }
        System.out.println("");

        System.out.println("***CDs***");
        for(int i = 0; i < bookstore.getCdInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getCdInventory().get(i).getName());
            id++;
        }
        System.out.println("");

        System.out.println("***DVDs***");
        for(int i = 0; i < bookstore.getDvdInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getDvdInventory().get(i).getName());
            id++;
        }

        firstItemId = scan.nextInt();
        scan.nextLine();

        if(firstItemId > bookstore.getBookInventory().size() + bookstore.getCdInventory().size()){
            id = firstItemId - bookstore.getBookInventory().size() - bookstore.getCdInventory().size() - 1;
            firstItem = bookstore.getDvdInventory().get(id);
        } else if (firstItemId > bookstore.getBookInventory().size()){
            id = firstItemId - bookstore.getBookInventory().size() - 1;
            firstItem = bookstore.getCdInventory().get(id);
        } else {
            id = firstItemId - 1;
            firstItem = bookstore.getBookInventory().get(id);
        }

        System.out.println("What is the second item you would like to compare?");
        System.out.println("");

        id = 1;
        System.out.println("***Books***");
        for(int i = 0; i < bookstore.getBookInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getBookInventory().get(i).getName());
            id++;
        }
        System.out.println("");

        System.out.println("***CDs***");
        for(int i = 0; i < bookstore.getCdInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getCdInventory().get(i).getName());
            id++;
        }
        System.out.println("");

        System.out.println("***DVDs***");
        for(int i = 0; i < bookstore.getDvdInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getDvdInventory().get(i).getName());
            id++;
        }
        secondItemId = scan.nextInt();
        scan.nextLine();

        if(secondItemId > bookstore.getBookInventory().size() + bookstore.getCdInventory().size()){
            id = secondItemId - bookstore.getBookInventory().size() - bookstore.getCdInventory().size() - 1;
            secondItem = bookstore.getDvdInventory().get(id);
        } else if (secondItemId > bookstore.getBookInventory().size()){
            id = secondItemId - bookstore.getBookInventory().size() - 1;
            secondItem = bookstore.getCdInventory().get(id);
        } else {
            id = secondItemId - 1;
            secondItem = bookstore.getBookInventory().get(id);
        }
        
        return firstItem.compareTo(secondItem);

        } catch (InputMismatchException ex){
            System.out.println("Invalid input. Please try again.");   
        } catch (Exception ex){
            System.out.println("Exception occurred");
        }
       return 0.0;
    }

    /**
     * Method to restock products of user choice and amount
     * @param bookstore the bookstore
     * @param scan the scanner
    */
    private static void restockItems(Bookstore bookstore, Scanner scan){
       
        try { 

        int id = 1;
        int productId;
        int restockValue;

        System.out.println("Which item would you like to restock?");
        System.out.println("");

        System.out.println("***Books in Stock***");
        for(int i = 0; i < bookstore.getBookInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getBookInventory().get(i).getName() + ": " + bookstore.getBookInventory().get(i).getStock());
            id++;
        }
        System.out.println("");

        System.out.println("***CDs in Stock***");
        for(int i = 0; i < bookstore.getCdInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getCdInventory().get(i).getName() + ": " + bookstore.getCdInventory().get(i).getStock());
            id++;
        }
        System.out.println("");

        System.out.println("***DVDs in Stock***");
        for(int i = 0; i < bookstore.getDvdInventory().size(); i++){
            System.out.println(id + ". " + bookstore.getDvdInventory().get(i).getName() + ": " + bookstore.getDvdInventory().get(i).getStock());
            id++;
        }

        productId = scan.nextInt();
        scan.nextLine();
        System.out.println("How many items would you like to add?");

        restockValue = scan.nextInt();
        scan.nextLine();

        bookstore.restockProduct(restockValue, productId);
        
        } catch (InputMismatchException ex){
            System.out.println("Invalid input. Please try again.");
        } catch (Exception ex){
            System.out.println("Exception occurred");
        }
    }

    /**
     * Method to show stock of all products
     * Lists all products and their current stock numbers
     * Lists total inventory of all products combined
     * @param bookstore the bookstore
    */
    private static void bookstoreInventory(Bookstore bookstore){
        System.out.println("****Current Inventory****");
        System.out.println("Total Inventory: " + (int)bookstore.inventoryValue());
    }
      
    /**
     * Method to generate the end of day report and updated inventory report before closing
     * End of day report lists purchased products, number of new members, money made, and total sales
     * Updated inventory report lists updated inventory at end of day
     * Scans through the inventory arraylists to get updated stock using product id
     * @param bookstore the bookstore
     * @param cart ArrayList
     * @param fs FileOutputStream
     * @param outFS PrintWriter 
     * @param fs2 FileOutputStream
     * @param outFS2 PrintWriter
     */
     private static void closingTime(ArrayList<String> cart, Bookstore bookstore, FileOutputStream fs, PrintWriter outFS, FileOutputStream fs2, PrintWriter outFS2){
        
        Scanner fileScanner;
        String line;
        String newLine;
        int id;
        int newStock = 0;

        try { 
        fileScanner = new Scanner(new File("ProductInventory.csv"));      

        outFS.println(""); 
        outFS.println("Products Sold:");
        outFS.println(cart);
         
        outFS.println("Total Sales: " + bookstore.getNumProductsSold());
        outFS.println("Revenue: $" + getTotalPurchase(cart));
        outFS.println("Members added today: " + bookstore.getNewMemberAmount());

        // loop to copy contents of productInventory.csv to productInventoryDay2.csv with change to stock numbers
        while(fileScanner.hasNext()) {
            line = fileScanner.nextLine();
            String[] parsedLine = line.split(",");
            
            if (parsedLine[1].equals("book")){
                for(int i = 0; i < bookstore.getBookInventory().size(); i++){
                    if(bookstore.getBookInventory().get(i).getName().equals(parsedLine[2])){
                        id = i;
                        newStock = bookstore.getBookInventory().get(id).getStock();
                    }
                }
            }
            if (parsedLine[1].equals("cd")){
                for(int i = 0; i < bookstore.getCdInventory().size(); i++){
                    if(bookstore.getCdInventory().get(i).getName().equals(parsedLine[2])){
                        id = i;
                        newStock = bookstore.getCdInventory().get(id).getStock();
                    }
                }
            }
            if (parsedLine[1].equals("dvd")){
                for(int i = 0; i < bookstore.getDvdInventory().size(); i++){
                    if(bookstore.getDvdInventory().get(i).getName().equals(parsedLine[2])){
                        id = i;
                        newStock = bookstore.getDvdInventory().get(id).getStock();
                    }
                }
            }

            // keeps the header for new csv file
            if(parsedLine[0].equals("productID")){
                newLine = line;
            }

            // changes the stock number from previous inventory list
            else {
            newLine = parsedLine[0] + "," + parsedLine[1] + "," + parsedLine[2] + "," + parsedLine[3] + "," + newStock + "," + parsedLine[5];
            }

            outFS2.println(newLine);
        }

        // closing both printwriters and scanners
        outFS.close();
        fs.close();

        outFS2.close();
        fs2.close();

        }catch (IOException ex) {
            System.out.println("Error with PrintWriter");
        } 
     }
   

}
