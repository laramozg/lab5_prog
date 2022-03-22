package commands;

import collection.CollectionManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Save extends AbstractCommand {
    public Save(CollectionManager manager) {
        super(manager);
    }

    /**
     * Метод сохраняет коллекцию в файл
     *
     * @return
     */
    @Override
    public String execute() {
        File outfile = new File(System.getenv("hleb"));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outfile))) {
            CollectionManager.manager.getCollection().forEach((key, value) -> {
                try {
                    String position = value.getPosition().toString();
                    if (value.getPosition() == null) {
                        position = "";
                    }
                    String salary = value.getSalary().toString();
                    if (value.getSalary() == null) {
                        salary = "";
                    }
                    String status = value.getStatus().toString();
                    if (value.getStatus() == null) {
                        status = "";
                    }
                    String contact = key + "," + value.getId() + "," + value.getName() +
                            "," + value.getCoordinates().getX() + "," + value.getCoordinates().getY() +
                            "," + value.getCreationDate() + "," + salary +
                            "," + value.getStartDate() + "," + position +
                            "," + status + "," + value.getOrganization().getEmployeesCount() +
                            "," + value.getOrganization().getType() + "," + value.getOrganization().getPostalAddress() + "\n";
                    writer.write(contact);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception s) {
            return "Не сохраняется " + s.getMessage();
        }
        if (outfile.canWrite() && outfile.canRead()) {
            return "Коллекция сохранена";
        }
        return "Коллекция не сохранена";
    }
}
