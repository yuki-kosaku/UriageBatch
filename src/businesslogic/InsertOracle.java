package businesslogic;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class InsertOracle {

	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement ps = null;

	private UriageBatchDriverManeger dm = new UriageBatchDriverManeger();

	public void insertUriageTran(String sql, int seqTSaleTran, String inclusionYMD, String voucherNo, String ProductCd, int sales ) throws Exception
	{
		try {
			// Connectionの作成
			conn = dm.getConnection();

			//オートコミットはオフにする。
			conn.setAutoCommit(false);

			// Statementの作成
			stmt = conn.createStatement();

			ps = conn.prepareStatement(sql);
			ps.setInt(1, seqTSaleTran);
			ps.setString(2, inclusionYMD);
			ps.setString(3, voucherNo);
			ps.setString(4, ProductCd);
			ps.setInt(5, sales);

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

	public void insertUriage(String sql, String voucherNo, int salesAmount ) throws Exception
	{
		try {
			// Connectionの作成
			conn = dm.getConnection();

			//オートコミットはオフにする。
			conn.setAutoCommit(false);

			// Statementの作成
			stmt = conn.createStatement();

			ps = conn.prepareStatement(sql);
			ps.setString(1, voucherNo);
			ps.setString(2, voucherNo.substring(0, 3));
			ps.setString(3, voucherNo.substring(3, 11));
			ps.setInt(4, salesAmount);

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

	public void insertUriageMeisai(String sql, String voucherNo, int detailNo, String productCd, int sales ) throws Exception
	{
		try {
			// Connectionの作成
			conn = dm.getConnection();

			//オートコミットはオフにする。
			conn.setAutoCommit(false);

			// Statementの作成
			stmt = conn.createStatement();

			ps = conn.prepareStatement(sql);
			ps.setString(1, voucherNo);
			ps.setInt(2, detailNo);
			ps.setString(3, productCd);
			ps.setInt(4, sales);

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

	public void insertShiire(String sql, String costYMD, String productCd, int costs, int costAmount ) throws Exception
	{
		try {
			// Connectionの作成
			conn = dm.getConnection();

			//オートコミットはオフにする。
			conn.setAutoCommit(false);

			// Statementの作成
			stmt = conn.createStatement();

			ps = conn.prepareStatement(sql);
			ps.setString(1, costYMD);
			ps.setString(2, productCd);
			ps.setInt(3, costs);
			ps.setInt(4, costAmount);

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

	public void copyZaiko() throws Exception
	{
	try {
			// Connectionの作成
			conn = dm.getConnection();

			if(conn == null)
			{

				System.out.println("a");
			}

			//オートコミットはオフにする。
			conn.setAutoCommit(false);

			// Statementの作成
			stmt = conn.createStatement();

			ps = conn.prepareStatement("insert into T_STOCK select to_char(to_date(A.STOCK_YMD,'yyyy/mm/dd') + 1, 'yyyymmdd'), A.PRODUCT_CD, A.STOCKS from T_STOCK A where A.STOCK_YMD = (select to_char(to_date(B.SYS_BUSINESS_DAY,'yyyy/mm/dd') - 1, 'yyyymmdd') from T_SYSTEM_INFO B) " );

			System.out.println("aaa");


			//INSERT文を実行する
			int result = ps.executeUpdate();

			//処理件数を表示する
			System.out.println("結果：" + result);

			//コミット
			conn.commit();

		} catch (Exception e) {
			System.out.println(e.getMessage());

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

	public void updateZaikoFromCost(String sql) throws Exception
	{
		try {
			// Connectionの作成
			conn = dm.getConnection();

			//オートコミットはオフにする。
			conn.setAutoCommit(false);

			// Statementの作成
			stmt = conn.createStatement();

			ps = conn.prepareStatement(sql);

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

	public void insertZaiko(String sql, String stockYMD, String productCd, int stocks ) throws Exception
	{
		try {
			// Connectionの作成
			conn = dm.getConnection();

			//オートコミットはオフにする。
			conn.setAutoCommit(false);

			// Statementの作成
			stmt = conn.createStatement();

			ps = conn.prepareStatement(sql);
			ps.setString(1, stockYMD);
			ps.setString(2, productCd);
			ps.setInt(3, stocks);

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
}