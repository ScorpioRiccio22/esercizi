//
// Andare ad analizzare sia xPath che cssSelector mixati
// probabili formazioni
// l’info da tirar fuori andrebbe gestita o da console o passandola in modo statico per cercarla
// i siti web sono:
// Gazzetta.it: crea sua probabile formazione
// Corriere dello sport.it: crea sua probabile formazione qui menu, calcio, probabili formazioni(seriaA)
// fantacalcio.it: crea sua probabile clicca formazione probabili formazioni
// Vanno selezionati solo gli 11 titolari
// salvala, stampala mettile a paragone tra loro
// deve essere eseguibile per ogni nome di squadra
// 

package LabAcademy.TestSelenium;

import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MetodiProbabiliFormazioni 
{
	public static void GazzettaDelloSport(WebDriver webDriver, String squadra) throws URISyntaxException, InterruptedException 
	{
		webDriver.get("https://www.gazzetta.it/");  
		WebDriverWait waitDriver = new WebDriverWait(webDriver, Duration.ofSeconds(5));
		waitDriver.until(ExpectedConditions.elementToBeClickable(
			By.id("privacy-cp-wall-accept")
		)).click();
		//webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	
		Thread.sleep(30000);
		waitDriver.until(ExpectedConditions.visibilityOfElementLocated(
			By.cssSelector("a.menu-item.has-cursor-pointer[data-name='probabili-formazioni']")
		)).click();
		
		try 
		{
			//Per semplificazione dividiamo in pezzi il codiceTrova il div del match
			//Div del match
			WebElement matchDiv = waitDriver.until(ExpectedConditions.visibilityOfElementLocated(
			    By.xpath("//div[contains(@id, 'match-') and .//a[contains(text(), '" + squadra + "') and contains(@class, 'details-team')]]")
			));
			
			//Stringa indicante casa o fuoricasa
			WebElement squadraLink = matchDiv.findElement(
				By.xpath(".//a[contains(text(), '" + squadra + "') and contains(@class, 'details-team')]")
			);
			String casaOFuori = squadraLink.findElement(
				By.xpath("./ancestor::div[contains(@class, 'is--home') or contains(@class, 'is--away')]")
			).getAttribute("class").contains("is--home") ? "is--home" : "is--away";
			
			//Lista dei giocatori
			List<WebElement> giocatori = matchDiv.findElements(
				By.xpath(".//div[contains(@class, 'lineup-team " +casaOFuori+ "')]//li//span[contains(@class, 'lineup-team__name')]")
			);
			
			System.out.println("Gli 11 titolari di 'La Gazzetta dello Sport' della squadra " +squadra+ ", che gioca " +(casaOFuori.equals("is--home")?"in casa":"fuori casa")+ ", sono:");
			for (WebElement giocatore : giocatori) 
			{
				System.out.println("- " +giocatore.getText());
			}
		} 
		catch (Exception e) 
		{
			System.out.println("La squadra " +squadra+ " non è stata trovata nella pagina 'Probabili formazioni'.\nRecuperiamo la formazione utilizzata durante la partita.\n");
			
			waitDriver.until(ExpectedConditions.elementToBeClickable(By.id("hamburger"))).click();
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//li[contains(@class, 'fxr-between-center menu-item') and contains(@data-target, 'sport_calcio_calcio-italiano')]")
			)).click();
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//li[contains(@class, 'fxr-between-center menu-item') and contains(@data-name, 'calcio_serie-a')]/a[contains(@title, 'Serie A')]")
			)).click();
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//ul[contains(@class, 'tabs is-clipped')]/li[contains(@class, 'list-item')]/a[contains(@href, 'https://www.gazzetta.it/calcio/serie-a/calendario-risultati/')]")
			)).click();
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@id, 'root')]/div[contains(@class, 'sc-bdVaJa bKgzjF')]//div[contains(@class, 'sc-hMqMXs kPzOxg') and .//div[contains(text(), '" +squadra+ "')]]")
			)).click();
			
			//Lista delle squadre per gestire "in casa" o "fuori casa"
			List<WebElement> squadre = webDriver.findElements(
				By.xpath("//div[contains(@id,'root')]//div[contains(@class, 'sc-cTjmhe hnjUKN')]//div[contains(@class, 'sc-fEUNkw')]//div[contains(@class, 'sc-dREXXX')]/div")
			);
			String casaOFuori = squadre.get(0).getText().equals(squadra)?"in casa":"fuori casa";
			int num = casaOFuori.equals("in casa")?1:2;
			
			//Lista dei giocatori
			List<WebElement> giocatori = webDriver.findElements(
				By.xpath("//div[contains(@id,'root')]//div[contains(text(),'In campo')]/following-sibling::div[1]/div[" +num+ "]/div/div/a")
			);//[contains(@class,'sc-fnwBNb lhUVAp')]
			
			System.out.println("Gli 11 titolari di 'FantaCalcio' della squadra " +squadra+ ", che gioca " +casaOFuori+ ", sono:");
			for (WebElement giocatore : giocatori) 
			{
				System.out.println("- " +giocatore.getText());
			}
		}
	}
	
	public static void CorriereDelloSport(WebDriver webDriver, String squadra) throws URISyntaxException, InterruptedException 
	{
		webDriver.get("https://www.corrieredellosport.it/");  
		WebDriverWait waitDriver = new WebDriverWait(webDriver, Duration.ofSeconds(30));
		waitDriver.until(ExpectedConditions.elementToBeClickable(
			By.id("didomi-notice-agree-button")
		)).click();
		waitDriver.until(ExpectedConditions.elementToBeClickable(
			By.cssSelector("button.Hamburger_container__ZhFib[aria-label='Menu']")
		)).click();
		waitDriver.until(ExpectedConditions.elementToBeClickable(
			By.xpath("//button[contains(@class, 'Menu_menuItem__VDSs5') and contains(text(), 'Calcio')]")
		)).click();
		waitDriver.until(ExpectedConditions.elementToBeClickable(
			By.cssSelector("a.Menu_menuItem__VDSs5[href*='probabili-formazioni/calcio/serie-a']")
		)).click();
		
		try 
		{
			//Per semplificazione dividiamo in pezzi il codiceTrova il div del match
			//Div del match
			WebElement matchDiv = waitDriver.until(ExpectedConditions.visibilityOfElementLocated(
			    By.xpath("//div[contains(@data-item, 'item-') and .//div[contains(@class, 'ProbabiliFormazioni_teamName__DmOc1') and contains(text(), '" +squadra+ "')]]")
			));
			
			//Stringa indicante casa o fuoricasa
			WebElement squadraLink = matchDiv.findElement(By.xpath(".//div[contains(@class, 'ProbabiliFormazioni_teamName__DmOc1') and contains(text(), '" +squadra+ "')]"));
			String casaOFuori = squadraLink.findElement(
				By.xpath(".//ancestor::div[contains(@class, 'ProbabiliFormazioni_away') or contains(@class, 'ProbabiliFormazioni_home')]")
			).getAttribute("class").contains("ProbabiliFormazioni_away") ? "away" : "home";
			
			//Lista dei giocatori
			List<WebElement> giocatori = matchDiv.findElements(
				By.xpath(".//div[contains(@class, 'ProbabiliFormazioni_playersGrid__c9fAv')]//span[contains(@class, 'ProbabiliFormazioni_" +casaOFuori+ "Player')]")
			);
			
			System.out.println("Gli 11 titolari di 'Il Corriere dello Sport' della squadra " +squadra+ ", che gioca " +(casaOFuori.equals("home")?"in casa":"fuori casa")+ ", sono:");
			for (WebElement giocatore : giocatori) 
			{
				System.out.println("- " +giocatore.getText());
			}
		} 
		catch (Exception e) 
		{
			System.out.println("La squadra " +squadra+ " non è stata trovata nella pagina 'Probabili formazioni'.\nRecuperiamo la formazione utilizzata durante la partita.\n");
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("button.Hamburger_container__ZhFib[aria-label='Menu']")
			)).click();
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(@class, 'Menu_menuItem__VDSs5') and contains(text(), 'Calcio')]")
			)).click();
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(@class, 'Menu_menuItem__VDSs5') and contains(text(), 'Serie A')]")
			)).click();
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//a[contains(@class, 'Menu_menuItem__VDSs5') and contains(text(), 'Calendario e Risultati')]")
			)).click();
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//div[contains(@class,'swiper-slide-active')] //a[contains(@class, 'ResultsRow_row__Is4Wf') and ./div[contains(text(),'" +squadra+ "')]]")
			)).click();
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(@class, 'Button_button__r_ga_') and contains(text(), 'Tabellino')]")
			)).click();
			Thread.sleep(6000);
			
			//Stringa indicante casa o fuoricasa
			String casaOFuori = webDriver.findElement(
				By.xpath("//table[contains(@class,'Opta-SideScroll')] //table[contains(@class,'Opta-') and .//span[contains(text(),'" +squadra+ "')]]")
			).getAttribute("class").contains("Opta-Away")?"Away":"Home";
			
			//Lista dei giocatori
			List<WebElement> listGiocatori = webDriver.findElements(
				By.xpath("//table[contains(@class,'Opta-SideScroll')]//table[contains(@class,'Opta-"+casaOFuori+"')]//td[contains(@class,'Opta-Name')]")
			);	
			
			System.out.println("Gli 11 titolari di 'FantaCalcio' della squadra " +squadra+ ", che gioca " +(casaOFuori.equals("Home")?"in casa":"fuori casa")+ ", sono:");
			for (int i = 0; i < 11; i++) 
			{
			    System.out.println("- " +listGiocatori.get(i).getText());
			}
		}
	}
	
	public static void FantaCalcio(WebDriver webDriver, String squadra) throws URISyntaxException, InterruptedException 
	{
			webDriver.manage().window().maximize();
			webDriver.get("https://www.fantacalcio.it/");  
			WebDriverWait waitDriver = new WebDriverWait(webDriver, Duration.ofSeconds(5));
			//webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));	
			//Thread.sleep(30000);
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[contains(@mode, 'primary') and contains(@class, 'css-k8o10q') and ./span[contains(text(), 'ACCETTO')]]")
			)).click();
			//waitDriver.until(ExpectedConditions.elementToBeClickable(By.id("pushengage-opt-in-6-close"))).click();
			waitDriver.until(ExpectedConditions.elementToBeClickable(
				By.cssSelector("nav.secondary-nav.dropdown-nav.dark > ul > li > a[href*='/probabili-formazioni-serie-a']")
			)).click();
			
			//Per semplificazione dividiamo in pezzi il codiceTrova il div del match
			//Li del match
			WebElement matchLi = waitDriver.until(ExpectedConditions.visibilityOfElementLocated(
			    By.xpath("//li[contains(@id, 'match-') and .//label[contains(@itemprop, 'Team') and .//meta[contains(@itemprop, 'name') and contains(@content, '" +squadra+ "')]]]")
			));
			
			//Stringa indicante casa o fuoricasa
			String casaOFuori = matchLi.findElement(
				By.xpath(".//label[contains(@itemprop, 'Team') and .//meta[contains(@itemprop, 'name') and contains(@content, '" +squadra+ "')]]")
			).getAttribute("itemprop").contains("homeTeam") ? "home" : "away";
			
			//Div del match
			WebElement matchDiv = waitDriver.until(ExpectedConditions.visibilityOfElementLocated(
			    By.xpath("//div[contains(@class, 'card team-card dark col mt-4') and .//h3[contains(@class, 'team-name') and contains(text(), '" +squadra+ "')]]")
			));
			
			//Lista dei giocatori
			List<WebElement> giocatori = matchDiv.findElements(
				By.xpath(".//ul[contains(@class, 'player-list starters')]/li/a/span")
			);
			
			System.out.println("Gli 11 titolari di 'FantaCalcio' della squadra " +squadra+ ", che gioca " +(casaOFuori.equals("home")?"in casa":"fuori casa")+ ", sono:");
			for (WebElement giocatore : giocatori) 
			{
				System.out.println("- " +giocatore.getText());
			}
	}
}
