package chat;

import java.io.*;
import java.net.*;

public class Cliente {
	public static int PORT = 4567;
	public static String HOST = "127.0.0.1";
	//public static String HOST = "192.168.0.6";
	public static void main(String[] args) {
		System.err.println("Intentando conectar con la telefonista");
		Socket skt = null;
		try {
			skt = new Socket(HOST, PORT);
		}
		catch (Exception ex) {
			System.err.println("La telefonista no está en línea");
			System.exit(-1);
		}
		int tel;
		BufferedReader teclado = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			ObjectOutputStream salida = new ObjectOutputStream(skt.getOutputStream());
			ObjectInputStream entrada = new ObjectInputStream(skt.getInputStream());

			while (true) {
				if ((tel = leerInt(teclado)) == -1) {
					System.err.println("Programa terminado");
					salida.writeInt(-1);
					salida.flush();
					salida.close();
					entrada.close();
					skt.close();
					System.exit(0);
				} else {
					salida.writeInt(tel);
					salida.flush();
					int n = entrada.readInt();
					System.out.println(""+n);
				}
			}
		} catch (IOException ex1) {
			ex1.printStackTrace(System.err);
		}
	}
	public static int leerInt(BufferedReader buff) {
		int lee = 0;
		boolean error;
		do {
			error = false;
			try {
				lee = Integer.parseInt(buff.readLine());
			}catch (NumberFormatException ex) {
				System.err.println("Entrada erronea, repetir:?");
				error = true;
			}catch (Exception ex) {
				ex.printStackTrace(System.err);
			}
		} while (error);
		return lee;
	}
}
