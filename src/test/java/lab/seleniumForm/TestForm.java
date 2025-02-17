package lab.seleniumForm;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

//1. manipolare tutti gli elementi del form. Tutti gli elementi sono obbligatori, tranne disabled e read only.
//2. Fai scelta in select e in datalist.
//3. skippa l'input (opzionalmente).
//4. Cambiare il check, cambiare il radio button.
//5. Fare scelta di colori. O vari rgb oppure (obbligatoria), oppure vari colore di partenza simulando il mouse in orizzontale e andare sul pallino e variarlo
//6. Scegli una data a partire dal mese prossimo dal calendario e NON a mano.
//7. Clicco e faccio drag and drop aumentando o diminuendo di due step.
//8. Clicchi Submit.
//9. Verifico con Junit: voglio inserire tre persone, verifica che il form viene ricevuto 3 volte.

class TestForm {
	private WebDriver driver;

	@BeforeEach
	public void setUp() {	
		driver = new ChromeDriver();		
	}

	@ParameterizedTest
	@CsvSource({	
		"primo inserimento, password1, Prova 1 inserimento testo, One, San Francisco, #2675C5, 1, 01/01/2025",
		"due inserimento, password2, Prova 2 inserimento testo, Two, Chicago, #FF0000, 4, 25/09/2025",
		"tre inserimento, password3, Prova 3 inserimento testo, Three, Seattle, #FFF700, 5, 01/10/2027"
	})
	public void formValidazioneParametrico(String text,String pass, String TArea, String selec, String citta, String colore, int valore, String datainserita) throws InterruptedException {

		driver.manage().window().maximize();
		driver.get("https://www.selenium.dev/selenium/web/web-form");

		WebElement textBox = driver.findElement(By.name("my-text"));
		textBox.sendKeys(text);
		Thread.sleep(500);

		WebElement passwordBox = driver.findElement(By.name("my-password"));
		passwordBox.sendKeys(pass);
		Thread.sleep(500);

		WebElement textArea = driver.findElement(By.name("my-textarea"));        	 	
		textArea.sendKeys(TArea);	
		Thread.sleep(500);

		WebElement select = driver.findElement(By.name("my-select"));	
		select.findElement(By.xpath("//option[. = '"+selec+"']")).click(); 
		Thread.sleep(500);

		WebElement dataList = driver.findElement(By.name("my-datalist"));	
		dataList.sendKeys(citta);
		Thread.sleep(500);

		WebElement fileInput = driver.findElement(By.name("my-file"));
		String filePath = "F:\\Universita\\Java app\\seleniumForm\\src\\test\\java\\File.txt"; 
		fileInput.sendKeys(filePath);
		Thread.sleep(500);

		driver.findElement(By.id("my-check-1")).click();
		driver.findElement(By.id("my-check-2")).click();
		Thread.sleep(500);

		driver.findElement(By.id("my-radio-2")).click();
		Thread.sleep(500);

		driver.findElement(By.name("my-colors")).click();
		driver.findElement(By.name("my-colors")).sendKeys(colore);
		Thread.sleep(500);

		WebElement range = driver.findElement(By.name("my-range"));
		Actions builder = new Actions(driver);
		int sliderWidth = range.getSize().width;						//ottengo la larghezza dello slider		
		int offset = (int) ((sliderWidth / 10) * valore);				//valore tra 0 e 10, portiamo il valore un intervallo da 0 a sliderWidth
		builder.clickAndHold(range).moveByOffset(offset, 0).release().perform();
		Thread.sleep(500);

		WebElement data = driver.findElement(By.name("my-date"));
		data.click();
		data.sendKeys(datainserita);

		Thread.sleep(1000);

		WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
		submitButton.click();

		WebElement message = driver.findElement(By.id("message"));
		assertEquals("Received!", message.getText());

	}

	@AfterEach
	public void tearDown() {
		if(driver!=null) {
			driver.quit();
		}
	}

}

//@Test
//public void formValidazione1() throws InterruptedException {
//driver.manage().window().maximize();
//driver.get("https://www.selenium.dev/selenium/web/web-form");
//
//WebElement textBox = driver.findElement(By.name("my-text"));
//textBox.sendKeys("Primo inserimento");
//Thread.sleep(1000);
//
//WebElement passwordBox = driver.findElement(By.name("my-password"));
//passwordBox.sendKeys("password1");
//Thread.sleep(1000);
//
//WebElement textArea = driver.findElement(By.name("my-textarea"));        	 	
//textArea.sendKeys("Prova 1 inserimento testo");	
//Thread.sleep(1000);
//
//WebElement select = driver.findElement(By.name("my-select"));	
//select.findElement(By.xpath("//option[. = 'One']")).click(); 
//Thread.sleep(1000);
//
//WebElement dataList = driver.findElement(By.name("my-datalist"));	
//dataList.sendKeys("San Francisco");
//Thread.sleep(1000);
//
//WebElement fileInput = driver.findElement(By.name("my-file"));
//String filePath = "F:\\Universita\\Java app\\seleniumForm\\src\\test\\java\\File.txt"; 
//fileInput.sendKeys(filePath);
//Thread.sleep(1000);
//
//driver.findElement(By.id("my-check-1")).click();
//driver.findElement(By.id("my-check-2")).click();
//Thread.sleep(1000);
//
//driver.findElement(By.id("my-radio-1"));
//driver.findElement(By.id("my-radio-2")).click();
//Thread.sleep(1000);
//
//driver.findElement(By.name("my-colors")).click();
//driver.findElement(By.name("my-colors")).sendKeys("#2675C5");
//Thread.sleep(1000);
//
//WebElement range = driver.findElement(By.name("my-range"));
//Actions builder = new Actions(driver);      
//int sliderWidth = range.getSize().width;
//int offset = (int) ((valore / 10.0) * sliderWidth);
//builder.clickAndHold(range).moveByOffset(offset, 0).release().perform();
//Thread.sleep(1000);
//
//WebElement data = driver.findElement(By.name("my-date"));
//data.click();
//data.findElement(By.xpath("//th[@class='next']")).click();    
//data.findElement(By.xpath("//td[contains(@class, 'day') and text()='15']")).click();	
//Thread.sleep(1000);
//
//WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
//submitButton.click();
//
//WebElement message = driver.findElement(By.id("message"));
//assertEquals("Received!", message.getText());		
//}