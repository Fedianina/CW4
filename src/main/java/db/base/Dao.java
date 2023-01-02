package db.base;

import java.util.List;

public interface Dao <T, PK> {

    //добавление инфы в табл

    void add (T t);

    // обновление данных в табл
    void update (T t);

    // удаление данных по уникальному идентификатору
      void  delete (T t);

      // полученгие инфы по уник идентиф
    T get (PK pk);

    //получ всех записей


    List<T> get();




}
