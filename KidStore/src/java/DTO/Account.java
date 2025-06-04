package DTO;

public class Account {
    private int userId;
    private String userName;
    private String password;
    private String fullName;
    private String phone;
    private String address;
    private boolean active = true;
    private String role;
    private int roleId = 3;
    private int paymentId;

    public Account() {
    }

    public Account(String userName, String password, String fullName, String phone, String address) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
    }
    
    public Account(String userName, String password, String fullName, String phone, String address, int roleId) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.roleId = roleId;
    }

    public Account(int userId, String userName, String password, String fullName, String phone, String address, boolean active, int roleId, int paymentId) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.active = active;
        this.roleId = roleId;
        this.paymentId = paymentId;
    }

    public Account(int userId, String userName, String fullName, String phone, String address, boolean active, int role_id, String role) {
        this.userId = userId;
        this.userName = userName;
        this.fullName = fullName;
        this.phone = phone;
        this.address = address;
        this.active = active;
        this.roleId = role_id;
        this.role = role;
        
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    } 

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}

   
