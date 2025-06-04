package DTO;

import java.util.Date;

/**
 *
 * @author trant
 */
public class OrderWarranty {
    private int orderDetailId;
    private Date orderDate;
    private byte[] imageToy;
    private String toyName;
    private int quantity;
    private String status;
    private Date warrantyTime;
    private String phone;
    private String address;
    private String warrantyCode;
    private String nameUser;
    private String descriptionWarranty;
    private double price;
    private String feedback;

    public OrderWarranty(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public OrderWarranty(int orderDetailId, String toyName, String nameUser, double price, String feedback) {
        this.orderDetailId = orderDetailId;
        this.toyName = toyName;
        this.nameUser = nameUser;
        this.price = price;
        this.feedback = feedback;
    }
    
    

    public OrderWarranty(int orderDetailId, String phone, String address, String descriptionWarranty) {
        this.orderDetailId = orderDetailId;
        this.phone = phone;
        this.address = address;
        this.descriptionWarranty = descriptionWarranty;
    }
    
    public OrderWarranty(int orderDetailId, Date orderDate, byte[] imageToy, String toyName, int quantity, String status, Date warrantyTime, String phone, String address, String warrantyCode, String nameUser, String descriptionWarranty) {
        this.orderDetailId = orderDetailId;
        this.orderDate = orderDate;
        this.imageToy = imageToy;
        this.toyName = toyName;
        this.quantity = quantity;
        this.status = status;
        this.warrantyTime = warrantyTime;
        this.phone = phone;
        this.address = address;
        this.warrantyCode = warrantyCode;
        this.nameUser = nameUser;
        this.descriptionWarranty = descriptionWarranty;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public byte[] getImageToy() {
        return imageToy;
    }

    public void setImageToy(byte[] imageToy) {
        this.imageToy = imageToy;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getWarrantyTime() {
        return warrantyTime;
    }

    public void setWarrantyTime(Date warrantyTime) {
        this.warrantyTime = warrantyTime;
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

    public String getWarrantyCode() {
        return warrantyCode;
    }

    public void setWarrantyCode(String warrantyCode) {
        this.warrantyCode = warrantyCode;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getDescriptionWarranty() {
        return descriptionWarranty;
    }

    public void setDescriptionWarranty(String descriptionWarranty) {
        this.descriptionWarranty = descriptionWarranty;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
