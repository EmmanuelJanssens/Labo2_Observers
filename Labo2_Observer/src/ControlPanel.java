/**
 * This is the main controller of the project, responsible of the chronometers to display
 * It creates the basic controls for starting, stopping and resetting a chronometer
 * as well as adding new observers by creating windows for each display
 *
 * @author Emmanuel Janssens
 * @author Rosalie Chhen
 *
 * @date 21.04.2021
 */
import observer.chronoGUI.*;
import subject.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.LinkedList;
import java.util.List;

public class ControlPanel
{
    // Main display window
    private JFrame mainFrame;

    // Main content panel
    private JPanel mainPanel;

    // List of all the subjects to display
    private List<Subject> subjects = new LinkedList<>();

    /**
     * Creates a non resizeable window that contains a list of observers
     *
     * @param resizeable indicates if the window can be resized or not
     * @param components list of components representing the observers
     */
    private void createChronoWindow(boolean resizeable, ChronoPanel... components) {
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        for(ChronoPanel observer: components)
        {
            frame.add(observer);
            observer.setFrameListener();
        }
        frame.setVisible(true);
        frame.setResizable(resizeable);
        frame.pack();
    }

    /**
     * Creates the window that displays, for each chronometer,
     * a specific displayer either :
     *  - Roman
     *  - Arab
     *  - Numeric
     */
    private void createAllChronoWindow() {

        JLabel label = new JLabel();
        label.setText("Tout les chronos:" );
        mainPanel.add(label);

        // Button that creates 3 roman panels in a window
        mainPanel.add(createButton("Cadran romain", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RomanChrono> chrono = new LinkedList<>();
                for(int i = 0; i < subjects.size(); i++) {
                    chrono.add( new RomanChrono((Chrono) subjects.get(i), "Chrono #" + (i+1)));
                }
                createChronoWindow(true,  chrono.toArray(new RomanChrono[chrono.size()]));
            }
        }));

        // Button that creates 3 arabic panels in a window
        mainPanel.add(createButton("Cadran arabe", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ArabChrono> chrono = new LinkedList<>();
                for(int i = 0; i < subjects.size(); i++){
                    chrono.add( new ArabChrono((Chrono) subjects.get(i), "Chrono #" + (i+1)));
                }
                createChronoWindow(true, chrono.toArray(new ArabChrono[chrono.size()]));
            }
        }));

        // Button that creates 3 numeric panels in a window
        mainPanel.add(createButton("Numérique", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<DigitalChrono> chrono = new LinkedList<>();
                for(int i = 0; i < subjects.size(); i++){
                    chrono.add( new DigitalChrono((Chrono) subjects.get(i), "Chrono #" + (i+1)));
                }
                createChronoWindow(true, chrono.toArray(new DigitalChrono[chrono.size()]));
            }
        }));
    }

    /**
     * Create a button, set its preferred size and add an action listener to it
     *
     * @param title content of the button's name
     * @param a action to perform when clicked
     * @return a JButton
     */
    private JButton createButton(String title, ActionListener a) {
        JButton button = new JButton(title);
        button.setPreferredSize(new Dimension(120,30));
        button.addActionListener( a );
        return button;
    }

    /**
     * Add a button to the main panel that opens a Roman dial
     *
     * @param title content of the button's name
     * @param subject Subject that must be observed
     * @param id Subject's identifier
     */
    private void createRoman(String title,Chrono subject, int id) {
        mainPanel.add(createButton(title, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChronoWindow(false,new RomanChrono(subject, "Chrono #" + id));
            }
        }));
    }

    /**
     * Add a button to the main panel that opens an Arabic dial
     *
     * @param title content of the button's name
     * @param subject Subject that must be observed
     * @param id Subject's identifier
     */
    private void createArab(String title,Chrono subject, int id) {
        mainPanel.add(createButton(title, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChronoWindow(false, new ArabChrono(subject, "Chrono #" + id));
            }
        }));
    }

    /**
     * Add a button to the main panel that opens a numeric dial
     *
     * @param title content of the button's name
     * @param subject Subject that must be observed
     * @param id Subject's identifier
     */
    private void createDigital(String title,Chrono subject, int id) {
        mainPanel.add(createButton(title, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChronoWindow(false, new DigitalChrono(subject, "Chrono #"+id));
            }
        }));
    }

    /**
     * Create all actions specific buttons to a Chrono subject
     *  start timer
     *  stop timer
     *  reset timer
     *
     * @param subject subject
     * @param id
     */
    private void createButtonRow(Chrono subject,int id) {
        // Create label
        JLabel label = new JLabel();
        label.setText("Chrono #" +id );
        mainPanel.add(label);

        // Creates buttons
        mainPanel.add(createButton("Démarrer", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject.start();
            }
        }));

        mainPanel.add(createButton("Arreter", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject.stop();
            }
        }));

        mainPanel.add(createButton("Réinitialiser", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject.reset();
            }
        }));

        createRoman("Cadran Romain",subject,id);
        createArab("Cadran Arabe",subject,id);
        createDigital("Digital",subject,id);
    }

    /**
     * Creates the main frame and calls other function to generate its content
     *
     * @param nbChronos number of subjects to create
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

        // Create assigned number of chronometers
        for(int i = 0; i < nbChronos; i++)
        {
            subjects.add(new Chrono());
            createButtonRow((Chrono) subjects.get(i),(i+1));
        }

        createAllChronoWindow();

        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

}