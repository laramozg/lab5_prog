package commands;


import foundation.Worker;
import collection.CollectionManager;

import java.util.Enumeration;

public class Update extends AbstractCommand {
    public Update(CollectionManager manager) {
        super(manager);
    }

    /**
     * Метод обновляет элемент в коллекции по его id
     *
     * @param str
     * @param worker
     * @return
     */
    @Override
    public String execute(String str, Worker worker) throws NumberFormatException {
        if (!(worker == null)) {
            if (CollectionManager.manager.getCollection().size() != 0) {
                int idNew = Integer.parseInt(str);
                Enumeration<String> key = CollectionManager.manager.getCollection().keys();
                while (key.hasMoreElements()) {
                    String k = key.nextElement();
                    if (CollectionManager.manager.getCollection().get(k).getId() == idNew) {
                        worker.setId(idNew);
                        CollectionManager.manager.getCollection().put(k,worker);
                        return "Элемент коллекции обновлен";
                    }
                }return "Элемента с таким id нет";
            }return "Коллекция пуста";
        }return "Ошибка при добавлении элемента. Поля указаны не верно";
    }
}