/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareamvc;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author pinam
 */
public class Vista extends JFrame {

    private String ip;
    private int puerto;

    public JButton pesos, conectar;
    private JPanel panelB, panelR;
    public JLabel lResultado;
    public JTextField campoTexto;
    private ConexionAServidor conexionAServidor;

    public void inicializar() {
        getContentPane().setLayout(new BorderLayout());
        panelB = new JPanel();
        panelB.setLayout(new FlowLayout());

        panelR = new JPanel();
        panelR.setLayout(new FlowLayout());

        conectar = new JButton("Iniciar Conexión");
        this.conexionAServidor = new ConexionAServidor(this, ip, puerto);

        lResultado = new JLabel("Presione Botón Para Conectar");

        campoTexto = new JTextField(20);
        campoTexto.setEditable(false);
        campoTexto.setText("Esperando Mensaje");
        campoTexto.setHorizontalAlignment(JTextField.CENTER);

        panelB.add(conectar);

        panelR.add(lResultado);

        add(campoTexto, BorderLayout.NORTH);
        add(panelB, BorderLayout.SOUTH);
        add(panelR, BorderLayout.CENTER);

        this.setTitle("Panel de conexión");
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void inicializarDatosDelServidor(String ip, int puerto) {
        this.ip = ip;
        this.puerto = puerto;
    }

}

class ConexionAServidor implements ActionListener {

    private Vista vista;
    private String ip;
    private int puerto;

    public ConexionAServidor(Vista vista, String ip, int puerto) {
        this.ip = ip;
        this.puerto = puerto;
        this.vista = vista;
        vista.conectar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vista.conectar == e.getSource()) {
            try {
                Socket s1 = new Socket(this.ip, this.puerto);
                InputStream s1In = s1.getInputStream();
                DataInputStream dis = new DataInputStream(s1In);

                String st = new String(dis.readUTF());
                
                this.vista.campoTexto.setText(st);

                dis.close();
                s1In.close();
                s1.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

}
