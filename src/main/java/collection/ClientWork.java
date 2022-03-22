package collection;

import foundation.*;
import commands.AbstractCommand;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;

public class ClientWork {
    public ClientWork(Map<String, AbstractCommand> commandMap) {
        this.commandMap = commandMap;
    }
    private Map<String, AbstractCommand> commandMap;
    int id = 1;
    String command="";
    /**
     * Метод обрабатывает команды
     *
     */
    public void work() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (!command.equals("exit")) {
                command = scanner.nextLine();
                String[] finalUserCommand = command.trim().split(" ");
                try {
                    if (finalUserCommand.length == 1) {
                        switch (command) {
                            case "":
                                break;
                            case "help":
                            case "info":
                            case "show":
                            case "save":
                            case "clear":
                            case "print_field_descending_start_date": {
                                Command answer = new Command(finalUserCommand[0]);
                                System.out.println(commandMap.get(answer.getName()).execute());}
                                break;
                            case "remove_greater":{
                                Command answer = new Command(finalUserCommand[0],readElement());
                                System.out.println(commandMap.get(answer.getName()).execute(answer.getWorker()));}
                                break;
                            case "exit":
                                System.exit(0);
                            default:
                                System.out.println("Неизвестная команда");
                        }
                    } else if (finalUserCommand.length == 2) {
                        switch (finalUserCommand[0]) {
                            case "filter_greater_than_position":{
                                try {
                                   Position.valueOf(finalUserCommand[1].trim().toUpperCase());
                                    Command answer = new Command(finalUserCommand[0], finalUserCommand[1].trim());
                                    System.out.println(commandMap.get(answer.getName()).execute(answer.getArgs()));
                                } catch (Exception e) {
                                    System.out.println("Нет такого элемента. Введите снова и выберите элемент из: " + Arrays.toString(Position.values()));
                                }}
                                break;
                            case "count_by_start_date":{
                                try {
                                    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
                                    formatForDateNow.parse(finalUserCommand[1]);
                                    Command answer = new Command(finalUserCommand[0], finalUserCommand[1]);
                                    System.out.println(commandMap.get(answer.getName()).execute(answer.getArgs()));
                                } catch (ParseException e) {
                                    System.out.println("Введите дату в формате год-месяц-день (yyyy-MM-dd): ");
                                }}
                                break;
                            case "replace_if_greater":
                            case "replace_if_lowe":
                            case "insert": {
                                Command answer = new Command(finalUserCommand[0], finalUserCommand[1], readElement());
                                System.out.println(commandMap.get(answer.getName()).execute(answer.getArgs(),answer.getWorker()));}
                                break;
                            case "remove_key":
                            case "execute_script": {
                                Command answer = new Command(finalUserCommand[0], finalUserCommand[1]);
                                System.out.println(commandMap.get(answer.getName()).execute(answer.getArgs()));
                                }
                                break;
                            case "update":{
                                try {
                                    Integer.parseInt(finalUserCommand[1]);
                                    Command answer = new Command(finalUserCommand[0], finalUserCommand[1], readElement());
                                    System.out.println(commandMap.get(answer.getName()).execute(answer.getArgs(),answer.getWorker()));
                                } catch (NumberFormatException e) {
                                    System.out.println("Вы ввели строку или число выходит за пределы int. Введите снова");
                                }}
                                break;
                            default:
                                System.out.println("Неизвестная команда или команда введена без аргументов. Введите снова");
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Отсутствует аргумент");
                }
            }
        }
    }



