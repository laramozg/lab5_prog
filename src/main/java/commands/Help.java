package commands;

import collection.CollectionManager;

public class Help extends AbstractCommand {
     public Help(CollectionManager manager) {
        super(manager);
    }

    /**
     * Метод выводит все доступные команды
     *
     * @return
     */
    @Override
    public String execute() {
        return "help - вывести справку по доступным командам\n" +
        "info - вывести в стандартный поток вывода информацию о коллекции\n" +
        "show - вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n" +
        "insert null - добавить новый элемент с заданным ключом\n" +
        "update id - обновить значение элемента коллекции, id которого равен заданному\n" +
        "remove_key null - удалить элемент из коллекции по его ключу\n" +
        "clear - очистить коллекцию\n" +
        "save - сохранить коллекцию в файл\n" +
        "execute_script file_name - считать и исполнить скрипт из указанного файла\n" +
        "exit - завершить программу (без сохранения в файл)\n" +
        "remove_greater - удалить из коллекции все элементы, превышающие заданный\n" +
        "replace_if_greater null - заменить значение по ключу, если новое значение больше старого\n" +
        "replace_if_lowe null - заменить значение по ключу, если новое значение меньше старого\n" +
        "count_by_start_date startDate - вывести количество элементов, значение поля startDate которых равно заданному\n" +
        "filter_greater_than_position position - вывести элементы, значение поля position которого больше заданного\n" +
        "print_field_descending_start_date - вывести значения поля startDate всех элементов в порядке убывания";
    }
}