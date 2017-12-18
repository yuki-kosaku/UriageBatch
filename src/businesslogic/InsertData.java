package businesslogic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import businessEntity.dao.DaoConnectionDriverManeger;
import businessEntity.dao.InsertT_COST;
import businessEntity.dao.InsertT_SALE;
import businessEntity.dao.InsertT_SALE_DETAIL;
import businessEntity.dao.InsertT_SALE_TRAN;
import businessEntity.dao.InsertT_STOCK;
import businessEntity.dao.MergeT_STOCK;
import businessEntity.dto.T_COST;
import businessEntity.dto.T_SALE;
import businessEntity.dto.T_SALE_DETAIL;
import businessEntity.dto.T_SALE_TRAN;

public class InsertData {

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;

	private DaoConnectionDriverManeger dm = new DaoConnectionDriverManeger();

	public void inserTSaleTran(T_SALE_TRAN tSaleTran) throws SQLException {
		InsertT_SALE_TRAN insertTSaleTran = new InsertT_SALE_TRAN();

		insertTSaleTran.insertTSaleTran(tSaleTran);
	}


	public void insertTSale(T_SALE tSale) throws Exception
	{
		InsertT_SALE insertTsale = new InsertT_SALE();

		insertTsale.insertTSale(tSale);
	}

	public void insertTSaleDetail(T_SALE_DETAIL tSaleDetail) throws Exception
	{
		InsertT_SALE_DETAIL insertTsaleDetail = new InsertT_SALE_DETAIL();

		insertTsaleDetail.insertTSaleDetail(tSaleDetail);

	}

	public void insertCost(T_COST tCost) throws SQLException
	{
		InsertT_COST insertTCost = new InsertT_COST();

		insertTCost.insertTCost(tCost);
	}

	public void copyTStock() throws SQLException
	{
		InsertT_STOCK insertTStock = new InsertT_STOCK();

		insertTStock.copyTStock();
	}

	public void updateZaikoFromUriage() throws Exception
	{
		try {
			// Connectionの作成
			conn = dm.getConnection();

			//オートコミットはオフにする。
			conn.setAutoCommit(false);

			// Statementの作成
			stmt = conn.createStatement();

			ps = conn.prepareStatement("update T_STOCK A SET STOCKS = STOCKS - (select sum(D.SALES) from T_SALE_DETAIL D, T_SALE E where D.VOUCHER_NO = E.VOUCHER_NO AND D.PRODUCT_CD = A.PRODUCT_CD AND E.SALE_YMD = (select C.SYS_BUSINESS_DAY from T_SYSTEM_INFO C)) where A.STOCK_YMD = (select C.SYS_BUSINESS_DAY from T_SYSTEM_INFO C)" );

			//INSERT文を実行する
			int result = ps.executeUpdate();

			//処理件数を表示する
			System.out.println("結果：" + result);

			//コミット
			conn.commit();

		} catch (Exception e) {
			conn.rollback();
			throw e;
		} finally {
			try {
				/* クローズ処理 */

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

	public void inserTCost(T_COST tCost) throws SQLException {
		InsertT_COST insertTCost = new InsertT_COST();

		insertTCost.insertTCost(tCost);
	}

	public void mergeTStock() throws SQLException {
		MergeT_STOCK mergeTStock = new MergeT_STOCK();

		mergeTStock.mergeTStock();
	}
}
