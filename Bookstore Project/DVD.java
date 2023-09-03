/**
 * Represents a DVD
 */
public class DVD extends Product {
    
    private String name;
    private String category;
    private double price;
    private int stock;
    
    /**
     * DVD constructor
     * @param name the DVD's title
     * @param category the author of the DVD
     * @param price the price of the DVD
     * @param stock number of copies in stock
     */
    public DVD (String name, String category, double price, int stock){
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
    }
    
    /**
     * Getting for the category field
     * @return the value of category
     */
    public String getCategory(){
        return category;
    }
    
    /**
     * Setting for category field
     * @param category the category
     */
    public void setCategory(String category){
        this.category = category;
    }
    
    /**
     * Getting for the name field
     * @return the value of name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Setting for name field
     * @param name the name
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Getting for price field
     * @return the value of price
     */
    public double getPrice(){
        return price;
    }
    
    /**
     * Setting for price field
     * @param price the price
     */
    public void setPrice(double price){
        this.price = price;
    }
    
    /**
     * Getting for stock field
     * @return the value of stock
     */
    public int getStock(){
        return stock;
    }
    
    /**
     * Setting for stock field
     * @param stock the stock
     */
    public void setStock(int stock){
        this.stock = stock;
    }
}
