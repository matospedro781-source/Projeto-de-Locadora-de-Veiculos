/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.com.vroom.abas;

import java.sql.*;
import br.com.vroom.dal_cad.Principal;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.sql.PreparedStatement;
import java.sql.SQLException;
// alinha abaixo importa recursos da biblioteca rs2xml
import net.proteanit.sql.DbUtils;

/**
 * Esta classe representa uma janela para gerenciamento de alocação de veículos.
 */
public class Aba_ALOCAMENTO extends javax.swing.JFrame {

    double valortotal;
    int diasAluguel;
    double valorCarro;

    //usando variavel conexao do DAL(CAD)
    Connection conexao = null;
    /*
    PreparedStatement e ResultSet são frameworks do package java.sql
    Eles servem para preparar e executar as instruções SQL
     */
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Construtor da classe. Inicializa os componentes da interface gráfica e
     * estabelece a conexão com o banco de dados.
     */
    public Aba_ALOCAMENTO() {
        initComponents();
        conexao = Principal.conector();
    }

    /**
     * Método para calcular o custo total com base nas datas de alocação e
     * devolução e no valor do carro.
     *
     * @param dataAloc Data de alocação do veículo
     * @param dataDev Data de devolução do veículo
     * @param valorCarro Valor do veículo por dia
     * @return O custo total da alocação
     */
    private double calcularCustoTotal(String dataAloc, String dataDev, double valorCarro) {
        // Cálculo do número de dias de alocação
        // Calcula a diferença em dias entre as duas datas
        // Multiplica a diferença de dias pelo valor diário do veículo
        String[] dataAlocArray = dataAloc.split("/");
        String[] dataDevArray = dataDev.split("/");
        int diaAloc = Integer.parseInt(dataAlocArray[0]);
        int diaDev = Integer.parseInt(dataDevArray[0]);

        // Calcular o número de dias de alocação
        int diasAluguel = diaDev - diaAloc;
        double valortotal = diasAluguel * valorCarro;
        return valortotal;
    }

    /**
     * Método para limpar os campos após a adição bem-sucedida.
     */
    private void limparCampos() {
        // Define todos os campos de texto como nulos ou vazio
        txt_A_nome.setText(null);
        txt_A_cpf.setText(null);
        txt_A_telefone.setText(null);
        txt_A_marca.setText(null);
        txt_A_modelo.setText(null);
        txt_A_dataAloc.setText(null);
        txt_A_dataDev.setText(null);
        txt_valor.setText(null);
        txt_A_VFINAL.setText(null);
    }

