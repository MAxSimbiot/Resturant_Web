package entity;

public enum Role {

    ADMIN("Site administrator"),
    CLIENT("Fellow food enjoyer"),
    MANAGER("Worker..int`t much more");

    private String description;

    private Role(String description){
        this.description = description;
    }

    public static Role getByid(int id){
        return Role.values()[id-1];
    }

    @Override
    public String toString() {
        return "Role{" +
                "name = '" + name() + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
