package ec.edu.uisrael.ventas.vista;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ec.edu.uisrael.ventas.R;
import ec.edu.uisrael.ventas.controlador.ControllerVehiculo;
import ec.edu.uisrael.ventas.controlador.ControllerViews;
import ec.edu.uisrael.ventas.modelo.Vehiculo;

public class VehiculoAddUpdate extends AppCompatActivity {
    private EditText etPlaca;
    private EditText etMarca;
    private EditText etcolor;
    private EditText etDate;
    private CheckBox ckestado;
    private Button btnsave;
    private Button btncancl;
    private Button btndelete;
    private ControllerVehiculo adminSale;
    private DatePickerDialog fromDatePickerDialog;
    private SimpleDateFormat dateFormatter;
    private int posicion_list;
    private Intent viewListVh;
    private Vehiculo vh = new Vehiculo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sale);
        etPlaca = (EditText) findViewById(R.id.txtplaca);
        etMarca = (EditText) findViewById(R.id.txtmarca);
        etcolor = (EditText) findViewById(R.id.txtcolor);
        ckestado = (CheckBox) findViewById(R.id.estado);
        adminSale = ControllerVehiculo.getInstance();
        etDate = (EditText) findViewById(R.id.etPlannedDate);
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        btnsave = (Button) findViewById(R.id.btnguardar);
        btncancl = (Button) findViewById(R.id.btncancelar);
        btndelete =(Button) findViewById(R.id.btndelete);
        posicion_list = getIntent().getExtras().getInt("posicion");
        if (posicion_list > 0) {
            ckestado.setChecked(false);
            obtenerObjeto(posicion_list-1);
            btndelete.setVisibility(View.VISIBLE);
            btnsave.setText(R.string.action_update_sale);
        }else {
            btnsave.setText(R.string.action_save_sale);
            etDate.setText(dateFormatter.format(Calendar.getInstance().getTime()).toString());
            ckestado.setChecked(true);
            btndelete.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onclickSave (View v) {
        /** Invoca a los metodos actualizar o guardar dependiendo del contexto.
         *  Crea un objeto apartir de los datos de la vista.
         * */
        String placa = etPlaca.getText().toString().toUpperCase();
        String marca = etMarca.getText().toString().toUpperCase();
        String color = etcolor.getText().toString().toUpperCase();
        boolean estado = ckestado.isChecked();
        Date fecha = new Date();
        try {
            fecha = dateFormatter.parse(etDate.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (placa.isEmpty() || placa.equals(null)){
            Toast.makeText(VehiculoAddUpdate.this, getString(R.string.sms_error_placa), Toast.LENGTH_SHORT).show();
            return;
        }
       if (marca.isEmpty() || marca.equals(null)){
            Toast.makeText(VehiculoAddUpdate.this, getString(R.string.sms_error_marca), Toast.LENGTH_SHORT).show();
            return;
        }
        if (color.isEmpty() || color.equals(null)){
            Toast.makeText(VehiculoAddUpdate.this, getString(R.string.sms_error_color), Toast.LENGTH_SHORT).show();
            return;
        }
        Pattern mPattern = Pattern.compile("^[A-Z]{3}[0-9]{4}$");
        Pattern mLetras = Pattern.compile("^[A-Za-z]*$");
        Matcher matcherPlaca = mPattern.matcher(placa.toString());
        if(!matcherPlaca.matches()){
            Toast.makeText(VehiculoAddUpdate.this, getString(R.string.sms_validar_placa), Toast.LENGTH_SHORT).show();
            return;
        }
        Matcher matMarca = mLetras.matcher(marca.toString());
        if(!matMarca.matches()){
            Toast.makeText(VehiculoAddUpdate.this, getString(R.string.sms_validar_marca), Toast.LENGTH_SHORT).show();
            return;
        }
        Matcher matcolor = mLetras.matcher(color);
        if(!matcolor.matches()){
            Toast.makeText(VehiculoAddUpdate.this, getString(R.string.sms_validar_color), Toast.LENGTH_SHORT).show();
            return;
        }

        vh.setMarca(marca);
        vh.setPlaca(placa);
        vh.setColor(color);
        vh.setFecha(fecha);
        vh.setEstado(estado);

        try {
            if (posicion_list > 0 ) {
                adminSale.updatevehiculo(vh, posicion_list - 1);
                Toast.makeText(VehiculoAddUpdate.this, getString(R.string.sms_update), Toast.LENGTH_SHORT).show();
            }
            else {
                adminSale.addvehiculo(vh);
                Toast.makeText(VehiculoAddUpdate.this, getString(R.string.sms_save), Toast.LENGTH_SHORT).show();
            }
            regresar();

        }catch (Exception ex){
            Toast.makeText(VehiculoAddUpdate.this, getString(R.string.sms_error_save) + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onclickDelete (View v) {
        try {
            if (posicion_list > 0 ){
                adminSale.removevehiculo(posicion_list-1);
                Toast.makeText(VehiculoAddUpdate.this, getString(R.string.sms_ok_delete), Toast.LENGTH_SHORT).show();
                regresar ();
            }
        } catch (Exception e) {
            Toast.makeText(VehiculoAddUpdate.this, getString(R.string.sms_error_delete), Toast.LENGTH_SHORT).show();
        }
    }
    public void onclickCancel (View v) {
        regresar();
    }

    public void regresar () {
        /** Invoca a la vista VehiculoListar
         * */
        viewListVh = ControllerViews.getViewVehList(VehiculoAddUpdate.this, VehiculoList.class);
        startActivityForResult (viewListVh,1);
        finish();
    }

    public void onClickDate(View view) {
        /**Invoca DatePickerDialog para seleccionar la fecha formato (Year-Month-Day)
         * */
        if (view == etDate) {
            if (fromDatePickerDialog == null){
                Calendar newCalendar = Calendar.getInstance();
                fromDatePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        etDate.setText(dateFormatter.format(newDate.getTime()));
                    }
                },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
            }
            fromDatePickerDialog.show();
        }
    }

    private void  obtenerObjeto(int posicion_list)
    {
        /** Obtenenos el objeto Vehiculo segun la posicion de la lista seleccionada.
         *  llenamos el formulario con los campos del objeto recuperado
        * */
        Vehiculo vhmod = adminSale.obtenerVehiculo(posicion_list);
        if (!vhmod.equals(null)){
            etPlaca.setText(vhmod.getPlaca());
            etMarca.setText(vhmod.getMarca());
            etcolor.setText(vhmod.getColor());
            ckestado.setChecked(vhmod.isEstado());
            etDate.setText(dateFormatter.format(vhmod.getFecha()));
        }

    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
        } else {
            finish();
        }

    }



}
