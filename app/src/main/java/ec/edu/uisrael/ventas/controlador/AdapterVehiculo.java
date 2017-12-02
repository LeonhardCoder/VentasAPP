package ec.edu.uisrael.ventas.controlador;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ec.edu.uisrael.ventas.R;
import ec.edu.uisrael.ventas.modelo.Vehiculo;

/**
 * Created by Victor on 29/11/2017.
 */

public class AdapterVehiculo extends ArrayAdapter<Vehiculo> {
    List<Vehiculo> aListVehiculo = new ArrayList<>();
    private static class ViewHolder {
        TextView placa;
        TextView marca;
        TextView color;
        TextView estado;
    }

    public AdapterVehiculo(@NonNull Context context, @LayoutRes int resource, @NonNull List<Vehiculo> objects) {
        super(context, resource, objects);
        aListVehiculo = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        //LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //v = inflater.inflate(R.layout.activity_sale, null);
       // Get the data item for this position
        Vehiculo vh = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_sale, parent, false);
            viewHolder.placa = (TextView) convertView.findViewById(R.id.tPlaca);
           viewHolder.marca = (TextView) convertView.findViewById(R.id.tMarca);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.placa.setText(vh.getPlaca());
        viewHolder.marca.setText(vh.getMarca());
        // Return the completed view to render on screen
        return convertView;


    }
}
