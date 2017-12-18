package modelo;

public class Premio {
    private int id;
    private String descripcion;
    private int precio;

    public Premio(int id, String descripcion, int precio) {
        this.id = id;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Premio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Premio[" + "id=" + id + ", descripcion=" + descripcion + ", precio=" + precio + ']';
    }
}
