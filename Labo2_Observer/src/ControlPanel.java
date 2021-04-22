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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.LinkedList;
import java.util.List;

public class ControlPanel
{
    // List of all the concrete subjects to display
    private List<Chrono> subjects = new LinkedList<>();

    /**
     * Creates the main frame and calls other function to generate its content
     *
     * @param nbChronos number of subjects to create passed in argument in the application
     */
    public ControlPanel(int nbChronos)
    {
        // Main display window
        JFrame mainFrame = new JFrame();
        mainFrame.setTitle("Panneau de contrôle");
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the layout to a grid of one column and
        // a row per chrono subject + the row of buttons "Tous les chronos"
        mainFrame.setLayout(new GridLayout(nbChronos + 1,1));

        // Create a row of buttons for each chronometer and add it to the list
        for(int i = 0; i < nbChronos; i++) {
            subjects.add(new Chrono());
            mainFrame.add(createButtonsRow(subjects.get(i)));
        }
        mainFrame.add(createButtonsAllChrono());

        // Enable the frame size to fit correctly its content
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    /**
     * Create a button and add an action listener to it
     *
     * @param title content of the button's text
     * @param a action to perform when clicked
     * @return a JButton
     */
    private JButton createButton(String title, ActionListener a) {
        JButton button = new JButton(title);
        button.addActionListener( a );
        return button;
    }

    /**
     * Creates a window with a Chrono subject displayed by a list of observers
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
     * Create the row of buttons :
     * - doing all specific action to a Chrono subject : start, stop and reset
     * - creating all display type for a Chrono subject : roman, arab and digital
     *
     * @param subject subject to display
     * @return a JPanel containing the row of buttons
     */
    private JPanel createButtonsRow(Chrono subject) {

        // JPanel to return
        JPanel buttonRowPanel = new JPanel();
        // Put component in a row
        buttonRowPanel.setLayout(new FlowLayout());

        // Create and add label to JPanel
        JLabel label = new JLabel();
        label.setText("Chrono #" + subject.getId() );
        buttonRowPanel.add(label);

        // Creates and add buttons, each button do something different
        buttonRowPanel.add(createButton("Démarrer", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject.start();
            }
        }));

        buttonRowPanel.add(createButton("Arreter", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject.stop();
            }
        }));

        buttonRowPanel.add(createButton("Réinitialiser", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subject.reset();
            }
        }));

        buttonRowPanel.add(createButton("Cadran Romain", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChronoWindow(false,new RomanChrono(subject));
            }
        }));

        buttonRowPanel.add(createButton("Cadran Arabe", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChronoWindow(false,new ArabChrono(subject));
            }
        }));

        buttonRowPanel.add(createButton("Numérique", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChronoWindow(false,new DigitalChrono(subject));
            }
        }));

        return buttonRowPanel;
    }

    /**
     * Create the row of buttons which displays all the chronometers in the type :
     * roman, arab or digital indicated on the button text
     *
     * @return a JPanel containing all this buttons in a row
     */
    private JPanel createButtonsAllChrono() {

        // JPanel to return
        JPanel buttonRowPanel = new JPanel();
        buttonRowPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Create and add label to JPanel
        JLabel label = new JLabel();
        label.setText("Tout les chronos:" );
        buttonRowPanel.add(label);

        // Button that creates a roman panel for each Chrono subject, in one resizeable window
        buttonRowPanel.add(createButton("Cadran romain", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<RomanChrono> romanChronos = new LinkedList<>();
                for (Subject subject : subjects) {
                    romanChronos.add(new RomanChrono((Chrono) subject));
                }
                createChronoWindow(true,  romanChronos.toArray(new RomanChrono[romanChronos.size()]));
            }
        }));

        // Button that creates an arab panel for each Chrono subject, in one resizeable window
        buttonRowPanel.add(createButton("Cadran arabe", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<ArabChrono> arabChronos = new LinkedList<>();
                for (Subject subject : subjects) {
                    arabChronos.add(new ArabChrono((Chrono) subject));
                }
                createChronoWindow(true, arabChronos.toArray(new ArabChrono[arabChronos.size()]));
            }
        }));

        // Button that creates a digital panel for each Chrono subject, in one resizeable window
        buttonRowPanel.add(createButton("Numérique", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<DigitalChrono> digitalChronos = new LinkedList<>();
                for (Subject subject : subjects) {
                    digitalChronos.add(new DigitalChrono((Chrono) subject));
                }
                createChronoWindow(true, digitalChronos.toArray(new DigitalChrono[digitalChronos.size()]));
            }
        }));

        return buttonRowPanel;
    }
}