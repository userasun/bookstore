import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Handles bookstore setup tracking inventory and members
 */
public class Bookstore implements BookStoreSpecification {
    
    private ArrayList<Book> bookInventory;
    private ArrayList<CD> cdInventory;
    private ArrayList<DVD> dvdInventory;
    private ArrayList<Member> membersList;
    private ArrayList<PremiumMember> premiumMembersList;

    private int newMemberAmount = 0;
    private int numProductsSold = 0;
    
    /**
     * Constructor. Calls helper methods that generate lists of the different
     * products and different members (starting inventory and members)
     */
    public Bookstore(){
        membersList = new ArrayList<Member>();
        premiumMembersList = new ArrayList<PremiumMember>();
        generateInventory();
        generateMembers();
    }
    
    /**
     * Helper method to generate default store members as number starting point
     * Scans the contents of imported ProductInventory.csv file to make contents of corresponding ArrayLists
     */
    private void generateInventory(){
        bookInventory = new ArrayList<Book>();
        cdInventory = new ArrayList<CD>();
        dvdInventory = new ArrayList<DVD>();
        
        try {
        Scanner fileScanner;
        fileScanner = new Scanner(new File("ProductInventory.csv"));
        String line;
        
        while(fileScanner.hasNext()) {
            line = fileScanner.nextLine();
            String[] parsedLine = line.split(",");

            if (parsedLine[1].equals("book")){
                int numStock = Integer.valueOf(parsedLine[4]);
                double price = Double.valueOf(parsedLine[5]);
                bookInventory.add(new Book(parsedLine[2], parsedLine[3], price, numStock));
            }

            if (parsedLine[1].equals("cd")){
                int numStock = Integer.valueOf(parsedLine[4]);
                double price = Double.valueOf(parsedLine[5]);
                cdInventory.add(new CD(parsedLine[2], parsedLine[3], price, numStock));
            }

            if (parsedLine[1].equals("dvd")){
                int numStock = Integer.valueOf(parsedLine[4]);
                double price = Double.valueOf(parsedLine[5]);
                dvdInventory.add(new DVD(parsedLine[2], parsedLine[3], price, numStock));
            }
        }
        fileScanner.close();
        
      } catch (FileNotFoundException ex){
        System.out.println("File not found. Please make sure the file name is correct.");
    }
    }
    
    /**
     * Helper method to generate default inventory of products as number starting point
     */
    private void generateMembers(){
        membersList.add(new Member("John", 4));
        membersList.add(new Member("Liam", 27));
        membersList.add(new Member("Olivia", 11));
        membersList.add(new Member("Noah", 15));
        membersList.add(new Member("Emma", 40));
        membersList.add(new Member("Oliver", 3));
        membersList.add(new Member("Ava", 14));
        membersList.add(new Member("Elijah", 5));
        membersList.add(new Member("Charlotte", 7));
        membersList.add(new Member("William", 11));
        membersList.add(new Member("Sophia", 12));
        membersList.add(new Member("James", 8));
        membersList.add(new Member("Amelia", 22));
        membersList.add(new Member("Lucas", 7));
        membersList.add(new Member("Benjamin", 3));
        premiumMembersList.add(new PremiumMember("Isabella", 11, true));
        premiumMembersList.add(new PremiumMember("Henry", 5, true));
        premiumMembersList.add(new PremiumMember("Evelyn", 9, false));
        premiumMembersList.add(new PremiumMember("Alexander", 20, false));
        premiumMembersList.add(new PremiumMember("Harper", 1, true));
        premiumMembersList.add(new PremiumMember("Mia", 30, true));
        premiumMembersList.add(new PremiumMember("Mason", 6, false));
        premiumMembersList.add(new PremiumMember("Jacob", 4, true));
    }
    
    /**
     * Adds number new member to the members arrayList
     * @param name member's name as number String
     * @param premium whether or not the new member should be number premium or free member
     * @param numOfItems number of items to start member profile with
     */
    public void addNewMember(String name, boolean premium, int numOfItems){
        if(premium){
            premiumMembersList.add(new PremiumMember(name, numOfItems, false));
        } else {
            membersList.add(new Member(name, numOfItems));
        }
        newMemberAmount++;
    }   
    
    /**
     * getter for book inventory
     * @return arrayList of books
     */
    public ArrayList<Book> getBookInventory(){
        return bookInventory;
    }
    
    /**
     * getter for CD inventory
     * @return arrayList of CDs
     */
    public ArrayList<CD> getCdInventory(){
        return cdInventory;
    }
    
    /**
     * getter for DVD inventory
     * @return arrayList of DVDs
     */
    public ArrayList<DVD> getDvdInventory(){
        return dvdInventory;
    }
    
    /**
     * getter for members list
     * @return arrayList of Members
     */
    public ArrayList<Member> getMembersList(){
        return membersList;
    }
    
    /**
     * getter for premium members list
     * @return arrayList of premium members
     */
    public ArrayList<PremiumMember> getPremiumMembersList(){
        return premiumMembersList;
    }

