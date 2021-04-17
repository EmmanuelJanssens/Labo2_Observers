import observer.AnalogChrono;
import observer.DigitalChrono;
import subject.*;

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
            JButton start = new JButton("Démarrer");
            start.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    //subject.start();
                    subject.setChronoData(State.RUNNING);
                }
            });
            panel.add(start);

            //create pause
            JButton pause = new JButton("Arreter");
            pause.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    //subject.pause();
                    subject.setChronoData(State.PAUSED);
                }
            });
            panel.add(pause);

            //create restart
            JButton restart = new JButton("Réinitialiser");
            restart.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    //subject.reset();
                    subject.setChronoData(State.RESET);
                }
            });
            panel.add(restart);

            //create roman
            JButton roman = new JButton("Cadran romain");
            roman.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AnalogChrono(subject, "Chrono #"+id, "images/cadran_chiffres_romains.jpg");
                }
            });
            panel.add(roman);

            //create arab
            JButton arab = new JButton("Cadran arabe");
            arab.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AnalogChrono(subject, "Chrono #"+id, "images/cadran_chiffres_arabes.jpg");
                }
            });
            panel.add(arab);

            //create numeric
            JButton numeric = new JButton("Numérique");
            numeric.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    new DigitalChrono(subject, "Chrono #"+id);
                }
            }); 
            panel.add(numeric);
        }

        frame.add(panel);
        frame.setVisible(true);
    }

}