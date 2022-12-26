import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    public static final String YANDEX_BOT = "YandexBot";
    public static final String GOOGLE_BOT = "GoogleBot";
    public static final String GOOGLE_BOT_SMALL = "Googlebot";

    private static final Pattern ipPattern = Pattern.compile("^(\\d{1,3}[\\.]){3}\\d{1,3}");
    private static final Pattern datePattern = Pattern.compile("\\[((\\w+\\/){2}(\\d+\\:){3}\\d+\\ (\\+|-)\\d+)]");
    private static final Pattern methodPattern = Pattern.compile("(GET|POST)(\\s+\\/.*\\HTTP\\/\\w.\\w|\\w)");
    private static final Pattern codeAndSizePattern = Pattern.compile("\\ (\\d*) (\\d*) ");
    private static final Pattern httpPagePattern = Pattern.compile("\"https:.*\"");
    private static final Pattern compatiblePattern = Pattern.compile("(compatible;.*)\\)");
    private static final Pattern botPattern = Pattern.compile("(\\w+)\\/");
    private static final Pattern osPattern = Pattern.compile("(Linux|Windows NT|Mac OS X)");
    private static final Pattern operaPattern = Pattern.compile("(Opera).(\\d.\\d+)|(OPR.\\w+.*)");
    private static final Pattern chromeMobilePattern = Pattern.compile("(Chrome).(\\d+.)*Mobile");
    private static final Pattern chromePattern = Pattern.compile("(Chrome).(\\d+.*)Safari.(\\d+.){2}(\"?)");
    private static final Pattern safariPattern = Pattern.compile("(Safari)\\/(\\d+)*(.)*(\"?)");
    private static final Pattern edgePattern = Pattern.compile("(Edge).(\\d+.)*(\"?)");
    private static final Pattern firefoxPattern = Pattern.compile("(Firefox).(\\d+.\\d+)\"");

    public static HashMap<String, Object> findFieldsForLogEntry(String line){
        Matcher matcher;
        HashMap<String, Object> result = new HashMap<>();

        matcher = ipPattern.matcher(line);
        if (matcher.find()) result.put("ip", matcher.group());

        matcher = datePattern.matcher(line);
        if (matcher.find()) result.put("date", matcher.group(1));

        matcher = methodPattern.matcher(line);
        if (matcher.find()) {
            result.put("method", matcher.group(1));
            result.put("httpPath", matcher.group(2));
        }

        matcher = codeAndSizePattern.matcher(line);
        if (matcher.find()) {
            result.put("httpCode", matcher.group(1));
            result.put("size", matcher.group(2));
        }

        matcher = httpPagePattern.matcher(line);
        if(matcher.find()) result.put("httpPage", matcher.group());

        result.put("uAgent", line.substring(line.lastIndexOf("\"", line.lastIndexOf("\"") - 1) + 1, line.lastIndexOf("") - 1));

        return result;
    }

    public static HashMap<String, String> setFieldsUserAgent(String line){
        Matcher matcher;
        HashMap<String, String> result = new HashMap<>();
        List<Pattern> browsers = Arrays.asList(operaPattern, edgePattern, firefoxPattern, chromePattern, chromeMobilePattern, safariPattern);

        matcher = osPattern.matcher(line);
        if (matcher.find()) result.put("os", matcher.group());

        for (Pattern pattern:browsers){
            matcher = pattern.matcher(line);
            if (matcher.find()) result.put("browser", matcher.group(1));
            if (result.containsKey("browser")) break;
        }

        return result;
    }

}
