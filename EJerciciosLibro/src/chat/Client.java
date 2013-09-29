package chat;

import io.IO;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

class Lector implements Runnable {
	Client client;
	private boolean running=true;

	public Lector(Client client) {
		this.client = client;
	}

	@Override
	public void run() {
		while (running) {
			try {
				String cad = client.entrada.readUTF();
				client.view.muestraMensaje(cad);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		running = false;
	}
}

public class Client {
	private static final String HOST = "localhost";
	private static final int PORT = 1111;
	Lector lector;
	DataInputStream entrada;
	DataOutputStream salida;
	ClientView view;

	public static void main(String[] args) {
		new Client();
	}

	public Client() {
		lector = new Lector(this);
		try {
			Socket socket = new Socket(HOST, PORT);
			entrada = new DataInputStream(socket.getInputStream());
			salida = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start(){
		new Thread(lector).start();
	}
	public void setView(ClientView view) {
		this.view = view;
	}

	public void enviaMensaje(String text) {
		try {
			salida.writeUTF(text);
			salida.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		lector.stop();
		try {
			entrada.close();	
			salida.close();
		} catch (IOException e) {

		}

	}
}

