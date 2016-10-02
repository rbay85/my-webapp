
package com.myJavaSchool.entities;

import javax.persistence.*;
import java.io.Serializable;

public class Option implements Serializable{

    private long id;            // идентификационный номер опции
    private String name;        // Название
    private double price;       // Цена
    private double onCost;      // Стоимость подключения

    public Option (long iD, String n, double p, double oC){

        this.id = iD;
        this.name = n;
        this.price = p;
        this.onCost = oC;
    }

    //сеттеры
    public void setId( long value ) { id = value; }
    public void setName( String value ) { name = value; }
    public void setPrice( double value ) { price = value; }
    public void setOnCost( double value ) { onCost = value; }

    //геттеры
    public long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getOnCost() { return onCost; }

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
