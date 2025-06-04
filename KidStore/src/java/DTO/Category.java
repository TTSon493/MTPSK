package DTO;

public class Category {
    private int categoryId;
    private String categoryName;
    private int userId;
    private String name_staff;
    private int isActive;
    private int isDisable;
    

    public Category(int categoryId) {
        this.categoryId = categoryId;
    }

    public Category(String categoryName, int userId) {
        this.categoryName = categoryName;
        this.userId = userId;
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }           
    
    public Category(int categoryId, String categoryName, String name_staff) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.name_staff = name_staff;
    }
    
    public Category(int categoryId, String categoryName, int userId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.userId = userId;
    }
    
    public Category(int categoryId, String categoryName, int isActive, int isDisable) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.isActive = isActive;
        this.isDisable = isDisable;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
    
}
