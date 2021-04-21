/**
 * Main application creating a ControlPanel
 * with the number of chronometers to create passed in argument
 *
 * @author Emmanuel Janssens
 * @author Rosalie Chhen
 *
 * @date 21.04.2021
 */
public class App {
    public static void main(String[] args)
    {
        new ControlPanel(Integer.parseInt(args[0]));
    }
}
