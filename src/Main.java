import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        int count = 1;
        String path = "access.log";

        List<String> lines = new ArrayList<>();
        HashMap<String, Object> result = new HashMap<>();
        Statistics statistics = new Statistics();

        while (true){
//            System.out.println("Введите путь до файла:");
//            path = new Scanner(System.in).nextLine();

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
                if (line.length() > 1024) throw new LengthExceptions("Длина строки " + (lines.size() + 1) + " больше 1024 символов");
                lines.add(line);
            }
        } catch (IOException | LengthExceptions exception){
            exception.printStackTrace();
        }

        for (String line: lines){
            statistics.addEntry(new LogEntry(line));
        }

        System.out.println(statistics.getTrafficRate());
    }
}
