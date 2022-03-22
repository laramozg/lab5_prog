package collection;

import org.xml.sax.SAXParseException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

public class Main {
    private static File file = null;

    public static void main(String[] args) throws IOException {

        try {
            file = new File("collection.csv");
        } catch (NullPointerException e) {
            System.out.println("Создайте переменную окружения(hleb=\"/home/s286535/ww\"\n" +
                    "export hleb)");
        }
        try {
            if (!file.exists()){throw new FileNotFoundException();}
            if (!file.canRead() || !file.canWrite()) {throw new SecurityException();}
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    System.out.println("\nВыход...");
                }
            });
            CollectionManager.app(file);
        } catch (SecurityException se) {
            System.out.println("Файл защищен от чтения и/или записи. Для программы нужны оба разрешения");
        } catch (FileNotFoundException e) {
            System.out.println("Файла по указанному пути не существует");
        } catch (NullPointerException e) {
            System.out.println("Ошибка в файле, введите все данные");
        }
    }
}


