package tel;
import java.io.*;
import java.net.*;
public class Servidor {
	public static int PORT = 4567;
	public static int BUFF_SIZE = 40;
	public static int TIMER_SLEEP = 30 * 1000; // 60sx1000ms
	public static int buff_elem = 0;
	public static int[] buffer = new int[BUFF_SIZE];

	public static void main(String[] args) {
		Socket skt = null;
		ServerSocket Ss = null;
		System.err.println("Escuchando el puerto:" + PORT);
		try {
			Ss = new ServerSocket(PORT);
		}
		catch (IOException ex) {
			System.out.println("El sistema no permite abrir el puerto");
			System.exit(-1);
		}
		// Si no ocurrió error arriba entonces esperamos a la secretaria
		System.err.println("Esperando conexión...");
		try {
			skt = Ss.accept();
		} catch (IOException ex1) {
			ex1.printStackTrace(System.err);
			System.exit(-1);
		}
		// Si no ocurrió error arriba la secretaria está lista para enviar
		System.err.println("Conectado... Esperando teléfonos");
		try {
			ObjectInputStream datos = new ObjectInputStream(
					skt.getInputStream());
			long timer = 0;
			boolean timer_on = false;
			while (true) {
				if ((skt.isClosed() && (buff_elem < 1)) || (buffer[0] == -1)) {
					// Terminamos el programa si la secretaria terminó
					System.err
							.println("Ultima llamada, nos vamos... terminado");
					System.exit(0);
				}
				// si hay teléfonos los guardamos
				if (datos.available() > 0) {
					put_tel(datos.readInt());
				}
				if (timer_on) {
					// si el timer funciona no hacer nada, si se pasó pararlo
					if ((timer + TIMER_SLEEP) < System.currentTimeMillis()) {
						timer_on = false;
					}
				} else {
					// Si el timer está apagado, mostramos un tel si es que hay
					if (buff_elem > 0) {
						System.out.println("Secretaria llamando al tel:"
								+ get_tel());

						// Encendemos el timer y guardamos la hora en que empezó
						timer_on = true;
						timer = System.currentTimeMillis();
					}

				}
				// Pausamos 100ms para no sobrecargar el procesador
				try {
					Thread.sleep(100);
				}
				catch (InterruptedException ex3) {
				}

			} 
		} catch (IOException ex2) {
			ex2.printStackTrace(System.err);
			System.exit(-1);
		}
	} 
	public static void put_tel(int tel) {
		if (BUFF_SIZE < (buff_elem + 1)) {
			System.err
					.println("Buffer overrun: El buffer se llenó demasiado rápido");
			System.exit(-1);
		}
		buffer[buff_elem++] = tel;
	}

	public static int get_tel() {
		int tel = buffer[0];
		buff_elem--;
		for (int i = 0; i < buff_elem; i++){
			buffer[i] = buffer[i + 1];
		}
		return tel;
	}
}
