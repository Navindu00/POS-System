/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pos.mvc3.model;

import java.util.Date;

/**
 *
 * @author navindudulanjaya <your.name at your.org>
 */
public class Order {
    private int orderID;
    private int CustomerID;
    private Date orderDate;
    private double total;

    public Order() {
    }

    public Order(int orderID, int CustomerID, Date orderDate, double total) {
        this.orderID = orderID;
        this.CustomerID = CustomerID;
        this.orderDate = orderDate;
        this.total = total;
    }

    /**
     * @return the orderID
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     * @param orderID the orderID to set
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * @return the CustomerID
     */
    public int getCustomerID() {
        return CustomerID;
    }

    /**
     * @param CustomerID the CustomerID to set
     */
    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", CustomerID=" + CustomerID + ", orderDate=" + orderDate + ", total=" + total + '}';
    }
    
    
}
