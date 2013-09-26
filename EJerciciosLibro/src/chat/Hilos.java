package chat;

import io.IO;

class Hilo1 extends Thread {
	@Override
	public void run() {
		while (true) {
			System.out.print(".");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class Vehiculo{
	public void avanzar(){
		System.out.print(">");
	}
}
class Avion extends Vehiculo implements Runnable{
	@Override
	public void run() {
		while(true){
			avanzar();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
public class Hilos {
	public static void main(String[] args) {
		Hilo1 hilo = new Hilo1();
		hilo.start();
		
		Thread t = new Thread(new Avion());
		t.start();
		
		while(true){
			System.out.println(IO.pideEntero("Entero", 0, 1000));
		}
	}
}

/*
Formas de crear hilos:
	1- Heredar de Thread 
	2- Implementar Runnable

	Para arrancar, llamar al método start() de la hebra.
	El método start() llama al método run() en un segundo plano
*/	
	
	