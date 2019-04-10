package com.phda.threadTest;

public class StaticVarTest {
	public static int num = 0;
	
	public static int taskNum = 0;
	
	public synchronized  void add() {
		num++;
	}

	public static void main(String[] args) throws InterruptedException {
		
		StaticVarTest sv = new StaticVarTest();
		int taskNum = 100;
		for(int i=0 ; i< taskNum ; i++) {
			task t = new task(sv);
			t.start();
		}
		
		//等待线程运行完毕
		while(true)
		{
			if(sv.taskNum == taskNum) {
				System.out.println("线程运行完毕");
				break ;
			}
		}
		
		System.out.println("num:"+num);
	}

}


class task extends Thread{
	
	private StaticVarTest sv;
	public task() {}
	public task(StaticVarTest sv) {
		this.sv =sv;
	}
	
	@Override
	public void run() {
		//sv.add();
		StaticVarTest.num++;
		sv.taskNum++;
	}
}



