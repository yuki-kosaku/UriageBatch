package businessEntity.dao;

import java.sql.SQLException;

import businessEntity.dto.T_SALE_DETAIL;

public class InsertT_SALE_DETAIL extends DaoConnectionDriverManeger{
	private static final String insertSql = "INSERT INTO T_SALE_DETAIL values(?, ?, ?, ?)";

	public void insertTSaleDetail(T_SALE_DETAIL tSaleDetail)  throws SQLException {
		try {
			ps = conn.prepareStatement(insertSql);
			ps.setString(1, tSaleDetail.VOUCHER_NO);
			ps.setInt(2, tSaleDetail.DETAIL_NO);
			ps.setString(3, tSaleDetail.PRODUCT_CD);
			ps.setInt(4, tSaleDetail.SALES);

			//INSERT文を実行する
			int result = ps.executeUpdate();

			//処理件数を表示する
			System.out.println("結果：" + result);

			//コミット
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}
	}
}

