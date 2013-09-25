package chat;
import io.IO;

import java.io.*;
import java.net.*;
public class Servidor {
	public static final int PORT = 4567;
	public static void main(String[] args) {
		Socket skt = null;
		ServerSocket ss = null;
		System.err.println("Escuchando el puerto:" + PORT);
		try {
			ss = new ServerSocket(PORT);
		}
		catch (IOException ex) {
			System.out.println("El sistema no permite abrir el puerto");
			System.exit(-1);
		}
		// Si no ocurrió error arriba entonces esperamos a la secretaria
		System.err.println("Esperando conexión...");
		try {
			skt = ss.accept();
		} catch (IOException ex1) {
			ex1.printStackTrace(System.err);
			System.exit(-1);
		}
		System.err.println("Conectado... Esperando teléfonos");
		try {
			ObjectInputStream entrada = new ObjectInputStream(skt.getInputStream());
			ObjectOutputStream salida = new ObjectOutputStream(skt.getOutputStream());

			while (true) {
				if ((skt.isClosed())) {
					System.err.println("Conexión cerrada... terminado");
					System.exit(0);
				}
				if (entrada.available() > 0) {
					int dato = entrada.readInt();
					if(dato == -1){
						System.err.println("Ultima llamada, nos vamos... terminado");
						System.exit(0);	
					}
					System.out.println(""+dato);
				}
				int n = IO.pideInt(">", -1, 1000);
				salida.writeInt(n);
			} 
		} catch (IOException ex2) {
			ex2.printStackTrace(System.err);
			System.exit(-1);
		}
	} 
}
