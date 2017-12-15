package models;

import io.ebean.Finder;
import io.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Transaction extends Model{//Модель транзакции
    @Id
    public Integer id;
    public Integer transaction;
    public String recipient;
    public Date date;
    public Integer lostBalance;
    public String status;

    public static Finder<Integer, Transaction> find = new Finder<>(Transaction.class);

    public String getDateFormat(){//Метод для читаемого отображения даты в браузере
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        return simpleDateFormat.format(date);
    }
}
