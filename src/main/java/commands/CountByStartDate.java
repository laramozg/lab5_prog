package commands;

import foundation.Worker;
import collection.CollectionManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CountByStartDate extends AbstractCommand {
    public CountByStartDate(CollectionManager manager) {
        super(manager);
    }

    /**
     * Метод выводит количество элементов, значение поля startDate которых равно заданному
     *
     * @param str
     * @return
     */
    @Override
    public String execute(String str) {
        try {
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
            Date start_date;
            start_date = formatForDateNow.parse(str);
            if (CollectionManager.manager.getCollection().size() != 0) {
                int count = 0;
                for (Worker p : CollectionManager.manager.getCollection().values()) {
                    if (p.getStartDate().equals(start_date)) {
                        count++;
                    }
                }
                return String.valueOf(count);
            }return "Коллекция пуста";
        }catch (ParseException e){
            return "Неверный формат даты";
        }
    }
}