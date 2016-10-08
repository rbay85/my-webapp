
package main.java;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "tariffs", uniqueConstraints = { @UniqueConstraint( columnNames = {"id"} ) } )

@NamedQueries({
        @NamedQuery( name = "Tariff.getAll", query = "SELECT t FROM Tariff t" ),
        @NamedQuery( name = "Tariff.getAllByName", query = "SELECT t FROM Tariff t ORDER BY t.name" )
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
    private double price;


    public Tariff( String name, double price ){

        this.name = name;
        this.price = price;
    }

    // сеттеры
    public void setName( String value )         { name = value; }
    public void setPrice( double value )        { price = value; }

    // геттеры
    public int getId()              { return id; }
    public String getName()         { return name; }
    public double getPrice()        { return price; }

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
