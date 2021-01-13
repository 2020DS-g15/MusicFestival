
public class WebNode {

	public WebPage webPage;
	public int nodeScore;

	public WebNode(WebPage webPage) {
		this.webPage = webPage;
		setNodeScore();
	}

	public void setNodeScore() {
		nodeScore = webPage.getScore();
	}

}
