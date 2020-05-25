package Default.Entiteit;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Routes {

    private int id;
    private String route;

    public Routes(int id, String route) {
        this.id = id;
        this.route = route;
    }

    public int getId() {
        return id;
    }

    public String getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "Route - "+getId();
    }

}
