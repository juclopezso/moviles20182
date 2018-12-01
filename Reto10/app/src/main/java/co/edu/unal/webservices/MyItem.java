package co.edu.unal.webservices;

/**
 * Created by vr on 11/19/17.
 */

public class MyItem {
    private String departamento;
    private String municipio;
    private String direccion;
    private double latitud;
    private double longitud;
    private String zona_inagurada;

    public MyItem(){}

    public MyItem(String departamento, String municipio, String direccion, double latitud, double longitud, String zona_inagurada) {
        this.departamento = departamento;
        this.municipio = municipio;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.zona_inagurada = zona_inagurada;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getZona_inagurada() {
        return zona_inagurada;
    }

    public void setZona_inagurada(String zona_inagurada) {
        this.zona_inagurada = zona_inagurada;
    }

    @Override
    public String toString() {
        return "Departamento: " + departamento + '\n'
                + "Municipio: " + municipio + '\n'
                + "Dirección: " + direccion + '\n'
                + "Coordenadas: " + latitud + ", " + longitud + '\n'
                + "Inagurado: " + zona_inagurada + '\n';
    }
}
