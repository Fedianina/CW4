package tables;


import jakarta.persistence.*;
import Help.ParentClass;

import javax.xml.namespace.QName;
import java.util.Objects;

@Entity
@Table
public class Alpenist extends ParentClass {

    @Column(nullable = false)
    private String name;

    @Column (nullable = false)
    private String address;

    @Column (nullable = false)
    private int age;

    @ManyToOne
    @Transient
    private Groups groups;


    public Alpenist() {
    }

    public Alpenist(String name, String address, int age){
        setName(name);
        setAddress(address);
        setAge(age);

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length()<3)
            throw new IllegalArgumentException("Имя не должно быть короче 3х символов");
        this.name = name;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.length()<5)
            throw new IllegalArgumentException("Адрес не должен быть короче 5х символов");

        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18)
            throw new IllegalArgumentException("Возраст не должен быть меньше 18 лет");
        this.age = age;
    }
}