    /**
     * Метод создает элемент для коллекции
     *
     * @return
     */
    public Worker readElement() {
        Scanner scanner = new Scanner(System.in);
        Worker worker;
        String name;
        do {
            System.out.println("Введите имя:");
            name = scanner.nextLine().trim().toUpperCase();
        } while (name.equals(""));

        String x1;
        Double x = null;
        do {
            System.out.println("Координаты: Введите координаты, x:");
            x1 = scanner.nextLine().trim().toUpperCase();
            try {
                x = Double.parseDouble(x1);
                if (x <= -86.0) {
                    x = null;
                    System.out.println("Поле должно быть больше -86");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (x == null);

        Double y = null;
        String y1;
        do {
            System.out.println("Введите координаты y:");
            y1 = scanner.nextLine().trim().toUpperCase();
            try {
                y = Double.parseDouble(y1);
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (y == null);

        Float salary = null;
        String s ;
        do {
            System.out.println("Введите зарплату работника:");
            s = scanner.nextLine().trim().toUpperCase();
            if (!s.equals("")) {
                try {
                    salary = Float.parseFloat(s);
                    s = "m";
                    if (salary <= 0) {
                        s = "";
                        System.out.println("Поле должно быть больше 0");
                    }
                } catch (NumberFormatException n) {
                    s = "";
                    System.out.println("Вы ввели строку");
                }
            }else{
                salary = null;
            }
        } while (salary != null && !s.equals("m"));


        Position position = null;
        String posit = "";
        do {
            System.out.println("Выберите должность из: " + Arrays.toString(Position.values()));
            posit = scanner.nextLine().trim().toUpperCase();
            try {
                if (posit.equals("")) {
                    position = null;
                } else {
                    position = Position.valueOf(posit);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Такой должности нет. Повторите ввод");
            }
        } while (!posit.equals("") && !posit.equals("HUMAN_RESOURCES") && !posit.equals("DEVELOPER") && !posit.equals("HEAD_OF_DIVISION"));


        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        String date;
        do {
            System.out.println("Введите дату в формате год-месяц-день:");
            date = scanner.nextLine().trim().toUpperCase();
            try {
                startDate = formatForDateNow.parse(date);
            } catch (ParseException e) {
                System.out.println("Введите дату в правильном формате!");
            } catch (NullPointerException e) {
                System.out.println("Дата не может быть null. Повторите ввод");
            }
        } while (startDate == null);


        Status status = null;
        String stat;
        do {
            System.out.println("Выберите должность из: " + Arrays.toString(Status.values()));
            stat = scanner.nextLine().trim().toUpperCase();
            try {
                if (stat.equals("")) {
                    status = null;
                } else {
                    status = Status.valueOf(stat);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Такого статуса нет. Повторите ввод");
            }
        } while (!stat.equals("") && !stat.equals("RECOMMENDED_FOR_PROMOTION") && !stat.equals("HIRED") && !stat.equals("FIRED") && !stat.equals("REGULAR") && !stat.equals("PROBATION"));


        Long employeesCount = null;
        String eCount;
        do {
            System.out.println("Количество сотрудников:");
            eCount = scanner.nextLine().trim().toUpperCase();
            try {
                employeesCount = Long.parseLong(eCount);
                if (employeesCount <= 0) {
                    employeesCount = null;
                    System.out.println("Поле должно быть больше 0");
                }
            } catch (NumberFormatException n) {
                System.out.println("Это не число");
            }
        } while (employeesCount == null);

        OrganizationType organizationType = null;
        String oType;
        do {
            System.out.println("Выберите тип организации из: " + Arrays.toString(OrganizationType.values()));
            oType = scanner.nextLine().trim().toUpperCase();
            try {
                organizationType = OrganizationType.valueOf(oType);
            } catch (IllegalArgumentException e) {
                System.out.println("Такого типа организации нет.Повторите ввод");
            } catch (NullPointerException e) {
                System.out.println("Организация не может быть null. Повторите ввод");
            }
        } while (organizationType == null);


        String street = "";
        do {
            System.out.println("Введите почтовый адрес:");
            street = scanner.nextLine().trim().toUpperCase();
            if (street.equals("")){
                System.out.println("Адрес не может быть null. Повторите ввод");
            }
            if (street.length() > 130) {
                street = "";
                System.out.println("Длина почтового адреса не может быть больше 130. Повторите ввод");
            }
        } while (street.equals(""));
        Address postalAddress = new Address(street);

        for (int i = 0; i < CollectionManager.manager.getCollection().size(); i++) {
            for (Worker w : CollectionManager.manager.getCollection().values()) {
                if (w.getId() == id) {
                    id++;
                }
            }
        }
        ZonedDateTime creationDate = ZonedDateTime.now();

        worker = new Worker(id, name, new Coordinates(x, y), creationDate, salary, startDate, position, status, new Organization(employeesCount, organizationType, postalAddress));
        return worker;

    }
}