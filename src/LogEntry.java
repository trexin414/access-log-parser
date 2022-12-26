import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;

@Getter
public class LogEntry {
    private final String ip;
    private final LocalDateTime date;
    private final HttpMethods method;
    private final String pathMethod;
    private final int httpCode;
    private final int packageSize;
    private final String httpPage;
    private final UserAgent userAgent;

    public LogEntry(String line){
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
        HashMap<String, Object> result = Helper.findFieldsForLogEntry(line);
        this.ip = result.containsKey("ip") ? (String) result.get("ip") : null;
        this.date = result.containsKey("date") ? LocalDateTime.parse((String) result.get("date"), dTF)  : null;
        if (result.containsKey("method")) this.method = result.get("method").equals("GET") ? HttpMethods.GET : HttpMethods.POST;
        else this.method = null;

        this.pathMethod = result.containsKey("pathMethod") ? (String) result.get("pathMethod")  : null;
        this.httpCode = result.containsKey("httpCode") ?  Integer.parseInt((String) result.get("httpCode"))  : 0;
        this.packageSize = result.containsKey("size") ? Integer.parseInt((String) result.get("size"))  : 0;
        this.httpPage = result.containsKey("httpPage") ? (String) result.get("httpPage")  : null;
        this.userAgent = result.containsKey("ip") ? new UserAgent((String) result.get("uAgent"))  : null;
    }
}