package applicationLogic;

import java.sql.SQLException;
import java.util.ArrayList;

import businessEntity.dto.T_COST;
import businesslogic.InsertData;
import businesslogic.ReadCsv;

public class SetCost {

	public void setCost() throws SQLException {
		ReadCsv readCsv = new ReadCsv();

		// 売上伝票CSVを読み込む
		ArrayList<ArrayList<String>> csvDatas = readCsv.readCsv("C:\\TEMP\\SIIRE.csv");

		InsertData insertData = new InsertData();

		for (ArrayList<String> csvData : csvDatas) {
			T_COST tCost = new T_COST();

			tCost.COST_YMD = csvData.get(0);
			tCost.PRODUCT_CD = csvData.get(1);
			tCost.COSTS = Integer.parseInt(csvData.get(2));
			tCost.COST_AMOUNT = Integer.parseInt(csvData.get(3));

			insertData.inserTCost(tCost);
		}

		// 仕入を在庫テーブルに反映
		insertData.mergeTStock();
	}
}

