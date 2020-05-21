package Default.Planners;

import Default.Entiteit.Werknemer;

public class Planner extends Werknemer {

    private String tussenvoegsel;
    private String email;
    private String password;
    private String function;

    public Planner(int werknemerID, String voornaam, String achternaam, String tussenvoegsel, String email, String password, String function) {
        super(werknemerID, voornaam, achternaam, tussenvoegsel, email);
        this.password = password;
        this.function = function;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public String getEmail() {
        return email;
    }

    public String getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return "Default.Planners.Planner{" +
                "middlename='" + tussenvoegsel + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", function=" + function +
                '}';
    }
}
