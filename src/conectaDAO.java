
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adm
 */
public class conectaDAO {

    Connection conn = null;

    public Connection connectDB() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?user=root&password=Leandro007.");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }

    public void desconnectBD() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("desconectado com sucesso");
            }
        } catch (SQLException ex) {
        }
    }
}
