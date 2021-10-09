/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.StyledEditorKit;
import persistencia.ConexionBD;

public class Contacto {

    private int identificacion;
    private String nombre;
    private String apellido;
    private String genero;
    private String tipoIdentificacion;
    private String telefono;
    private String direccion;
    private String correo;

    public Contacto() {
    }

    public Contacto getContacto(int identificacion) throws SQLException {
        this.identificacion = identificacion;
        return this.getContacto();
    }

    public int getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(int identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public void setTipoIdentificacion(String tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

   

    public boolean guardarContacto() {
        ConexionBD conexion = new ConexionBD();
        String Sentencia = "INSERT into contacto(identificacion, nombre, apellido, genero, tipoIdentificacion, telefono, direccion, correo)"
                + "VALUES ('" + this.identificacion + "','" + this.nombre + "','" + this.apellido + "','" + this.genero + "','" + this.tipoIdentificacion + "','" + this.telefono + "','"
                + this.direccion + "','" + this.correo + "');";

        if (conexion.setAutoCommitBD(false)) {
            if (conexion.insertarBD(Sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }

    }
    
        public boolean borarContacto() {
        ConexionBD conexion = new ConexionBD();
        String Sentencia = "DELETE FROM 'contacto' WHERE 'identificacion' ='"+this.identificacion+"';";

        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(Sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }
        } else {
            conexion.cerrarConexion();
            return false;
        }

    }

    public boolean actualizarContacto() {
        ConexionBD conexion = new ConexionBD();
        String Sentencia = "UPDATE  'contacto' SET 'nombre'" + this.nombre + "'apellido'" + this.apellido + "'genero'" + this.genero + "'tipoIdentificacion'" + this.tipoIdentificacion + "'telefono'" + this.telefono + "'direccion'" + this.direccion + "'correo'" + this.correo;
        if (conexion.setAutoCommitBD(false)) {
            if (conexion.actualizarBD(Sentencia)) {
                conexion.commitBD();
                conexion.cerrarConexion();
                return true;
            } else {
                conexion.rollbackBD();
                conexion.cerrarConexion();
                return false;
            }

        } else {

            conexion.cerrarConexion();
            return false;
        }
    }

    public List<Contacto> listarContatos() throws SQLException {
        ConexionBD conexion = new ConexionBD();

        List<Contacto> listaContactos = new ArrayList<>();
        String sql = "select * from contacto order by identificacion asc";
        ResultSet rs = conexion.consultarBD(sql);

        Contacto c;

        while (rs.next()) {
            c = new Contacto();
            c.setIdentificacion(rs.getInt("identificacion"));
            c.setNombre(rs.getString("nombre"));
            c.setApellido(rs.getString("apellido"));
            c.setGenero(rs.getString("genero"));
            c.setTipoIdentificacion(rs.getString("tipoIdentificacon"));

            c.setTelefono(rs.getString("telefono"));
            c.setDireccion(rs.getString("direccion"));
            c.setCorreo(rs.getString("correo"));
            listaContactos.add(c);
        }
        conexion.cerrarConexion();
        return listaContactos;

    }

    public Contacto getContacto() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        String sql = "select * from contacto where identificacion='" + this.identificacion + "'";
        ResultSet rs = conexion.consultarBD(sql);
        if (rs.next()) {
            this.identificacion = rs.getInt("identificacion");
            this.nombre = rs.getString("nombre");
            this.apellido = rs.getString("apellido");
            this.genero = rs.getString("genero");
            this.tipoIdentificacion = rs.getString("tipoIdentificacion");
            this.telefono = rs.getString("telefono");
            this.direccion = rs.getString("direccion");
            this.correo = rs.getString("correo");
            return this;

        }else{

        conexion.cerrarConexion();
        return null;

    } 
    }

    @Override
    public String toString(){
    return "contacto("+"identificacion"+identificacion+", nombre"+nombre+", apellido"+apellido+", genero"+genero+", tipoIdentificacion"+tipoIdentificacion+", telefono"+telefono+", direccion"+direccion+", correo"+correo+"')'";
    }
    
    
}
