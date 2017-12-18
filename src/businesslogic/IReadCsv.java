package businesslogic;

import java.util.ArrayList;

public interface IReadCsv {
	public ArrayList<ArrayList<String>> readCsv(String fileName);

	public void validation(ArrayList<ArrayList<String>> raw);
}

