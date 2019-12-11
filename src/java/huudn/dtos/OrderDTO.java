/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package huudn.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ngochuu
 */
public class OrderDTO implements Serializable {
    private int orderID;
    private boolean checkout;
    private Date dateCheckout;
    private int total;
    private int userID;

    public OrderDTO() {
    }

    public OrderDTO(int orderID, boolean checkout, Date dateCheckout, int total, int userID) {
        this.orderID = orderID;
        this.checkout = checkout;
        this.dateCheckout = dateCheckout;
        this.total = total;
        this.userID = userID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public boolean isCheckout() {
        return checkout;
    }

    public void setCheckout(boolean checkout) {
        this.checkout = checkout;
    }

    public Date getDateCheckout() {
        return dateCheckout;
    }

    public void setDateCheckout(Date dateCheckout) {
        this.dateCheckout = dateCheckout;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

}
