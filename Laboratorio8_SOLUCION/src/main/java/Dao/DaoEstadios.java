package Dao;

import Bean.Estadios;
import Bean.Jugadores;

import java.sql.*;
import java.util.ArrayList;

public class DaoEstadios {

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
                System.out.println("ID: " + estadios.getIdEstadios()
                        + " | nombre: " + estadios.getNombre()
                        + " | Provincia: " + estadios.getProvincia()
                        + " | Club: " + estadios.getClub() );
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return estadiosArrayList;
    }


}
