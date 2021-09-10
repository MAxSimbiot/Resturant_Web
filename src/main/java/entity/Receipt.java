package entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Receipt {

    private int id;
    private LocalDate creationTime;
    private LocalDate lastUpdate;
    private int clientId;
    private int statusId;
    private Status statusEntity;

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public LocalDate getCreationTime() {return creationTime;}

    public void setCreationTime(LocalDate creationTime) {this.creationTime = creationTime;}

    public LocalDate getLastUpdate() {return lastUpdate;}

    public void setLastUpdate(LocalDate lastUpdate) {this.lastUpdate = lastUpdate;}

    public int getClientId() {return clientId;}

    public void setClientId(int clientId) {this.clientId = clientId;}

    public int getStatusId() {return statusId;}

    public void setStatusId(int statusId) {this.statusId = statusId;}

    public Status getStatusEntity() {return statusEntity;}

    public void setStatusEntity(Status statusEntity) {this.statusEntity = statusEntity;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return id == receipt.id &&
                clientId == receipt.clientId &&
                statusId == receipt.statusId &&
                creationTime.equals(receipt.creationTime) &&
                lastUpdate.equals(receipt.lastUpdate) &&
                statusEntity == receipt.statusEntity &&
                Objects.equals(products, receipt.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, creationTime, lastUpdate, clientId, statusId, statusEntity, products);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Receipt{");
        sb.append("id=").append(id);
        sb.append(", creationTime=").append(creationTime);
        sb.append(", lastUpdate=").append(lastUpdate);
        sb.append(", clientId=").append(clientId);
        sb.append(", statusId=").append(statusId);
        sb.append(", status= ").append(statusEntity.toString()).append("}");
        return sb.toString();
    }
}
