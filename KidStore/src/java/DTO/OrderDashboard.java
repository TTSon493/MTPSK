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
public class OrderDashboard {
    private int OrderId;
    private Date OrderDate;
    private double OrderAmount;
    private String name;
    private String phone;
    private String address;
    private boolean paymentType;
    

    public OrderDashboard(int OrderId, Date OrderDate, double OrderAmount, String name, String phone, String address, boolean paymentType) {
        this.OrderId = OrderId;
        this.OrderDate = OrderDate;
        this.OrderAmount = OrderAmount;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.paymentType = paymentType;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public double getOrderAmount() {
        return OrderAmount;
    }

    public void setOrderAmount(double OrderAmount) {
        this.OrderAmount = OrderAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
}
