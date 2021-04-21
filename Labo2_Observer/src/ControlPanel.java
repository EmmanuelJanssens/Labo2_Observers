import observer.chronoGUI.*;
import subject.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class ControlPanel
{

    interface Action
    {
        void Do();
    }

    JFrame mainFrame;
    JPanel mainPanel;

    List<Subject> subjets = new LinkedList<>();
    /**
     *
     * @param title
     * @param components
     * @return
     */
    public JFrame createChronoWindow(String title, ChronoPanel... components)
    {
        JFrame frame = new JFrame(title);
        frame.setLayout(new FlowLayout());
        for(ChronoPanel observer: components)
        {
            frame.add(observer);
            observer.setFrameListener();
        }
        frame.setVisible(true);
        frame.setResizable(false);
        frame.pack();
        return frame;
    }

    public JButton createButton(String title, Action a)
    {
        //create roman
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(120,30));
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                a.Do();
            }
        });
        return button;
    }

    public void createRoman(String title,Chrono subject, int id)
    {
        mainPanel.add(createButton(title, new Action() {
            @Override
            public void Do() {
                createChronoWindow(title,new RomanChrono(subject, "Chrono #" + id));
            }
        }));
    }
    public void createArab(String title,Chrono subject, int id)
    {
        mainPanel.add(createButton(title, new Action() {
            @Override
            public void Do() {
                createChronoWindow(title, new ArabChrono(subject, "Chrono #" + id));
            }
        }));
    }
    public void createDigital(String title,Chrono subject, int id)
    {
        mainPanel.add(createButton(title, new Action() {
            @Override
            public void Do() {
                createChronoWindow(title, new DigitalChrono(subject, "Chrono #"+id));
            }
        }));
    }
    /**
     *
     * @param subject
     */
    public void createButtonRow(Chrono subject,int id)
    {
        //create label
        JLabel label = new JLabel();
        label.setText("Chrono #" +id );
        mainPanel.add(label);

        mainPanel.add(createButton("Démarrer", new Action() {
            @Override
            public void Do() {
                subject.start();
            }
        }));

        mainPanel.add(createButton("Arreter", new Action() {
            @Override
            public void Do() {
                subject.stop();
            }
        }));

        mainPanel.add(createButton("Réinitialiser", new Action() {
            @Override
            public void Do() {
                subject.reset();
            }
        }));


        createRoman("Cadran Romain",subject,id);
        createArab("Cadran Arabe",subject,id);
        createDigital("Digital",subject,id);

    }

    /**
     *
     * @param nbChronos
     */
    public ControlPanel(int nbChronos)
    {
        mainFrame = new JFrame();
        mainFrame.setTitle("Panneau de contrôle");
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize((120+5)*7,nbChronos * 60);


        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        //create assigned number of chronometers
        for(int i = 0; i < nbChronos; i++)
        {
            subjets.add(new Chrono());
            createButtonRow((Chrono) subjets.get(i),i);
        }

        JLabel label = new JLabel();
        label.setText("Tout les chronos:" );
        mainPanel.add(label);

        mainPanel.add(createButton("Cadran romain", new Action() {
            @Override
            public void Do() {
                List<RomanChrono> chrono = new LinkedList<>();
                for(int i = 0; i < subjets.size(); i++) {
                    chrono.add( new RomanChrono((Chrono) subjets.get(i), "Chrono #" + i));
                }
                createChronoWindow("Cadran romain",  chrono.toArray(new RomanChrono[chrono.size()]));
            }
        }));
        mainPanel.add(createButton("Cadran arabe", new Action() {
            @Override
            public void Do() {
                List<ArabChrono> chrono = new LinkedList<>();
                for(int i = 0; i < subjets.size(); i++){
                    chrono.add( new ArabChrono((Chrono) subjets.get(i), "Chrono #" + i));
                }
                createChronoWindow("Cadran arabe", chrono.toArray(new ArabChrono[chrono.size()]));
            }
        }));
        mainPanel.add(createButton("Numérique", new Action() {
            @Override
            public void Do() {
                List<DigitalChrono> chrono = new LinkedList<>();
                for(int i = 0; i < subjets.size(); i++){
                    chrono.add( new DigitalChrono((Chrono) subjets.get(i), "Chrono #" + i));
                }
                createChronoWindow("Numérique", chrono.toArray(new DigitalChrono[chrono.size()]));
            }
        }));

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

}