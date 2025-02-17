package lab.RicercaBooking;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//1. https://www.booking.com (accetta cookie, popup e roba simile)
//2. manipola quel piccolo form, scegliendo destinazione. Il punto è fare una ricerca.
//3. Scegliere tre località (Sicilia, Calabria, Sardegna). Digito "Sicilia" come primo test e clicco la prima scelta.
//4. Passo al calendario. Specifico le date (uguale per tutte e tre le località), 02-16 agosto 2025.
//5. Numero di persone. Apro il menu. Di default offre due adulti, scegliere i bambini (mettine 2 e setta le età). Non toccare le camere. Clicchi fatto.
//6. Seleziona Cerca.
//7. Attenzione a pop-up. Nella pagina in cui mi trovo devo applicare filtri sul menu a sx. Fronte spiaggia (checkbox). Filtra per il prezzo 60-300€ a notte. Checka Colazione inclusa e piscina.
//8. Dopo aver applicato filtri, al centro puoi sortare risultati affianco ai filtri. Sorta per "recensioni migliori e prezzo più basso".
//9. Verificare tra gli hotel proposti se esistono + strutture a in un posto x(Catania). Se sì, stampa nome seconda struttura, prezzo totale del soggiorno. Se esiste solo una struttura a Catania, stampa nome e prezzo del primo.
//Per tutte e 3 ricerche non variare range temporale. Puoi variare età bambini (sempre due). I filtri rimangono sempre gli stessi, cambia la città da associare a regione.
//10. Digiti Calabria e modifichi età e cerchi. I filtri vanno ricliccati (fronte spiaggia, piscina, colazione inclusa, max 300€ a notte), sorti per recensione migliore e prezzo più basso.
//11. Città "Crotone". Verifica sempre se ci sono almeno 2 strutture. Se sì, stampi nome e prezzo della seconda. Se ce ne sta una sola, stampi titolo e prezzo della prima.
//Per Sardegna sempre stessi filtri, tranne prezzo (500€ a notte) (magari modifica età bambini a tua scelta) e stesso sorting. Castelsardo. Ha 4 strutture. Piglia sempre secondo e stampa nome hotel e prezzo.
//12. Sempre su Sardegna, ultimo test, selezionando una città che non esiste nei checkbox (tipo nei checkbox non c'è Cagliari, tu metti Cagliari e il test non va. In quel caso, stampi messaggio che non ci sono strutture per quella città).
//per vedere piu risultati fare lo scrool

class TestBooking {

	private WebDriver driver;
	private ChromeOptions options;

	@BeforeEach
	public void setUp() {	
		options = new ChromeOptions();
		options.addArguments("--disable-notifications"); 
		options.addArguments("--start-fullscreen"); 
		driver = new ChromeDriver(options);
	}

