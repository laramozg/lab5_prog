package commands;

import foundation.Worker;
import collection.CollectionManager;

public class ReplaceIfLowe extends AbstractCommand {
    public ReplaceIfLowe(CollectionManager manager) {
        super(manager);
    }

    /**
     * Метод заменяет значение по ключу, если новое значение меньше старого
     *
     * @param str
     * @param worker
     * @return
     */
    @Override
    public String execute(String str, Worker worker) {
        if (CollectionManager.manager.getCollection().size() != 0) {
            if (CollectionManager.manager.getCollection().containsKey(str)) {
                if (worker.getOrganization().getEmployeesCount() < CollectionManager.manager.getCollection().get(str).getOrganization().getEmployeesCount()) {
                    CollectionManager.manager.getCollection().put(str, worker);
                    return "Элемент коллекции сохранен, так как новое значение меньше старого";
                }return "Элемент коллекции не сохранен, так как новое значение превышает старого";
            }return "Элемента коллекции с таким id не существует";
        }return "Коллекция пуста";
    }
}