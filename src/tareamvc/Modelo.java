/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tareamvc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author pinam
 */
public class Modelo {

    public String recibirDatosDeBaseDeDatos() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return "Te mando un hola a las: " + sdf.format(cal.getTime());
    }
}
