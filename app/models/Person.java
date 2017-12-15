package models;

import io.ebean.Model;

import javax.persistence.Id;
import java.util.List;

public class Person extends Model {//Модель юзера
    @Id
    public Integer id;
    public String name;
    public Integer balance;

    public Person(){
    }

    public Person(String name, Integer balance) {
        this.name = name;
        this.balance = balance;
    }
}
