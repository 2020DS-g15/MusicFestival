import java.io.IOException;
import java.util.ArrayList;

public class WebPage {

	public String title;
	public String url;
	public WordCounter counter;
	public int score;

	public WebPage(String title, String url) {
		this.title = title;
		this.url = url;
		this.counter = new WordCounter(url);
	}

	public void setScore() throws IOException {
		score = counter.countScore();
	}

}
