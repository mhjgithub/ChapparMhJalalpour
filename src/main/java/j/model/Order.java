package j.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "tb_order")
public class Order {
    // ____________________________________________________________ fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSefaresh", unique = true)
    private Long idSefaresh;

    @NotNull
    @Column(name = "count")
    private int count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMoshtari")
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idKala")
    private Product product;

    public Order() {
    }
    // ____________________________________________________________ auto generated constructor and setters and getters
    @Override
    public String toString() {
        return "Order{" +
                "idSefaresh=" + idSefaresh +
                ", count=" + count +
                ", customer=" + customer +
                ", product=" + product +
                '}';
    }

    public Long getIdSefaresh() {
        return idSefaresh;
    }

    public void setIdSefaresh(Long idSefaresh) {
        this.idSefaresh = idSefaresh;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order(Long idSefaresh, int count, Customer customer, Product product) {
        this.idSefaresh = idSefaresh;
        this.count = count;
        this.customer = customer;
        this.product = product;
    }
}
