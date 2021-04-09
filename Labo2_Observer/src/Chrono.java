import java.util.Timer;
import java.util.TimerTask;
public class Chrono extends Subject {
    Timer timer;

    ChronoData data;
    Chrono(){
        timer = new Timer();
        
    }

    public void Start()
    {
        timer.schedule(new TimerTask(){
            
            @Override
            public void run()
            {
                data = new ChronoData();
                data.Start();

                while(true)
                {
                    System.out.println(data.getCurrentTimeInSeconds());
                    try{
                        Thread.sleep(1000);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }, 0);
    }

}