	@AfterEach
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}

	@ParameterizedTest
	@CsvSource({	
		"Sicilia ,2 agosto 2025, 16 agosto 2025, 3 anni, 7 anni, 300, Catania",
		"Calabria ,2 agosto 2025, 16 agosto 2025, 5 anni, 9 anni, 300, Crotone",
		"Sardegna ,2 agosto 2025, 16 agosto 2025, 2 anni, 4 anni, 500, Castelsardo",
		"Sardegna ,2 agosto 2025, 16 agosto 2025, 5 anni, 6 anni, 300, Cagliari"
	})
	public void RicercaLocalita(String regione,String check_in,String check_out, String eta1,String eta2,double spesaAttesa,String citta) throws InterruptedException {		
		//driver.manage().window().setSize(new Dimension(1920, 1080));
		driver.get("https://www.booking.com");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler"))).click();	//accetta cookie

		Thread.sleep(500);
		WebElement localitaCercata = wait.until(ExpectedConditions.elementToBeClickable(By.id(":rh:")));
		localitaCercata.sendKeys(regione);																				//inserisci Localita
		Thread.sleep(500);

		WebElement conferma = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[@id='autocomplete-result-0']/div"))); 	//conferma scelta
		try {
			conferma.click(); 																	// Questo potrebbe lanciare StaleElementReferenceException
		} catch (StaleElementReferenceException e) {	   										// Ritrova l'elemento 
			conferma = driver.findElement(By.xpath("//li[@id='autocomplete-result-0']/div")); 	// Prova di nuovo
			conferma.click(); 		 												 
		}

		for (int i = 0; i < 7; i++) {
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='mese successivo']"))).click();		//seleziona mese agosto
		}

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label='"+check_in+"']"))).click(); 		//Data check-in
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@aria-label='"+check_out+"']"))).click();		//Data check-out

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@data-testid='occupancy-config']"))).click();	//Selezione adulti-bambini
		Thread.sleep(500);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'Bambini')]/ancestor::div/div/button[2]"))).click();  //Aumenta numero di bambini
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(text(), 'Bambini')]/ancestor::div/div/button[2]"))).click();	
		Thread.sleep(500);

		WebElement eta1Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='kids-ages']/div[1]/div/select"))); //seleziona eta primo bambino
		eta1Element.click();
		eta1Element.findElement(By.xpath("./option[. = '"+eta1+"']")).click();
		eta1Element.click();
		Thread.sleep(500);

		WebElement eta2Element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-testid='kids-ages']/div[2]/div/select"))); //seleziona eta secondo bambino
		eta2Element.click();
		eta2Element.findElement(By.xpath("./option[. = '"+eta2+"']")).click();
		eta2Element.click();
		Thread.sleep(500);

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Fatto')]"))).click();		//accetta 
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Cerca')]"))).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@aria-label='Finestra in cui ti offriamo sconti dal 10% in su se accedi al tuo account su Booking.com']//button"))).click(); 		//chiudi popup

		Thread.sleep(2000);	
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-filters-group='mealplan']//div[@data-testid='filters-group-label-content' and contains(text(), 'Colazione inclusa')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-filters-group='ht_beach']//div[@data-testid='filters-group-label-content' and contains(text(), 'Fronte spiaggia')]"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-filters-group='hotelfacility']//button"))).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-filters-group='hotelfacility']//div[@data-testid='filters-group-label-content' and contains(text(), 'Piscina')]"))).click();

		Thread.sleep(3000);		
		WebElement rangeMin = driver.findElement(By.xpath("//div[@data-testid='filters-group-slider']/div[2]//input[1]"));
		WebElement rangeMax = driver.findElement(By.xpath("//div[@data-testid='filters-group-slider']/div[2]//input[2]"));			//seleziona range per ricavarne valori nel secondo input
		WebElement bollino =  driver.findElement(By.xpath("//input[@type='range' and @aria-label='max.']/following::div"));			//seleziona cursore da muovere
		Thread.sleep(1000);	

		double min = Double.parseDouble(rangeMin.getDomAttribute("value"));
		double max = Double.parseDouble(rangeMax.getDomAttribute("value"));

		WebElement sliderContainer = rangeMax.findElement(By.xpath("..")); 		// Trova l'elemento genitore
		double width = sliderContainer.getSize().width;							//212 width

		double totalRange = max - min;
		int offset = (int ) ((spesaAttesa - max)/ (double) totalRange * width);

//		double coeff = ((max-min-100)/50)*0.25;		//50 euro corrisponde a 0.25 pixel
//		double offset = (spesaAttesa - max)/coeff;
		
		Actions actions = new Actions(driver);
		actions.clickAndHold(bollino).moveByOffset((int) offset , 0).release().perform();

		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@data-testid='sorters-dropdown-trigger']")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//button[@data-id='review_score_and_price']")).click();
		Thread.sleep(3000);

		//List<WebElement> e = driver.findElements(By.xpath("//div[@data-testid='property-card-container']/div[2]/div"));  //path per prendere tutte le strutture
		List<WebElement> e = driver.findElements(By.xpath("//div[@data-testid='property-card-container' and .//div[@class='c624d7469d a0e60936ad a3214e5942' and .//span[@data-testid='address' and contains(text(), '"+citta+"')]]]"));

		stampaStrutture(e);					//Stampa tutte le strutture di una determinata localita
		stampaSecondaLocalita(e,citta);		//Stampa la seconda località
		System.out.println();

	}

	public static void stampaStrutture(List<WebElement> elementi) {
		System.out.println("[STAMPA STRUTTURE LOCALITA]");
		for (WebElement elemento : elementi) {
			stampaDettagliStruttura(elemento);
		}
	}

	public static void stampaSecondaLocalita(List<WebElement> elements,String citta) {
		if (elements == null || elements.isEmpty()) {
			System.out.println("Nella località " + citta + " non ci sono strutture");
			return;
		}
		
		if(elements.size()==1){
			System.out.println("[STAMPA PRIMA STRUTTURA]");
			WebElement primoElemento = elements.get(0); 
			stampaDettagliStruttura(primoElemento);
		}else{
			System.out.println("[STAMPA SECONDA STRUTTURA]");
			WebElement secondoElemento = elements.get(1); 
			stampaDettagliStruttura(secondoElemento);
		}
		
	}

	private static void stampaDettagliStruttura(WebElement elemento) {
		WebElement nomeElement = elemento.findElement(By.xpath(".//h3/a/div[@data-testid='title']"));
		WebElement cittaElement = elemento.findElement(By.xpath(".//span[@data-testid='address']"));
		WebElement prezzoElement = elemento.findElement(By.xpath(".//span[@data-testid='price-and-discounted-price']"));

		String output = String.format("STRUTTURA: %-40s | CITTA: %-30s | PREZZO: %s%n", 
				nomeElement.getText(), 
				cittaElement.getText(), 
				prezzoElement.getText());
		System.out.print(output);
	}

}