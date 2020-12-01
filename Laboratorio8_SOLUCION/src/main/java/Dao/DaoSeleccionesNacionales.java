package Dao;

import Bean.Estadios;
import Bean.SeleccionesNacionales;

import java.sql.*;
import java.util.ArrayList;

public class DaoSeleccionesNacionales {

    public ArrayList<SeleccionesNacionales> listarSelecciones(){

        ArrayList<SeleccionesNacionales> seleccionesNacionalesArrayList = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "SELECT * FROM sw1lab8.seleccionesnacionales";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SeleccionesNacionales seleccionesNacionales = new SeleccionesNacionales();
                seleccionesNacionales.setIdSeleccionesNacionales(rs.getInt(1));
                seleccionesNacionales.setNombre(rs.getString(2));
                seleccionesNacionales.setTecnico(rs.getString(3));
                seleccionesNacionales.setEstadios_idEstadio(rs.getInt(4));
                seleccionesNacionalesArrayList.add(seleccionesNacionales);
                System.out.println("ID: " + seleccionesNacionales.getIdSeleccionesNacionales()
                        + " | nombre: " + seleccionesNacionales.getNombre()
                        + " | Director Técnico: " + seleccionesNacionales.getTecnico()
                        + " | Estadio: " + seleccionesNacionales.getEstadios_idEstadio() );
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return seleccionesNacionalesArrayList;
    }

    public ArrayList<Estadios> listarEstadios(){

        ArrayList<Estadios> estadiosArrayList = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "SELECT * FROM sw1lab8.estadios";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Estadios estadios= new Estadios();
                estadios.setIdEstadios(rs.getInt(1));
                estadios.setNombre(rs.getString(2));
                estadios.setProvincia(rs.getString(3));
                estadios.setClub(rs.getString(4));
                estadiosArrayList.add(estadios);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return estadiosArrayList;
    }


    public void crearSeleccion(SeleccionesNacionales seleccionesNacionales){

        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "INSERT INTO `sw1lab8`.`seleccionesnacionales` (`nombre`, `tecnico`, `estadios_idEstadios`) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql , PreparedStatement.RETURN_GENERATED_KEYS);) {

            pstmt.setString(1, seleccionesNacionales.getNombre());
            pstmt.setString(2, seleccionesNacionales.getTecnico());
            pstmt.setInt(3,seleccionesNacionales.getEstadios_idEstadio());
            pstmt.executeUpdate();

        } catch (SQLException e) {

        }
    }

    public void actualizarSeleccion(SeleccionesNacionales seleccionesNacionales){

        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "UPDATE `sw1lab8`.`seleccionesnacionales` SET `nombre` = ?, `tecnico` = ?, `estadios_idEstadios` = ? WHERE (`idSeleccionesNacionales` = ?);";
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql , PreparedStatement.RETURN_GENERATED_KEYS);) {

            pstmt.setString(1, seleccionesNacionales.getNombre());
            pstmt.setString(2, seleccionesNacionales.getTecnico());
            pstmt.setInt(3,seleccionesNacionales.getEstadios_idEstadio());
            pstmt.setInt(4,seleccionesNacionales.getIdSeleccionesNacionales());
            pstmt.executeUpdate();

        } catch (SQLException e) {

        }
    }
    public void borrarSeleccion(int id){

        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "DELETE FROM `sw1lab8`.`seleccionesnacionales` WHERE (`idSeleccionesNacionales` = ?)";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql , PreparedStatement.RETURN_GENERATED_KEYS);) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {

        }
    }

    public SeleccionesNacionales buscarSeleccion(int id){

        SeleccionesNacionales seleccionesNacionales = new SeleccionesNacionales();
        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "SELECT * FROM sw1lab8.seleccionesnacionales where idSeleccionesNacionales = ?";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    seleccionesNacionales.setIdSeleccionesNacionales(id);
                    seleccionesNacionales.setNombre(rs.getString(2));
                    seleccionesNacionales.setTecnico(rs.getString(3));
                    seleccionesNacionales.setEstadios_idEstadio(rs.getInt(4));
                    System.out.println("ID: " + seleccionesNacionales.getIdSeleccionesNacionales()
                            + " | nombre: " + seleccionesNacionales.getNombre()
                            + " | Director Técnico: " + seleccionesNacionales.getTecnico()
                            + " | Estadio: " + seleccionesNacionales.getEstadios_idEstadio() );
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return seleccionesNacionales;
    }



}
