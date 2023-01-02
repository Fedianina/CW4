package tables;

import jakarta.persistence.*;
import Help.ParentClass;

@Entity//создаем таблицу
@Table//создаем таблицу
public class Mountain extends ParentClass {
//Гора создаётся с названием (не менее 4 символов),
// страной (не менее 4 символов) и высотой (не менее 100 метров)
    @Column (unique = true, nullable = false)//название дублироваться не может и не может быть налом

    private String name;
    @Column (nullable = false)
    private String country;
    @Column (nullable = false)
    private int height;

    @OneToOne
    @Transient
    private Groups groups;


    public Mountain() {
    }

    public Mountain(String name, String country, int height){


    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length()<4)
            throw new IllegalArgumentException("Название горы не должно быть короче 4х символов");
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        if (country.length()<4)
            throw new IllegalArgumentException("Название страны не должно быть короче 4х символов");
        this.country = country;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height<100)
            throw new IllegalArgumentException("Высота горы должна быть не ниже");
        this.height = height;
    }
}
