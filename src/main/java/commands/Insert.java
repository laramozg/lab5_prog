package commands;

import foundation.Worker;
import collection.CollectionManager;

public class Insert extends AbstractCommand {
    public Insert(CollectionManager manager) {
        super(manager);
    }


    /**
     * Метод добавляет элемент в коллекцию по ключу
     *
     * @param str
     * @param worker
     * @return
     */
    @Override
    public String execute(String str, Worker worker) {
        CollectionManager.manager.getCollection().put(str, worker);
        return "Элемент коллекции добавлен";
    }
}