package commands;

import collection.CollectionManager;

import java.util.Enumeration;

public class Show extends AbstractCommand {
    public Show(CollectionManager manager) {
        super(manager);
    }

    /**
     * Метод выводит элементы коллекции
     *
     * @return
     */
    @Override
    public String execute() {
        if (CollectionManager.manager.getCollection().size() != 0) {
            Enumeration<String> key = CollectionManager.manager.getCollection().keys();
            StringBuilder list = new StringBuilder();
            while (key.hasMoreElements()) {
                String k = key.nextElement();
                list.append(k).append(" - ").append(CollectionManager.manager.getCollection().get(k)).append("\n");
            }
            return list.toString();
        }
        return "Коллекция пуста.";
    }
}