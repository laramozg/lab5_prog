package collection;

import foundation.*;
import commands.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * Хранит коллекцию
 */
public class CollectionManager {
    public Map<String, AbstractCommand> commandMap;
    public static CollectionManager manager = new CollectionManager();
    private Hashtable<String, Worker> collection = new Hashtable<>();
    public Hashtable<String, Worker> getCollection() {
        return collection;
    }
    {
        commandMap = new HashMap<>();
        commandMap.put("clear", new Clear(manager));
        commandMap.put("show", new Show(manager));
        commandMap.put("info", new Info(manager));
        commandMap.put("help", new Help(manager));
        commandMap.put("count_by_start_date", new CountByStartDate(manager));
        commandMap.put("filter_greater_than_position", new FilterGreaterThanPosition(manager));
        commandMap.put("print_field_descending_start_date", new PrintFieldDescendingStartDate(manager));
        commandMap.put("remove_key", new RemoveKey(manager));
        commandMap.put("insert", new Insert(manager));
        commandMap.put("remove_greater", new RemoveGreater(manager));
        commandMap.put("replace_if_lowe", new ReplaceIfLowe(manager));
        commandMap.put("replace_if_greater", new ReplaceIfGreater(manager));
        commandMap.put("update", new Update(manager));
        commandMap.put("execute_script", new ExecuteScript(manager, commandMap));
        commandMap.put("save", new Save(manager));
    }

    /**
     * Добавляет коллекцию из файла и запускает интерактивное приложение
     *
     * @param file
     * @throws IOException
     */
    public static void app(File file) throws IOException {
        int id = 0;
        try {
            Worker worker;
            Float salary;
            Position position;
            Status status;
            FileReader Data = new FileReader(file);
            CSVParser parser = CSVParser.parse(Data, CSVFormat.DEFAULT);
            for (CSVRecord csvRecord : parser) {
                try {
                    if (csvRecord.get(6).trim().equals("")) {
                        salary = null;
                    } else if (Float.parseFloat(csvRecord.get(6).trim()) <= 0) {
                        throw new BadArgument("(salary должно быть больше нуля. Элемент не добавлен...)");
                    } else {
                        salary = Float.parseFloat(csvRecord.get(6).trim());
                    }
                    if (csvRecord.get(8).trim().equals("")) {
                        position = null;
                    } else {
                        position = Position.valueOf(csvRecord.get(8).trim());
                    }
                    if (csvRecord.get(9).trim().equals("")) {
                        status = null;
                    } else {
                        status = Status.valueOf(csvRecord.get(9).trim());
                    }
                    if (Double.parseDouble(csvRecord.get(3)) <= -86) {
                        throw new BadArgument("(x должен быть больше -86. Элемент не добавлен...)");
                    }
                    if (Long.parseLong(csvRecord.get(10)) <= 0) {
                        throw new BadArgument("(employeesCount должно быть больше 0. Элемент не добавлен...)");
                    }
                    if (csvRecord.get(0) == null || csvRecord.get(2) == null || csvRecord.get(3) == null || csvRecord.get(4) == null || csvRecord.get(10) == null || csvRecord.get(11) == null || csvRecord.get(12) == null) {
                        throw new BadArgument("(поле не может быть null. Элемент не добавлен...)");
                    }
                    if (csvRecord.get(12).length() > 130) {
                        throw new BadArgument("(postalAddress не может быть больше 130 символов. Элемент не добавлен...)");
                    }
                    id++;
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                    Date startDate = simpleDateFormat.parse(csvRecord.get(7).trim());
                    worker = new Worker(id, csvRecord.get(2).trim(), new Coordinates(Double.valueOf(csvRecord.get(3).trim()), Double.parseDouble(csvRecord.get(4).trim())), ZonedDateTime.now(), salary, startDate, position, status, new Organization(Long.parseLong(csvRecord.get(10).trim()), OrganizationType.valueOf(csvRecord.get(11).trim()), new Address(csvRecord.get(12).trim())));
                    manager.getCollection().put(csvRecord.get(0).trim(), worker);
                } catch (BadArgument e) {
                    System.out.println(e.getMessage());
                } catch (ParseException e) {
                    System.out.println("(startDate указано в неверном формате. Элемент не добавлен...)");
                }catch (IllegalArgumentException e) {
                    System.out.println("(поле указано в неверном формате. Элемент не добавлен...)");
                }
            }
            if (manager.getCollection().size() != 0) {
                System.out.println("\nКоллекция добавлена!");
                System.out.println("----------------------------");
            }

            ClientWork clientWork = new ClientWork(manager.commandMap);
            System.out.println("Здравствуйте, вы находитесь в интерактивном режиме! Введите help для просмотра возможных команд");
            clientWork.work();

        }catch (IllegalArgumentException e) {
            System.out.println("Ошибка в csv файле. Элемент не добавлен");
        }
    }
}