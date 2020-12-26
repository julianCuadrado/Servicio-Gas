package co.com.abc.logica;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import co.com.abc.Pago;
import co.com.abc.PagosInerface;
import co.com.abc.PagosService;
import co.com.abc.ReferenciaFactura;
import co.com.abc.Resultado;
import co.com.abc.ResultadoConsulta;
import co.com.abc.dto.MensajeDTO;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.BindingProvider;

/**
 *
 * @author Jcuadrado
 */
public class ClienteGas {
    
    private static final Logger LOGGER = Logger.getLogger(ClienteGas.class.getName());
    
    private static final String URL_GAS_WS = "http://130.211.116.156/gas-service/PagosService?wsdl";
    private static final String ENDPOINT_GAS_WS = "http://130.211.116.156/gas-service/PagosService";
    
    public MensajeDTO pagar(String referenciaPago, double valorTotalPagar){
        MensajeDTO salida = new MensajeDTO();
        try {
            PagosService servicio = new PagosService(new URL(URL_GAS_WS));
            PagosInerface ws = servicio.getPagosPort();
            BindingProvider bp = (BindingProvider) ws;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT_GAS_WS);
            ReferenciaFactura referenciaFactura = new ReferenciaFactura();
            referenciaFactura.setReferenciaFactura(referenciaPago);
            Pago entrada = new Pago();
            entrada.setReferenciaFactura(referenciaFactura);
            entrada.setTotalPagar(valorTotalPagar);
            Resultado resultado = ws.pagar(entrada);
            salida.setEstado(Boolean.TRUE);
            salida.setObject(resultado);
        } catch (Exception ex) {
            salida.setDescripcion("Ocurrio un error al pagar la factura, por favor intente mas tarde.");
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return salida;
    }
    
    public MensajeDTO consultar(String referenciaPago){
        MensajeDTO salida = new MensajeDTO();
        try {
            PagosService servicio = new PagosService(new URL(URL_GAS_WS));
            PagosInerface ws = servicio.getPagosPort();
            BindingProvider bp = (BindingProvider) ws;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT_GAS_WS);
            ReferenciaFactura referenciaFactura = new ReferenciaFactura();
            referenciaFactura.setReferenciaFactura(referenciaPago);
            ResultadoConsulta consulta = ws.cosultar(referenciaFactura);
            salida.setEstado(Boolean.TRUE);
            salida.setObject(consulta);
        } catch (Exception ex) {
            salida.setDescripcion("Ocurrio un error al consultar la factura, por favor intente mas tarde.");
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return salida;
    }
    
    public MensajeDTO compensar(String referenciaPago, double totalPagar){
        MensajeDTO salida = new MensajeDTO();
        try {
            PagosService servicio = new PagosService(new URL(URL_GAS_WS));
            PagosInerface ws = servicio.getPagosPort();
            BindingProvider bp = (BindingProvider) ws;
            bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT_GAS_WS);
            ReferenciaFactura referenciaFactura = new ReferenciaFactura();
            referenciaFactura.setReferenciaFactura(referenciaPago);
            Pago pago = new Pago();
            pago.setReferenciaFactura(referenciaFactura);
            pago.setTotalPagar(totalPagar);
            Resultado resultado = ws.compensar(pago);
            salida.setEstado(Boolean.TRUE);
            salida.setObject(resultado);
        } catch (Exception ex) {
            salida.setDescripcion("Ocurrio un error realizando la compensacion, por favor intente mas tarde.");
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return salida;
    }
}
