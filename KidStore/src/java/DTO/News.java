package DTO;

import java.util.Date;

/**
 *
 * @author trant
 */
public class News {

    private int newsId;
    private String title;
    private byte[] image;
    private Date date;
    private String description;
    private int userId;
    private String name_staff;

    public News(String title, byte[] image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public News(int newsId, String title, byte[] image, Date date, String description, String name_staff) {
        this.newsId = newsId;
        this.title = title;
        this.image = image;
        this.date = date;
        this.description = description;
        this.name_staff = name_staff;
    }
    
    public News(String title, Date date, String description, int userId) {
        this.title = title;
        this.date = date;
        this.description = description;
        this.userId = userId;
    }      

    public News(String title, byte[] image, Date date, String description, int userId) {
        this.title = title;
        this.image = image;
        this.date = date;
        this.description = description;
        this.userId = userId;
    }        

    public News(int newsId, String title, byte[] image, Date date, String description, int userId) {
        this.newsId = newsId;
        this.title = title;
        this.image = image;
        this.date = date;
        this.description = description;
        this.userId = userId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
