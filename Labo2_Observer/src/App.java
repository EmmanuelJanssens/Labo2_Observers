public class App {
    public static void main(String[] args) throws Exception 
    {
        // observeur concret
        DigitalChrono digitalChrono = new DigitalChrono();
        ArabChrono arabChrono = new ArabChrono();

        // sujet concret
        Chrono chrono = new Chrono();

        chrono.attach(digitalChrono); // on abonne l'observeur au sujet
        digitalChrono.setChrono(chrono); // ??? comment rendre réciproque ("abonné" le sujet à l'observeur)
        chrono.attach(arabChrono);
        arabChrono.setChrono(chrono);
    }
}
