package DTO;

public class Image {
    private int imageId;
    private byte[] image;
    private int toyId;
    private boolean main;

    public Image() {
    }

    public Image(int imageId, byte[] image, int toyId) {
        this.imageId = imageId;
        this.image = image;
        this.toyId = toyId;
    }

    public Image(int imageId, byte[] image, int toyId, boolean main) {
        this.imageId = imageId;
        this.image = image;
        this.toyId = toyId;
        this.main = main;
    }
    

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getToyId() {
        return toyId;
    }

    public void setToyId(int toyId) {
        this.toyId = toyId;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }
    
}
