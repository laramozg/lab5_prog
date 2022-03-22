package commands;

import collection.CollectionManager;
import java.util.Date;

public class Info extends AbstractCommand {
    private Date initDate = new Date();

    public Info(CollectionManager manager) {
        super(manager);
    }

    /**
     * Метод показывает информацию о коллекции
     *
     * @return
     */
    @Override
    public String execute() {
        return "Тип коллекции - Hashtable\n" +
                "Дата инициализации " + initDate + "\n" +
                "Размер коллекции " + CollectionManager.manager.getCollection().size();
    }
}