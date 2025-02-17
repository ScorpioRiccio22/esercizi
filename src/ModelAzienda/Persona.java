package ModelAzienda;

import java.time.LocalDate;

public class Persona {
    private int id;
    private String nome;
    private String cognome;
    private String cf;
    private LocalDate dataDiNascita;
    public static int conta=1;

    public int getId() {
        return id;
    }

    public int setId()
    {
        return this.id=conta++;
    }

    public int setId(int id) {
       return this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public Persona(String nome, String cognome, String cf, LocalDate dataDiNascita)
    {
        this.nome=nome;
        this.cognome=cognome;
        this.cf=cf;
        this.dataDiNascita=dataDiNascita;
    }

    @Override
    public String toString ()
    {
        return "Persona [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", cf=" + cf + ", dataDiNascita="
				+ dataDiNascita + "]";
    }

    public Persona(){}

}
