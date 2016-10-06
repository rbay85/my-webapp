
package main.java;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "options", uniqueConstraints = { @UniqueConstraint( columnNames = {"id"} ) } )

@NamedQueries({
        @NamedQuery( name = "Option.getAll", query = "SELECT o FROM Option o" ),
        @NamedQuery( name = "Option.getAllByName", query = "SELECT o FROM Option o ORDER BY o.name" )
})


public class Option implements Serializable{

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
    @Column( name = "сost_of_switching_on", nullable = false )
    private double onCost;

    public Option(int i, String n, double p, double oC){

        this.name = n;
        this.price = p;
        this.onCost = oC;
    }

    //сеттеры
    public void setId( long value )         { id = value; }
    public void setName( String value )     { name = value; }
    public void setPrice( double value )    { price = value; }
    public void setOnCost( double value )   { onCost = value; }

    //геттеры
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
