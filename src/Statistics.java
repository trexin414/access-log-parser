import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;

public class Statistics {

    long totalTraffic = 0;
    LocalDateTime minTime;
    LocalDateTime maxTime;

    HashSet<String> pages = new HashSet<>();
    HashSet<String> pagesWith404 = new HashSet<>();

    HashMap<String, Integer> os = new HashMap<>();
    HashMap<String, Integer> browser = new HashMap<>();
    HashMap<String, Double> percentOS = new HashMap<>();

    public Statistics(){

    }

    public void addEntry(LogEntry logEntry){
        String os = logEntry.getUserAgent().getOs();
        String browser = logEntry.getUserAgent().getBrowser();

        this.totalTraffic += logEntry.getPackageSize();
        if(this.minTime == null) this.minTime  = logEntry.getDate();
        if(this.maxTime == null) this.maxTime  = logEntry.getDate();
        if (this.minTime.compareTo(logEntry.getDate()) > 0) this.minTime = logEntry.getDate();
        if (this.maxTime.compareTo(logEntry.getDate()) < 0) this.maxTime = logEntry.getDate();

        if (logEntry.getHttpCode() == 200) this.pages.add(logEntry.getHttpPage());
        if (logEntry.getHttpCode() == 404) this.pagesWith404.add(logEntry.getHttpPage());
        if (logEntry.getUserAgent().getOs() == null) os = "withoutUserAgent";
        if (logEntry.getUserAgent().getBrowser() == null) browser = "withoutUserAgent";

        if (this.os.containsKey(os)) this.os.put(os,  this.os.get(os) + 1);
        else this.os.put(os, 1);

        if (this.browser.containsKey(browser)) this.browser.put(browser,  this.browser.get(browser) + 1);
        else this.browser.put(browser, 1);
    }

    public double getTrafficRate(){
        Duration duration = Duration.between(this.minTime, this.maxTime);
        return (double) totalTraffic / duration.toHours();
    }

    public HashSet<String> returnPages(){
        return pages;
    }

    public HashSet<String> returnPages404(){
        return pagesWith404;
    }

    public HashMap<String, Integer> returnOS(){
        return os;
    }

    public HashMap<String, Integer> returnBrowser(){
        return browser;
    }

    public HashMap<String,Double> returnPercentOS(){
        return this.percentOS = createPercentOS();
    }

    private HashMap<String, Double> createPercentOS(){
        HashMap<String, Double> result = new HashMap<>();
        Double len = 0.0, check = 0.0;

        for (String key: this.os.keySet()) len+= this.os.get(key);
//        System.out.println("All: " + len);

        for (String key: this.os.keySet()) {
//            System.out.println(key + ":" +this.os.get(key));
            result.put(key, this.os.get(key) / len);
        }

//        for (String key: result.keySet()) check += result.get(key);
//        System.out.println(check);

        return result;
    }

    public HashMap<String,Double> returnPercentBrowsers(){
        return this.percentOS = createPercentBrowsers();
    }

    private HashMap<String, Double> createPercentBrowsers(){
        HashMap<String, Double> result = new HashMap<>();
        Double len = 0.0, check = 0.0;

        for (String key: this.browser.keySet()) len+= this.browser.get(key);
//        System.out.println("All: " + len);

        for (String key: this.browser.keySet()) {
//            System.out.println(key + ":" +this.browser.get(key));
            result.put(key, this.browser.get(key) / len);
        }

        for (String key: result.keySet()) check += result.get(key);
//        System.out.println(check);

        return result;
    }
}
