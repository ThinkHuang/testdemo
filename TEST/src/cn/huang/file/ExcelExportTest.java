package cn.huang.file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class ExcelExportTest {
	
	private static ArrayList subdata = new ArrayList();
	
	private static String tablename;
	
	private static String filepath;
	
	public static void main(String[] args) {
		String filepath = "f:\\SIT_ETS_TC_day_count_basis_20150703.xlsx";
		readFromExcel(filepath);
	}

	private static ArrayList readFromExcel(String filepath) {
		// TODO Auto-generated method stub
//		subdata.clear();
//		try {
//			InputStream is = new FileInputStream(filepath);
//			Workbook work = Workbook.getWorkbook(is);
//			Sheet[] st = work.getSheets();
//			for(int i = 0; i < st.length ; i++){
//				ArrayList allList = new ArrayList();
//				ArrayList tablenames = new ArrayList();
//				ArrayList tableAndcontents = new ArrayList();
//				tablename = st[i].getName().trim();
//				int b = 0;
//				for(int a = 0; a < st[i].getRows(); a++){
//					ArrayList al = new ArrayList();
//					for(int j = 0; j < st[i].getColumns(); j++){
//						Cell coo = st[i].getCell(a, j);
//						coo.getContents().trim();
//						//String strcoo = StringUtils
//					}
//				}
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return subdata;
	}
}
