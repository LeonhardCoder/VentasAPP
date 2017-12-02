package ec.edu.uisrael.ventas.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import ec.edu.uisrael.ventas.R;
import ec.edu.uisrael.ventas.controlador.ControllerVehiculo;
import ec.edu.uisrael.ventas.modelo.Vehiculo;

public class VehiculoUpdate extends AppCompatActivity {
    private int posicion_list;
    private List<Vehiculo> datos;
    private ControllerVehiculo adminSale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sale);
        adminSale = ControllerVehiculo.getInstance();
        posicion_list = getIntent().getExtras().getInt("posicion");
        datos = adminSale.listar();
        obtenerObjeto(posicion_list);
    }

    private void  obtenerObjeto(int posicion_list)
    {
        if (posicion_list >= 0){
            Vehiculo vhdata = datos.get(posicion_list);
            if (!vhdata.equals(null)) {

            }

        }

    }

}
