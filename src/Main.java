import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static final String YANDEX_BOT = "YandexBot";
    private static final String GOOGLE_BOT = "GoogleBot";
    private static final String GOOGLE_BOT_SMALL = "Googlebot";
    public static void main(String[] args) {
        int count = 1, googlebotCount = 0, yandexCount = 0, googleBotCount = 0;
        String path = "access.log", ip = "", date = "", method = "", httpCode = "", size = "", httpPage = "", uAgent = "", comp = "", fragment = "";
        List<String> lines = new ArrayList<>();
        HashMap<String, HashMap<String, String>> mapLine = new HashMap<>();
        Pattern ipPattern = Pattern.compile("^(\\d{1,3}[\\.]){3}\\d{1,3}");
        Pattern datePattern = Pattern.compile("\\[((\\w+\\/){2}(\\d+\\:){3}\\d+\\ (\\+|-)\\d+)]");
        Pattern methodPattern = Pattern.compile("GET\\s+\\/.*\\HTTP\\/(\\w.\\w|\\w)");
        Pattern codeAndSizePattern = Pattern.compile("\\ (\\d*) (\\d*) ");
        Pattern httpPagePattern = Pattern.compile("\"https:.*\"");
        Pattern compatiblePattern = Pattern.compile("(compatible;.*)\\)");
        Pattern botPattern = Pattern.compile("(\\w+)\\/");

        Matcher matcher;

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
            matcher = ipPattern.matcher(line);
            if (matcher.find()) ip = matcher.group();

            matcher = datePattern.matcher(line);
            if (matcher.find()) date = matcher.group(1);

            matcher = methodPattern.matcher(line);
            if (matcher.find()) method = matcher.group();

            matcher = codeAndSizePattern.matcher(line);
            if (matcher.find()) {
                httpCode = matcher.group(1);
                size = matcher.group(2);
            }

            matcher = httpPagePattern.matcher(line);
            if(matcher.find()) httpPage = matcher.group();

            uAgent = line.substring(line.lastIndexOf("\"", line.lastIndexOf("\"") - 1) + 1, line.lastIndexOf("") - 1);

            matcher = compatiblePattern.matcher(uAgent);
            if (matcher.find()) comp = matcher.group(1);

            List<String> parts = Arrays.asList(comp.split(";"));

            parts.replaceAll(s -> s.replace(" ", ""));

            for(String s: parts){
                matcher = botPattern.matcher(s);
                if (matcher.find()) {
                    fragment = matcher.group(1);
                    break;
                }
            }

            if(fragment.equals(YANDEX_BOT)) yandexCount++;
            if(fragment.equals(GOOGLE_BOT)) googleBotCount++;
            if(fragment.equals(GOOGLE_BOT_SMALL)) googlebotCount++;
        }
        System.out.println("Доля запросов от YandexBot = " + (double) yandexCount / lines.size());
        System.out.println("Доля запросов от GoogleBot = " + (double) googleBotCount / lines.size());
        System.out.println("Доля запросов от Googlebot = " + (double) googlebotCount / lines.size());
    }
}
