package entity;

import java.util.Objects;

public class Category {

    private int id;
    private String name_ru;
    private String name_us;
    private String description_ru;
    private String description_us;
    private int parent_id;

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

    public int getParent_id() {return parent_id;}

    public void setParent_id(int parent_id) {this.parent_id = parent_id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                parent_id == category.parent_id &&
                name_ru.equals(category.name_ru) &&
                name_us.equals(category.name_us) &&
                description_ru.equals(category.description_ru) &&
                description_us.equals(category.description_us);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name_ru, name_us, description_ru, description_us, parent_id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Category{");
        sb.append("id=").append(id);
        sb.append(", name_ru='").append(name_ru);
        sb.append(", name_us='").append(name_us);
        sb.append(", description_ru='").append(description_ru);
        sb.append(", description_us='").append(description_us);
        sb.append(", parent_id=").append(parent_id).append("}");
        return sb.toString();
    }
}
