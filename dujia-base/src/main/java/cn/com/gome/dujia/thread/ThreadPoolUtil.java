package cn.com.gome.dujia.thread;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
public class ThreadPoolUtil{
	private static ThreadPoolExecutor pool  ;
	private ThreadPoolUtil(){}
	static{
		 pool=new ThreadPoolExecutor(10, 500,
				5,TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(20000),
				new ThreadPoolExecutor.DiscardPolicy());
	}
	public static ThreadPoolExecutor getInstance(){
		return pool;
	}
	public static void destoryInstance(){
		pool.shutdown();
	}
}
