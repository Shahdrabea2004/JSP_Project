package itemJava;

public class items {

    private int id;
    private String name;
    private String description;
    private String image;
    private double price;

    public items(int id, String name, String description, String image, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getImage() { return image; }
    public double getPrice() { return price; }
}
