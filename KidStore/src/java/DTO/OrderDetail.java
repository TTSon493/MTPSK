package DTO;

import java.util.Date;

/**
 *
 * @author admin
 */
public class OrderDetail{
    private int id;
    private int toyId;
    private String toyName;
    private int quantity;
    private double amount;
    private int orderId;
    private Date orderDate;
    private String customerName;
    
    public OrderDetail() {
    }

    public OrderDetail(int id, int toyId, int quantity, double amount, int orderId) {
        this.id = id;
        this.toyId = toyId;
        this.quantity = quantity;
        this.amount = amount;
        this.orderId = orderId;
    }

    public OrderDetail(int id, int toyId, int quantity, double amount, int orderId, Date orderDate, String customerName) {
        this.id = id;
        this.toyId = toyId;
        this.quantity = quantity;
        this.amount = amount;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
    }

    public OrderDetail(int id, String toyName, int quantity, double amount, int orderId, Date orderDate, String customerName) {
        this.id = id;
        this.toyName = toyName;
        this.quantity = quantity;
        this.amount = amount;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
    }

    public OrderDetail(int id, int toyId, String toyName, int quantity, double amount, int orderId, Date orderDate, String customerName) {
        this.id = id;
        this.toyId = toyId;
        this.toyName = toyName;
        this.quantity = quantity;
        this.amount = amount;
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerName = customerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getToyId() {
        return toyId;
    }

    public void setToyId(int toyId) {
        this.toyId = toyId;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
