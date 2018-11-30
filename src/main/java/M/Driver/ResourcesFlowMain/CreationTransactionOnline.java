package M.Driver.ResourcesFlowMain;

import org.openqa.selenium.support.ui.Select;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import M.Driver.General.*;


public class CreationTransactionOnline {
	private By v_valuewaited=By.id("ctl00_cphMainContent_DirectProcessRequestOnline_lblNoteOnlineSuccess");
	private By u_direct=By.linkText("Direct");
	private By u_directOnline=By.linkText("Direct OnLine Payment");
	private By v_email = By.id("ctl00_cphMainContent_DirectProcessStep1_txtEmail");
	private By v_orderID = By.id("ctl00_cphMainContent_DirectProcessStep1_txtMerchantSalesID");
	private By v_amount = By.id("ctl00_cphMainContent_DirectProcessStep1_txtAmount");
	private By v_expirationtime = By.id("ctl00_cphMainContent_DirectProcessStep1_SelExpirationTime");
	private By v_country = By.id("ctl00_cphMainContent_DirectProcessStep1_ddlCountry_Input");
	private By v_currency = By.id("ctl00_cphMainContent_DirectProcessStep1_ddlCurrencies");
	private By b_buttonCreateTransactionOnline = By.id("ctl00_cphMainContent_DirectProcessStep1_btnContinue");
	private WebDriver webDriver ;

	private By b_buttonNewTransactionOnline=By.id("ctl00_cphMainContent_DirectProcessRequestOnline_btnNewPayment");
	

	public CreationTransactionOnline(String navegador, boolean remoto) {
		//System.out.print("entro CreateTransactionOnline="+navegador+remoto+"/n");
		DriverMMSGeneral driverMMS= new DriverMMSGeneral();
		webDriver = driverMMS.inicializarDriver1(navegador, remoto);
	}
	public CreationTransactionOnline(String navegador, WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	public String CreateTransaction(String email, String orderID,String amount,String expirationtime,String currency, String country) throws Exception{
	
		Thread.sleep(3000);
		webDriver.findElement(u_direct).click();
		webDriver.findElement(u_directOnline).click();
		/* values of the form*/
		webDriver.findElement(v_email).clear();
		webDriver.findElement(v_email).sendKeys(email);
		webDriver.findElement(v_orderID).clear();
		webDriver.findElement(v_orderID).sendKeys(orderID);
		
		Select selectE=new Select(webDriver.findElement(v_expirationtime));
				selectE.selectByVisibleText(expirationtime);
				webDriver.findElement(v_country).click();	
				Thread.sleep(1000);
				WebElement countryUL= webDriver.findElement(By.xpath("//div[@id='ctl00_cphMainContent_DirectProcessStep1_ddlCountry_DropDown']/div/ul"));
			    List<WebElement> countriesList=countryUL.findElements(By.tagName("li"));
			  //  System.out.println(countriesList);
			    int i=1;
			    for (WebElement lis : countriesList) {
			  //   System.out.println(lis.getText());
			    //  System.out.println(i);
			    if (lis.getText().equals(country)) {
			    	Thread.sleep(1000);
			    	webDriver.findElement(By.xpath("//div[@id='ctl00_cphMainContent_DirectProcessStep1_ddlCountry_DropDown']/div/ul/li["+i+"]")).click();
			    	//System.out.println("xpath=//div[@id='ctl00_cphMainContent_DirectProcessStep1_ddlCountry_DropDown']/div/ul/li[3]");
			    //	System.out.println(webDriver.findElement(By.xpath("//div[@id='ctl00_cphMainContent_DirectProcessStep1_ddlCountry_DropDown']/div/ul/li["+i+"]")));
			    	 	 	break;
			       }
			   i= i+1;
			    }
			   
		Thread.sleep(2000);
		webDriver.findElement(v_amount).clear();
		webDriver.findElement(v_amount).sendKeys(amount);
		Select selectC=new Select(webDriver.findElement(v_currency));
		selectC.selectByValue(currency);
		webDriver.findElement(b_buttonCreateTransactionOnline).click();
		Thread.sleep(3000);
		return webDriver.findElement(v_valuewaited).getText();
	}

	public void ReturnToCreateTransaction()  throws Exception{
	webDriver.findElement(b_buttonNewTransactionOnline).click();
		Thread.sleep(3000);
	
		//TODO
	}
	public void ClosePage() {
		// TODO
		DriverMMSGeneral.CloseAllPage(webDriver);
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
}
