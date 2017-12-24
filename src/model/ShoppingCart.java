/*
 * TCSS 305 - Autumn 2017
 * Assignment 2 - Shopping Cart 
 */

package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This class stores information about the customer's purchase.
 * It will use an ArrayList to store the information about the purchases.
 * @author Andrew Joshua Loria
 * @version October 18, 2017
 *
 */
public class ShoppingCart {

    /**
     * List.
     */
    private List<ItemOrder> myList;
    
    /**
     * Boolean of if a customer is a member.
     */
    private boolean myMember;
    

    /**
     * Constructs empty shopping cart.
     */
    public ShoppingCart() {
        myList = new ArrayList<ItemOrder>(); //creates new ArrayList

    }

    /**
     * Adds an order to the shopping cart, replacing any previous order 
     * for an equivalent item with the new order. 
     * @param theOrder which is an ItemOrder
     */
    public void add(final ItemOrder theOrder) {
        for (int i = 0; i < myList.size(); i++) {
            if (theOrder.getItem() == myList.get(i).getItem()) {
                myList.set(i, theOrder);
                return; //this exits method, doesn't run anything else in method
                //line above resolved my bulk pricing problem mentioned in summary

            }
        }
        myList.add(theOrder);
        
    }

    /**
     * Sets whether or not the customer for this shopping cart has a store
     * membership.
     * @param theMembership whether or not customer has membership.
     */
    public void setMembership(final boolean theMembership) {
        myMember = theMembership;
    }

    /**
     * Returns the total cost of this shopping cart as a BigDecimal. The 
     * returned BigDecimal has scale of 2 and uses the ROUND_HALF_EVEN rounding rule.
     * @return total with type BigDecimal.
     */
    public BigDecimal calculateTotal() {
        BigDecimal cartTotal = BigDecimal.ZERO;
        for (int j = 0; j < myList.size(); j++) {
            //getting price of item
            final BigDecimal price = myList.get(j).getItem().getPrice();
            //getting quantity of item
            final BigDecimal quantity = new BigDecimal(myList.get(j).getQuantity());
            //getting bulk quantity of item
            final BigDecimal bulkquantity = 
                            new BigDecimal(myList.get(j).getItem().getBulkQuantity());

            
            //checks if item is bulk and customer checked membership box 
            if (myList.get(j).getItem().isBulk() && myMember) {
                //divide quantity by bulk quantity  rounded down to nearest whole number
                BigDecimal calculation = quantity.divideToIntegralValue(bulkquantity);
                //multiply by bulk price of item
                calculation = calculation.multiply(myList.get(j).getItem().getBulkPrice());
                //add line above to the remainder of the quantity multiplied by the price
                calculation = calculation.add((quantity.remainder(bulkquantity)).
                                              multiply(price));
                cartTotal = cartTotal.add(calculation);
            } else {
                //if not a member, just multiply price * quantity.
                cartTotal = cartTotal.add(price.multiply(quantity));
            }
        }
        //Round half even    
        return cartTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
                            
    }
    
    
    /**
     * Removes all orders from the cart.
     */
    public void clear() {
        myList = new ArrayList<ItemOrder>(); //create new ArrayList.
        
    }
    
    /**
     * {@inheritDoc}
     * Returns a String representation of this ShoppingCart.
     * 
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        builder.append(myList);
        return builder.toString();
    }

}
