/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.abc.dto;

import lombok.Data;
/**
 *
 * @author Jcuadrado
 */
public @Data class MensajeDTO {
    
    private boolean estado;
    private Object object;
    private String descripcion;

    public MensajeDTO() {
        this.estado = Boolean.FALSE;
    }
}