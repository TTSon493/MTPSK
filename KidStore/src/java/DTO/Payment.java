package DTO;

/**
 *
 * @author admin
 */
public class Payment {
    private int paymentId;
    private String bankName;


    public Payment() {
    }

    public Payment(int paymentId, String bankName) {
        this.paymentId = paymentId;
        this.bankName = bankName;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


}
