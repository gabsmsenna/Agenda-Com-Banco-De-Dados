package VIEW;

import DAO.ContatoDAO;
import DTO.ContatoDTO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class AgendaVIEW {
    private JPanel Main;
    private JLabel agendaTitulo;
    private JTextField nameTextField;
    private JTextField emailTextField;
    private JTextField telefoneTextField;
    private JButton buttonAdd;
    private JTable table1;
    private JButton buttonList;
    private JButton buttonUpdate;
    private JButton buttonDelete;
    private JButton carregarButton;
    private JTextField idTextField;
    private JLabel idLabel;


    public AgendaVIEW() {
        createTable();
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerContact();
                listarContatos();
            }
        });
        buttonList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            listarContatos();
            }
        });

        carregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                carregarCampos();
            }
        });
        buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarContato();
                listarContatos();
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirContato();
                listarContatos();
            }
        });
    }

    private void registerContact() {
        String nome, email, telefone;

        nome = nameTextField.getText();
        email = emailTextField.getText();
        telefone = telefoneTextField.getText();

        ContatoDTO objContatoDTO = new ContatoDTO();
        objContatoDTO.setName_user(nome);
        objContatoDTO.setEmail_user(email);
        objContatoDTO.setTelefone_user(telefone);

        ContatoDAO objContatoDao = new ContatoDAO();
        objContatoDao.cadastrarContato(objContatoDTO);
    }

    private void listarContatos() {
        try {
            ContatoDAO objContatoDao = new ContatoDAO();

            DefaultTableModel model = (DefaultTableModel) table1.getModel();
            model.setNumRows(4);

            ArrayList<ContatoDTO> lista = objContatoDao.PesquisarContato();

            for (int num = 0 ; num < lista.size(); num++) {
                model.addRow(new Object[]{
                        lista.get(num).getID_User(),
                        lista.get(num).getName_user(),
                        lista.get(num).getEmail_user(),
                        lista.get(num).getTelefone_user()
                });
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Listar Valores" + erro);
        }
    }

    private void carregarCampos() {
        int setar = table1.getSelectedRow(); //esta linha de código pega os valores de uma determinada linha, para depois percorrer as colunas

        idTextField.setText(table1.getModel().getValueAt(setar, 0).toString());
        nameTextField.setText(table1.getModel().getValueAt(setar, 1).toString());
        emailTextField.setText(table1.getModel().getValueAt(setar,2).toString());
        telefoneTextField.setText(table1.getModel().getValueAt(setar, 3).toString());

    }

    public void createTable() {
        table1.setModel(new DefaultTableModel(
                null,
                new String[]{"ID","Nome", "E-mail", "Telefone"}
        ));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VIEW");
        frame.setContentPane(new AgendaVIEW().Main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void editarContato() {
        int id_contato;
        String nome_contato, email_contato, telefone_contato;

        id_contato = Integer.parseInt(idTextField.getText()) ;
        nome_contato = nameTextField.getText();
        email_contato = emailTextField.getText();
        telefone_contato = telefoneTextField.getText();

        ContatoDTO objetoContatoDTO = new ContatoDTO();
        objetoContatoDTO.setID_User(id_contato);
        objetoContatoDTO.setName_user(nome_contato);
        objetoContatoDTO.setEmail_user(email_contato);
        objetoContatoDTO.setTelefone_user(telefone_contato);

        ContatoDAO objetoContatoDAO = new ContatoDAO();
        objetoContatoDAO.editarContato(objetoContatoDTO);


    }

    private void excluirContato() {
        int id_contato;

        id_contato = Integer.parseInt(idTextField.getText());

        ContatoDTO objContatoDTO = new ContatoDTO();
        objContatoDTO.setID_User(id_contato);

        ContatoDAO objContatoDAO = new ContatoDAO();
        objContatoDAO.excluirContato(objContatoDTO);

    }










}
