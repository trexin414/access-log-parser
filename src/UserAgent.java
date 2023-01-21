import lombok.Getter;

import java.util.HashMap;

@Getter
public class UserAgent {
    private final String os;
    private final String browser;

    private final Boolean bot;

    public UserAgent(String line){
        HashMap<String, String> result = Helper.setFieldsUserAgent(line);
        this.os = result.get("os");
        this.browser = result.get("browser");
        this.bot = line.contains("bot") || line.contains("Bot");
    }
}
