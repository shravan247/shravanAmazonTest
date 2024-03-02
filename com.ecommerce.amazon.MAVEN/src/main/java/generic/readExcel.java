package generic;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class readExcel {

	@DataProvider(name="signUp")
	public static Object[][] excelData() throws Exception {
		FileInputStream fis = new FileInputStream("C:\\Users\\shrad\\eclipse-workspace\\com.ecommerce.amazon.MAVEN\\userdata\\amazonData.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet s = book.getSheet("signUp");
		int rowsize = s.getPhysicalNumberOfRows();
		int columnsize = s.getRow(0).getPhysicalNumberOfCells();
		Object[][] d = new Object[rowsize-1][columnsize];
		DataFormatter form =new DataFormatter();
		for (int i = 0; i < rowsize-1; i++) {
			for (int j = 0; j < columnsize; j++) {
				d[i][j] = form.formatCellValue(s.getRow(i+1).getCell(j));
			}
		}
		return d;
	}
	
	@DataProvider(name="signIn")
	public static Object[][] excelData1() throws Exception {
		FileInputStream fis = new FileInputStream("C:\\Users\\shrad\\eclipse-workspace\\com.ecommerce.amazon.MAVEN\\userdata\\amazonData.xlsx");
		Workbook book = WorkbookFactory.create(fis);
		Sheet s = book.getSheet("signIn");
		int rowsize = s.getPhysicalNumberOfRows();
		int columnsize = s.getRow(0).getPhysicalNumberOfCells();
		Object[][] d = new Object[rowsize-1][columnsize];
		DataFormatter form =new DataFormatter();
		for (int i = 0; i < rowsize-1; i++) {
			for (int j = 0; j < columnsize; j++) {
				d[i][j] = form.formatCellValue(s.getRow(i+1).getCell(j));
			}
		}
		return d;
	}
}
