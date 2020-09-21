package util;

import java.io.*;
import org.apache.poi.xssf.usermodel.*;

public class ReadExcelUtil {
	public XSSFSheet ExcelWSheet;
	public XSSFWorkbook ExcelWBook;
	// 通过路径和sheet名定位Excel

	public ReadExcelUtil(String Path, String SheetName) {
		// 打开excel
		FileInputStream ExcelFile = null;
		try {
			ExcelFile = new FileInputStream(Path);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 定位sheet
		try {
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
	}

	// 返回当前excel下有多少行
	public int excel_get_rows() {
		try {
			return ExcelWSheet.getLastRowNum();
		} catch (Exception e) {

		}
		return 0;
	}

	// 根据行和列获取单元格的值（String）
	public String getCellDataasstring(int RowNum, int ColNum) {
		try {
			String CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getStringCellValue()
					.replaceAll("(\r\n|\r|\n|\n\r)", "");
			return CellData.trim();
		} catch (Exception e) {
			// System.out.println(e);
			return "Errors in Getting Cell Data";
		}
	}

	// 根据行和列获取单元格的值（number）
	public double getCellDataasnumber(int RowNum, int ColNum) {
		try {
			double CellData = ExcelWSheet.getRow(RowNum).getCell(ColNum).getNumericCellValue();
			return CellData;
		} catch (Exception e) {
			return 000.00;
		}
	}

	// 根据列名查 --对象专用
	public String[] getvalue(String LocatorName) {
		int num = excel_get_rows();
		String[] locatorMap = new String[2];
		for (int i = 1; i <= num; i++) {
			String Name = getCellDataasstring(i, 0);
			if (Name.equals(LocatorName)) {
				locatorMap[0] = getCellDataasstring(i, 1);
				locatorMap[1] = getCellDataasstring(i, 2);
				break;
			}
		}
		if (locatorMap != null || (locatorMap == null && locatorMap.length != 0)) {
			System.out.println("对象仓库：<" + Tools.getValue("UIlibrary") + ">：对象名称<" + LocatorName + ">，定位方式："
					+ locatorMap[1] + "，值：" + locatorMap[0]);
		}
		return locatorMap;
	}



}

