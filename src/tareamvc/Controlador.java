/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareamvc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static jdk.nashorn.internal.objects.NativeError.printStackTrace;

/**
 *
 * @author pinam
 */
public class Controlador {

    public void iniciarServlet(int puerto) {
        new Servidor(puerto).start();
    }

}

class Servidor extends Thread {

    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }

    @Override
    public void run() {
        ServerSocket server = null;

        try {
            server = new ServerSocket(this.puerto);
            System.out.println("Servidor ejecutándose");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        while (true) {
            try {
                Socket sPrivado = server.accept();
                new ConexionPrivada(sPrivado).start();

            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}

class ConexionPrivada extends Thread {

    protected Socket socket;

    public ConexionPrivada(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        OutputStream s1out;
        try {
            s1out = this.socket.getOutputStream();
            DataOutputStream dos = new DataOutputStream(s1out);
            dos.writeUTF("" + new Modelo().recibirDatosDeBaseDeDatos());
            dos.close();
            s1out.close();
            this.socket.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
