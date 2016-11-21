package main.java.dto;

public class DtoTariff {

    private String Id;
    private String Name;

    public String getId() {
        return Id;
    }
    public void setId( String id ) {
        Id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName( String name ) {
        Name = name;
    }

    @Override
    public String toString() {
        return "DtoTariff{" +
                "Id='" + Id + '\'' +
                ", Name='" + Name + '\'' +
                '}';
    }
}
