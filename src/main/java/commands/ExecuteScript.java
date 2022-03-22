package commands;

import foundation.*;
import collection.CollectionManager;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;

public class ExecuteScript extends AbstractCommand {
    public ExecuteScript(CollectionManager manager, Map<String, AbstractCommand> commandMap) {
        super(manager);
        this.commandMap = commandMap;
    }
    private Map<String, AbstractCommand> commandMap;

    public List<String> stack = new Stack<>();
    /**
     * Метод выполняет скрипт из файла
     *
     * @param str
     * @return
     */
    @Override
    public String execute(String str) {
        StringBuilder builder = new StringBuilder();
        String userCommand = "";
        String[] finalUserCommand;
        stack.add(str);
        try {
            FileReader script = new FileReader(str);
            try (Scanner commandReader = new Scanner(script)) {
                while (commandReader.hasNextLine() && !userCommand.equals("exit")) {
                    userCommand = commandReader.nextLine();
                    finalUserCommand = userCommand.trim().split(" ", 2);
                    if (finalUserCommand.length == 1) {
                        switch (finalUserCommand[0]) {
                            case "exit":
                                return "exit";
                            case "help":
                            case "clear":
                            case "show":
                            case "info":
                            case "save":
                            case "print_field_descending_start_date":
                                builder.append(commandMap.get(finalUserCommand[0]).execute()).append("\n");
                                break;
                            case "remove_greater":
                                String[] arr = new String[10];
                                for (int i = 0; i < arr.length; i++) {
                                    userCommand = commandReader.nextLine();
                                    arr[i] = userCommand;
                                }
                                builder.append(commandMap.get(finalUserCommand[0])
                                        .execute(finalUserCommand[1], execute(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6],
                                                arr[7], arr[8], arr[9]))).append("\n");
                                break;
                            default:
                                builder.append("Неизвестная команда.").append("\n");
                        }
                    } else if (finalUserCommand.length == 2) {
                        try {
                            switch (finalUserCommand[0]) {
                                case "update": {
                                    String[] arr = new String[10];
                                    for (int i = 0; i < arr.length; i++) {
                                        userCommand = commandReader.nextLine();
                                        arr[i] = userCommand;
                                    }
                                    builder.append(commandMap.get(finalUserCommand[0])
                                            .execute(finalUserCommand[1], execute(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6],
                                                    arr[7], arr[8], arr[9]))).append("\n");

                                break;}
                                case "insert":
                                case "replace_if_greater":
                                case "replace_if_lowe": {
                                    String[] arr = new String[10];
                                    for (int i = 0; i < arr.length; i++) {
                                        userCommand = commandReader.nextLine();
                                        arr[i] = userCommand;
                                    }
                                    builder.append(commandMap.get(finalUserCommand[0]).execute(finalUserCommand[1], execute(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6], arr[7], arr[8], arr[9]))).append("\n");
                                }
                                break;
                                case "execute_script": {
                                    if (stack.contains(finalUserCommand[1])){
                                        throw new StackOverflowError();
                                    }else{
                                        builder.append(commandMap.get(finalUserCommand[0]).execute(finalUserCommand[1]));
                                    }
                                    break;
                                }
                                case "count_by_start_date": {
                                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
                                    formatForDateNow.parse(finalUserCommand[1]);
                                    builder.append(commandMap.get(finalUserCommand[0]).execute(finalUserCommand[1])).append("\n");
                                    break;
                                }
                                case "filter_greater_than_position":
                                case "remove_key":
                                    builder.append(commandMap.get(finalUserCommand[0]).execute(finalUserCommand[1])).append("\n");
                                    break;
                                default:
                                    builder.append("Неизвестная команда или не указан аргумент").append("\n");
                            }
                        } catch (StackOverflowError e){
                            builder.append("Стек переполнен").append("\n");
                        } catch (IllegalArgumentException e) {
                            builder.append("Введен неверный аргумент").append("\n");
                        } catch (NullPointerException e) {
                            builder.append("Ошибка при добавлении/обновлении элемента. Поля в файле указаны неверно").append("\n");
                        } catch (ParseException e) {
                            builder.append("Введен неверный аргумент. Дата должна быть в формате год-месяц-день").append("\n");
                        }
                    }
                }
            }
        }catch (FileNotFoundException e) {
            return "Файл не найден";
        }
        return String.valueOf(builder).trim();
    }


    /**
     * Метод проверяет значения double для перегруженного readElement
     *
     * @param str
     * @return
     */
    Double checkDouble(String str) {
        double values;
        if (str.equals("")) {
            return null;
        } else {
            try {
                values = Double.parseDouble(str);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return values;
    }
    /**
     * Метод проверяет значения float для перегруженного readElement с возможным null
     *
     * @param str
     * @return
     */
    Float checkFloat(String str) {
        Float values;
        if (str.equals("")) {
            values = null;
        } else {
            try {
                values = Float.parseFloat(str);
                if (values<= 0){
                    return null;
                }
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return values;
    }

    /**
     * Метод проверяет значения String для перегруженного readElement
     *
     * @param str
     * @return
     */
    String checkName(String str) {
        String name;
        if (str.equals("")) {
            return null;
        } else {
            name = str;
            return name;
        }
    }


    /**
     * Перегруженный метод readElement для execute_script
     *
     * @param str1
     * @param str2
     * @param str3
     * @param str4
     * @param str5
     * @param str6
     * @param str7
     * @param str8
     * @param str9
     * @param str10
     * @return
     */
    @Override
    public Worker execute(String str1, String str2, String str3, String str4, String str5, String str6,
                          String str7, String str8, String str9, String str10) {
        Worker worker;
        Position position;
        Status status;
        OrganizationType organizationType;
        String name;
        Double x;
        double y;
        Float salary;
        Date startDate;
        long employeesCount ;
        SimpleDateFormat formatForDate = new SimpleDateFormat("yyyy-MM-dd");
        try {
            name = checkName(str1);
            x = checkDouble(str2);
            if (x<=-86){
                return null;
            }
            y = checkDouble(str3);
            salary = checkFloat(str4);
            startDate = formatForDate.parse(str5);
            employeesCount = Long.parseLong(str8);
            if (employeesCount<=0){
                return null;
            }
        } catch (NullPointerException | ParseException e) {
            return null;
        }

        try {
            if (str6.equals("")) {
                position = null;
            } else {
                position = Position.valueOf(str6.trim());
            }
        } catch (IllegalArgumentException e) {
            return null;
        }

        try {
            if (str7.equals("")) {
                status = null;
            } else {
                status = Status.valueOf(str7);
            }
        } catch (IllegalArgumentException e) {
            return null;
        }

        try {
            if (str9.equals("")) {
                return null;
            } else {
                organizationType = OrganizationType.valueOf(str9);
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
        String street;
        if (str10.equals("") || str10.length() > 130) {
            return null;
        }else{
            street = str10;
        }
        Address postalAddress = new Address(street);

        int id = 1;
        for (int i = 0; i < CollectionManager.manager.getCollection().size(); i++) {
            for (Worker w : CollectionManager.manager.getCollection().values()) {
                if (w.getId() == id) {
                    id++;
                }
            }
        }
        worker = new Worker(id,name, new Coordinates(x, y), ZonedDateTime.now(), salary, startDate, position, status, new Organization(employeesCount, organizationType, postalAddress));
        return worker;
    }
}