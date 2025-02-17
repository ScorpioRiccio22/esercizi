package ModelAzienda;

import java.time.LocalDate;

public class Dipendente extends Persona{

    private double stipendio;
    public double getStipendio() {
        return stipendio;
    }
    public void setStipendio(double stipendio) {
        this.stipendio = stipendio;
    }
    private LocalDate dataDiAssunzione;
    public LocalDate getDataDiAssunzione() {
        return dataDiAssunzione;
    }
    public void setDataDiAssunzione(LocalDate dataDiAssunzione) {
        this.dataDiAssunzione = dataDiAssunzione;
    }

    @Override
    public String toString()
    {
        return super.toString() + "Dipendente [stipendio=  "+ stipendio + "Data di assunzione= " + dataDiAssunzione + "]";
    }
}
