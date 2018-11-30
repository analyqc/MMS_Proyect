package M.Driver.General;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverMMSGeneral {
	private WebDriver webDriver;
	public   WebDriver inicializarDriver1(String navegador, boolean remoto) {
	
		remoto=true;
		//System.out.print("entro DriverMMSGeneral.inicializarDriver="+navegador+remoto+"/n");
		System.out.println("Ejecucion Remota: " + (remoto ? "SI" : "NO"));
		System.out.println(navegador);
		switch (navegador) {
		case "firefox":
		
				 
			System.setProperty("webdriver.gecko.driver", "C:\\Programas\\geckodriver-v0.14.0-win32\\geckodriver.exe");
			webDriver = new FirefoxDriver();
			
			break;
		case "chrome":
			
			if(remoto)	 {
				 try{
				URL  server= new URL("http://localhost:4444/wd/hub");
				
				DesiredCapabilities  capacidades=DesiredCapabilities.chrome();
				webDriver = new RemoteWebDriver(server,capacidades);
							
						 }
							 catch(Exception e)
							 {
								e.printStackTrace(); 
							 }
						 }	 
							 
			
			else
				{
			
				webDriver = new ChromeDriver();
			}
			
			break;
			
			
			}
		
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return webDriver;
	}

	public static void CloseAllPage(WebDriver webDriver) {
		if (webDriver != null) {
			webDriver.quit();
		}
	}
}
