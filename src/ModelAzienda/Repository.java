package ModelAzienda;

import java.util.HashMap;

public class Repository {
    Persona p = new Persona();
    private HashMap<Integer, Persona> h = new HashMap<Integer, Persona>();


    public HashMap<Integer, Persona> getList()
    {
        return this.h;
    }

    public void insert (Persona p)
    {
        if(p!=null)
        {
            h.put(p.getId(), p);
        } else{System.out.println("Persona non valida, impossibile da aggiungere.");}
    }

    public Boolean isCfPresente(String cf, HashMap<Integer, Persona> h)
    {
        for(Persona p : h.values())
        {
            if(p.getCf().equalsIgnoreCase(cf))
            {
                return true;
            }
        }
        return false;
    }

    public int getKey()
    {
        return Persona.conta-1;
    }

    public void delete(Persona p)
    {
        if(p!=null)
        {
            if(h.containsKey(p.getId()))
            {
                h.remove(p.getId());
            } else{System.out.println("ERRORE: Persona non presente nella mappa!");}
        } else{System.out.println("ERRORE: Persona non valida!!");}
    }

    public Persona trovaPerCf(String cf, Repository repo)
    {
        for(Persona p : repo.getList().values())
        {
            if(p.getCf().equals(cf)){return p;}
        }
        return null;
    }

    public Persona trovaPerKey(int key, Repository repo)
    {
        return repo.getList().get(key);
    }

    public void update(int key, Persona p)
    {
        if(h.containsKey(key))
        {
            h.put(key, p);
        } else{System.out.println("ERRORE: Key non trovata, impossibile aggiornare.");}
    }

    public void promuoviAManager(int key, String ruolo, double aumentoStipendio) {

		Persona p = h.get(key); // trovo il contenuto tramite key

		if (p != null && p instanceof Dipendente) { // se pa è presente ed è dipendente
			Dipendente d = (Dipendente) p; // cast

			double nuovoStipendio = aumentoStipendio;

			Manager managerPromosso = new Manager(); // creo nuovo manager

			// Copia i dati dal Dipendente al Manager
			managerPromosso.setId(d.getId()); // tutti i set e get sono da dipendente inserito
			managerPromosso.setCf(d.getCf());
			managerPromosso.setNome(d.getNome());
			managerPromosso.setCognome(d.getCognome());
			managerPromosso.setDataDiNascita(d.getDataDiNascita());
			managerPromosso.setStipendio(d.getStipendio() + nuovoStipendio);
			managerPromosso.setDataDiAssunzione(d.getDataDiAssunzione());

			// Aggiungi il ruolo specifico del Manager
			managerPromosso.setRuolo(ruolo);

			// La chiave (key) rimane invariata
			update(key, managerPromosso);
			// Aggiorna la HashMap con il Manager

			System.out.println("Promozione avvenuta con successo! La persona è ora un Manager con ruolo: " + ruolo);
		} else {
			System.out.println("Persona non trovata o non è un Dipendente.");
		}
	}



}
