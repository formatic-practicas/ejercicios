package chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
public class Server{
	Captador captador;
	Comunicador comunicador;
	Vector<DataInputStream> entradas;
	Vector<DataOutputStream> salidas;
	public Server(){
		entradas = new Vector<DataInputStream>();
		salidas  = new Vector<DataOutputStream>();
		comunicador = new Comunicador(this);
		captador = new Captador(this);
	}
	public void start(){
		new Thread(captador).start();
		new Thread(comunicador).start();
	}
}

class Comunicador implements Runnable{
	Server server;
	public Comunicador(Server server) {
		this.server = server;
	}

	@Override
	public void run() {
		while(true){
			for(int n=0; n< server.entradas.size(); n++){
				try {
					if(server.entradas.get(n).available() > 0){
						String dato = server.entradas.get(n).readUTF();
						for(int s = 0; s<server.salidas.size(); s++){
							server.salidas.get(s).writeUTF(dato);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
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
				server.entradas.add(new DataInputStream(socket.getInputStream()));
				server.salidas.add(new DataOutputStream(socket.getOutputStream()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
