package thread;

public class Synchronized4 {

	public static void main(String[] args) {
	// 다섯번째 호출
		Synchronized4 sy = new Synchronized4(); // 객체생성
		sy.test1();
	}
	
	// 네번째 메서드 생성
	private void test1() {
		Drink drink = new Drink(50000);
		
		Thread th1 = new Thread(new Delivery(drink), "kh지점");
		Thread th2 = new Thread(new Delivery(drink), "종로지점");
		Thread th3 = new Thread(new Delivery(drink), "강남본점");
		
		th1.start();
		th2.start();
		th3.start();
	}
	// 첫번째 클래스 생성
	class Drink{
		public int stock; // 재고물량
		
		Drink(int stock){ // 생성자
			this.stock = stock;
		}
		// 1. 메서드에 직접 동기화 synchronized 추가
		public synchronized void sale(int count) { // 재고물량에서 몇개를 판매할 것인지
			String name = Thread.currentThread().getName();  
			// 2. 동기화 블럭
			// synchronized(this 공유개체){ 끝나는 지점까지 }
			if(stock >= count) { // 재고물량 5 판매수량 6 이 될수 없으니
				 stock -= count; // 개수만큼 빼줌
				 System.out.printf("[%s] 판매 %d개 | 현재재고 %d\n", name, count, stock); // 재고물량 몇개가 남았는지
			}else {
				System.out.println("재고가 부족합니다");
			}
		}
	}
	
	// 두번째 클래스 생성
	class Delivery implements Runnable{
		// 세번째 생성자 생성
		private Drink drink;
		
		Delivery(Drink drink){
			this.drink = drink;
		}
		
		@Override
		public void run() {
			while(drink.stock > 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				int count = (int)(Math.random()*5 + 1) *1000; //1000~5000까지
				drink.sale(count); // 수행문
			}
			System.out.println(Thread.currentThread().getName() + "End");
		}
	}
}
