import observer.AnalogChrono;
import observer.DigitalChrono;
import subject.Chrono;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel
{

    public ControlPanel(int nbChronos)
    {
        JFrame frame = new JFrame();
        frame.setTitle("Panneau de contrôle");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,nbChronos * 60);
        JPanel panel = new JPanel();

        GridLayout box = new GridLayout(nbChronos,7,5,5);

        panel.setLayout(box);
        //create assigned number of chronometers
        for(int i = 0; i < nbChronos; i++)
        {
            final int id = i;
            Chrono subject = new Chrono();
            //create label
            JLabel label = new JLabel();
            label.setText("Chrono #" +i );
            panel.add(label);
            //create start
            JButton start = new JButton("démarrer");
            start.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    subject.Start();
                }
            });
            panel.add(start);
            //create restart
            JButton restart = new JButton("réinitialiser");
            restart.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    subject.Reset();
                }
            });
            panel.add(restart);
            //create roman
            JButton roman = new JButton("cadran romain");
            roman.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    AnalogChrono c = new AnalogChrono("Chrono #"+id, "images/cadran_chiffres_romains.jpg");
                    subject.attach(c);
                    c.setChrono(subject);
                }
            });
            panel.add(roman);

            //create arab
            JButton arab = new JButton("cadran arabe");
            arab.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    AnalogChrono c = new AnalogChrono("Chrono #"+id, "images/cadran_chiffres_arabes.jpg");
                    subject.attach(c);
                    c.setChrono(subject);
                }
            });
            panel.add(arab);

            //create numeric
            JButton numeric = new JButton("numérique");
            numeric.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    DigitalChrono c = new DigitalChrono("Chrono #"+id);
                    subject.attach(c);
                    c.setChrono(subject);
                }
            }); 
            panel.add(numeric);
        }

        frame.add(panel);
        frame.setVisible(true);
    }
    
}