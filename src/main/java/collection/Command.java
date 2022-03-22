package collection;

import foundation.Worker;
import java.io.Serializable;

/**
 * Класс для передачи команд в виде объекта
 */
public class Command {
    String name;
    String args;
    Worker worker;

    public Command(String name, String args) {
        this.name = name;
        this.args = args;
    }

    public Command(String name) {
        this.name = name;
    }

    public Command(String name, Worker worker) {
        this.name = name;
        this.worker = worker;
    }

    public String getName() {
        return name;
    }

    public String getArgs() {
        return args;
    }

    public Worker getWorker() {
        return worker;
    }

    public Command(String name, String args, Worker worker) {
        this.name = name;
        this.args = args;
        this.worker = worker;
    }
}

