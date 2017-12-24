/*
 * TCSS 305 - Autumn 2017
 * Assignment 2 - Shopping Cart 
 */

package model;

/**
 * This class represents the order of an item. The ItemOrder object
 * stores information about a purchase order for a particular item, a reference to
 * the item itself and the quantity desired.
 * @author Andrew Joshua Loria
 * @version October 18, 2017
 *  
 */
public final class ItemOrder {

    /**
     * Quantity of the item.
     */
    private final int myQuantity;
    
    /**
     * The item being ordered.
     */
    private final Item myItem;
    
    /**
     * Constructor that creates an item order for the given quantity of the given Item.
     * @param theItem name of the item
     * @param theQuantity quantity of the item being purchased
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        myItem = theItem;
        myQuantity = theQuantity;
          
    }

    /**
     * Returns a reference to the Item in this ItemOrder.
     * @return myItem
     */
    public Item getItem() {
        
        return myItem;
    }
    
    /**
     * Returns quantity for this ItemOrder.
     * @return myQuantity
     */
    public int getQuantity() {
        
        return myQuantity;
    }

    /**
     * {@inheritDoc}
     * 
     * The String representation of the Item Order will be as follows:
     * Item: myItem; Quantity: myQuantity
     */
    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder(128);
        builder.append("Item: ");
        builder.append(myItem);
        builder.append("; Quantity: ");
        builder.append(myQuantity);
        return builder.toString();
    }

}
