package ec.edu.uisrael.ventas.modelo;

import java.util.Date;

/**
 * Created by Victor on 28/11/2017.
 */

public class Vehiculo {
    //Integer acepta nulos, 
    private int codigo;
    private String placa;
    private String marca;
    private String color;
    private Date fecha;
    private boolean estado;

    public Vehiculo() {
    }

    public Vehiculo(int codigo, String placa, String marca, String color, Date fecha, boolean estado) {
        this.codigo = codigo;
        this.placa = placa;
        this.marca = marca;
        this.color = color;
        this.fecha = fecha;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    //implementar equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehiculo)) return false;

        Vehiculo vehiculo = (Vehiculo) o;

        return getCodigo() == vehiculo.getCodigo();

    }

    @Override
    public int hashCode() {
        return getCodigo();
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    //Agregar metodo de to string
}
