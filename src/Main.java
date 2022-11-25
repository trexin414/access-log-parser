import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 1;
        while (true){
            System.out.println("Введите путь до файла:");
            String path = new Scanner(System.in).nextLine();

            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if(isDirectory){
                System.out.println("Указан путь до каталога! Необходимо указать путь до файла");
                continue;
            }

            if (fileExists) System.out.println("Путь указан верно\nЭто файл номер " + count++);
            else System.out.println("Данного файла не существует");
        }
    }
}
