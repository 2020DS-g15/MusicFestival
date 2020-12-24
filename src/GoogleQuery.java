import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery {

	public String searchKeyword;
	public int searchNum;
	public String url;
	public String content;
	public PriorityQueue<WebNode> heap;

	public GoogleQuery(String searchKeyword, int searchNum) {
		this.searchKeyword = searchKeyword;
		this.searchNum = searchNum;
		this.url = "http://www.google.com/search?q=" + searchKeyword + "&oe=utf8&num=" + 2 * searchNum;
		this.heap = new PriorityQueue<WebNode>(2 * searchNum, new WebComparator());
	}

	public PriorityQueue<WebNode> heap() throws IOException {
		if (content == null) {
			content = Content.fetchContent(url);
		}

		Document doc = Jsoup.parse(content);
		Elements links = doc.select("div.kCrYT > a");
		System.out.println("lis siaze = " + links.size());

		for (Element link : links) {
			try {
				String title = link.select("h3").text();
				if (title.equals("")) {
					continue;
				}
				String url = "https://www.google.com" + link.attr("href");
				WebNode node = new WebNode(new WebPage(title, url));
				heap.offer(node);
			} catch (IndexOutOfBoundsException e) {
				e.printStackTrace();
			}
		}
		
		return heap;
	}

	public HashMap<String, String> query() throws IOException {
		HashMap<String, String> retVal = new HashMap<String, String>();
		for(int i = 0; i < searchNum; i++) {
			retVal.put(heap.poll().webPage.title, heap.poll().webPage.url);
		}
		return retVal;
	}

}
