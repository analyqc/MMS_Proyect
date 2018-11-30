package M.Driver.ResourcesFlowMain;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import M.Driver.General.DriverMMSGeneral;


public class StartSession {
	private By v_user = By.id("ctl00_cphMainContent_logSession_UserName");
	private By v_key = By.id("ctl00_cphMainContent_logSession_Password");
	private By b_buttonStartSession = By.id("ctl00_cphMainContent_logSession_LoginButton");
	private String u_urInitial;
	private WebDriver webDriver;
	DriverMMSGeneral driverMMS= new DriverMMSGeneral();
	
	public StartSession(String navegador, String u_urInitial, boolean remoto) {
				
		//System.out.print("entro Login="+u_urInitial+remoto+navegador+"/n------------------");
		webDriver=driverMMS.inicializarDriver1(navegador, remoto);
		this.u_urInitial = u_urInitial;
	}
	
	public void StartSessionLogin(String user, String key) throws Exception{
		//TODO
		webDriver.get(u_urInitial);
		webDriver.findElement(v_user).clear();
		webDriver.findElement(v_user).sendKeys(user);
		webDriver.findElement(v_key).clear();
		webDriver.findElement(v_key).sendKeys(key);
		
		webDriver.findElement(b_buttonStartSession).click();
		Thread.sleep(3000);
	}
	
	public void ClosePage(){
		//TODO
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
}
