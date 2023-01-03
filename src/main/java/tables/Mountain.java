package tables;

import jakarta.persistence.*;
import Help.ParentClass;

import java.util.List;
import java.util.Objects;

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

    @OneToMany (mappedBy = "mountain")
    private List <Groups> groups;


    public Mountain() {
    }

    public Mountain(String name, String country, int height){
        setCountry(country);
        setName(name);
        setHeight(height);


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mountain mountain = (Mountain) o;

        if (height != mountain.height) return false;
        if (!Objects.equals(name, mountain.name)) return false;
        if (!Objects.equals(country, mountain.country)) return false;
        return Objects.equals(groups, mountain.groups);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + height;
        result = 31 * result + (groups != null ? groups.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mountain{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", height=" + height +
                ", groups=" + groups +
                '}';
    }
}
