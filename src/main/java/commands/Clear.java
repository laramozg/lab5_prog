package commands;

import collection.CollectionManager;

public class Clear extends AbstractCommand {

    public Clear(CollectionManager manager) {
        super(manager);
    }

    /**
     * Метод очищает коллекцию
     *
     * @return
     */
    @Override
    public String execute() {
        CollectionManager.manager.getCollection().clear();
        return "Коллекция очищена";
    }
}