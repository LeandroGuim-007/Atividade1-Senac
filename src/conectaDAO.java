
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

    private Connection conn = null;
    private static final Logger LOGGER = Logger.getLogger(conectaDAO.class.getName());

    public Connection connectDB() {

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/uc11?useSSL=false&user=root&password=Leandro007.");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + erro.getMessage());
        }
        return conn;
    }

    public void desconnectBD() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                LOGGER.info("desconectado com sucesso");
            }
        } catch (SQLException ex) {
            LOGGER.info("erro para desconectar");
        }
    }
}
