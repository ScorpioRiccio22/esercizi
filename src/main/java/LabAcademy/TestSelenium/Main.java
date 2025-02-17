package LabAcademy.TestSelenium;

import java.net.URISyntaxException;

import org.openqa.selenium.WebDriver;

import LabAcademy.TestSelenium.Configurazione.Browser;

public class Main 
{
    public static void main( String[] args ) throws URISyntaxException, InterruptedException
    {
        Configurazione configurazione = new Configurazione();
        WebDriver webDriver = configurazione.SeleniumTutorial(Browser.CHROME);
        
        String squadra = "Lecce";
        
        MetodiProbabiliFormazioni.GazzettaDelloSport(webDriver, squadra);
        //MetodiProbabiliFormazioni.CorriereDelloSport(webDriver, squadra);
        //MetodiProbabiliFormazioni.FantaCalcio(webDriver, squadra);
    }
}
