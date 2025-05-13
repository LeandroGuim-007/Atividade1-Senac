/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    conectaDAO cDAO = new conectaDAO();

    public void cadastrarProduto(ProdutosDTO produto) {
        try {
            conn = cDAO.connectDB();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO produtos(nome, valor, status) values (?,?,?)");
            ps.setString(1, produto.getNome());
            ps.setInt(2, produto.getValor());
            ps.setString(3, produto.getStatus());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar o produto, por  favor verifique os dados informados!");
            System.out.println(se.getMessage());
        } finally {
            cDAO.desconnectBD();
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            conn = cDAO.connectDB();
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM produtos");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProdutosDTO produto = new ProdutosDTO();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setValor(rs.getInt("valor"));
                produto.setStatus(rs.getString("status"));
                listagem.add(produto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cDAO.desconnectBD();
        }

        return listagem;
    }

    public boolean venderProduto(int id) {
        try {
            conn = cDAO.connectDB();
            PreparedStatement ps = conn.prepareStatement("UPDATE produtos SET status = \'vendido\' WHERE id = " + id);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
