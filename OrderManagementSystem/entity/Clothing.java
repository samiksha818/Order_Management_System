package entity;

public class Clothing extends Product {
    private String size;
    private String color;

    public Clothing() {}

    public Clothing(int productId, String productName, String description, double price, int quantityInStock,
                    String size, String color) {
        super(productId, productName, description, price, quantityInStock, "Clothing");
        this.size = size;
        this.color = color;
    }

    public Clothing(int productId, String name, String description, double price, int quantity, String type,
            String size2, String color2) {
        //TODO Auto-generated constructor stub
    }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}

