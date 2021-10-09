/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import persistencia.ConexionBD;


public class Producto {
    private int idProducto;
    private Categoria idCategoria;
    private String codigonombre;
    private String precioVenta;
    private int stock;
    private String descripcion;
    private String estado;

        public Contacto getProducto(int idProducto) throws SQLException {
        this.idProducto = idProducto;
        return this.getProducto(idProducto);
    }
    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Categoria getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Categoria idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCodigonombre() {
        return codigonombre;
    }

    public void setCodigonombre(String codigonombre) {
        this.codigonombre = codigonombre;
    }

    public String getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(String precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Producto() {
    }
    
    
    
    public boolean guardarProducto() {
        ConexionBD conexion = new ConexionBD();
        String Sentencia = "INSERT into producto(idProducto, idCategoria, codigonombre, precioVenta, stock, descripcion,estado )"
                + "VALUES ('" + this.idProducto + "','" + this.idCategoria + "','" + this.codigonombre + "','" + this.precioVenta + "','" + this.stock + "','" + this.descripcion + "','"
                + this.estado+ "');";

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
    
        public boolean borarProducto() {
        ConexionBD conexion = new ConexionBD();
        String Sentencia = "DELETE FROM 'producto' WHERE 'idProducto' ='"+this.idProducto+"';";

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

    public boolean actualizarProducto() {
        ConexionBD conexion = new ConexionBD();
        String Sentencia = "UPDATE  'contacto' SET 'idCategoria'" + this.idCategoria + "'codigonombre'" + this.codigonombre + "'precioVenta'" + this.precioVenta + "'stock'" + this.stock + "'descripcion'" + this.descripcion + "'estado'" + this.estado;
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

    public List<Producto> listarProducto() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        List<Producto> listaProducto = new ArrayList<>();
        String sql = "select * from producto order by idProducto asc";
        ResultSet rs = conexion.consultarBD(sql);
        Producto c;

        while (rs.next()) {
            c = new Producto();
            c.setIdProducto(rs.getInt("idProducto"));
          //  c.setIdCategoria(rs.ge("idCategoria"));
            c.setCodigonombre(rs.getString("codigonombre"));
            c.setPrecioVenta(rs.getString("precioVenta"));
            c.setStock(rs.getInt("stock"));
            c.setDescripcion(rs.getString("descripcion"));
            c.setEstado(rs.getString("estado"));
            listaProducto.add(c);
        }
        conexion.cerrarConexion();
        return listaProducto;

    }

    public Producto getProducto() throws SQLException {
        ConexionBD conexion = new ConexionBD();
        String sql = "select * from producto where idProducto='" + this.idProducto + "'";
        ResultSet rs = conexion.consultarBD(sql);
        if (rs.next()) {
            this.idProducto = rs.getInt("idProducto");
            //this.idCategoria = rs.getString("idCategoria");
            this.codigonombre = rs.getString("codigonombre");
            this.precioVenta = rs.getString("precioVenta");
            this.stock = rs.getInt("stock");
            this.descripcion = rs.getString("descripcion");
            this.estado = rs.getString("estado");
            return this;

        }else{

        conexion.cerrarConexion();
        return null;

    } 
    }

    @Override
    public String toString(){
    return "producto("+"idProducto"+idProducto+", idCategoria"+idCategoria
            +", codigonombre"+codigonombre+", precioVenta"+precioVenta
            +", stock"+stock+", descripcion"+descripcion+", estado"+estado+"')'";
    }

 



    
  
}
