package applicationLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import businessEntity.dto.T_SALE_TRAN;
import businesslogic.InsertData;
import businesslogic.ReadCsv;

public class SetUriageTran {

	public void setUriageTran() throws SQLException {
		ReadCsv readCsv = new ReadCsv();

		// 売上伝票CSVを読み込む
		ArrayList<ArrayList<String>> csvDatas = readCsv.readCsv("C:\\TEMP\\URIAGE.csv");

		InsertData insertData = new InsertData();
		
		for (ArrayList<String> csvData : csvDatas) {
			T_SALE_TRAN tSaleTran = new T_SALE_TRAN();

			tSaleTran.SEQ_T_SALE_TRAN = Integer.parseInt(csvData.get(0));
			tSaleTran.INCLUSION_YMD = csvData.get(1);
			tSaleTran.VOUCHER_NO = csvData.get(2);
			tSaleTran.PRODUCT_CD = csvData.get(3);
			tSaleTran.SALES = Integer.parseInt(csvData.get(4));
			
			insertData.inserTSaleTran(tSaleTran);
		}
	}
}
