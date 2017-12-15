package controllers;

import models.Person;
import models.Transaction;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionController extends Controller {//Контроллер для упраления транзакциями
    @Inject
    FormFactory formFactory;
    private Person person;//Тестовый Юзер

    TransactionController(){
        person = new Person("Test",1000);
    }

    public Result index(){//Стартовая страниц. Общая информация
        return ok(index.render(person));
    }

    public Result history(){
        List<Transaction> list = Transaction.find.all();//Посмотреть историю операций
        return ok(views.html.history.render(list));
    }

    public Result debit(){//Переход на страницу с дебитовыми операциями. Для примера - простое пополнение счета
        Form<Transaction> list = formFactory.form(Transaction.class);
        return ok(views.html.debit.render(list));
    }

    public Result credit(){//Переход на страницу с кредитовыми операциями. Для примера - перевод средств кому то
        Form<Transaction> list = formFactory.form(Transaction.class);
        return ok(views.html.credit.render(list));
    }

    public Result exception(String exception){//Страница для различных ошибок
        return ok(views.html.exception.render(exception));
    }

    public Result saveCredit(){//Сохранение кредитовой операции
        Form<Transaction> transactionForm = formFactory.form(Transaction.class).bindFromRequest();
        Transaction transaction = transactionForm.get();
        transaction.date = new Date();
        transaction.lostBalance =  person.balance - transaction.transaction;
        transaction.transaction = - transaction.transaction;
        if(transaction.lostBalance<0){
            transaction.status = "not confirmed";
            transaction.save();
            return ok(views.html.exception.render("Sorry, but you do not have enough money for this operation."));
        }
        person.balance = person.balance + transaction.transaction;
        transaction.status = "confirmed";
        transaction.save();
        return redirect(routes.TransactionController.index());
    }

    public Result saveDebit(){//Сохранение дебитовой операции
        Form<Transaction> transactionForm = formFactory.form(Transaction.class).bindFromRequest();
        Transaction transaction = transactionForm.get();
        transaction.date = new Date();
        transaction.lostBalance = person.balance + transaction.transaction;
        person.balance = transaction.lostBalance;
        transaction.recipient = "-";
        transaction.status = "confirmed";
        transaction.save();
        return redirect(routes.TransactionController.index());
    }
}



