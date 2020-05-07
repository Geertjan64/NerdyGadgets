public class Planner extends Werknemer {

    private String middlename;
    private String email;
    private String password;
    private String function;

    public Planner(int werknemerID, String voornaam, String achternaam, String middlename, String email, String password, String function) {
        super(werknemerID, voornaam, achternaam);
        this.middlename = middlename;
        this.email = email;
        this.password = password;
        this.function = function;
    }

    public String getMiddlename() {
        return middlename;
    }

    public String getEmail() {
        return email;
    }

    public String getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return "Planner{" +
                "middlename='" + middlename + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", function=" + function +
                '}';
    }
}
