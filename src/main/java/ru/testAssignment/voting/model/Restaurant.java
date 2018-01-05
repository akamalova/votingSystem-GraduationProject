package ru.testAssignment.voting.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = Restaurant.ALL_SORTED, query = "SELECT m FROM Restaurant m ORDER BY m.name desc"),
        @NamedQuery(name = Restaurant.DELETE, query = "DELETE FROM Restaurant m WHERE m.id=:id")
})
@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractNamedEntity{

    public static final String ALL_SORTED = "Restaurant.getAll";
    public static final String DELETE = "Restaurant.delete";

    @Column(name = "description")
    @NotNull
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @OrderBy("dateTime DESC")
    private List<Menu> menu;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public Restaurant(Integer id, String name, String description, LocalDateTime dateTime) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public List<Menu> getMenu() {
        return menu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
