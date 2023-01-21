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

    HashMap<String, Integer> os = new HashMap<>();
    HashMap<String, Double> percentOS = new HashMap<>();

    public Statistics(){

    }

    public void addEntry(LogEntry logEntry){
        String os = logEntry.getUserAgent().getOs();

        this.totalTraffic += logEntry.getPackageSize();
        if(this.minTime == null) this.minTime  = logEntry.getDate();
        if(this.maxTime == null) this.maxTime  = logEntry.getDate();
        if (this.minTime.compareTo(logEntry.getDate()) > 0) this.minTime = logEntry.getDate();
        if (this.maxTime.compareTo(logEntry.getDate()) < 0) this.maxTime = logEntry.getDate();

        if (logEntry.getHttpCode() == 200) this.pages.add(logEntry.getHttpPage());
        if (logEntry.getUserAgent().getOs() == null) os = "withoutUserAgent";
        if (this.os.containsKey(os)) this.os.put(os,  this.os.get(os) + 1);
        else this.os.put(os, 1);
    }

    public double getTrafficRate(){
        Duration duration = Duration.between(this.minTime, this.maxTime);
        return (double) totalTraffic / duration.toHours();
    }

    public HashSet<String> returnPages(){
        return pages;
    }

    public HashMap<String, Integer> returnOS(){
        return os;
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
}
