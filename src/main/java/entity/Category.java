package entity;

import java.util.Objects;

public class Category {

    private int id;
    private String name;
    private String description;
    private int parent_id;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getDescription() {return description;}

    public void setDescription(String description) {this.description = description;}

    public int getParent_id() {return parent_id;}

    public void setParent_id(int parent_id) {this.parent_id = parent_id;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return id == category.id &&
                parent_id == category.parent_id &&
                name.equals(category.name) &&
                description.equals(category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, parent_id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Category{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name);
        sb.append(", description='").append(description);
        sb.append(", parent_id=").append(parent_id).append("}");
        return sb.toString();
    }
}