    /**
     * Método para pesquisar veículos no banco de dados com base na marca.
     */
    public void pesquisarV() {
        // Prepara e executa uma consulta SQL para buscar veículos por marca

        String sql = "select * from tabveiculos where Marca like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_A_pesquisarV.getText() + "%");
            rs = pst.executeQuery();

            tbl_Estoque.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para pesquisar clientes no banco de dados com base no nome.
     */
    public void pesquisarC() {
        // Prepara e executa uma consulta SQL para buscar clientes por nome

        String sql = "select * from tabclientes where nomecliente like ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_A_pesquisarC.getText() + "%");
            rs = pst.executeQuery();

            tbl_Clientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para preencher os campos de texto com os dados do cliente
     * selecionado na tabela.
     */
    public void preencher_camposC() {
        // Obtém o índice da linha selecionada na tabela de clientes e preenche os campos de texto com os dados correspondentes

        int preencher = tbl_Clientes.getSelectedRow();
        txt_A_nome.setText(tbl_Clientes.getModel().getValueAt(preencher, 1).toString());
        txt_A_cpf.setText(tbl_Clientes.getModel().getValueAt(preencher, 3).toString());
        txt_A_telefone.setText(tbl_Clientes.getModel().getValueAt(preencher, 4).toString());

    }

    /**
     * Método para preencher os campos de texto com os dados do veículo
     * selecionado na tabela.
     */
    public void preencher_camposV() {
        // Obtém o índice da linha selecionada na tabela de veículos e preenche os campos de texto com os dados correspondentes

        int preencher = tbl_Estoque.getSelectedRow();
        txt_A_marca.setText(tbl_Estoque.getModel().getValueAt(preencher, 1).toString());
        txt_A_modelo.setText(tbl_Estoque.getModel().getValueAt(preencher, 2).toString());
        txt_A_renavam.setText(tbl_Estoque.getModel().getValueAt(preencher, 4).toString());
        txt_valor.setText(tbl_Estoque.getModel().getValueAt(preencher, 7).toString());

    }

    /**
     * Método para adicionar uma alocação de veículo ao banco de dados.
     */
    private void adicionar() {
        // Insere os dados da alocação na tabela de alocação no banco de dados
        // Calcula o custo total da alocação e insere na tabela
        String sql = "insert into alocamento (Nome, CPF, Telefone, Marca, Modelo, RENAVAM, dataalocar, datadevolver, Valor) values(?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_A_nome.getText());
            pst.setString(2, txt_A_cpf.getText());
            pst.setString(3, txt_A_telefone.getText());
            pst.setString(4, txt_A_marca.getText());
            pst.setString(5, txt_A_modelo.getText());
            pst.setString(6, txt_A_renavam.getText());
            pst.setString(7, txt_A_dataAloc.getText());
            pst.setString(8, txt_A_dataDev.getText());

            if (txt_A_nome.getText().isEmpty() || txt_A_cpf.getText().isEmpty() || txt_A_telefone.getText().isEmpty() || txt_A_marca.getText().isEmpty() || txt_A_modelo.getText().isEmpty() || txt_A_renavam.getText().isEmpty() || txt_A_dataAloc.getText().isEmpty() || txt_A_dataDev.getText().isEmpty() || txt_valor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                double custoTotal = calcularCustoTotal(txt_A_dataAloc.getText(), txt_A_dataDev.getText(), Double.parseDouble(txt_valor.getText()));
                pst.setString(9, String.valueOf(custoTotal));

                int adicionado = pst.executeUpdate();

                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Veículo alocado com sucesso!");
                    limparCampos();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para adicionar um veículo à tabela de veículos alocados.
     */
    private void adicionaralocamento() {
        // Verifica se o veículo existe na tabela de veículos
        String SELECTSql = "SELECT * FROM tabveiculos WHERE RENAVAM = ?";
        try {
            pst = conexao.prepareStatement(SELECTSql);
            pst.setString(1, txt_A_renavam.getText());
            rs = pst.executeQuery();

            // Se estiver disponível, adiciona o veículo à tabela de veículos alocados
            if (rs.next()) {
                String insertSql = "INSERT INTO tab_veiculos_alocados (Marca, Modelo, Ano, Renavam, Placa, Cor, Valor) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                pst = conexao.prepareStatement(insertSql);
                pst.setString(1, rs.getString("Marca"));
                pst.setString(2, rs.getString("Modelo"));
                pst.setInt(3, rs.getInt("Ano"));
                pst.setString(4, rs.getString("Renavam"));
                pst.setString(5, rs.getString("Placa"));
                pst.setString(6, rs.getString("Cor"));
                pst.setDouble(7, rs.getDouble("Valor"));
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Veículo movido para Veiculos Alocados");
            } else {
                JOptionPane.showMessageDialog(null, "Veículo não encontrado na tabela veiculos.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar veículo: " + e.getMessage());
        }
    }

    /**
     * Método para remover um veículo da tabela de veículos disponíveis.
     */
    private void removerVeiculo() {
        // Remove o veículo selecionado da tabela de veículos disponíveis

        String DELETEsql = "DELETE FROM tabveiculos WHERE RENAVAM = ?";
        try {
            pst = conexao.prepareStatement(DELETEsql);
            pst.setString(1, txt_A_renavam.getText());
            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover veículo: " + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Estoque = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Clientes = new javax.swing.JTable();
        txt_A_pesquisarV = new javax.swing.JTextField();
        txt_A_pesquisarC = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_A_telefone = new javax.swing.JTextField();
        txt_A_nome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_A_modelo = new javax.swing.JTextField();
        txt_A_marca = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_A_renavam = new javax.swing.JTextField();
        txt_A_cpf = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_A_VFINAL = new javax.swing.JTextField();
        txt_A_dataAloc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_A_dataDev = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        but_calcular = new javax.swing.JButton();
        txt_valor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tbl_Estoque.setBackground(new java.awt.Color(255, 255, 255));
        tbl_Estoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Marca", "Modelo", "RENAVAM", "Valor Diário"
            }
        ));
        tbl_Estoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_EstoqueMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Estoque);

        tbl_Clientes.setBackground(new java.awt.Color(255, 255, 255));
        tbl_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Nome", "CPF", "Telefone"
            }
        ));
        tbl_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_Clientes);

        txt_A_pesquisarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_A_pesquisarVActionPerformed(evt);
            }
        });

        txt_A_pesquisarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_A_pesquisarCActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Estoque");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("Telefone");

        txt_A_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_A_nomeActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("*CPF");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 153, 153));
        jLabel3.setText("Modelo");

        txt_A_marca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_A_marcaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("RENAVAM");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Marca");

        txt_A_renavam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_A_renavamActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Valor Final");

        txt_A_dataAloc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_A_dataAlocActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 153, 153));
        jLabel5.setText("Data da Aloc.");

        txt_A_dataDev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_A_dataDevActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Data da Dev.");

        jButton1.setText("ALOCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        but_calcular.setText("Calcular");
        but_calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_calcularActionPerformed(evt);
            }
        });

        txt_valor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_valorActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Valor");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("*Nome");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("Clientes");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(txt_A_pesquisarC, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txt_A_pesquisarV, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)))
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_A_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_A_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_A_dataDev, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_A_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_A_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(4, 4, 4)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel11)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_A_renavam, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_A_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(68, 68, 68))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(but_calcular)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_A_VFINAL, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_A_dataAloc, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(236, 236, 236)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(219, 219, 219))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel9))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_A_pesquisarV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_A_pesquisarC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_A_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_A_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txt_A_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txt_A_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_A_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_A_renavam, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txt_A_dataAloc, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_A_dataDev, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(but_calcular, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txt_A_VFINAL, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_EstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_EstoqueMouseClicked
        preencher_camposV();

    }//GEN-LAST:event_tbl_EstoqueMouseClicked

    private void tbl_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ClientesMouseClicked
        preencher_camposC();
    }//GEN-LAST:event_tbl_ClientesMouseClicked

    private void txt_A_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_A_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_A_nomeActionPerformed

    private void txt_A_marcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_A_marcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_A_marcaActionPerformed

    private void txt_A_renavamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_A_renavamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_A_renavamActionPerformed

    private void txt_A_dataAlocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_A_dataAlocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_A_dataAlocActionPerformed

    private void txt_A_dataDevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_A_dataDevActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_A_dataDevActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Adiciona o alocamento no banco de dados
        adicionar();

        // Move o veículo para a tabela de veículos alocados
        adicionaralocamento();

        // remove o veículo da tabela de veículos disponíveis após alocação
        removerVeiculo();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_A_pesquisarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_A_pesquisarCActionPerformed
        // Faz a pesquisa dos Clientes
        pesquisarC();
    }//GEN-LAST:event_txt_A_pesquisarCActionPerformed

    private void txt_A_pesquisarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_A_pesquisarVActionPerformed
        //Faz a pesquisa dos Veículos
        pesquisarV();
    }//GEN-LAST:event_txt_A_pesquisarVActionPerformed

    private void but_calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_calcularActionPerformed
        double valorTotal = calcularCustoTotal(txt_A_dataAloc.getText(), txt_A_dataDev.getText(), Double.parseDouble(txt_valor.getText()));
        txt_A_VFINAL.setText(String.valueOf(valorTotal));

    }//GEN-LAST:event_but_calcularActionPerformed

    private void txt_valorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_valorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_valorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Aba_ALOCAMENTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aba_ALOCAMENTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aba_ALOCAMENTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aba_ALOCAMENTO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aba_ALOCAMENTO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_calcular;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tbl_Clientes;
    private javax.swing.JTable tbl_Estoque;
    private javax.swing.JTextField txt_A_VFINAL;
    private javax.swing.JTextField txt_A_cpf;
    private javax.swing.JTextField txt_A_dataAloc;
    private javax.swing.JTextField txt_A_dataDev;
    private javax.swing.JTextField txt_A_marca;
    private javax.swing.JTextField txt_A_modelo;
    private javax.swing.JTextField txt_A_nome;
    private javax.swing.JTextField txt_A_pesquisarC;
    private javax.swing.JTextField txt_A_pesquisarV;
    private javax.swing.JTextField txt_A_renavam;
    private javax.swing.JTextField txt_A_telefone;
    private javax.swing.JTextField txt_valor;
    // End of variables declaration//GEN-END:variables
}
