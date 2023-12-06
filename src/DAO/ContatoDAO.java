package DAO;

import DTO.ContatoDTO;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ContatoDAO {

    Connection connection;
    PreparedStatement pstm;
    ResultSet resultSet;
    ArrayList<ContatoDTO> listaContatos = new ArrayList<>();

    public void cadastrarContato(ContatoDTO objContatoDTO) {
        String sql = "INSERT INTO users (nome, email, telefone) VALUES (?, ?, ?)";
        connection = new Conecta_DB().connectDB();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, objContatoDTO.getName_user());
            pstm.setString(2, objContatoDTO.getEmail_user());
            pstm.setString(3, objContatoDTO.getTelefone_user());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ContatoDAO Cadastrar " + erro); //Mostro a classe que está o problema, qual método/ação e concateno com a mensagem de erro
        }
    }

    public ArrayList<ContatoDTO> PesquisarContato() { //Resultset pega o data table e percorre o sql | Estava escrito da seguinte forma: public ResultSet<ContatoDTO> PesquisarContato()
        String sql = "SELECT * from users";
        connection = new Conecta_DB().connectDB();

        try {
            pstm = connection.prepareStatement(sql);
            resultSet = pstm.executeQuery();

            while (resultSet.next()) {
                ContatoDTO objContatoDTO = new ContatoDTO();
                objContatoDTO.setID_User(resultSet.getInt("idusers"));
                objContatoDTO.setName_user(resultSet.getString("nome"));
                objContatoDTO.setEmail_user(resultSet.getString("email"));
                objContatoDTO.setTelefone_user(resultSet.getString("telefone"));

                listaContatos.add(objContatoDTO);

            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Contato DAO Pesquisar: " + erro);
        }
        return listaContatos;
    }

    public void editarContato(ContatoDTO objContatoDTO) {
        String sql = "UPDATE users set nome = ?, email = ?, telefone = ? where idusers = ?";
        connection = new Conecta_DB().connectDB();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setString(1, objContatoDTO.getName_user());
            pstm.setString(2, objContatoDTO.getEmail_user());
            pstm.setString(3, objContatoDTO.getTelefone_user());
            pstm.setInt(4, objContatoDTO.getID_User());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ContatoDAO Editar " + erro);
        }
    }

    public void excluirContato(ContatoDTO objContatoDTO) {
        String sql = "DELETE from users where idusers = ?";
        connection = new Conecta_DB().connectDB();

        try {
            pstm = connection.prepareStatement(sql);
            pstm.setInt(1, objContatoDTO.getID_User());

            pstm.execute();
            pstm.close();

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ContatoDAO Excluir " + erro);
        }
    }
}



