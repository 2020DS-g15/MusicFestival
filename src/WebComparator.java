import java.util.Comparator;

public class WebComparator implements Comparator<WebNode> {

	@Override
	public int compare(WebNode o1, WebNode o2) {
		if (o1 == null || o2 == null)
			throw new NullPointerException();
		return o1.nodeScore - o2.nodeScore;
	}

}
