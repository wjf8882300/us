package com.runxsports.provider.cs.cms.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelExportUtils {
	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param title  表格标题名
	 * @param headers 列名称
	 * @param values 字段名称
	 * @param dataset  数据集合
	 * @param out 与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 */
	public static <T> void exportExcelData(String title, Object[] headers, String[] values, Collection<T> dataset, HttpServletResponse response) {	
		 //excel文件名
	    String fileName = title+System.currentTimeMillis()+".xlsx";
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]+"");
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			for (int i = 0; i < values.length; i++) {
				HSSFCell cell = row.createCell(i);
				String filedName = values[i];
				String getMethodName = "get"+ filedName.substring(0, 1).toUpperCase()+ filedName.substring(1);
				try {
					// 用反射取值
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					String textValue = value != null? value.toString():"";
					cell.setCellValue(textValue);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		outExcel(response, workbook,fileName);
	}
	

	public  static void outExcel(HttpServletResponse response,HSSFWorkbook workbook,String fileName) {
		OutputStream ouputStream = null;
        try {
        	response.setContentType("application/ms-excel;charset=UTF-8");  
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8")+new Date().getTime()+".xls");
        	ouputStream = response.getOutputStream(); 
			workbook.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
