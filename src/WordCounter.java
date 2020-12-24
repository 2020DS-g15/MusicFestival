import java.io.IOException;

public class WordCounter {

	private String url;
	private String content;
	public KeywordList keywordList;

	public WordCounter(String url) {
		this.url = url;
		this.keywordList = new KeywordList();
	}
	
	public int countScore() throws IOException {
		if (content == null) {
			content = Content.fetchContent(url);
		}
		content = content.toUpperCase();
		
		int score = 0;
		for(Keyword keyword : keywordList.lst) {
			score += countKeyword(keyword.name);
		}
		return score;
	}
	
	public int countKeyword(String keyword) throws IOException {
		keyword = keyword.toUpperCase();
	
		int retVal = 0;
		int fromIdx = 0;
		int found = -1;
	
		while ((found = content.indexOf(keyword, fromIdx)) != -1){
		    retVal++;
		    fromIdx = found + keyword.length();
		}
	
		return retVal;
    }

}
