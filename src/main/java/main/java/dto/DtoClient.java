package main.java.dto;


public class DtoClient {

    private String firstName;
    private String lastName;
    private String birthDay;

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }
    public String getBirthDay() {
        return birthDay;
    }
    public void setBirthDay( String birthDay ) {
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "DtoClient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }
}
