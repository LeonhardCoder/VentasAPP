package ec.edu.uisrael.ventas.controlador;

import android.content.Intent;

import ec.edu.uisrael.ventas.vista.MainActivity;
import ec.edu.uisrael.ventas.vista.VehiculoAddUpdate;
import ec.edu.uisrael.ventas.vista.VehiculoList;

/**
 * Created by Victor on 30/11/2017.
 */

public class ControllerViews {
    private static Intent loginok = null;
    private static Intent vehiculoAddUpdate = null;
    private static Intent vehiculoAdd = null;
    private static Intent vehiculoList = null;
    private static final ControllerViews adminview = new ControllerViews();

    public static ControllerViews getInstance() {
        return adminview;
    }

    private ControllerViews() {
    }

    public static Intent getViewlogin(MainActivity mainActivity, Class<VehiculoList> vehiculoListClass) {
        if (loginok == null)
            loginok = new Intent(mainActivity,vehiculoListClass);
        return loginok;
    }

    public static Intent getViewVehAddUpdate(VehiculoList vehiculoList, Class<VehiculoAddUpdate> vehiculoAddUpdateClass) {
        if (vehiculoAddUpdate == null)
            vehiculoAddUpdate = new Intent(vehiculoList,vehiculoAddUpdateClass);
        return vehiculoAddUpdate;
    }

    public static Intent getViewVehList(VehiculoAddUpdate vehiculoAddUpdate, Class<VehiculoList> vehiculoListClass) {
        if (vehiculoList == null)
            vehiculoList = new Intent(vehiculoAddUpdate,vehiculoListClass);
        return vehiculoList;
    }
}
