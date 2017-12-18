package businesslogic;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import businessEntity.customDao.SelectT_SALES_TRAN;
import businessEntity.dao.DaoConnectionDriverManeger;
import businessEntity.dto.T_SALE;
import businessEntity.dto.T_SALE_DETAIL;

public class SelectData {

	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rset = null;

	private DaoConnectionDriverManeger dm = new DaoConnectionDriverManeger();

	public List<T_SALE> getTSalesData() throws Exception {

		SelectT_SALES_TRAN selectTSalesTran = new SelectT_SALES_TRAN();

		List<T_SALE> tSaleData = selectTSalesTran.selectTSalesFromTSalesTran();

		return tSaleData;
	}

	public List<T_SALE_DETAIL> getTSalesDetailData() throws Exception {


		SelectT_SALES_TRAN selectTSalesTran = new SelectT_SALES_TRAN();

		List<T_SALE_DETAIL> tSaleDeatailData = selectTSalesTran.selectTSalesDetailFromTSalesTran();

		return tSaleDeatailData;
	}

	public int seqTranUri() throws Exception {

		int seqTranUri = 0;

		try {
			// Connectionの作成
			conn = dm.getConnection();

			// Statementの作成
			stmt = conn.createStatement();

			// Resultsetの作成
			rset = stmt.executeQuery("select SEQ_TRAN_URI.NEXTVAL AS SEQ_TRAN_URI FROM DUAL");

			// 取得したデータを出力する
			while (rset.next()) {
				seqTranUri = Integer.parseInt(rset.getString("SEQ_TRAN_URI"));
			}

			return seqTranUri;
		} catch (Exception e) {
			return 0;
		} finally {
			try {
				/* クローズ処理 */
				if (rset != null) {
					rset.close();
					rset = null;
				}

				if (stmt != null) {
					stmt.close();
					stmt = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Throwable e) {
				// nop
			}
		}
	}

	public int selectCostUnitPrice(String costYMD, String productCd) throws Exception {

		int costUnitPrice = 0;

		try {
			// Connectionの作成
			conn = dm.getConnection();

			// Statementの作成
			stmt = conn.createStatement();

			// Resultsetの作成
			rset = stmt.executeQuery("select A.COST_UNIT_PRICE from M_PRODUCT A where A.PRODUCT_CD = '" + productCd
					+ "' and A.UNIT_PRICE_START_YMD = (select max(UNIT_PRICE_START_YMD) from M_PRODUCT C where C.PRODUCT_CD = '"
					+ productCd + "')");

			// 取得したデータを出力する
			while (rset.next()) {
				costUnitPrice = Integer.parseInt(rset.getString("COST_UNIT_PRICE"));
			}

			return costUnitPrice;
		} catch (Exception e) {
			return 0;
		} finally {
			try {
				/* クローズ処理 */
				if (rset != null) {
					rset.close();
					rset = null;
				}

				if (stmt != null) {
					stmt.close();
					stmt = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Throwable e) {
				// nop
			}
		}
	}
}
