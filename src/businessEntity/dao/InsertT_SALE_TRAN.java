package businessEntity.dao;

import java.sql.SQLException;

import businessEntity.dto.T_SALE_TRAN;;

public class InsertT_SALE_TRAN extends DaoConnectionDriverManeger{
	private static final String insertSql = "INSERT INTO T_SALE_TRAN values(?, ?, ?, ?, ?)";

	public void insertTSaleTran(T_SALE_TRAN tSaleTran)  throws SQLException {
		try {
			ps = conn.prepareStatement(insertSql);
			ps.setInt(1, tSaleTran.SEQ_T_SALE_TRAN);
			ps.setString(2, tSaleTran.INCLUSION_YMD);
			ps.setString(3, tSaleTran.VOUCHER_NO);
			ps.setString(4, tSaleTran.PRODUCT_CD);
			ps.setInt(5, tSaleTran.SALES);

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
