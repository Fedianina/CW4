package db.base;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlpenistDao implements Dao <Alpenist, Integer>{
    //вынести в рефлексию
    public void createTable(){
        String create = "CREATE TABLE IF NOT EXISTS alpenists(" +
                "alpenist_id SERIAL PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "address VARCHAR(100) NOT NULL"+
                ");";

        //используется для динамической подгрузки класса в память (непосредственно включает библиотеку)
        try {
            Class.forName("org.postgresql.Driver");
        }
        catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(
                ConnectionSettings.CONNECTION_STR,
                ConnectionSettings.LOGIN,
                ConnectionSettings.PASSWORD
        )){

            System.out.println("Подключился");

            //для выполнения запроса создается объект
            try (Statement statement = connection.createStatement()) {
                System.out.println("Полет нормальный");
                // выполнение запроса
                statement.executeUpdate(create);
                System.out.println("Таблица создана");
                // executeUpdate используется для запросов, которые не предполагают извлечение из БД
            }
        }catch (SQLException e){
            System.out.println("Таблица не была создана по причине" + e.getMessage());
            throw new RuntimeException(e);
        }

    }






    @Override
    public void add(Alpenist alpenist) {

        String insert = "INSERT INTO alpenists (name, address)" +
                "VALUES (?,?)";//тут потом будет имя и возраст
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Драйвер потерялся");
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(
                ConnectionSettings.CONNECTION_STR,
                ConnectionSettings.LOGIN,
                ConnectionSettings.PASSWORD
        )){
            try (PreparedStatement statement = connection.prepareStatement(insert)){// объект, хранящий в себе строчку запроса insert
                // передаем вместо ? конкретные данные
                statement.setString(1, alpenist.getName());// 1 - какой столбец заполним, далее - чем
                statement.setString(2, alpenist.getAddress());
                statement.executeUpdate();//сюда ничего не передаем, тк строка запроса была передана в 66 строке
                System.out.println("Альпинист добавлен");
            }
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }
    }

    @Override
    public void update(Alpenist alpenist) {

    }

    @Override
    public void delete(Alpenist alpenist) {

    }

    @Override
    public Alpenist get(Integer integer) {
        String select = "SELECT name, age FROM alpenists" +
                "WHERE alpenist_id = ?";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("org.postgresql.Driver не был загружен");
            throw new RuntimeException(e);
        }
        try (Connection connection = DriverManager.getConnection(
                ConnectionSettings.CONNECTION_STR,
                ConnectionSettings.LOGIN,
                ConnectionSettings.PASSWORD
        )){
            try (PreparedStatement statement = connection.prepareStatement(select)){
                statement.setInt(1, integer);

                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                   Alpenist alpenist = new Alpenist();
                    alpenist.setId(integer);
                    alpenist.setName(resultSet.getString("name"));
                    alpenist.setAddress(resultSet.getString("address"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public List<Alpenist> get() {
        List<Alpenist> alpenists = new ArrayList<>();
        String select = "SELECT * FROM tb_authors";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try (Connection connection = DriverManager.getConnection(
                ConnectionSettings.CONNECTION_STR,
                ConnectionSettings.LOGIN,
                ConnectionSettings.PASSWORD
        )){
            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery(select);
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String address = resultSet.getString("address");
                    int id = resultSet.getInt("author_id");


                    Alpenist alpenist = new Alpenist();
                    alpenist.setId(id);
                    alpenist.setName(name);
                    alpenist.setAddress(address);

                    alpenists.add(alpenist);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // try - close вызывается сразу после Exception до передачи управления блоку catch,
        // или в конце блока try, если ошибки не возникнет
        // finally - close вызывается по месту объявления блока finally, после try-catch

        return alpenists;
    }
}


