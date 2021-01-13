import java.io.IOException;
import java.net.SocketTimeoutException;

public class WebPage {

	public String title;
	public String url;
	public WordCounter wordCounter;

	public WebPage(String title, String url, KeywordList keywordList) throws IOException, SocketTimeoutException {
		this.title = title;
		this.url = url;
		this.wordCounter = new WordCounter(title, url, keywordList);
	}

	public int getScore() {
		return wordCounter.countScore();
	}

}
