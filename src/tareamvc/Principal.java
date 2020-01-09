/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareamvc;

/**
 *
 * @author pinam
 */
public class Principal {

    public static void main(String arf[]) {
        //Modelo modelo = new Modelo();
        Vista vista = new Vista();
        vista.inicializarDatosDelServidor("127.0.0.1", 1234);
        vista.inicializar();
        
        Controlador controlador = new Controlador();
        controlador.iniciarServlet(1234);

    }
}
