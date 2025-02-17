package ControllerAziendale;

import ModelAzienda.Dipendente;
import ModelAzienda.Manager;
import ModelAzienda.Persona;
import ModelAzienda.Repository;
import ViewAziendale.View;

public class Avvio {
    public static void main(String[] args) throws CfException, MinorenneException, DataNonValidaException, StipendioException{
    Repository repo = new Repository();

		View v = new View();

		Persona pa = new Persona();

		boolean flag = true;

		do {
			int scelta = v.menu(); // parte il metodo menù
			switch (scelta) {
			case 1: {
				System.out.println("Inserire il tipo di persona: [1]Manager, [2]Dipendente");
				int tipoPersona = (int) v.leggiValore("Fai una scelta: "); // Scegli chi inserire
				Persona nuovaPersona = (tipoPersona == 1) ? new Manager() : new Dipendente();
				// L'inserimento e controllo avvengono in mascheraInserimento
				v.mascheraInserimento(nuovaPersona, repo);
				// Inserisci la persona nel repository
				repo.insert(nuovaPersona);

				// Ottieni la chiave dell'oggetto appena inserito
				int ultimaKey = repo.getKey();
				System.out.println("Persona inserita con successo!" + ultimaKey + nuovaPersona);
			}
				break;
			case 2: { // stampa il contenuto della lista
				System.out.println("Persone attualmente presenti: ");
				for (Persona persone : repo.getList().values()) {
					if (persone != null)
						System.out.println(persone);

				}
				break;
			}
			case 3: {
				// Usa il metodo sceltaDiRicerca per cercare una persona
				Persona personaTrovata = v.sceltaDiRicerca(repo, v);

				if (personaTrovata != null) {
					System.out.println(personaTrovata); // Mostra la persona trovata
				} else {
					System.out.println("Persona non trovata.");
				}
				break;
			}
			case 4: { // Cerca e poi elimina una persona
				Persona personaTrovata = v.sceltaDiRicerca(repo, v);

				if (personaTrovata != null) {
					// Elimina la persona trovata
					repo.delete(personaTrovata);
				} else {
					System.out.println("Persona non trovata.");
				}
				break;
			}
			case 5: { // Cerca una persona e aggiorna i suoi dati
				Persona personaDaAggiornare = v.sceltaDiRicerca(repo, v);

				if (personaDaAggiornare != null) {
					v.aggiornaDati(personaDaAggiornare); // Aggiorna i dati della persona
					System.out.println("Dati aggiornati con successo! Nuovi dati: " + personaDaAggiornare);
				} else {
					System.out.println("Persona non trovata.");
				}
				break;
			}
			case 6: {
				// Chiedi l'ID della persona da promuovere
				int idDaPromuovere = (int) v.leggiValore("Inserisci l'ID della persona da promuovere a Manager: ");

				// Cerca la persona nel repository
				Persona personaTrovata = repo.trovaPerKey(idDaPromuovere, repo);

				if (personaTrovata != null) { // se esiste, è dipendente e non è manager
					if (personaTrovata instanceof Dipendente && !(personaTrovata instanceof Manager)) {
						// mettici nuovo ruolo e mettici l'aumento
						String nuovoRuolo = v.leggiString("Inserisci il ruolo del nuovo Manager: ");
						double aumento = (double) v.leggiValore("Inserisci aumento: ");

						// Promuovi la persona a Manager
						repo.promuoviAManager(idDaPromuovere, nuovoRuolo, aumento);
					} else if (personaTrovata instanceof Manager) {
						System.out.println("Errore: la persona con ID " + idDaPromuovere + " è già un Manager.");
					} else {
						System.out.println("Errore: la persona con ID " + idDaPromuovere + " non è un Dipendente.");
					}
				} else {
					System.out.println("Errore: la persona con ID " + idDaPromuovere + " non esiste.");
				}
				break;
			}
			case 0: {
				System.out.println("FINE!!!");
				flag = false;
			}
				break;

			default:
				System.out.println("Opzione non valida.");
			}
		} while (flag);


















        
        
    }
}
