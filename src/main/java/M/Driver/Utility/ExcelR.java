package M.Driver.Utility;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
//import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelR {
	public static String[][] ReadExcel(String rutaArchivo,String nombrehoja) {
		String[][] lista = null;
		int i = 0;
		try {
			FileInputStream archivo=new 	FileInputStream(new File (rutaArchivo));
			
			// leer la ruta del archivos
			HSSFWorkbook archivoExcel = new  HSSFWorkbook(archivo);
			// indica la hoJa
			HSSFSheet hojaExcel = archivoExcel.getSheet(nombrehoja);
			// obtener filas
			Iterator<Row>  filas =hojaExcel.iterator();
			filas.next();
			
			// Crear el arreglo con el tama�o de las filas
			lista= new String[hojaExcel.getLastRowNum()][];
			
			// recorrer cada una de las fiLAS
			while (filas.hasNext())
			{
				Row filaActual = filas.next();
				Iterator<Cell>  celdas =filaActual.cellIterator();
				lista[i]= new String[filaActual.getLastCellNum()];
				int  j=0;
				while (celdas.hasNext())
				{
					
					Cell celda=celdas.next();
					lista[i][j]= celda.getStringCellValue();
					j++;
				}
				i++;
				
			}
			
		archivoExcel.close();
		archivo.close();
		
	}	
		catch (Exception e) {
			e.printStackTrace();
		} 
		return lista;
	
}
}
