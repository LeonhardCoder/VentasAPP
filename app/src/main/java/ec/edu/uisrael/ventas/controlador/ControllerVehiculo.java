package ec.edu.uisrael.ventas.controlador;

import java.util.ArrayList;
import java.util.List;

import ec.edu.uisrael.ventas.modelo.Vehiculo;

/**
 * Created by Victor on 29/11/2017.
 */

public class ControllerVehiculo {
    private List<Vehiculo> datos = new ArrayList<>();
    private static final ControllerVehiculo adminsale = new ControllerVehiculo();

    public static ControllerVehiculo getInstance() {
        return adminsale;
    }

    private ControllerVehiculo() {
    }

    public  ArrayList<Vehiculo> listar(){
        return (ArrayList<Vehiculo>) datos;
    }

    public boolean  addvehiculo(Object obj) throws Exception {
        Vehiculo vh = (Vehiculo) obj;
        if (datos.contains(vh)) return false;
        vh.setCodigo(datos.size()+ 1);
        datos.add(vh);
        return true;
    }

    public boolean  updatevehiculo( Vehiculo vh, int id)throws Exception {
        Vehiculo vhmodif = datos.get(id);
        vhmodif.setPlaca(vh.getPlaca());
        vhmodif.setMarca(vh.getMarca());
        vhmodif.setColor(vh.getColor());
        vhmodif.setFecha(vh.getFecha());
        vhmodif.setEstado(vh.isEstado());
        return true;
    }

    public boolean  removevehiculo(int id) throws Exception {
        datos.remove(id);
        return true;
    }

    public Vehiculo obtenerVehiculo(int posicion_list)
    {
        Vehiculo vhdata = null;
        if (posicion_list >= 0){
            vhdata = datos.get(posicion_list);
        }
        return vhdata;
    }
}
