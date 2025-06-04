package DTO;

import java.util.Date;

/**
 *
 * @author trant
 */
public class Voucher {
    
    private int voucherId;
    private String voucherName;
    private String description;
    private Date dateExpiration;
    private double discount;
    private int limit;
    private double limitPrice;
    public Voucher() {
    }

    public Voucher(String voucherName, double discount) {
        this.voucherName = voucherName;
        this.discount = discount;
    }

    public Voucher(int voucherId, String voucherName, String description, Date dateExpiration, double discount, int limit, double limitPrice) {
        this.voucherId = voucherId;
        this.voucherName = voucherName;
        this.description = description;
        this.dateExpiration = dateExpiration;
        this.discount = discount;
        this.limit = limit;
        this.limitPrice = limitPrice;
    }

    public Voucher(int voucherId, double discount) {
        this.voucherId = voucherId;
        this.discount = discount;
    }

    public int getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(int voucherId) {
        this.voucherId = voucherId;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public double getLimitPrice() {
        return limitPrice;
    }

    public void setLimitPrice(double limitPrice) {
        this.limitPrice = limitPrice;
    }
    
}
