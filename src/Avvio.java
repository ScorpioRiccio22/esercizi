public class Avvio {

	public static void main(String[] args) {

		int scelta=0;
		Lab l = new Lab();
		
		do {	
			scelta =l.menu();
			
			switch (scelta) {
			case 1:
				System.out.println("[1] Esecuzione StampaOrizzontale");
				l.contaOrizzontale();
				break;

			case 2:
				System.out.println("[2] Esecuzione Totalizzatore");
				l.totalizzatore2();
				break;
				
			case 3:
				System.out.println("[3] Esecuzione ContatoreVocali");
				l.countVocali(l.leggiStringa("Inserisci una frase: "));
				break;
				
			case 4:
				System.out.println("[4] Esecuzione IsPalindroma.");
				boolean result;
				result = l.isPalindroma(l.leggiStringa("Inserisci una frase: "));
				if(result)	
					System.out.println("è palindroma.");
				else		
					System.out.println("non è palindroma");
				break;
				
			case 5:
				System.out.println("[5] Esecuzione Calcolatrice.");
				l.calcolatrice(); 
				break;

			case 6:
				System.out.println("[6] Esecuzione initArrayInt!");
				int dim;
				int array[];
				dim = l.leggiValoreInt("Inserisci dimensione array: ");
				array =l.initArrayInt(dim);
				l.stampaArray(array);
				break;

			case 7:
				System.out.println("[7] Esecuzione RicercaNominativo");
				int x =l.leggiValoreInt("Digita 1 se vuoi eseguire la ricerca per codice Ascii(Altro per standard)");
				String array1[];
				int dim1;
				dim1 = l.leggiValoreInt("Inserisci dimensione array: ");
				array1 =l.initArrayString(dim1);
				l.stampaArrayString(array1);
	
				if(x==1) 	  		 	
					l.ricercaNominativo(array1,l.leggiStringa("\nInserisci il nome da cercare: "));
				else 		 	
					l.ricercaNominativoAscii(array1,l.leggiStringa("\nInserisci il nome da cercare: "));
				break;

			case 8:
				System.out.println("[8] Esecuzione AnalizzaArray");
				int array2[];
				int dim2;
				dim2 = l.leggiValoreInt("Inserisci dimensione array: ");
				array2 =l.initArrayInt(dim2);
				l.stampaArray(array2);
				l.analizzaArray(array2);	
				l.stampaArray(array2);
				break;

			case 9:
				System.out.println("[9] Esecuzione AnalizzaMatrice.");
				int matrice[][]= l.initMatriceInt(l.leggiValoreInt("Inserisci numero righe: "),l.leggiValoreInt("Inserisci numero colonne: "));
				
				System.out.println("Matrice generata: ");
				l.stampaMatrice(matrice);
				l.analizzaMatrice(matrice);
				System.out.println("Matrice Analizzata: ");
				l.stampaMatrice(matrice);
				break;
				
			case 10:
				System.out.println("[10] Esecuzione OrdinamentoArray");
				String array3[];
				int dim3;
				dim3 = l.leggiValoreInt("Inserisci dimensione array: ");
				array3 =l.initArrayNominativo(dim3);
				l.stampaArrayString(array3);
				String tipoOrdinamento = l.leggiStringa("Scrivi 'crescente' o 'decrescente' per il tipo di ordinamento: ");
				l.ordinaNominativi(array3,tipoOrdinamento);
				l.stampaArrayString(array3);
				break;
				
			case 11:
				System.out.println("[11] Esecuzione MatriceMeteo.");
				Double matrice1[][]= l.initMatriceDouble(7,4);
				System.out.println("MatriceMeteo generata: ");
				l.StampaMatriceMeteo(matrice1);
				break;
				
			case 0:
				System.out.println("Uscita dal menu.");
				break;
				
			default:
				System.out.println("Scelta non valida, riprova.");
			}
			
			
		} while (scelta != 0);

	}
}	