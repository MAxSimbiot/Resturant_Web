package entity;

import java.util.Objects;

public class Product {

    private int id;
    private String name;
    private String description;
    private int price;
    private String image_url;
    private int category_id;
    private Category category_entity;

    private int count = 1;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public void setDescription(String description) {this.description = description;}

    public String getDescription() {return description;}

    public int getPrice() {return price;}

    public void setPrice(int price) {this.price = price;}

    public String getImage_url() {return image_url;}

    public void setImage_url(String image_url) {this.image_url = image_url;}

    public int getCategory_id() {return category_id;}

    public void setCategory_id(int category_id) {this.category_id = category_id;}

    public Category getCategory_entity() {return category_entity;}

    public void setCategory_entity(Category category_entity) {this.category_entity = category_entity;}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                price == product.price &&
                category_id == product.category_id &&
                name.equals(product.name) &&
                description.equals(product.description) &&
                image_url.equals(product.image_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, image_url, category_id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name);
        sb.append(", description='").append(description);
        sb.append(", price=").append(price);
        sb.append(", image_url='").append(image_url);
        sb.append(", category_id=").append(category_id);
        sb.append(", category_entity=").append(category_entity).append("}");
        return sb.toString();
    }
}
