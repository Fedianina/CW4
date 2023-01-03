package tables;


import jakarta.persistence.*;
import Help.ParentClass;

import javax.xml.namespace.QName;
import java.util.List;
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

    @OneToMany
    private List<Groups> groups;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alpenist alpenist = (Alpenist) o;

        if (age != alpenist.age) return false;
        if (!Objects.equals(name, alpenist.name)) return false;
        if (!Objects.equals(address, alpenist.address)) return false;
        return Objects.equals(groups, alpenist.groups);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Alpenist{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", groups=" + groups +
                '}';
    }
}
