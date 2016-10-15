
package main.java;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table( name = "tariff", uniqueConstraints = { @UniqueConstraint( columnNames = {"id"} ) } )

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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tariff_option",
            joinColumns = @JoinColumn(name = "tariff_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private Set<Option> optionSet;

    // пустой конструктор
    public Tariff() {}

    // сеттеры
    public void setName      ( String name )           { this.name = name; }
    public void setPrice     ( double price )          { this.price = price; }
    public void setOptionSet ( Set<Option> optionSet ) { this.optionSet = optionSet; }

    // геттеры
    public int getId()                 { return id; }
    public String getName()            { return name; }
    public double getPrice()           { return price; }
    public Set<Option> getOptionSet()  { return optionSet; }

    // выдать все одной строкой
    @Override
    public String toString() {
        return " \nTariff: " + "\n" +
                " ID = " + id + "\n" +
                " Name = " +  name + "\n" +
                " Price = " + price + "\n"
                ;
    }
}
