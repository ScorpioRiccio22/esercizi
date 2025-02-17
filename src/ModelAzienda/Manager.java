package ModelAzienda;

public class Manager extends Dipendente{
private String ruolo;

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString()
    {return super.toString() + "Manager [ruolo = " + ruolo + "]";}


}
