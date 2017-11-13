package UriageBatch;

import businesslogic.InsertOracle;
import businesslogic.ReadCsv;
import businesslogic.SelectOracle;

public class UriageBatch {

	public static void main(String[] args)
	{
		try {
			InsertOracle insertOracle = new InsertOracle();

			// 前日の在庫をコピー
			insertOracle.copyTStock();

			ReadCsv readCsv = new ReadCsv();

			// 売上伝票CSVを読み込む
			readCsv.readUriageCsv();
			// 仕入CSVを読み込む
			readCsv.readShiireCsv();

			SelectOracle selectOracle = new SelectOracle();

			// 本日の売上をトランテーブルから取得し売上と売上明細に登録
			selectOracle.selectTSaleTran();

			// 本日の在庫を確定
			insertOracle.updateZaikoFromUriage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
