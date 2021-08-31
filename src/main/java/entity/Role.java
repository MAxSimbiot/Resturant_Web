package entity;

public enum Role {

    ADMIN("Admin","Site administrator"),
    CLIENT("Client","Fellow food enjoyer"),
    MANAGER("Manager","Worker..int`t much more");

    private String name;
    private String description;

    private Role(String name,String description){
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
