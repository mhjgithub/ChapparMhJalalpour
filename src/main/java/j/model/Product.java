package j.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_product")
public class Product {
    // ____________________________________________________________ fields
    // _________________ ID  name  age
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idKala", unique = true)
    private Long idKala;

    @Column(name = "name")
    private String name;

    @Column (name = "price")
    private double price;

    // _________________ OneToMany
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Order> orders;

    public Product() {

    }
    // ____________________________________________________________ auto generated constructor and setters and getters

    @Override
    public String toString() {
        return "Product{" +
                "idKala=" + idKala +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", orders=" + orders +
                '}';
    }

    public Long getIdKala() {
        return idKala;
    }

    public void setIdKala(Long idKala) {
        this.idKala = idKala;
    }

    public Long getidKala() {
        return idKala;
    }

    public void setidKala(Long idKala) {
        this.idKala = idKala;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Product(Long idKala, String name, double price, List<Order> orders) {
        this.idKala = idKala;
        this.name = name;
        this.price = price;
        this.orders = orders;
    }

}
