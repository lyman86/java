import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(4);
		CompletionService<String> pool = new ExecutorCompletionService<String>(
				threadPool);

		for (int i = 0; i < 10; i++) {

			// pool.submit();
			pool.submit(new Task2("task " + i), i + "");
		}

		for (int i = 0; i < 10; i++) {
			try {
				String result = pool.take().get();
				Thread.sleep(1000);
				System.out.println(result + "   >>>>");
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		threadPool.shutdown();
	}
}

class Task2 implements Runnable {
	private String data;

	public Task2(String data) {
		this.data = data;
	}

	//
	public void run() {
		System.out
				.println(Thread.currentThread().getName() + " handle " + data);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		data = null;
	}

	// public String call() throws Exception {
	// // TODO Auto-generated method stub
	// return data;
	// }

}
