/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.service;

import dao.model.Usuario;
import java.util.Date;

/**
 *
 * @author José Pablo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("comencee");
        UsuarioDao dao = new UsuarioDao();
        Usuario test = new Usuario("as", "zx", "zx", "asd", "1192","M","123", new Date(), 12.0);
        dao.insert(test);
        System.out.println("termineeeee");
        //test.buscarUsuarios();

    }
    
}
