import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class KeywordList {

	public ArrayList<Keyword> lst;

	public KeywordList() {
		this.lst = new ArrayList<Keyword>();
		try {
			this.addKeyword();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addKeyword() throws FileNotFoundException {
		File file = new File("input.txt");
		Scanner scan = new Scanner(file);

		while (scan.hasNext()) {
			String name = scan.next().toUpperCase();
			int weight = scan.nextInt();
			Keyword k = new Keyword(name, weight);
			lst.add(k);
		}
		scan.close();
	}
	
}
