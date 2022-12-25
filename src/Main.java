import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int count = 1;
        String path;
        List<String> lines = new ArrayList<>();
        int max = 0, min = 1024;

        while (true){
            System.out.println("Введите путь до файла:");
            path = new Scanner(System.in).nextLine();

            File file = new File(path);
            boolean fileExists = file.exists();
            boolean isDirectory = file.isDirectory();

            if(isDirectory){
                System.out.println("Указан путь до каталога! Необходимо указать путь до файла");
                continue;
            }

            if (fileExists) {
                System.out.println("Путь указан верно\nЭто файл номер " + count++);
                break;
            }
            else System.out.println("Данного файла не существует");
        }

        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            while ((line = reader.readLine()) != null) {
                int length = line.length();
                if (length > 1024) throw new LengthExceptions("Длина строки " + (lines.size() + 1) + " больше 1024 символов");
                if (length < min) min = length;
                if (length > max) max = length;
                lines.add(line);
            }
            System.out.println("Кол-во строк в файле = " + lines.size());
            System.out.println("Максимальное число символов в строке = " + max);
            System.out.println("Минимальное число символов в строке = " + min);
        } catch (IOException | LengthExceptions exception){
            exception.printStackTrace();
        }

    }
}
