
package main.java;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table( name = "options", uniqueConstraints = { @UniqueConstraint( columnNames = { "id", "name" } ) } )

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
    private int id;

    // Название
    @Column( name = "name", nullable = false )
    private String name;

    // Цена
    @Column( name = "price", nullable = false)
    private double price;

    // Стоимость подключения
    @Column( name = "onCost", nullable = false )
    private double onCost;

    // список необходимых опций
    @ManyToMany( fetch = FetchType.LAZY )
    @JoinTable( name = "necessary_option",
            joinColumns = @JoinColumn( name = "option1_id" ),
            inverseJoinColumns = @JoinColumn( name = "option2_id" )
    )
    private List<Option> necessaryOptionList;

    // список несовместимых опций
    @ManyToMany( fetch = FetchType.LAZY )
    @JoinTable( name = "incompatible_option",
            joinColumns = @JoinColumn( name = "option1_id" ),
            inverseJoinColumns = @JoinColumn( name = "option2_id" )
    )
    private List<Option> incompatibleOptionList;

    // ДОБАВИТЬ ПРОВЕРКУ, ЧТОБ ОДНА И ТА ЖЕ ОПЦИЯ НЕ ОКАЗАЛАСЬ В ОБОИХ СПИСКАХ !!!

    // пустой конструктор
    public Option() {}

    // сеттеры
    public void setName                   ( String name )                         { this.name = name; }
    public void setPrice                  ( double price )                        { this.price = price; }
    public void setOnCost                 ( double onCost )                       { this.onCost = onCost; }
    public void setNecessaryOptionList    ( List<Option> necessaryOptionList )    { this.necessaryOptionList = necessaryOptionList; }
    public void setIncompatibleOptionList ( List<Option> incompatibleOptionList ) { this.incompatibleOptionList = incompatibleOptionList; }

    // геттеры
    public int getId()                              { return id; }
    public String getName()                         { return name; }
    public double getPrice()                        { return price; }
    public double getOnCost()                       { return onCost; }
    public List<Option> getNecessaryOptionList()    { return necessaryOptionList; }
    public List<Option> getIncompatibleOptionList() { return incompatibleOptionList; }

    // переопределения
    @Override
    public String toString() {
        return " \nOption: " + "\n" +
                " ID = " + id + "\n" +
                " Name = " +  name + "\n" +
                " Price = " + price + "\n" +
                " Cost of switching on = " + onCost + "\n"
                ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Option)) return false;

        Option option = (Option) o;

        if (id != option.id) return false;
        if (Double.compare(option.price, price) != 0) return false;
        if (Double.compare(option.onCost, onCost) != 0) return false;
        return name.equals(option.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        /*
        long temp;
        result = 31 * result + name.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(onCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        */
        return result;
    }
}
