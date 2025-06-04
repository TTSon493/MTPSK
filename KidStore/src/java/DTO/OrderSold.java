/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author admin
 */
public class OrderSold {
    private int orderId;
    private String CustomerName;
    private String phone;
    private String address;
    private boolean paymentType;
    private byte[] image;
    private String toyName;
    private int quantity;
    private String fullName;
    private double toyPrice;
    private Date orderDate;
    private double totalPrice;
    private double discount;

    public OrderSold(int orderId, Date orderDate, double totalPrice) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
    }

    public OrderSold(byte[] image, String toyName, int quantity, String fullName,double toyPrice, Date orderDate, double totalPrice, double discount) {
        this.image = image;
        this.toyName = toyName;
        this.quantity = quantity;
        this.fullName = fullName;
        this.toyPrice = toyPrice;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.discount = discount;
    }

    public OrderSold(int orderId, String CustomerName, String phone, String address, boolean paymentType, byte[] image, String toyName, int quantity, String fullName, double toyPrice, Date orderDate, double totalPrice, double discount) {
        this.orderId = orderId;
        this.CustomerName = CustomerName;
        this.phone = phone;
        this.address = address;
        this.paymentType = paymentType;
        this.image = image;
        this.toyName = toyName;
        this.quantity = quantity;
        this.fullName = fullName;
        this.toyPrice = toyPrice;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.discount = discount;
    }

   

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isPaymentType() {
        return paymentType;
    }

    public void setPaymentType(boolean paymentType) {
        this.paymentType = paymentType;
    }
    
    public OrderSold() {
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public double getToyPrice() {
        return toyPrice;
    }

    public void setToyPrice(double toyPrice) {
        this.toyPrice = toyPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    
}
