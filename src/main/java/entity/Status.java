package entity;

public class Status {
    private int id;
    private String name;
    private String description;

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }

    public Status(int id,String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Status{");
        sb.append("id = ").append(id);
        sb.append(", name='").append(name);
        sb.append(", description='").append(description).append("}");
        return sb.toString();
    }

}
