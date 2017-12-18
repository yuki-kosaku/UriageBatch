package businessEntity.dao;

import java.sql.SQLException;

import businessEntity.dto.T_SALE;

public class InsertT_SALE extends DaoConnectionDriverManeger{
	private static final String insertSql = "INSERT INTO T_SALE values(?, ?, ?, ?)";

	public void insertTSale(T_SALE tSale)  throws SQLException {
		try {
			ps = conn.prepareStatement(insertSql);
			ps.setString(1, tSale.VOUCHER_NO);
			ps.setString(2, tSale.SHOP_CD);
			ps.setString(3, tSale.SALE_YMD);
			ps.setInt(4, tSale.SALE_AMOUNT);

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

