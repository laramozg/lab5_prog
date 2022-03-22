package commands;

import foundation.Worker;
import collection.CollectionManager;

import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class PrintFieldDescendingStartDate extends AbstractCommand {
    public PrintFieldDescendingStartDate(CollectionManager manager) {
        super(manager);
    }
    private final List<Date> date = new LinkedList<>();
    /**
     * Метод выводит StartDate в порядке убывания
     *
     * @return
     */
    @Override
    public String execute() {
        if (CollectionManager.manager.getCollection().size() != 0) {
            for (Worker worker : CollectionManager.manager.getCollection().values()) {
                date.add(worker.getStartDate());
            }
            Comparator<Date> comparator = Comparator.comparing(Date::getTime).reversed();
            date.sort(comparator);
            return date.toString();
        }return "Коллекция пуста";
    }
}