package main.java.entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "user" )

@NamedQueries({
        @NamedQuery( name = "User.getAll", query = "SELECT u FROM User u" ),
        @NamedQuery( name = "User.getByMail", query = "SELECT u FROM User u WHERE u.email = :email" )
})


public class User implements Serializable{

    private static final long serialVersionUID = 5L;

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    @Column( name = "id", nullable = false )
    private int id;

    // электронная почта
    @Column( name = "email", nullable = false )
    @Email( message = "Input correct e-mail, please !" )
    private String email;

    // пароль
    @Column( name = "password", nullable = false )
    @Length( min = 4, message = "password must have at least 4 characters !" )
    private String passWord;

    // роль
    @Column( name = "role", nullable = false )
    private String role;

    @OneToOne
    @JoinColumn(name = "client_id", nullable = false )
    private Client client;

    // сеттеры
    public void setEmail     ( String email )      { this.email = email; }
    public void setPassWord  ( String passWord )   { this.passWord = passWord; }
    public void setRole      ( String role )       { this.role = role; }
    public void setClient    ( Client client )     { this.client = client; }

    // геттеры
    public int getId()           { return id; }
    public String getEmail()     { return email; }
//  public String getPassWord()  { return passWord; }
//  public String getRole()      { return role; }
    public Client getClient()    { return client; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", passWord='" + passWord + '\'' +
                ", role='" + role + '\'' +
                ", client=" + client +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!email.equals(user.email)) return false;
        if (!passWord.equals(user.passWord)) return false;
        if (!role.equals(user.role)) return false;
        return client.equals(user.client);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + passWord.hashCode();
        result = 31 * result + role.hashCode();
        result = 31 * result + client.hashCode();
        return result;
    }
}
