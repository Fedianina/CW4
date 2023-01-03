import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import tables.Alpenist;
import tables.Groups;
import tables.Mountain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class pvs {
    public static void main(String[] args) {

        Mountain mountain1 = new Mountain("logfd", "fsfsd", 1234545);
        Alpenist alpenist1 = new Alpenist("Майк", "Мира 1", 24);
        Alpenist alpenist2 = new Alpenist("Марк", "Мира 2", 27);
        Alpenist alpenist3 = new Alpenist("Лида", "Комсомола 2", 30);
        Groups groups = new Groups(mountain1,3, LocalDateTime.of(LocalDate.of(2023,10,28), LocalTime.of(12,00)));
        groups.addAlpenist(alpenist1);




        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormLesson");//создали фабрику для создания классов (инфа из круглыхскобок взята из ресурса)
        EntityManager manager = factory.createEntityManager();//создали менеджера, который будет создавать объекты

        manager.getTransaction().begin();// добавление класса в табл проходит в рамках транзакции
        manager.persist(mountain1);
        manager.persist(alpenist1);
        manager.persist(alpenist2);
        manager.persist(alpenist3);
        manager.persist(groups);

        //хотим добавить объект, перечисляем все запроссы, которые дб выполнены

        //если что то пошло не так, можно выподнить manager.getTransaction().rollback() и запросы не будут переданы в бд
        manager.getTransaction().commit();// подтверждение транзакции. выполнение запроса
        groups.addAlpenist(alpenist2);


/*
        //узнать о записи по первичному ключу

        /*Mountain fromBD = manager.find(Mountain.class, 1); //класс который ищем, № артикля
        // удаление из табл. тоже в рамках транзакции
        manager.getTransaction().begin();
        manager.remove(fromBD); //по первичному ключу
        manager.getTransaction().commit();

        */
        //Alpenist alpenist1 = new Alpenist("Mike", "Lensoveta 15", 20);



    }
}
