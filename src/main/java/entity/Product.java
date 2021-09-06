package entity;

import java.util.Objects;

public class Product {

    private int id;
    private String name_ru;
    private String name_us;
    private String description_ru;
    private String description_us;
    private int price;
    private String image_url;
    private int category_id;
    private Category category_entity;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName_ru() {return name_ru;}

    public void setName_ru(String name_ru) {this.name_ru = name_ru;}

    public String getName_us() {return name_us;}

    public void setName_us(String name_us) {this.name_us = name_us;}

    public String getDescription_ru() {return description_ru;}

    public void setDescription_ru(String description_ru) {this.description_ru = description_ru;}

    public String getDescription_us() {return description_us;}

    public void setDescription_us(String description_us) {this.description_us = description_us;}

    public int getPrice() {return price;}

    public void setPrice(int price) {this.price = price;}

    public String getImage_url() {return image_url;}

    public void setImage_url(String image_url) {this.image_url = image_url;}

    public int getCategory_id() {return category_id;}

    public void setCategory_id(int category_id) {this.category_id = category_id;}

    public Category getCategory_entity() {return category_entity;}

    public void setCategory_entity(Category category_entity) {this.category_entity = category_entity;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id &&
                price == product.price &&
                category_id == product.category_id &&
                name_ru.equals(product.name_ru) &&
                name_us.equals(product.name_us) &&
                description_ru.equals(product.description_ru) &&
                description_us.equals(product.description_us) &&
                image_url.equals(product.image_url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name_ru, name_us, description_ru, description_us, price, image_url, category_id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", name_ru='").append(name_ru);
        sb.append(", name_us='").append(name_us);
        sb.append(", description_ru='").append(description_ru);
        sb.append(", description_us='").append(description_us);
        sb.append(", price=").append(price);
        sb.append(", image_url='").append(image_url);
        sb.append(", category_id=").append(category_id);
        sb.append(", category_entity=").append(category_entity).append("}");
        return sb.toString();
    }
}
