package DTO;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Toy {

    private int toyId;
    private String toyName;
    private int quantity;
    private byte[] image;
    private double price;
    private String description;
    private int categoryId;
    private double discount;
    private Date warranty_time;
    private int isActive;
    private int isDisable;
    private int userId;
    private String name_staff;
    private String name_category;

    public Toy(int toyId) {
        this.toyId = toyId;
    }

    public Toy(String toyName) {
        this.toyName = toyName;
    }        

    public Toy(String toyName, byte[] image, double price, double discount) {
        this.toyName = toyName;
        this.image = image;
        this.price = price;
        this.discount = discount;
    }        

    public Toy(int toyId, String toyName, byte[] image, double price, double discount) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.image = image;
        this.price = price;
        this.discount = discount;
    }

    public Toy(int toyId, String toyName, int quantity, byte[] image, double price, String description, String name_category, double discount) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.description = description;
        this.name_category = name_category;
        this.discount = discount;
        
    }

    public Toy(int toyId, String toyName, int quantity, byte[] image, double price, String description, int categoryId, double discount) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.discount = discount;
    }

    public Toy(String toyName, double price, String description, int categoryId, double discount, Date warranty_time, int isActive, int isDisable, int userId) {
        this.toyName = toyName;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.discount = discount;
        this.warranty_time = warranty_time;
        this.isActive = isActive;
        this.isDisable = isDisable;
        this.userId = userId;
    }        

    public Toy(int toyId, String toyName, int quantity, byte[] image, double price, String description, int categoryId, double discount, Date warranty_time, int isActive, int isDisable) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.discount = discount;
        this.warranty_time = warranty_time;
        this.isActive = isActive;
        this.isDisable = isDisable;
    }        

    public Toy(String toyName, byte[] image, double price, String description, int categoryId, double discount, Date warranty_time, int isActive, int isDisable, int userId) {
        this.toyName = toyName;
        this.image = image;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.discount = discount;
        this.warranty_time = warranty_time;
        this.isActive = isActive;
        this.isDisable = isDisable;
        this.userId = userId;
    }

    public Toy(int toyId, String toyName, byte[] image, double price, String description, int categoryId, double discount, Date warranty_time, int isActive, int isDisable, int userId, String name_staff) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.image = image;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.discount = discount;
        this.warranty_time = warranty_time;
        this.isActive = isActive;
        this.isDisable = isDisable;
        this.userId = userId;
        this.name_staff = name_staff;
    }

    public Toy(int toyId, String toyName, int quantity, byte[] image, double price, String description, int categoryId, double discount, Date warranty_time, int isActive, int isDisable, String name_category) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.discount = discount;
        this.warranty_time = warranty_time;
        this.isActive = isActive;
        this.isDisable = isDisable;
        this.name_category = name_category;
    }            

    public Toy(int toyId, String toyName, int quantity, byte[] image, double price, String description, int categoryId, double discount, Date warranty_time, int isActive, int isDisable, int userId, String name_staff) {
        this.toyId = toyId;
        this.toyName = toyName;
        this.quantity = quantity;
        this.image = image;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.discount = discount;
        this.warranty_time = warranty_time;
        this.isActive = isActive;
        this.isDisable = isDisable;
        this.userId = userId;
        this.name_staff = name_staff;
    }   



    public Toy(String name, InputStream image, double price, String description, int idCategory, double discount, java.sql.Date warrantyTime, int i, int i0, int userId) {
        this.toyName = toyName;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.discount = discount;
        this.warranty_time = warranty_time;
        this.isActive = isActive;
        this.isDisable = isDisable;
        this.userId = userId;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Date getWarranty_time() {
        return warranty_time;
    }

    public void setWarranty_time(Date warranty_time) {
        this.warranty_time = warranty_time;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsDisable() {
        return isDisable;
    }

    public void setIsDisable(int isDisable) {
        this.isDisable = isDisable;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName_staff() {
        return name_staff;
    }

    public void setName_staff(String name_staff) {
        this.name_staff = name_staff;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }
  
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.toyId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Toy other = (Toy) obj;
        if (this.toyId != other.toyId) {
            return false;
        }
        return true;
    }

}
