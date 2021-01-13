import java.io.IOException;
import java.util.PriorityQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery {

	//aaaaaaaaa
	public String searchKeyword;
	public int searchNum;
	public String url;
	public String content;
	public PriorityQueue<WebNode> heap;
	public KeywordList keywordList;
	public String results;
	public long startingTime;

	public GoogleQuery(String searchKeyword, int searchNum) throws IOException {
		this.searchKeyword = searchKeyword;
		this.searchNum = searchNum;
		setKeywords();
		this.content = Content.fetchContent(url);
		this.heap = new PriorityQueue<WebNode>(2 * searchNum, new WebComparator());
		this.results = "";
		this.startingTime = System.currentTimeMillis();
		getUrl();
		
		
	}

	public void setKeywords() {
		String[] tokens = searchKeyword.split(" ");
		String keyword = "";
		for (int i = 0; i < tokens.length; i++) {
			keyword += tokens[i] + "+";
		}
		this.url = "http://www.google.com/search?q=" + keyword + "音樂節" + "&oe=utf8&num=" + 2 * searchNum;
		this.keywordList = new KeywordList(tokens);
//		System.out.println(keywordList.toString());
	}

	public void getUrl() {
		Document doc = Jsoup.parse(content);
		Elements links = doc.select("div.kCrYT > a");

		int okUrl = 0;
		int readErrorUrl = 0;
		for (Element link : links) {
			
			//title排錯
			String title = link.select("h3").text();
			if (title.equals("")) {
				continue;
			}

			//取得網站連結, new webNode, add to heap
			String url = "https://www.google.com" + link.attr("href");
			try {
				WebNode node = new WebNode(new WebPage(title, url, keywordList));
				heap.offer(node);
				okUrl++;
				System.out.printf("%2d%s%n%s%d%n", okUrl, ": " + title, "    Score: ", node.nodeScore);
//				System.out.println("    Text length: " + node.webPage.wordCounter.content.length());
//				System.out.println("    Score: " + node.nodeScore);
			} catch (Exception e) {
				System.out.println("Skip: " + e.getMessage());
				readErrorUrl++;
				continue;
			}
		}
		results += "-----Searching Results-----\n";
		results += "Keyword: " + searchKeyword + "\n";
		results += "Search website: " + 2 * searchNum + "\n";
		results += "Fine website: " + okUrl + "\n";
		results += "Read error website: " + readErrorUrl + "\n";
		results += "---------------------------" + "\n";
		results += "Searching Time: " + ((System.currentTimeMillis() - startingTime) / 1000) + " sec.\n\n";
	}
	
	public String getResults() {
		return results;
	}

	public String[][] query() throws IOException {
		String[][] retVal = new String[searchNum][2];
		for (int i = 0; i < searchNum; i++) {
			WebNode k;
			if ((k = heap.poll()) != null) {
				retVal[i][0] = k.webPage.title;
				retVal[i][1] = k.webPage.url;
//				System.out.println(k.webPage.title + " score : " + k.nodeScore);
			}
		}
		return retVal;
	}

}