
abstract public class Observer {



    public Observer(){

        /*
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);

        panel = createPanel();
        label = new JLabel("00h 00m 00s");
        panel.setLayout(new FlowLayout());
        panel.add(label);
        frame.setContentPane(panel);

        frame.setVisible(true);
*/
    }



    abstract void update();
}
