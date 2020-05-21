package Default.Entiteit;

import java.util.ArrayList;

public class BezorgerLijst {

    private static ArrayList<Bezorger> bezorgers;

    public BezorgerLijst() {
        bezorgers = new ArrayList<>();
    }

    public void addBezorger(Bezorger bezorger) {
        bezorgers.add(bezorger);
    }

    public ArrayList<Bezorger> getBezorgers() {
        return bezorgers;
    }

    public void printLijst() {
        for (Bezorger b : bezorgers) {
            System.out.println(b);
        }
    }
}
