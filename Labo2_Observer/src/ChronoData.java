public class ChronoData {

    private long seconds;
    private long minutes;
    private long hours;

    public ChronoData(){
        seconds = 0;
        minutes = 0;
        hours = 0;
    }

    // équivalent de "play"
    public void addOneSecond(){
        seconds++;
    }

    public long getSeconds() {
        return seconds;
    }

    public long getMinutes() {
        return minutes;
    }

    public long getHours() {
        return hours;
    }
}
