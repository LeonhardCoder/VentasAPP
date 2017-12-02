package ec.edu.uisrael.ventas.controlador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ec.edu.uisrael.ventas.modelo.Usuario;

import static android.R.id.list;

/**
 * Created by Victor on 28/11/2017.
 */

public class ControllerLogin {
    private List<Usuario> ltuser = new ArrayList<>();

    public boolean login (String user, String pwd ) {
        /**
         *  Valida el usuario y contraseña.
         */
        if (ltuser.isEmpty() || ltuser.size() == 0){
            Usuario usr1 = new Usuario("Victor","Salazar");
            Usuario usr2 = new Usuario("Veronica","Munoz");
            Usuario usr3 = new Usuario("Cristina","Freire");
            Usuario usr4 = new Usuario("David","Achina");
            Usuario admin = new Usuario("admin","1");
            ltuser.add(usr1);
            ltuser.add(usr2);
            ltuser.add(usr3);
            ltuser.add(usr4);
            ltuser.add(admin);
        }
        if ((user == null) || (pwd == null)) return false;
        for(Usuario usr : ltuser){
            if (user.equals(usr.getUsuario()) && pwd.equals(usr.getClave())){
                return  true;
            }
        }
        return false;
    }
}
