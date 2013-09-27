package chat;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ClientView {

	JFrame frame;
	private JTextField mensajes;
	private JTextArea respuestas;
	private JPanel panel;
	private JCheckBox conectar;
	private JTextField url;
	private JLabel lblServidor;
	private Client client;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientView window = new ClientView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mensajes = new JTextField();
		mensajes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				client.enviaMensaje(mensajes.getText());
				mensajes.setText("");
			}
		});
		frame.getContentPane().add(mensajes, BorderLayout.SOUTH);
		mensajes.setColumns(10);

		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);

		lblServidor = new JLabel("Servidor");
		panel.add(lblServidor);

		url = new JTextField();
		panel.add(url);
		url.setColumns(10);

		conectar = new JCheckBox("Conectar");
		conectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		panel.add(conectar);

		respuestas = new JTextArea();
		scrollPane = new JScrollPane(respuestas);
		frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void muestraMensaje(String cad) {
		respuestas.append(cad + "\n");
		Rectangle rect = scrollPane.getVisibleRect();
		rect.y = respuestas.getHeight() - rect.height;
		scrollPane.scrollRectToVisible(rect);
	}
}
