import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lab {
	Scanner input = new Scanner(System.in);

	/*
	 * Esercizio 1

		Esercizio 1 
		Inserire da console un valore
		Se il valore è int negativo stampa(scelta non valida)
		se il valore è int positivo (stampa)
		Input: 5
		Output: 0,1,2,3,4,5. (gestire , e .)
	 */

	public void contaOrizzontale() {

		System.out.print("Inserisci un numero: ");
		int num = input.nextInt();

		if (num>0) {
			for (int i = 0; i<num; i++) 
				System.out.print(i+",");    

			System.out.print(num+".\n");
		} else {
			System.out.println("Scelta non valida");
		}
	}

	/*
	 * Esercizio 2
	 * Totalizzatore
	 * 
	 */


	public void totalizzatore1() {
		//double sum=0,average=0;
		int sum=0;
		int count=0;
		String risposta;

		do {
			System.out.print("Inserisci un numero: ");        
			count++;
			sum+=input.nextInt();
			//sum+=input.nextDoble();
			//average=sum/count

			System.out.print("Vuoi inserire un altro numero? (si): ");
			risposta = input.next();  
		} while (risposta.equalsIgnoreCase("si"));

		System.out.println("La somma dei numeri inseriti è: " + sum);
		System.out.println("La media dei numeri inseriti è: " + (double) sum/count);

	}


	/*
	 * Esercizio 3
	 * Stampa in console la frase inserita inserita nel main come parametro 
	 */

	public String leggiStringa(String s) {
		System.out.print(s);	    
		return input.nextLine();	
	}

	//Stesso per gli interi

	public int leggiValoreInt(String s) {
		int num=0;
		boolean flag=true;

		do {
			flag=false;												//flag a false esco dal while
			System.out.print(s);									//Stampiamo a console la stringa inserita nel main
			try {  
				 num = Integer.parseInt(input.nextLine());
				// System.out.println("hai inserito:" +num);
		      }catch(NumberFormatException e){						//formato del numero non corretto
				flag=true;											//finquando entro nel catch il setto flag true, entriamo nel while e inseriamo nuovo numero
				System.out.println("Non hai inserito un numero. " +e+ "quello che hai inserito:" +num);	
			}
		}while(flag);
		
		return num;		
	}
	
	

	//Stesso per i double

	public Double leggiValoreDouble(String s) {
		Double num=0.0;
		boolean flag=true;
		do {
			flag=false;		
			System.out.print(s);
			try {
				num =Double.parseDouble(input.nextLine());
			}catch(NumberFormatException e){
				System.out.println("Non hai inserito un numero!");	
			}
		}while(flag);	
		return num;
	}

	/*
	 * Esercizio 4
	 * Metodo per contare vocali e consonanti escludendo spazi e caratteri speciali
	 */
	

	public void countVocali(String frase) {

		/*
		 * altro modo per eliminare tutto tranne quello compreso tra a-z sostituendo con vuoto , gestisci accentati	
		 * System.out.println(frase);
		 * frase=frase.replaceAll("[^a-zA-Z0-9àèéìò]","");
		 * System.out.println(frase);
		 */

		int countA=0, countE=0, countI=0, countO=0, countU=0;
		int countConsonanti=0;

		//Per ottimizzare e non iterare per ogni carattere possiamo filtrare prima 

		for (int i = 0; i < frase.length(); i++) {				// Vado avanti finché non finisce la stringa
			char c = Character.toLowerCase(frase.charAt(i)); 	// CharAt(i) prendo carattere in posizione i
			if (Character.isLetter(c)) { 						// attraverso il metodo character verifica se è una lettera , scarta spazi e caratteri speciali


				switch (c) {									// conta delle vocali, altrimenti è consonante
				case 'a','à':
					countA++;
				break;
				case 'e','è','é':
					countE++;
				break;
				case 'i','ì':
					countI++;
				break;
				case 'o','ò':
					countO++;
				break;
				case 'u','ù':
					countU++;
				break;
				default:
					countConsonanti++;
					break;
				}
			}
		}

		System.out.println("Numero di a: " + countA);
		System.out.println("Numero di e: " + countE);
		System.out.println("Numero di i: " + countI);
		System.out.println("Numero di o: " + countO);
		System.out.println("Numero di u: " + countU);
		System.out.println("Numero di consonanti: " + countConsonanti);

	}


	/*
	 * Esercizio 5
	 * Data una frase se ritorna palindroma restituisce true altrimenti false
	 * gestisci la stampa nell main.
	 * I topi non avevano nipoti
	 * A Legnano corro con Angela
	 * Anna
	 */

	public boolean isPalindroma(String frase) {
		frase = frase.replaceAll("[^a-zA-Z0-9àèéìòù]","").toLowerCase();		//pulisco la frase da spazi e carateri speciali
		int left = 0;
		int right = frase.length()-1;											//inizializzo puntatori ad inizio e fine frase
		while (left < right) {													//scorro la stringa pulita in verso opposto 
			if (frase.charAt(left) != frase.charAt(right)) {
				return false; 													// Non è palindroma abbiamo trovato un carattere diverso
			}
			left++;																// gestisco indici
			right--;
		}	
		return true;															//se il while termina senza entare nell'if allora sono palindrome
	}

	/*
	 * Esercizio 6
	 * Aggiungi al totalizzatore (esercizio2)
	 * vogliamo ora sapere dei valori pari e dispari il min ,il max e la media
	 */

	public void totalizzatore2() {
		int count=0, sum=0, sumPari=0, sumDispari=0;
		int countPari=0, minPari=0, maxPari=0;
		int countDispari=0, maxDispari=0, minDispari=0;
		String risposta;

		do {
			
			System.out.print("Inserisci un numero: ");   
			int num =input.nextInt();
			sum+=num;   
			count++;

			if (num % 2 == 0) {

				if (countPari==0 || num < minPari) 	//countPari==0 nel primo ingresso settiamo min che inizialmente avevamo messo a zero
					minPari = num;    
				if (countPari==0 || num > maxPari) 	//countPari==0 nel primo ingresso settiamo max che inizialmente avevamo messo a zero
					maxPari = num;
				sumPari += num;
				countPari++;

			} else {

				if (countDispari==0 || num < minDispari) 
					minDispari = num;  
				if (countDispari==0 || num > maxDispari) 
					maxDispari = num;  
				sumDispari += num;
				countDispari++;
			}

			System.out.print("Vuoi inserire un altro numero? (si per continuare) ");
			risposta = input.next(); 

		} while (risposta.equalsIgnoreCase("si"));


		System.out.println("La somma totale: "+ sum);
		System.out.println("La media totale è: "+ String.format("%.2f",(double) sum/count ));


		System.out.println("Numeri pari");
		System.out.println("Media: " + String.format("%.2f",(countPari > 0 ? (double) sumPari / countPari : 0)));
		System.out.println("Minimo: " + (countPari > 0 ? minPari : "Non sono stati inseriti pari"));
		System.out.println("Massimo: " + (countPari > 0 ? maxPari : "Non sono stati inseriti pari"));

		System.out.println("Numeri dispari");
		// System.out.println("Media: " + (countDispari > 0 ? (double) sumDispari / countDispari : "non ci sono"));
		System.out.println("Media: " + String.format("%.2f",(countDispari > 0 ? (double) sumDispari / countDispari : 0)));
		//condizione ? valore se vero : valore se falso
		System.out.println("Minimo: " + (countDispari > 0 ? minDispari : "Non sono stati inseriti dispari"));
		System.out.println("Massimo: " + (countDispari > 0 ? maxDispari : "Non sono stati inseriti dispari"));
	}

	/*
	 * Esercizio 7
	 * Simula comportamento calcolatrice
	 * Operatori +,-,*,/,=
	 * Comportamenti :
	 * se premo "=" mi da il risultato dell'operazione
	 * dopo inserimento del primo operatore devo poter inserire il secondo numero
	 * all'inserimento del secondo operatore devo ,prima di poter inserire il terzo numero,visualizzare il risultato precedente 
	 */

	public void calcolatrice(){

		double risultato=0;
		double altroNumero=0;

		System.out.print("Inserisci un  numero: ");
		//risultato = input.nextDouble();
		risultato = Double.parseDouble(input.nextLine());


		while (true) {	         
			System.out.print("Inserire operatore: [+],[-],[*],[/]  oppure [=] per il risultato: ");
			String operatore = input.next();

			if (operatore.equals("=")) {
				System.out.printf(Locale.US, "Risultato finale: %.2f%n", risultato);	// locale US altrimenti a me usa la virgola
				break;
			}else {
				try {
					System.out.print("Inserisci un altro numero: ");
					altroNumero = input.nextDouble();					
				} catch (Exception e) {
					System.out.println("Errore: Inserisci un numero valido");
					altroNumero=0;													// Se numero non valido setto a zero altrimenri mi risomma il valore vecchio
					input.next(); 													// Pulisci l'input non valido 
				}
			}

			switch (operatore) {
			case "+":
				risultato += altroNumero;
				break;
			case "-":
				risultato -= altroNumero;
				break;
			case "*":
				risultato *= altroNumero;
				break;
			case "/":
				if (altroNumero != 0)  risultato /= altroNumero;
				else {
					System.out.println("Divisione per zero! Riprova.");	
					input.next(); 	
					continue;
				}
				break;	
			default:
				System.out.println("Operatore " + operatore +" non valido! Riprova. ");	
				continue;		// Torna all'inizio del ciclo
			}
			System.out.printf(Locale.US, "Risultato attuale: %.2f%n", risultato);
		}
	}

	/*
	 * Esercizio 8 
	 * Altra cartella ModificatoriAccesso
	 */

	/*
	 * Esercizio 9
	 * Crea un Array di n interi e stampa usando un altro metodo
	 * inserisci il	valore 1^ 	valore:
	 * inserisci il valore 2^ 	valore:
	 * ....
	 * inserisci il valore 10^ 	valore:
	 */

	public int[] initArrayInt(int dim) {
		int array[] = null;
		try {
			if (dim < 0) throw new IllegalArgumentException("La dimensione dell'array non può essere negativa.");

			array=new int[dim];

			for (int i = 0; i < array.length; i++) {
				array[i] = leggiValoreInt("Inserisci il " + i + "^ valore: ");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		return array;
	}

	//Stampa Array Interi fornito a parametro

	public void stampaArray(int[] array) {
		if (array == null || array.length == 0) {
			System.out.println("L'array è vuoto o nullo.");
			return;
		}

		for (int i = 0; i < array.length; i++) {
			System.out.print("["+array[i]+"]");
		}
		System.out.println();
	}

	/*
	 * crea vettore Stringhe
	 */

	public String[] initArrayString(int dim) {
		String[] array = null;
		try {
			if (dim < 0) throw new IllegalArgumentException("La dimensione dell'array non può essere negativa.");
			array=new String[dim];

			for (int i = 0; i < array.length; i++) {
				array[i] = leggiStringa("Inserisci il " + i + "^ elemento: ");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		return array;
	}
	
	
	
	public String[] initArrayNominativo(int dim) {
		String[] array = null;
		try {
			if (dim < 0) throw new IllegalArgumentException("La dimensione dell'array non può essere negativa.");
			array=new String[dim];

			for (int i = 0; i < array.length; i++) {
				array[i] = leggiNominativo("Inserisci nominativo: ");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		return array;
	}

	/*
	 * stampa vettore Stringhe
	 */

	public void stampaArrayString(String[] array) {
		if (array == null || array.length == 0) {
			System.out.println("L'array è vuoto o nullo.");
			return;
		}
		
		for (int i = 0; i < array.length; i++) {
			System.out.print("["+array[i]+"]");
		}
		System.out.println();
	}

	/*
	 * Esercizio 10
	 * ricerca in base al nominativo
	 * stampa nominativo trovato n volte in posizione 2, 3, 5
	 * se non presente: Nominativo non trovato
	 */

	public void ricercaNominativo(String[]array,String nominativo) {
		boolean trovato = false;
		int count=0;

		for (int i = 0; i < array.length; i++) {					
			if (array[i].equalsIgnoreCase(nominativo)) {
				trovato = true;
				count++;
			}
		}
		if (trovato) {
			System.out.println("Nominativo "+nominativo+" trovato: "+count );
		} else {
			System.out.println(nominativo + " non trovato" );
		}
	}

	/*
	 * Esercizio 10b
	 * Ricerca nominativo usando codice ascii.
	 * controlla se nell'array esistono nominativi con uguale valore in ascii
	 * roma/amor/mora
	 */

	public String[] ricercaNominativoAscii(String[]array,String nominativo) {
		int asciiNominativo = calcolaSommaASCII(nominativo);
		int count=0;

		for (int i=0; i<array.length; i++) {														//ciclo per avere la dimensione,count, per creare array di occorrenze
			if (calcolaSommaASCII(array[i])== asciiNominativo) {									//verifico se valore ascii uguale
				count++;
			}
		}

		String[] paroleUgualeAscii = new String[count];												//creo array della giusta dimensione

		for (int i=0, j=0; i<array.length; i++) {													//ciclo copiando nell' array delle occorrenze le stringhe con uguale ascii
			if (calcolaSommaASCII(array[i])==asciiNominativo) {
				paroleUgualeAscii[j] = array[i];
				j++;
			}
		}

		if (count > 0) {
			System.out.println("Nominativo "+nominativo+" trovato: "+count );						//stampo la stringa cercata e il numero di occorrenze
			System.out.println("Nominativi uguale ascii: "+ Arrays.toString(paroleUgualeAscii));	//stampo array di stringhe con uguale valore ascii
		} else {
			System.out.println(nominativo + " non trovato" );
		}
		return paroleUgualeAscii;
	}

	/*
	 * Funzione somma i valori ascii dei caratteri che compongono una stringa
	 */

	public int calcolaSommaASCII(String parola) {
		int somma = 0;
		for (int i = 0; i < parola.length(); i++) {
			somma += (int) parola.charAt(i);	
		}
		return somma;
	}

	/*
	 * Esercizio 11
	 * Metodo Analizza vettore  
	 * riceve vettore di array, 
	 * qual'è il valore minimo e il max?
	 * sposta il primo nella prima locazione e il max all'ultima
	 * array[20 5 -10 80 3 1]
	 * array[-10 5 20 1 3 80]		  
	 */

	public void analizzaArray(int[] array) {

		int maxIndex = 0;                              //primo elemento come min e max
		int minIndex = 0;

		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[minIndex]) {			//confronto secondo con il primo
				minIndex = i;							// aggiorna indice min  
			}
			if (array[i] > array[maxIndex]) {
				maxIndex = i;							//aggiorna indice max
			}
		}

		// Scambia il minimo con il primo elemento
		if (minIndex != 0) {							// se min non è il primo elemento
			int tempMin = array[minIndex];				//  swap temp
			array[minIndex] = array[0];
			array[0] = tempMin;
			if (maxIndex == 0) {						//solo se max è al primo	
				//dopo che abbiamo portato il valore minimo al primo elem array
				maxIndex = minIndex; 					//aggiorno L'indice massimo a quello che prima era di min,ripuntanto cosi al max che stava al primo elemento prima dello swap
			}											//altrimenti max ora punterebbe al primo elemento che adesso è di min
		}

		// Scambia il massimo con l'ultimo elemento
		if (maxIndex != array.length - 1) {
			int tempMax= array[maxIndex];
			array[maxIndex] = array[array.length - 1];
			array[array.length - 1] = tempMax;
		}
	}  		


	/*
	 * Esercizio 12
	 * Inizializza la matrice con valori random interi range[-100 a 100] 
	 */

	public int[][] initMatriceInt(int dimRiga,int dimColonna) {
		int[][] matrice = new int [dimRiga][dimColonna];
		Random random = new Random();
		for (int i = 0; i < dimRiga; i++) {
			for (int j = 0; j < dimColonna; j++) {
				matrice[i][j] = random.nextInt(201) - 100; // genero numero tra 0 a 200 e poi sottraggo 100 cosi range é corretto
			}
		}
		return matrice;
	}

	/*
	 * Stampa Matrice di interi
	 */

	public void stampaMatrice(int[][] matrice) {
		for (int i = 0; i < matrice.length; i++) {
			for (int j = 0; j < matrice[i].length; j++) {
				System.out.print("["+ matrice[i][j]+ "]	"); 
			}
			System.out.println(); // Vai a capo dopo ogni riga   
		}
	}

	/*
	 * Esercizio 13
	 * Analizza riga e 
	 * metti nella prima posizione il valore max della riga 
	 * e nell'ultima posizione il valore min
	 */

	public void analizzaMatrice(int[][] matrice) {	
		int maxIndex, minIndex;

		for (int i=0; i < matrice.length; i++) {				//ad ogni iterazione cambio riga
			maxIndex = 0;
			minIndex = 0;  					
			for (int j=1; j < matrice[i].length; j++) {			//scorro la riga cambiando indice colonna ad ogni iterazione

				if (matrice[i][j] < matrice[i][minIndex]) {		//per ogni riga salvo indice min
					minIndex = j;							
				}
				if (matrice[i][j] > matrice[i][maxIndex]) {		//per ogni riga salvo indice max
					maxIndex = j;							 
				}
			}

			//Min: attenzione agli indici, agisco sulla j che scrorre la mia riga
			if (maxIndex != 0) {								
				int tempMax = matrice[i][maxIndex];				
				matrice[i][maxIndex] = matrice[i][0];			
				matrice[i][0] = tempMax;

				if (minIndex == 0) {																	
					minIndex = maxIndex; 
				}					
			}

			// Max: attenzione agli indici, agisco sulla j che scrorre la mia riga
			if (minIndex != matrice[i].length - 1) {				//ultimo elemento riga
				int tempMin= matrice[i][minIndex];			
				matrice[i][minIndex] = matrice[i][matrice[i].length - 1];			
				matrice [i][matrice[i].length - 1] = tempMin;
			}

		}	

		

	}
	
	
	
	/*
	 * public void ordinaNominativi(String[] nomi,String tipoOrdinamento) {  
	 * crea funzione di ordinamento lessicografico(scegli tra crescente e decrescente) come metodo di utilita (sort di java non bisogna usarla)
	 * Gestisci attraverso Eccezione personalizzata(nominativo non valido) se nome ha numeri o caratteri speciali
	 * 
	 * mario Luca Antonio anna maria
	 * anna antonio luca maria mario
	 */
	
	public void ordinaNominativi(String[] nomi,String tipoOrdinamento) {  
		
		boolean flag = false;
		if (tipoOrdinamento.equalsIgnoreCase("crescente")) {
			flag = true;
			selectionSort(nomi, flag);
		} else if (tipoOrdinamento.equalsIgnoreCase("decrescente")) {	
			flag = false;
			selectionSort(nomi, flag);
		} else {
			System.out.println("Tipo di ordinamento non valido. Usa 'crescente' o 'decrescente'.");
		}
	}

//ad ogni iterazione cerco il piu piccolo(o grande) lo posiziono nella corretta posizione e vado avanti
//	public void selectionSort(String[] array,boolean crescente) {
//		
//		for (int i = 0; i < array.length - 1; i++) {				//Ciclo attraverso ogni elemento dell'array (i mi fermo al penultimo con j ultimo)
//			int index = i;											//Inizializza l'indice del minimo (o massimo) alla prima posizione
//	
//			for (int j = i + 1; j < array.length; j++) {			//Ciclo per trovare il minimo (o massimo)
//				
//				if (crescente) {													//Ordine Crescente
//					if (array[j].compareToIgnoreCase(array[index]) < 0) {			// Confronto lessicografico tra l'elemento corrente nell'array "array[j]" e "array[index]" min(attuale)
//																					// CompareTo restituisce la differenza tra i valori ascii. valore < 0 se array[j] è lessicograficamente minore. 
//						index = j; 													// Trova il nuovo minimo salvo indice
//					}
//				} else {															//Ordine Decrescente
//					if (array[j].compareToIgnoreCase(array[index]) > 0) {			// Confronto lessicografico
//																					// Un valore negativo (<) se array[j] è lessicograficamente minore, altrimenti uguale.
//						index = j; 													// Trova il nuovo massimo salvo indice
//					}
//				}
//			}   
//			
//			// Scambia gli elementi
//			String temp = array[index];		//Salva il valore minimo (o massimo) temporaneamente
//			array[index] = array[i];		//Sposta il valore corrente nella posizione dove abbiamo trovato minimo (o massimo)
//			array[i] = temp;				//Posiziona il valore minimo (o massimo) nella posizione corrente
//		}
//
//	}
	
	//Versione compatta
	public void selectionSort(String[] array, boolean crescente) {
	    for (int i = 0; i < array.length - 1; i++) {
	        int index = i;
	        for (int j = i + 1; j < array.length; j++) {
	            if ((crescente && array[j].compareToIgnoreCase(array[index]) < 0) ||(!crescente && array[j].compareToIgnoreCase(array[index]) > 0)) {
	                index = j;
	            }
	        }
	        if (index != i) {
	            String temp = array[index];
	            array[index] = array[i];
	            array[i] = temp;
	        }
	    }
	}
	
	
	public String leggiNominativo(String s) {

		String nominativo;
		boolean flag=true;

		String regex = "[a-zA-Z]{2,}";					//nominativo min 2 caratteri fino a quando voglio. Non deve avere spazi numeri o caratteri speciali
		Pattern pattern = Pattern.compile(regex);		//compilazione pattern

		do {
			flag=false;
			System.out.print(s);
			nominativo = input.nextLine();	
			Matcher matcher = pattern.matcher(nominativo);

			try {											
				if(!matcher.matches()) {					//controllo la corrispondenza
					throw new NominativoException();		//se non rispetta la regex lancio eccezione
				}
			}catch(NominativoException e){
				flag=true;
				System.out.println(e);						//e richiam il toString scritto in NominativoException
			}
		}while(flag);

		return nominativo;
	}

	/*
	 * Inizializza con valori randomici double da -20 a 45 una Matrice
	 * approssimatizza per eccesso alla seconda cifra decimale i valori 25.8347483864 ----> 25.84
	 *
	 */
	
	public Double[][] initMatriceDouble(int dimRiga,int dimColonna) {
		Double[][] matrice = new Double [dimRiga][dimColonna];
		Random random = new Random();
		for (int i = 0; i < dimRiga; i++) {
			for (int j = 0; j < dimColonna; j++) {
				double randomValue= -20 + (random.nextDouble() * (65)); // parte da -20 e moltplica  per lunghezza intervallo
				double roundedValue = Math.ceil(randomValue * 100.0) / 100.0;
				matrice[i][j] =roundedValue;
			}
		}
		return matrice;
	}
	
	public void StampaMatriceMeteo(Double[][] matrice) {
		System.out.println("\t  6:00\t12:00\t18:00\t24:00");
		String giorni[]= {"Lun","Mar","Mer","Gio","Ven","Sab","Dom"};
		for (int i = 0; i < matrice.length; i++) {
			System.out.printf("%s\t",giorni[i]);
			
			for (int j = 0; j < matrice[i].length; j++) {
				System.out.print("[");
				System.out.printf("%6.2f" ,matrice[i][j]); 
				System.out.print("]");
			}
			System.out.println(); // Vai a capo dopo ogni riga   
		}
	}
	
	/*
	 * Menu con esercizi fatti
	 * metodi utilita inseriscili come case dell'esercizio principale
	 */

	public int menu() {
		
		System.out.println("[1]  Stampa Orizzontale");
		System.out.println("[2]  Totalizzatore2");
		System.out.println("[3]  CountVocali");
		System.out.println("[4]  IsPalindroma");
		System.out.println("[5]  Calcolatrice");
		System.out.println("[6]  Nuovo array");				
		System.out.println("[7]  Nominativo array"); //anche ascii come case
		System.out.println("[8]  Analizza Array"); 
		System.out.println("[9]  Analizza Matrice");	
		System.out.println("[10] Ordina ArrayStringhe");
		System.out.println("[11] Ordina MatriceMeteo");
		System.out.println("[0]  ESCI ");
		System.out.println();
		System.out.print("[?] Inserisci scelta: ");   
		
		int num =input.nextInt();
		input.nextLine();
		return num;
	}
}
