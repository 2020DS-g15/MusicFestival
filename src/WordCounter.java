import java.io.IOException;
import java.net.SocketTimeoutException;

public class WordCounter {

	public String content;
	public KeywordList keywordList;

	public WordCounter(String title, String url, KeywordList keywordList) throws IOException, SocketTimeoutException {
		this.content = title + Content.fetchContent(url);
		this.keywordList = keywordList;
	}

	public int countScore() {
		if (content == null) {
			return 0;
		}
		content = content.toUpperCase();

		int score = 0;
		for (Keyword keyword : keywordList.lst) {
			score += countKeyword(keyword.name);
		}
		return score;
	}

	public int countKeyword(String keyword) {
		keyword = keyword.toUpperCase();

		int retVal = 0;
		int fromIdx = 0;
		int found = -1;

		while ((found = content.indexOf(keyword, fromIdx)) != -1) {
			retVal++;
			fromIdx = found + keyword.length();
		}
		return retVal;
	}

}
