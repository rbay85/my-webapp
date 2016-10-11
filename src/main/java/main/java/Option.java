
package main.java;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "options", uniqueConstraints = { @UniqueConstraint( columnNames = {"id"} ) } )

@NamedQueries({
        @NamedQuery( name = "Option.getAll", query = "SELECT o FROM Option o" ),
        @NamedQuery( name = "Option.getByName", query = "SELECT o FROM Option o WHERE o.name = :name" )
})


public class Option implements Serializable{

    private static final long serialVersionUID = 4L;

    // идентификационный номер опции
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", nullable = false )
    private long id;

    // Название
    @Column( name = "name", nullable = false )
    private String name;

    // Цена
    @Column( name = "price", nullable = false)
    private double price;

    // Стоимость подключения
    @Column( name = "onCost", nullable = false )
    private double onCost;

    public Option( String name, double price, double onCost){

        this.name = name;
        this.price = price;
        this.onCost = onCost;
    }

    // сеттеры
    public void setName( String value )     { name = value; }
    public void setPrice( double value )    { price = value; }
    public void setOnCost( double value )   { onCost = value; }

    // геттеры
    public long getId()         { return id; }
    public String getName()     { return name; }
    public double getPrice()    { return price; }
    public double getOnCost()   { return onCost; }

    // выдать все одной строкой
    @Override
    public String toString() {
        return " \nOption: " + "\n" +
                " ID = " + id + "\n" +
                " Name = " +  name + "\n" +
                " Price = " + price + "\n" +
                " Cost of switching on = " + onCost + "\n"
                ;
    }
}
