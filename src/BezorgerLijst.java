import java.util.ArrayList;

public class BezorgerLijst {

    private ArrayList<Bezorger> bezorgers;

    public BezorgerLijst() {
        bezorgers = new ArrayList<Bezorger>();
    }

    public void addBezorger(Bezorger bezorger) {
        bezorgers.add(bezorger);
    }

    public ArrayList<Bezorger> getBezorgers() {
        return bezorgers;
    }

}
