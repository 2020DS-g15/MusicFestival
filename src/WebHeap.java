import java.util.PriorityQueue;

public class WebHeap {
	
	private PriorityQueue<WebNode> heap;
	
	public WebHeap() {
		this.heap = new PriorityQueue<WebNode>(10, new WebComparator());
	}
	
	public void add(WebNode k) {
		heap.offer(k);
	}
	
	public void removeMin() {
		heap.poll();		
	}
	
	public void output() {
		StringBuilder sb = new StringBuilder();
		WebNode k;
		
		while((k = heap.poll()) != null) {
			sb.append(k.toString());
			if(heap.peek() != null) sb.append("");
		}
		
		System.out.println(sb.toString());	
	}
	
}
