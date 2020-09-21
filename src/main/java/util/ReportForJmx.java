package util;

import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public  class ReportForJmx {

	 HSSFWorkbook mWorkbook = new HSSFWorkbook();
	public  void ReportSet(String sheetName,String title,String[] personNum,String[] value) {
		HSSFSheet mSheet = mWorkbook.createSheet(sheetName);
		// 创建Excel标题行，第一行。
		HSSFRow headRow = mSheet.createRow(0);
			headRow.createCell(0).setCellValue("并发数");
			headRow.createCell(1).setCellValue(title);
		
		HSSFRow dataRow = mSheet.createRow(mSheet.getLastRowNum() + 1);

		int valuenum=value.length;
		for(int i=0;i<valuenum;i++) {
			dataRow.createCell(0).setCellValue(personNum[i]);
			dataRow.createCell(1).setCellValue(value[i]);
			dataRow = mSheet.createRow(mSheet.getLastRowNum() + 1);
		}
	}

	public void report(String[] title,String[] personNum,String[]...value) {
		for(int i=0;i<title.length;i++) {
			this.ReportSet(title[i], title[i], personNum,value[i]);
		}
		
		File xlsFile = new File(Tools.getValue("报告目录")+"report.xls");
		System.out.println("正在生成测试报告，请稍后...");
		
		try {
			mWorkbook.write(xlsFile);// 或者以流的形式写入文件 mWorkbook.write(new FileOutputStream(xlsFile));
			mWorkbook.close();
			System.out.print("打印进度：");
			for (int i = 0; i <= 10; i++) {
				System.out.print("█");
				Thread.sleep(Tools.randomNumber(0, 1000));
			}
			System.out.println("100%");
			System.err.println("打印完毕，测试报告目录："+Tools.getValue("报告目录")+"report.xls");
			System.out.println("压测完成");
		} catch (Exception e) {
			System.err.println("测试报告输出失败！！！");
		}


	}

}
