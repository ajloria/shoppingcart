/*
 * TCSS 305 - Autumn 2017
 * Assignment 2 - Shopping Cart 
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * This class stores information about an individual item.
 * @author Andrew Joshua Loria
 * @version October 16, 2017
 *
 */
public final class Item {
    /**
     * Name of item.
     */
    private final String myName;
    
    /** 
     * Price of item. 
     */   
    private final BigDecimal myPrice;
    
    /**
     * Bulk quantity of item.  
     */
    private int myBulkQuantity;
    
    /**
     * Bulk price of item. 
     */
    private BigDecimal myBulkPrice;
    
    /**
     * Says whether or not the item is bulk.
     */
    private boolean myItemBulk;
 
    /**
     * Constructor that takes a name and a price as arguments. 
     * @param theName String name of item
     * @param thePrice Big Decimal price of item
     */
    public Item(final String theName, final BigDecimal thePrice) {
        myName = theName;
        myPrice = thePrice;
    }

    /**
     * Overloaded constructor. Constructor that takes a name, 
     * a single-item price, a bulk quantity, and a bulk price as arguments. 
     * @param theName name of item
     * @param thePrice price of item
     * @param theBulkQuantity bulk quantity of item
     * @param theBulkPrice bulk price of item
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        myName = theName;
        myPrice = thePrice;
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = theBulkPrice;
        myItemBulk = true;

    }

    /**
     * Returns name of item.
     * @return String name of item
     */
    public String getName() {
        
        return myName;
    }
    
    /**
     * Returns single item price.
     * @return BigDecimal item price
     */
    public BigDecimal getPrice() {
        
        return myPrice;
    }
    

    /**
     * Returns bulk quantity of item.
     * @return integer bulk quantity
     */
    public int getBulkQuantity() {
        
        return myBulkQuantity;
    }
    
    /**
     * Returns bulk price.
     * @return bulk price
     */
    public BigDecimal getBulkPrice() {
        
        return myBulkPrice;
    }

    /**
     * Returns True if the Item has bulk pricing; false otherwise.
     * @return Boolean myItemBulk.
     */
    public boolean isBulk() {
        return myItemBulk;
    }
    
    /**
     * {@inheritDoc}
     * 
     * Returns a String representation of this Item: name, followed by a
     * comma and a space, followed by price. If the item has a bulk price, there is
     * an extra space and a parenthesized description of the bulk pricing that has 
     * the bulk quantity, the word “for” and the bulk price. 
     * 
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        
        builder.append(myName);
        builder.append(", ");
        builder.append(nf.format(myPrice));
        
        if (isBulk()) {
            builder.append(" (");
            builder.append(myBulkQuantity);
            builder.append(" for ");
            builder.append(nf.format(myBulkPrice));
            builder.append(')');
        }
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * Returns true if the specified object is equivalent to this Item, and false
     * otherwise. Two items are equivalent if they have exactly equivalent
     * names, prices, bulk quantities and bulk prices. 
     */
    @Override
    public boolean equals(final Object theOther) {
        
        boolean returnVal = false; 
        
        if (theOther != null && (this.getClass() == theOther.getClass())) {
            final Item otherItem = (Item) theOther;
            returnVal = Objects.equals(myName, otherItem.myName)
                            && (myPrice.compareTo(otherItem.myPrice) == 0);
            
            if (isBulk()) { //if item comes in bulk 
                //check if names are equal and bulk prices, bulk quantities are same
                returnVal = Objects.equals(myName, otherItem.myName)
                                && (myBulkPrice.compareTo(otherItem.myBulkPrice) == 0)
                                && (Integer.compare(myBulkQuantity, 
                                                    otherItem.myBulkQuantity) == 0);
            }
        }
        return returnVal;
    }


    @Override
    public int hashCode() {
        return Objects.hash(myName, myPrice, myBulkPrice, myBulkQuantity);
    }

}
