package thread;

import java.util.Scanner;

public class Thread2 {
	/*
	 * 쓰레드 생명 주기
	 * - New 생성
	 * - RUNNABLE 실행/실행대기
	 * - WAITING / TIMED_WAITING / BLOCKED
	 * - TERMINATED 제거
	 */
	public static void main(String[] args) {
		Thread2 thread2 = new Thread2();
		// thread2.test1();
		// thread2.test2();
		thread2.test3();
		System.out.println(Thread.currentThread().getName()+ "End!");

	}
	private void test1() {
		new Thread(new SleepThread(), "밀리초쓰레드").start();
	}
	
	// sleep(밀리초) : 지정한 시간동안 일시정지
	class SleepThread implements Runnable {

		@Override
		public void run() {
			for(int i=1; i<=10; i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("쓰레드를 종료합니다");
					break;
				}
				System.out.println("[" + Thread.currentThread().getName() + "] " + i);
			}
			System.out.println(Thread.currentThread().getName()+ "End!");
		}
		
	}
	/*
	 * 데몬쓰레드(Daemon)
	 * - 백그라운드로 작동하는 쓰레드
	 * - 일반스레드가 종료되면 데몬쓰레드는 자동종료
	 */
	
	private void test2() {
		Thread th = new Thread(new SleepThread(), "데몬쓰레드");
		th.setDaemon(true);
		th.start();
		
		System.out.println("아무글자나 입력하세요...");
		String str = new Scanner(System.in).nextLine();
	}
	/*
	 * Interrupt
	 * - 다른 쓰레드에 InterruptException을 유발하여 종료/분기처리가 가능하도록 할 수 있다
	 */
	private void test3() {
		Thread th = new Thread(new SleepThread(), "안터럽트쓰레드");
		th.start();
		
		System.out.println("엔터를 치면 인터럽트쓰레드가 멈춥니다");
		new Scanner(System.in).nextLine();
		th.interrupt();
	}
}

