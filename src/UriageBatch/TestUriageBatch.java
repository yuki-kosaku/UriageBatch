package UriageBatch;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import businessEntity.dao.DaoConnectionDriverManeger;

public class TestUriageBatch {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		DaoConnectionDriverManeger dm = new DaoConnectionDriverManeger();  

		// Connectionの作成
		conn = dm.getConnection();

		//オートコミットはオフにする。
		conn.setAutoCommit(false);

		ps = conn.prepareStatement("delete T_STOCK where STOCK_YMD = (select SYS_BUSINESS_DAY from T_SYSTEM_INFO)");
		
		//DELETE文を実行する
		int result = ps.executeUpdate();

		//処理件数を表示する
		System.out.println("結果：" + result);
		
		ps = conn.prepareStatement("delete T_SALE_TRAN where INCLUSION_YMD = (select SYS_BUSINESS_DAY from T_SYSTEM_INFO)");
		
		//DELETE文を実行する
		result = ps.executeUpdate();

		//処理件数を表示する
		System.out.println("結果：" + result);
		
		ps = conn.prepareStatement("delete T_COST where COST_YMD = (select SYS_BUSINESS_DAY from T_SYSTEM_INFO)");
		
		//DELETE文を実行する
		result = ps.executeUpdate();
		
		//処理件数を表示する
		System.out.println("結果：" + result);
		
		ps = conn.prepareStatement("delete T_SALE_DETAIL where VOUCHER_NO in (select VOUCHER_NO from T_SALE where SALE_YMD = (select SYS_BUSINESS_DAY from T_SYSTEM_INFO))");
		
		//DELETE文を実行する
		result = ps.executeUpdate();

		//処理件数を表示する
		System.out.println("結果：" + result);
		
		ps = conn.prepareStatement("delete T_SALE where SALE_YMD = (select SYS_BUSINESS_DAY from T_SYSTEM_INFO)");
		
		//DELETE文を実行する
		result = ps.executeUpdate();

		//処理件数を表示する
		System.out.println("結果：" + result);
		
		conn.commit();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		UriageBatch.main(new String[]{""});
	}

}
