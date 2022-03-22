package commands;

import foundation.Worker;
import collection.CollectionManager;


/**
 * Класс наследник для всех команд
 */
public class AbstractCommand {
    CollectionManager manager;

    public AbstractCommand(CollectionManager manager) {
        this.manager = manager;
    }

    public String execute() {return null;}

    public String execute(String str) {return null;}

    public String execute(Worker worker) {
        return null;
    }

    public String execute(String str, Worker worker) {
        return null;
    }

    public Worker execute(String str1, String str2, String str3, String str4, String str5, String str6, String str7,
                          String str8, String str9, String str10) {
        return null;
    }
}