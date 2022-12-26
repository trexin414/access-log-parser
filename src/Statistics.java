import lombok.Getter;

import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {

    long totalTraffic = 0;
    LocalDateTime minTime;
    LocalDateTime maxTime;

    public Statistics(){

    }

    public void addEntry(LogEntry logEntry){
        this.totalTraffic += logEntry.getPackageSize();
        if(this.minTime == null) this.minTime  = logEntry.getDate();
        if(this.maxTime == null) this.maxTime  = logEntry.getDate();
        if (this.minTime.compareTo(logEntry.getDate()) > 0) this.minTime = logEntry.getDate();
        if (this.maxTime.compareTo(logEntry.getDate()) < 0) this.maxTime = logEntry.getDate();
    }

    public double getTrafficRate(){
        Duration duration = Duration.between(this.minTime, this.maxTime);
        return (double) totalTraffic / duration.toHours();
    }
}
