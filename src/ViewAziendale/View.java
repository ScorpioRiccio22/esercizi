package ViewAziendale;

import ControllerAziendale.CfException;
import ControllerAziendale.DataNonValidaException;
import ControllerAziendale.MinorenneException;
import ControllerAziendale.StipendioException;
import ModelAzienda.Dipendente;
import ModelAzienda.Manager;
import ModelAzienda.Persona;
import ModelAzienda.Repository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class View {

    Scanner scan = new Scanner(System.in);
    Repository repo = new Repository();

    public void print(String s)
    {
        System.out.println(s);
    }

    public String leggiString(String s)
    {
        System.out.println(s);
        return scan.nextLine();
    }

    public double leggiValore(String s) {
		double num = 0;
		boolean flag = true; // leggi double da console
		do {
			flag = false;
			System.out.println(s);
			try {
				num = Double.parseDouble(scan.nextLine());
			} catch (NumberFormatException e) {
				flag = true;
				System.out.println("Non hai inserito un numero!");
			}
		} while (flag);
		return num;
	}

    public void printPersona(Persona p)
    {
        System.out.println(p);
    }

    public int calcoloEta (LocalDate dataDiNascita)
    {
        return LocalDate.now().getYear() - dataDiNascita.getYear();
    }

    public LocalDate leggiData(String messaggio)
    {
        System.out.println(messaggio);
        DateTimeFormatter form = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data= null;
        boolean flag=true;
        do
        {
            try
            {
                String dataInput = scan.nextLine();
                data = LocalDate.parse(dataInput, form);
                flag=false;
            }catch(DateTimeParseException e)
            {
                System.out.println("Formato data non valido! inserire data nel formato dd-MM-yyyy.");
            }
        }while(flag);
        return data;
    }

    public String aggiornaString(String msg, String valAttuale)
    {
        System.out.println(msg);
        String info=scan.nextLine();
        return info.isEmpty() ? valAttuale : info;
    }

    public boolean isNomeValido(String nome)
    {
        return nome.matches("^[a-zA-Z\\s'-]+$");
    }

    public boolean isCfValido(String cf)
    {
        return !cf.isEmpty() && cf.matches("^[a-zA-Z0-9]+");
    }

    public void mascheraInserimento(Persona p, Repository repo)
    {
        boolean valido;
        p.setId();
        String nome;
        do
        {nome=leggiString("Come si chiama? ");
        if(!isNomeValido(nome))
        {System.out.println("ERRORE: il nome pu contenere solo lettere, spazi, apostrofi o trattini!");}
    }while(!isNomeValido(nome));
    p.setNome(nome);
    String cognome;
		do {
			cognome = leggiString("Quale cognome vuoi inserire? ");
			if (!isNomeValido(cognome)) {
				System.out.println("Errore: il cognome può contenere solo lettere, spazi, apostrofi o trattini!");
			}
		} while (!isNomeValido(cognome));
	p.setCognome(cognome);
    String cf;
    do {
        cf = leggiString("Quale CF vuoi inserire? ");
        if (!isCfValido(cf)) {
            System.out.println(
                    "Errore: il codice fiscale può contenere solo caratteri alfanumerici e non può essere vuoto!");
        }
    } while (!isCfValido(cf));
    p.setCf(cf);
    LocalDate dataDiNascita = leggiData("Quando è nato? (Formato: dd-MM-yyyy)");
	p.setDataDiNascita(dataDiNascita);

    if (p instanceof Manager) {
        Manager m = (Manager)p;
    m.setStipendio(leggiValore("Quanto guadagna? "));
    m.setDataDiAssunzione(leggiData("Quando è stato assunto? (Formato: dd-MM-yyyy)"));
    m.setRuolo(leggiString("Che ruolo ricopre in azienda? "));
}	else if (p instanceof Dipendente) {
    Dipendente d = (Dipendente) p;
    d.setStipendio(leggiValore("Quanto guadagna? "));
    d.setDataDiAssunzione(leggiData("Quando è stato assunto? (Formato: dd-MM-yyyy)"));}
    do {
			valido = true;
			try {
				// Controlli specifici su CF, età, stipendio, ecc.
				if (repo.isCfPresente(cf, repo.getList())) {
					throw new CfException("Codice fiscale già inserito!!");
				}

				if (calcoloEta(dataDiNascita) < 18) {
					throw new MinorenneException("La persona assunta non può essere minorenne!!!");
				}

				if (p instanceof Manager) {
					Manager m = (Manager) p;
					if (m.getStipendio() < 800) {
						throw new StipendioException("Lo stipendio non può essere inferiore a 800 euro!");
					}
					if (m.getDataDiAssunzione().isBefore(LocalDate.now())) {
						throw new DataNonValidaException(
								"La data di assunzione non può essere antecedente a quella odierna!");
					}
				} else if (p instanceof Dipendente) {
					Dipendente d = (Dipendente) p;
					if (d.getStipendio() < 800) {
						throw new StipendioException("Lo stipendio non può essere inferiore a 800 euro!");
					}
					if (d.getDataDiAssunzione().isBefore(LocalDate.now())) {
						throw new DataNonValidaException(
								"La data di assunzione non può essere antecedente a quella odierna!");
					}
				}
			} catch (CfException | MinorenneException | StipendioException | DataNonValidaException e) {
				System.out.println("Errore: " + e.getMessage());
				valido = false;
			}
		} while (!valido);
}

public double aggiornaValoreStipendio(String messaggio, double valoreAttuale) {
    System.out.print(messaggio + " (Premi Invio per mantenere il valore attuale): ");
    String valore = scan.nextLine(); // sostituisce se inserisce stipendio nuovo
    if (valore.isEmpty()) {
        return valoreAttuale; // Restituisce lo stipendio attuale se l'input è vuoto
    }
    try {
        // Converte l'input in double
        return Double.parseDouble(valore);
    } catch (NumberFormatException e) {
        System.out.println("Valore non valido. Mantenuto il valore attuale.");
        return valoreAttuale;
    }
}

public LocalDate aggiornaValoreData(String messaggio, LocalDate valoreAttuale) {
    System.out.print(messaggio + " (Formato: dd-MM-yyyy, premi Invio per mantenere il valore attuale): ");
    String data = scan.nextLine();
    if (data.isEmpty()) {
        return valoreAttuale; // Restituisce la data attuale se l'input è vuoto
    }
    try {
        // Converte l'input in LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(data, formatter);
    } catch (DateTimeParseException e) {
        System.out.println("Formato data non valido. Mantenuto il valore attuale.");
        return valoreAttuale;
    }
}

public Persona sceltaDiRicerca(Repository repo, View v) {
    // Chiedi come cercare la persona
    System.out.println("[1] Cerca per CF, [2] Cerca per Key");
    int sceltaRicerca = (int) v.leggiValore("Fai una scelta: ");

    Persona personaTrovata = null;

    if (sceltaRicerca == 1) {
        // Cerca per CF
        String cf = v.leggiString("Inserisci il CF della persona: ");
        personaTrovata = repo.trovaPerCf(cf, repo);
    } else if (sceltaRicerca == 2) {
        // Cerca per Key
        int key = (int) v.leggiValore("Inserisci la Key della persona: ");
        personaTrovata = repo.trovaPerKey(key, repo);
    }

    return personaTrovata;
}


public Persona aggiornaDati(Persona p) {
    boolean confermato = false;
    do {
        // Aggiorna i dati comuni di PersonaA
        p.setNome(aggiornaString("Nome attuale: [" + p.getNome() + "]: ", p.getNome()));
        p.setCognome(aggiornaString("Cognome attuale: [" + p.getCognome() + "]: ", p.getCognome()));
        p.setCf(aggiornaString("CF attuale: [" + p.getCf() + "]: ", p.getCf()));
        p.setDataDiNascita(aggiornaValoreData("Data di Nascita attuale: [" + p.getDataDiNascita() + "]: ",
                p.getDataDiNascita()));

        // Aggiorna i dati specifici per Manager o Dipendente
        if (p instanceof Manager) {
            Manager m = (Manager) p;
            m.setStipendio(
                    aggiornaValoreStipendio("Stipendio attuale: [" + m.getStipendio() + "]: ", m.getStipendio()));
            m.setDataDiAssunzione(aggiornaValoreData(
                    "Data di Assunzione attuale: [" + m.getDataDiAssunzione() + "]: ", m.getDataDiAssunzione()));
            m.setRuolo(aggiornaString("Ruolo attuale: [" + m.getRuolo() + "]: ", m.getRuolo()));
        } else if (p instanceof Dipendente) {
            Dipendente d = (Dipendente) p;
            d.setStipendio(
                    aggiornaValoreStipendio("Stipendio attuale: [" + d.getStipendio() + "]: ", d.getStipendio()));
            d.setDataDiAssunzione(aggiornaValoreData(
                    "Data di Assunzione attuale: [" + d.getDataDiAssunzione() + "]: ", d.getDataDiAssunzione()));
        }
        System.out.println("\nDati aggiornati:");
        System.out.println(p.toString());
        System.out.print("\nConfermi gli aggiornamenti? (si/no): ");

        String risposta = scan.nextLine();

        if (risposta.equalsIgnoreCase("si")) {
            System.out.println("Modifiche confermate.");
            confermato = true; // Esci dal ciclo
        } else if (risposta.equalsIgnoreCase("no")) {
            System.out.println("Modifiche annullate. Riprova.");
        } else {
            System.out.println("Risposta non valida. Inserire 'si' per confermare o 'no' per riprovare.");
        }
    } while (!confermato);

    return p; // Restituisci la stessa persona aggiornata
}






public int menu() {
		System.out.println("***GESTIONE AZIENDALE***");
		System.out.println("1) Inserimento");
		System.out.println("2) Stampa");
		System.out.println("3) Cerca");
		System.out.println("4) Rimozione");
		System.out.println("5) Modifica");
		System.out.println("6) Promozione");
		System.out.println("0) ESCI!");
		return (int) this.leggiValore("FAI UNA SCELTA: ");
	}


}
