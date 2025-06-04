package DTO;

import java.util.Date;

/**
 *
 * @author trant
 */
public class Order {
    private int orderId;
    private int userId;
    private String status;
    private Date createDate;
    private int voucherId;
    private int cardId;
    private boolean paymentType;

    public Order() {
    }

    public Order(int orderId, int userId, String status, Date createDate, int voucherId, int cardId, boolean paymentType) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
        this.createDate = createDate;
        this.voucherId = voucherId;
        this.cardId = cardId;
        this.paymentType = paymentType;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public boolean isPaymentType() {
        return paymentType;
    }

    public void setPaymentType(boolean paymentType) {
        this.paymentType = paymentType;
    }

    
}
