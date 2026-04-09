package tn.esprit.jobgestion.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String description;

    private double price;
    private String image;
    private boolean available;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    @JsonBackReference(value = "menu-foods")
    private Menu menu;

    public Food() {
    }

    // ===== GETTERS =====

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public boolean isAvailable() {
        return available;
    }

    public Menu getMenu() {
        return menu;
    }

    // ===== SETTERS =====

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
