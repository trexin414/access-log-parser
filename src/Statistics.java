import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Statistics {

    long totalTraffic = 0;
    LocalDateTime minTime;
    LocalDateTime maxTime;

    HashSet<String> path = new HashSet<>();
    HashSet<String> pathWith404 = new HashSet<>();
    HashSet<String> realIp = new HashSet<>();
    HashSet<String> domains = new HashSet<>();

    HashMap<String, Integer> os = new HashMap<>();
    HashMap<String, Integer> browser = new HashMap<>();
    HashMap<String, Integer> taskPerSec = new HashMap<>();
    HashMap<String, Integer> users = new HashMap<>();
    HashMap<String, Double> percentOS = new HashMap<>();

    HashMap<String, Integer> anyInfo = new HashMap<>();
    {
        anyInfo.put("lineCount", 0);
        anyInfo.put("botCount", 0);
        anyInfo.put("errCount", 0);
    }

    public Statistics(){

    }

    public void addEntry(LogEntry logEntry){
        String os = logEntry.getUserAgent().getOs();
        String browser = logEntry.getUserAgent().getBrowser();
        List<String> errCode = new ArrayList<>(Arrays.asList("4", "5"));

        this.totalTraffic += logEntry.getPackageSize();
        if(this.minTime == null) this.minTime  = logEntry.getDate();
        if(this.maxTime == null) this.maxTime  = logEntry.getDate();
        if (this.minTime.compareTo(logEntry.getDate()) > 0) this.minTime = logEntry.getDate();
        if (this.maxTime.compareTo(logEntry.getDate()) < 0) this.maxTime = logEntry.getDate();

        if (logEntry.getHttpCode() == 200) this.path.add(logEntry.getPathMethod());
        if (logEntry.getHttpCode() == 404) this.pathWith404.add(logEntry.getPathMethod());
        if (logEntry.getUserAgent().getOs() == null) os = "withoutUserAgent";
        if (logEntry.getUserAgent().getBrowser() == null) browser = "withoutUserAgent";

        if (this.os.containsKey(os)) this.os.put(os,  this.os.get(os) + 1);
        else this.os.put(os, 1);

        if (this.browser.containsKey(browser)) this.browser.put(browser,  this.browser.get(browser) + 1);
        else this.browser.put(browser, 1);

        this.anyInfo.put("lineCount", this.anyInfo.get("lineCount") + 1);
        if (logEntry.getUserAgent().getBot()) this.anyInfo.put("botCount", this.anyInfo.get("botCount") + 1);
        else this.realIp.add(logEntry.getIp());
        if (errCode.contains(String.valueOf(logEntry.getHttpCode()).substring(0,1))) this.anyInfo.put("errCount", this.anyInfo.get("errCount") + 1);

        countTaskPerSec(logEntry);
        countUsers(logEntry);

        if (!(logEntry.getHttpPage() == null)){
            String str = logEntry.getHttpPage();
            str = str.substring(str.indexOf("//") + 2);
            str = str.substring(0, str.indexOf("/"));
            domains.add(str);
        }
    }

    public double getTrafficRate(){
        Duration duration = Duration.between(this.minTime, this.maxTime);
        return (double) totalTraffic / duration.toHours();
    }

    public HashSet<String> returnPath(){
        return path;
    }

    public HashSet<String> returnPath404(){
        return pathWith404;
    }

    public HashSet<String> returnPages(){
        return domains;
    }

    public HashMap<String, Integer> returnOS(){
        return os;
    }

    public HashMap<String, Integer> returnBrowser(){
        return browser;
    }


    public double returnPplPerHour(){
        Duration duration = Duration.between(this.minTime, this.maxTime);

        return (double) duration.toHours() / (anyInfo.get("lineCount") - anyInfo.get("botCount"));
    }

    public double returnErrPerHour(){
        Duration duration = Duration.between(this.minTime, this.maxTime);

        return (double) duration.toHours() / (anyInfo.get("errCount"));
    }

    public double return1UserOnSite(){
        return (anyInfo.get("lineCount") - anyInfo.get("botCount")) / (double) realIp.size();
    }

    public int returnPickTask(){
        return this.taskPerSec.values().stream().max(Integer::compare).get();
    }

    public int returnMaxTaskByUser(){
        return this.users.values().stream().max(Integer::compare).get();
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

    private void countTaskPerSec(LogEntry logEntry){
        String time = logEntry.getDate().toString();

        if (!logEntry.getUserAgent().getBot())
            if (this.taskPerSec.containsKey(time)) this.taskPerSec.put(time, this.taskPerSec.get(time) + 1);
            else this.taskPerSec.put(time, 1);

    }

    private void countUsers(LogEntry logEntry){
        String ip = logEntry.getIp();

        if (!logEntry.getUserAgent().getBot()){
            if (this.users.containsKey(ip)) this.users.put(ip, this.users.get(ip) + 1);
            else this.users.put(ip, 1);
        }
    }
}
