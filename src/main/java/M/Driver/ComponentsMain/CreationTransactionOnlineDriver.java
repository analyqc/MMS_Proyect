package M.Driver.ComponentsMain;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import M.Driver.ResourcesFlowMain.CreationTransactionOnline;
import M.Driver.ResourcesFlowMain.StartSession;
import M.Driver.Utility.ExcelR;
import M.Driver.Utility.CaptureScreen;

public class CreationTransactionOnlineDriver {
	//private String urlInicial = "";
	private CreationTransactionOnline creationTransactionOnline;
	private StartSession login;
	private String rutaCarpetaError="C:\\Users\\aquesquen\\eclipse-workspace\\Proyecto_APPMMS\\Error";
	private static String createtransactionO_hoja="CreateTransaccionOnline";
	
	
	@BeforeTest
	@Parameters({"navegador", "remoto","urlInicial"})
	public void inicioClase(String navegador, boolean remoto,String urlInicial)throws Exception  {
		//System.out.print("entro inicioClase="+navegador+remoto+urlInicial+"/n--------------------");
		
		login = new StartSession(navegador, urlInicial, remoto);
		//System.out.print("entro termino login="+login+"/n--------------------");
		
		creationTransactionOnline = new CreationTransactionOnline(navegador, login.getWebDriver());
	}
	
	@Parameters({"rutaArchivo"})
	@DataProvider(name = "datosEntrada")
    
	public static Object[][] datosPoblados(ITestContext context) {
		Object[][] datos = null;
		System.out.print("1/n");
			///System.out.print("Root:" +  rutaArchivo);
			String rutaArchivo= "C:\\Users\\aquesquen\\eclipse-workspace\\Proyecto_APPMMS\\Data\\CreateTransaction.xls";
			System.out.print(rutaArchivo+" "+createtransactionO_hoja +": /n");
			datos= ExcelR.ReadExcel(rutaArchivo,createtransactionO_hoja);
		//	System.out.print("ffffffff"+datos);

		return datos;
	}
	
	
	@Test(dataProvider = "datosEntrada")
	public void createTransactionO(String user,String password,String email,String OrderID,String amount,String expirationtime, String currency,String country,String valorEsperado) throws Exception {
		try { 
			login.StartSessionLogin(user,password);
			//System.out.print("1");
			String valorObtenido = creationTransactionOnline.CreateTransaction(email, OrderID, amount,expirationtime,currency,country);
			//System.out.print("2");
			Assert.assertEquals(valorEsperado , valorObtenido);
			//System.out.print("3");
			creationTransactionOnline.ReturnToCreateTransaction();
			//System.out.print("4");
		}
		catch (AssertionError e) {
			System.out.print("CaptureScreenError validation");
			CaptureScreen.caputarPantallarError(rutaCarpetaError, e.getMessage(), creationTransactionOnline.getWebDriver());
			Assert.fail(e.getMessage(),e);
		}
				
		catch (Exception e) {
			CaptureScreen.caputarPantallarError(rutaCarpetaError, e.getMessage(), creationTransactionOnline.getWebDriver());
			System.out.print("CaptureScreenError Error Flow");
			Assert.fail();
		}
		
		
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		creationTransactionOnline.ClosePage();
	}
}
