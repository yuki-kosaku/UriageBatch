package UriageBatch;


import applicationLogic.CopyZaiko;
import businesslogic.SelectData;

public class UriageBatch {

	public static void main(String[] args)
	{
		try {

			CopyZaiko copyZaiko = new CopyZaiko();
			
			copyZaiko.copyZaiko();
			


			// 仕入CSVを読み込む
			//readCsv.readShiireCsv();

			SelectData selectData = new SelectData();

			// 本日の売上をトランテーブルから取得し売上と売上明細に登録
			selectData.selectTSaleTran();

			// 本日の在庫を確定
			//insertData.updateZaikoFromUriage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
