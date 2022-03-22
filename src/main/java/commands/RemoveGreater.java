package commands;

import foundation.Worker;
import collection.CollectionManager;

import java.util.Enumeration;

public class RemoveGreater extends AbstractCommand {
    public RemoveGreater(CollectionManager manager) {
        super(manager);
    }

    /**
     * Удаляет из коллекции все элементы, превышающие заданный
     *
     * @param worker
     * @return
     */
    @Override
    public String execute(Worker worker) {
        if (CollectionManager.manager.getCollection().size() != 0) {
            int count = CollectionManager.manager.getCollection().size();
            Enumeration<String> key = CollectionManager.manager.getCollection().keys();
            while (key.hasMoreElements()) {
                String k = key.nextElement();
                if (CollectionManager.manager.getCollection().get(k).getId() > worker.getId()) {
                    CollectionManager.manager.getCollection().remove(k);
                }
            }if (count == CollectionManager.manager.getCollection().size()){
                return "Коллекция не изменена, так как подходящих элементов нет";
            }return "Элементы удалены";
        } return "Коллекция пуста";
    }
}