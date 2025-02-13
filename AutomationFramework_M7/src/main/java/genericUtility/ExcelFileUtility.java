package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	/**
	 * This method is used to read data from excel file provided key
	 * @param sheetname
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	
	public String toReadDataFromExcel(String sheetname,int row,int cell) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(IconstantUtility.excelFileUtility);
		Workbook wb = WorkbookFactory.create(file);
		String value = wb.getSheet(sheetname).getRow(row).getCell(cell).toString();
		return value;
		
	}

}
