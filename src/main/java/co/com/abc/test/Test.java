/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.abc.test;

import co.com.abc.dto.MensajeDTO;
import co.com.abc.logica.ClienteGas;

/**
 *
 * @author Jcuadrado
 */
public class Test {
    
    public static void main(String[] args) {
        ClienteGas clienteGas = new ClienteGas();
        MensajeDTO salida = clienteGas.consultar("122");
        System.out.println(salida);
    }
}
