package businesslogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import businessEntity.dao.InsertT_SALE_TRAN;
import businessEntity.dto.T_SALE_TRAN;

public class ReadCsv {

	public ArrayList<ArrayList<String>> readCsv(String fileName) {
		ArrayList<ArrayList<String>> raw = new ArrayList<>();

		try {
			File csv = new File(fileName);

			BufferedReader br = new BufferedReader(new FileReader(csv));

			// 最終行まで読み込む
			String line = "";
			while ((line = br.readLine()) != null) {

				// 1行をデータの要素に分割
				StringTokenizer st = new StringTokenizer(line, ",");

				ArrayList<String> col = new ArrayList<>();
				while (st.hasMoreTokens()) {
					// 1行の各要素をカンマ区切りで表示
					col.add(st.nextToken());

				}
				raw.add(col);
			}

			br.close();
		} catch (FileNotFoundException e) {
			// Fileオブジェクト生成時の例外捕捉
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedReaderオブジェクトのクローズ時の例外捕捉
			e.printStackTrace();
		}

		return raw;

	}

	public void readUriageCsv() throws Exception {
		try {
			File csv = new File("C:\\TEMP\\URIAGE.csv"); // CSVデータファイル

			BufferedReader br = new BufferedReader(new FileReader(csv));

			SelectOracle selectOracle = new SelectOracle();
			InsertT_SALE_TRAN insertTSaleTran = new InsertT_SALE_TRAN();
			T_SALE_TRAN tSaleTran = new T_SALE_TRAN();

			// 最終行まで読み込む
			String line = "";
			tSaleTran.INCLUSION_YMD = selectOracle.selectTSystemInfo();
			while ((line = br.readLine()) != null) {

				// 1行をデータの要素に分割
				StringTokenizer st = new StringTokenizer(line, ",");

				tSaleTran.VOUCHER_NO = "";
				tSaleTran.PRODUCT_CD = "";
				tSaleTran.SALES = 0;
				tSaleTran.SEQ_T_SALE_TRAN = selectOracle.seqTranUri();

				while (st.hasMoreTokens()) {
					// 1行の各要素をタブ区切りで表示

					tSaleTran.VOUCHER_NO = st.nextToken();
					tSaleTran.PRODUCT_CD = st.nextToken();
					tSaleTran.SALES = Integer.parseInt(st.nextToken());
				}

				insertTSaleTran.insertTSaleTran(tSaleTran);
			}
			br.close();

		} catch (FileNotFoundException e) {
			// Fileオブジェクト生成時の例外捕捉
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedReaderオブジェクトのクローズ時の例外捕捉
			e.printStackTrace();
		}
	}

	public void readShiireCsv() throws Exception {
		try {
			File csv = new File("C:\\TEMP\\SHIIRE.csv"); // CSVデータファイル

			BufferedReader br = new BufferedReader(new FileReader(csv));

			InsertOracle insertOracle = new InsertOracle();
			SelectOracle selectOracle = new SelectOracle();

			// 最終行まで読み込む
			String line = "";
			while ((line = br.readLine()) != null) {

				// 1行をデータの要素に分割
				StringTokenizer st = new StringTokenizer(line, ",");

				String costYMD = "";
				String productCd = "";
				int costs = 0;
				int costAmount = 0;

				while (st.hasMoreTokens()) {
					// 1行の各要素をタブ区切りで表示

					costYMD = st.nextToken();
					productCd = st.nextToken();
					costs = Integer.parseInt(st.nextToken());
					costAmount = costs * selectOracle.selectCostUnitPrice(costYMD, productCd);
				}

				insertOracle.insertShiire("INSERT INTO T_COST values(?, ?, ?, ?)", costYMD, productCd, costs,
						costAmount);

				// 在庫に商品がない場合、在庫に登録する。
				if (selectOracle.selectZaikoRecord(productCd) == 0) {
					insertOracle.insertZaiko("INSERT INTO T_STOCK values(?, ?, ?)", costYMD, productCd, costs);
				} else {
					insertOracle.updateZaikoFromCost("UPDATE T_STOCK SET STOCKS = STOCKS + " + costs
							+ " WHERE STOCK_YMD = '" + costYMD + "' AND PRODUCT_CD = '" + productCd + "'");
				}
			}
			br.close();

		} catch (FileNotFoundException e) {
			// Fileオブジェクト生成時の例外捕捉
			e.printStackTrace();
		} catch (IOException e) {
			// BufferedReaderオブジェクトのクローズ時の例外捕捉
			e.printStackTrace();
		}
	}
}
