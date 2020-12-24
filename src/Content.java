import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Content {

	public static String fetchContent(String url) throws IOException {
		// 連接URL
		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		conn.setRequestProperty("User-agent", "Chrome/7.0.517.44");
		// 取得網站資料
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		// 寫入資料
		String retVal = "";
		String line = null;
		while ((line = bufReader.readLine()) != null) {
			retVal += line;
		}
		return retVal;
	}

}
