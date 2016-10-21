
package main.java.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;


@Entity
@Table( name = "tariff", uniqueConstraints = { @UniqueConstraint( columnNames = {"id", "name"} ) } )

@NamedQueries({
        @NamedQuery( name = "Tariff.getAll", query = "SELECT t FROM Tariff t" ),
        @NamedQuery( name = "Tariff.getByName", query = "SELECT t FROM Tariff t WHERE t.name = :name" )
})


public class Tariff implements Serializable{

    private static final long serialVersionUID = 3L;

    // идентификационный номер тарифа
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", nullable = false )
    private int id;

    // Название
    @Column( name = "name", nullable = false )
    private String name;

    // Цена
    @Column( name = "price", nullable = false )
    @Min(value = 0, message = "must be positive!")
    private double price;

    // список доступных опций
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tariff_option",
            joinColumns = @JoinColumn(name = "tariff_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> optionList;

    // пустой конструктор
    public Tariff() {}

    // сеттеры
    public void setName       ( String name )              { this.name = name; }
    public void setPrice      ( double price )             { this.price = price; }
    public void setOptionList ( List<Option> optionList )  { this.optionList = optionList; }

    // геттеры
    public int getId()                   { return id; }
    public String getName()              { return name; }
    public double getPrice()             { return price; }
    public List<Option> getOptionList()  { return optionList; }

    // переопределения
    @Override
    public String toString() {
        return " <br> tariff " + id + ". " + name + "<br>" +
                " price: " + price + "<br>" +
                " options: " + optionList + "<br>";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tariff)) return false;

        Tariff tariff = (Tariff) o;

        if (id != tariff.id) return false;
        if (Double.compare(tariff.price, price) != 0) return false;
        return name.equals(tariff.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        /*
        long temp;
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        */
        return result;
    }
}
