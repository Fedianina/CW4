import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import tables.Alpenist;
import tables.Mountain;

public class pvs {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ormLesson");//создали фабрику для создания классов (инфа из круглыхскобок взята из ресурса)


        EntityManager manager = factory.createEntityManager();//создали менеджера, который будет создавать объекты
        Mountain mountain1 = new Mountain("logfd", "fsfsd", 1234545);
        // TODO заполнить
        //TODO обернуть в трай

        manager.getTransaction().begin();// добавление класса в табл проходит в рамках транзакции
        manager.persist(mountain1);//хотим добавить объект, перечисляем все запроссы, которые дб выполнены

        //если что то пошло не так, можно выподнить manager.getTransaction().rollback() и запросы не будут переданы в бд
        manager.getTransaction().commit();// подтверждение транзакции. выполнение запроса


        //узнать о записи по первичному ключу

        /*Mountain fromBD = manager.find(Mountain.class, 1); //класс который ищем, № артикля
        // удаление из табл. тоже в рамках транзакции
        manager.getTransaction().begin();
        manager.remove(fromBD); //по первичному ключу
        manager.getTransaction().commit();

        //Alpenist alpenist1 = new Alpenist("Mike", "Lensoveta 15", 20);
*/

    }
}