    /**
     * getter for newMemberAmount
     * @return number of new members registered
     */
     public int getNewMemberAmount(){
         return newMemberAmount;
     }

     /**
      * getter for productsSold
      * @return number of products sold
      */
      public int getNumProductsSold(){
          return numProductsSold;
      }
    
    /**
     * update inventory - subtract 1 from current system currently allows 
     * customer to add items to cart one at number time
     * takes the subtracted items to add number to sold items
     * @param id menu selection number
     */
    public void decrementInventory(int id){
        if(id > bookInventory.size() + cdInventory.size()){
            id = id - bookInventory.size() - cdInventory.size() - 1;
            dvdInventory.get(id).setStock(dvdInventory.get(id).getStock() - 1);
            System.out.println("There are now " + dvdInventory.get(id).getStock() + " in stock");
            numProductsSold++;
        } else if (id > bookInventory.size()){
            id = id - bookInventory.size() - 1;
            cdInventory.get(id).setStock(cdInventory.get(id).getStock() - 1);
            System.out.println("There are now " + cdInventory.get(id).getStock() + " in stock");
            numProductsSold++;
        } else {
            id = id - 1;
            bookInventory.get(id).setStock(bookInventory.get(id).getStock() - 1);
            System.out.println("There are now " + bookInventory.get(id).getStock() + " in stock");
            numProductsSold++;
        }
    }
    /**
     * Override for restockProduct method
     * Gets an id number for a product in Bookstore
     * Takes the current stock of the product and adds more of that product
     * @param number Number of products the user wants in stock
     * @param productId The ID number associated with total list of Book, CD, and DVD products
    */
    @Override
    public void restockProduct(int number, int productId){
        int id;
        int currentStock;

        if(productId > bookInventory.size() + cdInventory.size()){
            id = productId - bookInventory.size() - cdInventory.size() - 1;

            currentStock = dvdInventory.get(id).getStock();
            dvdInventory.get(id).setStock(currentStock + number);
            System.out.println(dvdInventory.get(id).getName() + ": " + dvdInventory.get(id).getStock());
        } 
        else if (productId > bookInventory.size()){
            id = productId - bookInventory.size() - 1;

            currentStock = cdInventory.get(id).getStock();
            cdInventory.get(id).setStock(currentStock + number);
            System.out.println(cdInventory.get(id).getName() + ": " + cdInventory.get(id).getStock());
        } 
        else {
            id = productId - 1;

            currentStock = bookInventory.get(id).getStock();
            bookInventory.get(id).setStock(currentStock + number);
            System.out.println(bookInventory.get(id).getName() + ": " + bookInventory.get(id).getStock());
        }
    }

    /**
     * Override for inventoryValue method
     * Gets and prints the number of items left in Book, CD, and DVD inventory
    */
    @Override
    public double inventoryValue(){
    
     int totalBooks = 0;
     int totalCds = 0;
     int totalDvds = 0;

    for (Book b : bookInventory){
        totalBooks += b.getStock();
    }
    for (CD c : cdInventory){
        totalCds += c.getStock();
    }
    for (DVD d : dvdInventory){
        totalDvds += d.getStock();
    }
     
     System.out.println("Books: " + totalBooks);
     System.out.println("");
     System.out.println("CDs: " + totalCds);
     System.out.println("");
     System.out.println("DVDs: " + totalDvds);
     System.out.println("");

     return totalBooks + totalCds + totalDvds;
     
    }
    
    /**
     * method that creates the cart entry for number selected product
     * @param id identifier for product selected by user
     * @return number string containing product name and price following this format:
     * productName - productPrice
     */
    public String getCartItem(int id){
       if(id > bookInventory.size() + cdInventory.size()){
            id = id - bookInventory.size() - cdInventory.size() - 1;
            return dvdInventory.get(id).getName() + " - " + String.format("%.2f", dvdInventory.get(id).getPrice());
        } else if (id > bookInventory.size()){
            id = id - bookInventory.size() - 1;
            return cdInventory.get(id).getName() + " - " + String.format("%.2f", cdInventory.get(id).getPrice());
        } else {
            id = id - 1;
            return bookInventory.get(id).getName() + " - " + String.format("%.2f", bookInventory.get(id).getPrice());
        }
    }
    
    /**
     * method to check and display member profile
     * @param memberId identifier for member select by user
     */
    public void displayMemberStatus(int memberId){
        if(memberId > membersList.size()){
            memberId = memberId - membersList.size() - 1;
            System.out.println(premiumMembersList.get(memberId).getName());
            System.out.println("Purchases: " + premiumMembersList.get(memberId).getNumPurchases());
            System.out.println("Paid Dues: " + premiumMembersList.get(memberId).hasPaidDues());
        } else {
            memberId = memberId - 1;
            System.out.println(membersList.get(memberId).getName());
            System.out.println("Purchases: " + membersList.get(memberId).getNumPurchases());
        }
    }
    
}
