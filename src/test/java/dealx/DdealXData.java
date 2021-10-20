package dealx;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;

public class DdealXData {

    @DataProvider(name = "SearchItems" )
    public Object[][] searchItemData() throws Exception{

        try {
            Object[][] arrObj = getExcelData("src/test/testdata/DealXData.xlsx","Items");
            return arrObj;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DataProvider(name = "PageNavigationData" )
    public Object[][] pageNavigationData() throws Exception{

        try {
            Object[][] arrObj = getExcelData("src/test/testdata/DealXData.xlsx","PageNavigation");
            return arrObj;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DataProvider(name = "login" )
    public Object[][] loginData() throws Exception{

        try {
            Object[][] arrObj = getExcelData("src/test/testdata/DealXData.xlsx","Login");
            return arrObj;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[][] getExcelData(String path, String sheetName) throws Exception {
        String [][] data = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh = wb.getSheet(sheetName);
            XSSFRow row = sh.getRow(0);
            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();
            XSSFCell cell;
            data = new String[noOfRows-1][noOfCols];
            for(int i =1; i<noOfRows;i++){
                for(int j=0;j<noOfCols;j++){
                    row = sh.getRow(i);
                    cell= row.getCell(j);
                    if (cell != null) {
                        data[i-1][j] = cell.getStringCellValue();
                    } else {
                        data[i-1][j] = "";
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return  data;
    }

}
