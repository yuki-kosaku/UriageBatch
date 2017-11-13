package UriageBatch;

import businesslogic.InsertData;
import businesslogic.ReadCsv;
import businesslogic.SelectData;

public class UriageBatch {

	public static void main(String[] args)
	{
		try {
			InsertData insertOracle = new InsertData();

			// 前日の在庫をコピー
			insertOracle.copyTStock();

			ReadCsv readCsv = new ReadCsv();

			// 売上伝票CSVを読み込む
			readCsv.readUriageCsv();
			// 仕入CSVを読み込む
			readCsv.readShiireCsv();

			SelectData selectOracle = new SelectData();

			// 本日の売上をトランテーブルから取得し売上と売上明細に登録
			selectOracle.selectTSaleTran();

			// 本日の在庫を確定
			insertOracle.updateZaikoFromUriage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
