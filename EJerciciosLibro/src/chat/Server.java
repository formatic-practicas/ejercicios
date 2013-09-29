package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
	Captador captador;
	Vector<ServerClient> clientes;

	public static void main(String[] args) {
		new Server().start();
	}

	public Server() {
		captador = new Captador(this);
		clientes = new Vector<ServerClient>();
	}

	public void start() {
		new Thread(captador).start();
	}

	public synchronized void onMsgReceived(String dato) {
		for(int n=0;n<clientes.size();n++){
			clientes.get(n).send(dato);
		}
	}

	public synchronized void remove(ServerClient serverClient) {
		clientes.remove(serverClient);
	}
}


class Captador implements Runnable {
	ServerSocket serverSocket;
	Server server;

	public Captador(Server server) {
		this.server = server;
		try {
			serverSocket = new ServerSocket(1111);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = serverSocket.accept();
				DataInputStream entrada = new DataInputStream(
						socket.getInputStream());
				DataOutputStream salida = new DataOutputStream(
						socket.getOutputStream());
				ServerClient sc = new ServerClient(server, socket, entrada,
						salida);
				server.clientes.add(sc);
				sc.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}


class ServerClient implements Runnable {
	private Server server;
	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	Thread thread;
	boolean running = true;

	public ServerClient(Server server, Socket socket, DataInputStream entrada,
			DataOutputStream salida) {
		this.server = server;
		this.socket = socket;
		this.entrada = entrada;
		this.salida = salida;
	}

	void send(String msg) {
		try {
			salida.writeUTF(msg);
		} catch (IOException e) {
			//Si no se puede escribir porque ya está cerrado, simplemente no hacemos nada
		}
	}

	void start() {
		thread = new Thread(this);
		thread.start();
	}

	void stop() {
		running = false;
		thread.interrupt();
	}

	@Override
	public void run() {
		while (running) {
			try {
				String dato = entrada.readUTF();
				server.onMsgReceived(dato);
			} catch (IOException e) {
				stop();
				try {
					entrada.close();
					salida.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				server.remove(this);
			}
		}
	}
}
