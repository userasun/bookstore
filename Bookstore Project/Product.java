public abstract class Product implements Comparable<Product> {

/*
 * Initializing variables for product price and name
*/ 
    double firstProduct;
    double secondProduct;
    String firstProductName;
    String secondProductName;


/*
 * Method to compare two products from either the book, cd, or dvd classes
 * Finds the class of Product a to cast the correct class for getPrice method
 * Using the compareTo method to compare firstProduct and secondProduct
 * @param a The object from the Product class to compare to another product
*/
@Override
public int compareTo(Product a){
    
    if (this instanceof CD){
       firstProduct = ((CD)this).getPrice();
       firstProductName = ((CD)this).getName();
    }
    if (this instanceof DVD){
        firstProduct = ((DVD)this).getPrice();
        firstProductName = ((DVD)this).getName();
    }
    if (this instanceof Book){
        firstProduct = ((Book)this).getPrice();
        firstProductName = ((Book)this).getName();
    }
    if (a instanceof CD){
        secondProduct = ((CD)a).getPrice();
        secondProductName = ((CD)a).getName();  
    }
    if (a instanceof DVD){
        secondProduct = ((DVD)a).getPrice();
        secondProductName = ((DVD)a).getName();
    }
    if (a instanceof Book){
        secondProduct = ((Book)a).getPrice(); 
        secondProductName = ((Book)a).getName(); 
    }
    
    if (firstProduct > secondProduct){
        System.out.println(firstProductName + ": " + firstProduct + " > " + secondProductName + ": " + secondProduct);
        return 1;
    }
    else if (firstProduct < secondProduct){
        System.out.println(firstProductName + ": " + firstProduct + " < " + secondProductName + ": " + secondProduct);
        return -1;
    }
    else
        System.out.println(firstProductName + ": " + firstProduct + " = " + secondProductName + ": " + secondProduct);
        return 0;
} 
    
}