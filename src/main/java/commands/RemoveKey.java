package commands;

import collection.CollectionManager;

public class RemoveKey extends AbstractCommand {
    public RemoveKey(CollectionManager manager) {
        super(manager);
    }

    /**
     * Метод удаляет элемент из коллекции по его ключу
     *
     * @param str
     * @return
     */
    @Override
    public String execute(String str){
        if (CollectionManager.manager.getCollection().size() != 0) {
            if (CollectionManager.manager.getCollection().containsKey(str)) {
                CollectionManager.manager.getCollection().remove(str);
                return "Элемент удален";
            }return "Нет элемента с таким ключом";
        }return "Коллекция пуста";
    }
}