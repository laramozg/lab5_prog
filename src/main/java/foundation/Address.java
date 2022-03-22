package foundation;

public class Address {
    private final String street; //Длина строки не должна быть больше 130, Поле не может быть null

    public Address(String street){
        this.street = street;
    }
    public String getStreet(){return street;}

    @Override
    public String toString() {
        return street ;
    }
}
