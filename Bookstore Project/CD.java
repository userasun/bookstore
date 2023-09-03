/**
 * Represents a CD
 */
public class CD extends Product {
    
    private String name;
    private String artist;
    private double price;
    private int stock;
    
    /**
     * CD constructor
     * @param name the CD's title
     * @param artist the author of the CD
     * @param price the price of the CD
     * @param stock number of copies in stock
     */
    public CD (String name, String artist, double price, int stock){
        this.name = name;
        this.artist = artist;
        this.price = price;
        this.stock = stock;
    }
    
    /**
     * Getting for the author field
     * @return the value of author
     */
    public String getArtist(){
        return artist;
    }
    
    /**
     * Setting for artist field
     * @param artist the artist
     */
    public void setArtist(String artist){
        this.artist = artist;
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
