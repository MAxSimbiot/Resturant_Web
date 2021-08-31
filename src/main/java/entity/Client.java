package entity;

import java.time.LocalDate;
import java.util.Objects;

public class Client {
    private int id;

    private String login;
    private String password;
    private String name;
    private String phone;
    private LocalDate regDate;
    private int totalSpent;
    private int roleId;
    private Role roleEntity;

    public Client() {
    }

    public Client(int id, String login, String password, String name, String phone, LocalDate regDate, int totalSpent, int roleId, Role roleEntity) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.regDate = regDate;
        this.totalSpent = totalSpent;
        this.roleId = roleId;
        this.roleEntity = roleEntity;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getPhone() {return phone;}

    public void setPhone(String phone) {this.phone = phone;}

    public LocalDate getRegDate() {return regDate;}

    public void setRegDate(LocalDate regDate) {this.regDate = regDate;}

    public int getTotalSpent() {return totalSpent;}

    public void setTotalSpent(int totalSpent) {this.totalSpent = totalSpent;}

    public int getRoleId() {return roleId;}

    public void setRoleId(int roleId) {this.roleId = roleId;}

    public Role getRoleEntity() {return roleEntity;}

    public void setRoleEntity(Role roleEntity) {this.roleEntity = roleEntity;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id &&
                totalSpent == client.totalSpent &&
                roleId == client.roleId &&
                login.equals(client.login) &&
                password.equals(client.password) &&
                Objects.equals(name, client.name) &&
                Objects.equals(phone, client.phone) &&
                regDate.equals(client.regDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, name, phone, regDate, totalSpent, roleId);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("User:");
        res.append("\nId: ").append(id);
        res.append("\nLogin: ").append(login);
        res.append("\nPassword: ").append(password);
        res.append("\nName: ").append(name);
        res.append("\nPhone: ").append(phone);
        res.append("\nRegDate: ").append(regDate);
        res.append("\nTotalSpent: ").append(totalSpent);
        res.append("\nRoleId: ").append(roleId);
        return res.toString();
    }
}
