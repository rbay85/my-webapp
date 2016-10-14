
package main.java;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(
        name = "contract",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "id",
                                "phone"
                        }
                )
        }
)
@NamedQueries({
        @NamedQuery( name = "Contract.getAll", query = "SELECT ct FROM Contract ct" ),
        @NamedQuery( name = "Contract.getByPhone", query = "SELECT ct FROM Contract ct WHERE ct.phone = :name" )
})


public class Contract implements Serializable{

    private static final long serialVersionUID = 2L;

    // идентификационный номер контракта
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", nullable = false )
    private int id;

    // Номер
    @Column( name = "phone", nullable = false )
    @Pattern( regexp = "\\(\\d{3}\\)\\d{3}-\\d{4}", message = "{invalid phone number!}" )
    private String phone;

    // идентификационный номер соответсвующего контракта
    @ManyToOne
    @JoinColumn( name = "client_id" )
    private Client client;

    // Тариф
    @OneToOne
    @JoinColumn( name = "tariff_id", nullable = false )
    private Tariff tariff;

    // Выбранные опций
    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable(
            name = "contract_option",
            joinColumns = @JoinColumn( name = "contract_id" ),
            inverseJoinColumns = @JoinColumn( name = "option_id" ) )
    private Set<Option> optionSet;

    // блокирока
    @Column( name = "is_locked" )
    private int is_locked;

    // пустой конструктор
    public Contract() {}

    // сеттеры
    public void setPhone        ( String phone )            { this.phone = phone; }
    public void setTariff       ( Tariff tariff )           { this.tariff = tariff; }
    public void setOptionSet    ( Set<Option> optionSet )   { this.optionSet = optionSet; }
    public void setIs_locked    ( int is_locked )           { this.is_locked = is_locked; }

    // геттеры
    public int getId()                  { return id; }
    public String getPhone()            { return phone; }
    public Tariff getTariff()           { return tariff; }
    public Set<Option> getOptionSet()   { return optionSet; }
    public int getIs_locked()           { return is_locked; }

    // выдать все одной строкой
    @Override
    public String toString() {
        return " \nContract: " + "\n" +
                " ID = " + id + "\n" +
                " Phone No = " +  phone + "\n" +
                " Tariff = " + tariff + "\n" +
                " Options = " + optionSet + "\n" +
                " Lock = " + is_locked + "\n"
                ;
    }
}
