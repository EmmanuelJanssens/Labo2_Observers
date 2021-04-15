public class ChronoData {

    private long seconds;

    public ChronoData(){
        seconds = 0;
    }

    // équivalent de "play"
    public void addOneSecond(){
        seconds++;
    }

    public long getSeconds() {
        return seconds % 60;
    }

    public long getMinutes() {
        return (long) Math.floor(seconds / 60) % 60;
    }

    public long getHours() {
        return (long) Math.floor(seconds /3600);
    }
}
