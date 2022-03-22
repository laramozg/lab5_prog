package commands;

import foundation.Position;
import collection.CollectionManager;

import java.util.Enumeration;


public class FilterGreaterThanPosition extends AbstractCommand {
    public FilterGreaterThanPosition(CollectionManager manager) {
        super(manager);
    }

    /**
     * Метод выводит элементы, значение поля position которого больше заданного
     *
     * @param str
     * @return
     */
    @Override
    public String execute(String str) {
        Position position = Position.valueOf(str);
        if (CollectionManager.manager.getCollection().size() != 0) {
            int count = 0;
            Enumeration<String> key = CollectionManager.manager.getCollection().keys();
            StringBuilder list = new StringBuilder();
            while (key.hasMoreElements()) {
                String k = key.nextElement();
                if (CollectionManager.manager.getCollection().get(k).getPosition() != null) {
                    if (CollectionManager.manager.getCollection().get(k).getPosition().toString().length() > position.toString().length()) {
                        count++;
                        list.append(k).append(" - ").append(CollectionManager.manager.getCollection().get(k)).append("\n");
                    }
                }
            }if (count == 0){
                return "Таких элементов нет";
            }else {
                return list.toString();
            }
        } return "Коллекция пуста";
    }
}