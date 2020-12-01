package Dao;

import Bean.Jugadores;
import Bean.SeleccionesNacionales;

import java.sql.*;
import java.util.ArrayList;

public class DaoJugadores {

    public ArrayList<Jugadores> listarJugadores(){

        ArrayList<Jugadores> jugadoresArrayList = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "SELECT * FROM sw1lab8.jugadores";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Jugadores jugadores = new Jugadores();
                jugadores.setIdJugadores(rs.getInt(1));
                jugadores.setNombre(rs.getString(2));
                jugadores.setEdad(rs.getInt(3));
                jugadores.setPosicion(rs.getString(4));
                jugadores.setClub(rs.getString(5));
                jugadores.setSn_idSeleccion(rs.getInt(6));
                jugadoresArrayList.add(jugadores);
                System.out.println("ID: " + jugadores.getIdJugadores()
                        + " | nombre: " + jugadores.getNombre()
                        + " | Edad: " + jugadores.getEdad()
                        + " | posición: " + jugadores.getPosicion()
                        + " | Club: " + jugadores.getClub() );
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return jugadoresArrayList;
    }

    public Jugadores buscarJugadore(int id){

        Jugadores jugadores = new Jugadores();
        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "SELECT * FROM sw1lab8.jugadores where idJugadores= ?";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql);) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if(rs.next()){
                    jugadores.setIdJugadores(id);
                    jugadores.setNombre(rs.getString(2));
                    jugadores.setEdad(rs.getInt(3));
                    jugadores.setPosicion(rs.getString(4));
                    jugadores.setClub(rs.getString(5));
                    jugadores.setSn_idSeleccion(rs.getInt(6));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return jugadores;
    }

    public void crearJugador(Jugadores jugadores){

        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "INSERT INTO `sw1lab8`.`jugadores` (`nombre`, `edad`, `posicion`, `club`, `sn_idSeleccion`) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql , PreparedStatement.RETURN_GENERATED_KEYS);) {

            pstmt.setString(1, jugadores.getNombre());
            pstmt.setInt(2, jugadores.getEdad());
            pstmt.setString(3,jugadores.getPosicion());
            pstmt.setString(4,jugadores.getClub());
            pstmt.setInt(5, jugadores.getSn_idSeleccion());
            pstmt.executeUpdate();

        } catch (SQLException e) {

        }
    }

    public void actualizarJugador(Jugadores jugadores){

        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "UPDATE `sw1lab8`.`jugadores` SET `nombre` = ?, `edad` = ?, `posicion` = ?, `club` = ?, `sn_idSeleccion` = ?  WHERE (`idJugadores` = ?)";
        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql , PreparedStatement.RETURN_GENERATED_KEYS);) {

            pstmt.setString(1, jugadores.getNombre());
            pstmt.setInt(2, jugadores.getEdad());
            pstmt.setString(3,jugadores.getPosicion());
            pstmt.setString(4,jugadores.getClub());
            pstmt.setInt(5, jugadores.getSn_idSeleccion());
            pstmt.setInt(6,jugadores.getIdJugadores());
            pstmt.executeUpdate();

        } catch (SQLException e) {

        }
    }

    public void borrarJugador(int id){

        String url = "jdbc:mysql://localhost:3306/sw1lab8?serverTimezone=America/Lima";
        String sql = "DELETE FROM `sw1lab8`.`jugadores` WHERE (`idJugadores` = ?)";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql , PreparedStatement.RETURN_GENERATED_KEYS);) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {

        }
    }

}
