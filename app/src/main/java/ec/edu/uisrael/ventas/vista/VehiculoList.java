package ec.edu.uisrael.ventas.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import ec.edu.uisrael.ventas.R;
import ec.edu.uisrael.ventas.controlador.AdapterVehiculo;
import ec.edu.uisrael.ventas.controlador.ControllerVehiculo;
import ec.edu.uisrael.ventas.controlador.ControllerViews;


public class VehiculoList extends AppCompatActivity {
    private ControllerVehiculo adminSale;
    private Intent vehiculoAddUpdate;
    private ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        adminSale = ControllerVehiculo.getInstance();
        AdapterVehiculo adaptador;
        lista = (ListView) findViewById(R.id.listsale);
        //Utilizando la vista adapter obtenemos el listado de Vehiculos
        adaptador = new AdapterVehiculo(this, android.R.layout.simple_list_item_1, adminSale.listar());
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                irViewVehiculoAppUpdate( position + 1);
            }
        });

    }

    public void onclicknew (View v) {
        irViewVehiculoAppUpdate(0);
    }

    private void irViewVehiculoAppUpdate(int posicion){
        /// Invocamos a la ventana Actualiza/agregar Vehiculo, pasamos por parametro la posicion de la lista.
        vehiculoAddUpdate = ControllerViews.getViewVehAddUpdate(VehiculoList.this, VehiculoAddUpdate.class);
        vehiculoAddUpdate.putExtra("posicion", posicion);
        startActivity(vehiculoAddUpdate);
        finish();
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            getFragmentManager().popBackStack();
        } else {
            finish();
        }

    }
}
