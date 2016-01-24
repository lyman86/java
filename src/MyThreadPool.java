import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import utils.Task.onSuccessListener;

public class MyThreadPool {
	public static void main(String[] args) {
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 3,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4),
				new ThreadPoolExecutor.DiscardOldestPolicy());
		String url2 = "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&rsv_idx=1&tn=baidu&wd=java%20%E5%88%A9%E7%94%A8Future%E5%BC%82%E6%AD%A5%E8%8E%B7%E5%8F%96%E5%A4%9A%E7%BA%BF%E7%A8%8B%E4%BB%BB%E5%8A%A1%E7%BB%93%E6%9E%9C&oq=%26lt%3BompletionService&rsv_pq=ef1b78ec001cb949&rsv_t=275cafjslHI5Srp03av6QXhf%2BLCEU7EW7UNa6ggLRJttS22ONVcflrNhisM&rsv_enter=1&inputT=531&rsv_n=2&rsv_sug3=128&rsv_sug2=0&rsv_sug7=000&rsv_sug4=531";
		Task task = new Task("http://www.baidu.com");
		Task task2 = new Task(url2);
		List<Task>list = new ArrayList<>();
		list.add(task);
		list.add(task2);
		for (int i = 0; i < list.size(); i++) {
			threadPoolExecutor.execute(list.get(i));
			list.get(i).setOnSuccessListener(new onSuccessListener() {
				
				@Override
				public void onSucess(String s) {
					System.out.println(s);
				}
			});
		}


	}
}

class Task implements Runnable {
	private String data;
	private String StringData;
	private onSuccessListener mListener;

	public interface onSuccessListener {
		void onSucess(String s);
	}

	public void setOnSuccessListener(onSuccessListener mListener) {
		this.mListener = mListener;
	}

	public Task(String data) {
		this.data = data;
	}
	
	public void addTask(){
		
	}

	public void run() {

		UrlResponse urlResponse = new UrlResponse();
		StringData = urlResponse.getURLResponse(data);
		if (mListener != null) {
			mListener.onSucess(StringData);
		}
		// getData();
		// System.out.println(StringData);
	}

}
