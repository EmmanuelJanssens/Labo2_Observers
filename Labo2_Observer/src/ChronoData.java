public class ChronoData {
    
    long startTime = 0;
    long endTime = 0;
    long elapsedTime = 0;
    long time = 0;

    boolean stopped = false;
    boolean paused = false;
    ChronoData()
    {
        startTime = System.currentTimeMillis();
    }
    public void Start(){
        if(paused){
            startTime = elapsedTime;
            paused = false;
        }
        else
            startTime = System.currentTimeMillis();
    }

    public void Stop(){
        endTime = System.currentTimeMillis();
        
    }

    public void pause()
    {
        Stop();
        elapsedTime = endTime - startTime;
        paused = true;
        stopped = true;
    }


    public long getCurrentTime(){

        return System.currentTimeMillis() - startTime;
    }

    public long getCurrentTimeInSeconds()
    {
        return getCurrentTime()/1000;
    }
    public long getEllapsedTime()
    {
        return endTime - startTime;
    }
}
