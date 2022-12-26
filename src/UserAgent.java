import java.util.HashMap;

public class UserAgent {
    private final String os;
    private final String browser;

    public UserAgent(String line){
        HashMap<String, String> result = Helper.setFieldsUserAgent(line);
        this.os = result.get("os");
        this.browser = result.get("browser");
    }
}
