import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import tables.Alpenist;
import tables.Groups;
import tables.Mountain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Application {
    public static void main(String[] args) {

        Mountain mountain1 = new Mountain("logfd", "fsfsd", 1234545);
        Alpenist alpenist1 = new Alpenist("Майк", "Мира 1", 24);
        Alpenist alpenist2 = new Alpenist("Марк", "Мира 2", 27);
        Alpenist alpenist3 = new Alpenist("Лида", "Комсомола 2", 30);
        Groups groups = new Groups(mountain1,3, LocalDateTime.of(LocalDate.of(2023,10,28), LocalTime.of(12,00)));



        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormLesson");//создали фабрику для создания классов (инфа из круглыхскобок взята из ресурса)
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();
        manager.persist(mountain1);
        manager.persist(alpenist1);
        manager.persist(alpenist2);
        manager.persist(alpenist3);
        manager.persist(groups);


        manager.getTransaction().commit();
        groups.addAlpenist(alpenist2);
    }
}
