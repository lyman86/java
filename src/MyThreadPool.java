import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MyThreadPool {
	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 3,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4),
				new ThreadPoolExecutor.DiscardOldestPolicy());
		
		Task task = new Task("http://www.baidu.com");
			threadPoolExecutor.execute(task);
			System.out.println(task.getData());

	}
}

class Task implements Runnable {
	private String data;
	private String StringData;
	public Task(String data) {
		this.data = data;
	}

	public void run() {
		
		UrlResponse urlResponse = new UrlResponse();
		StringData = urlResponse.getURLResponse(data);
		
		
		data = null;
	}
	
	public String getData(){
		return StringData;
	}

}
