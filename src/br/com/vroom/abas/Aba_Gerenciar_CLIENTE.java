/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.vroom.abas;

import java.sql.*;
import br.com.vroom.dal_cad.Principal;
import javax.swing.JOptionPane;
// a linha abaixo importa recursos da biblioteca rs2xml.jar
import net.proteanit.sql.DbUtils;

/**
 * Janela interna para gerenciar clientes. Esta classe permite ao usuário
 * adicionar, editar, remover e pesquisar clientes no banco de dados. Ela se
 * integra com o módulo de conexão ao banco de dados e utiliza a biblioteca
 * rs2xml.jar para exibir os resultados das consultas em uma tabela. Os métodos
 * incluídos nesta classe permitem realizar operações CRUD (Create, Read,
 * Update, Delete) sobre os dados dos clientes.
 */
public class Aba_Gerenciar_CLIENTE extends javax.swing.JInternalFrame {

    //usando variavel conexao do DAL(CAD)
    Connection conexao = null;
    /*
    PreparedStatement e ResultSet são frameworks do package java.sql
    Eles servem para preparar e executar as instruções SQL
     */
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Construtor da classe.
     *
     * Inicializa os componentes da interface gráfica e estabelece a conexão com
     * o banco de dados.
     */
    public Aba_Gerenciar_CLIENTE() {
        initComponents();
        conexao = Principal.conector();
    }

    /**
     * Método para adicionar um novo cliente ao banco de dados.
     */
    private void adicionar() {
        // SQL para inserir um novo cliente na tabela tabclientes
        String sql = "insert into tabclientes (nomecliente, cpfcliente, CNH, telcliente, endcliente, emailcliente) values(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_C_nome.getText());
            pst.setString(2, txt_C_cpf.getText());
            pst.setString(3, txt_C_cnh.getText());
            pst.setString(4, txt_C_telefone.getText());
            pst.setString(5, txt_C_end.getText());
            pst.setString(6, txt_C_email.getText());

            // Verifica se todos os campos obrigatórios foram preenchidos
            if (txt_C_nome.getText().isEmpty() || txt_C_cpf.getText().isEmpty() || txt_C_cnh.getText().isEmpty() || txt_C_telefone.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                // Executa a instrução SQL para adicionar o novo cliente atualiza a tabclientes com os dados inseridos na Aba_Cadastrar_CLIENTE
                int adicionado = pst.executeUpdate();
                // Confirma a inserção(insert into) dos dados na tabclientes
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
                    // as linhas abaixa "limpa" os campos
                    txt_C_id.setText(null);
                    txt_C_nome.setText(null);
                    txt_C_cpf.setText(null);
                    txt_C_cnh.setText(null);
                    txt_C_telefone.setText(null);
                    txt_C_end.setText(null);
                    txt_C_email.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para pesquisar clientes pelo nome.
     */
    private void pesquisar_cliente() {
        String slq = "select * from tabclientes where nomecliente like ?";
        try {
            pst = conexao.prepareStatement(slq);
            // Passa o conteúdo da caixa de pesquisa para o ? e adiciona o %
            pst.setString(1, txt_C_PESQUISAR.getText() + "%");
            rs = pst.executeQuery();
            // Usa a biblioteca importada (rs2xml) para preencher a tabela com os resultados da consulta
            tbl_Clientes.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Preenche os campos do formulário com o conteúdo da tabela.
     */
    public void preencher_campos() {
        int pree = tbl_Clientes.getSelectedRow();
        txt_C_id.setText(tbl_Clientes.getModel().getValueAt(pree, 0).toString());
        txt_C_nome.setText(tbl_Clientes.getModel().getValueAt(pree, 1).toString());
        txt_C_end.setText(tbl_Clientes.getModel().getValueAt(pree, 2).toString());
        txt_C_cpf.setText(tbl_Clientes.getModel().getValueAt(pree, 3).toString());
        txt_C_telefone.setText(tbl_Clientes.getModel().getValueAt(pree, 4).toString());
        txt_C_email.setText(tbl_Clientes.getModel().getValueAt(pree, 5).toString());
        txt_C_cnh.setText(tbl_Clientes.getModel().getValueAt(pree, 6).toString());

    }

    /**
     * Remove um cliente do banco de dados.
     */
    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este cliente?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM tabclientes where idcliente=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txt_C_id.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
                txt_C_id.setText(null);
                txt_C_nome.setText(null);
                txt_C_end.setText(null);
                txt_C_cpf.setText(null);
                txt_C_telefone.setText(null);
                txt_C_email.setText(null);
                txt_C_cnh.setText(null);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    /**
     * Altera os dados de um cliente no banco de dados.
     */
    private void alterarC() {
        String sql = "UPDATE tabclientes set nomecliente=?, endcliente=?, cpfcliente=?, telcliente=?, emailcliente=?, cnh=? where idcliente=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_C_id.getText());
            pst.setString(1, txt_C_nome.getText());
            pst.setString(2, txt_C_end.getText());
            pst.setString(3, txt_C_cpf.getText());
            pst.setString(4, txt_C_telefone.getText());
            pst.setString(5, txt_C_email.getText());
            pst.setString(6, txt_C_cnh.getText());
            pst.setString(7, txt_C_id.getText());

            // a linha abaixo atualiza a tabclientes com os dados inseridos na Aba_Gerenciar_CLIENTE
            // a estrutura abaixo confirma a inserção(insert into) dos dados na tabclientes
            int alterado = pst.executeUpdate();
            // Confirma a alteração (UPDATE) dos dados na tabclientes
            if (alterado > 0) {
                JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
                /**
                 * Limpa os campos do formulário.
                 */
                txt_C_id.setText(null);
                txt_C_nome.setText(null);
                txt_C_end.setText(null);
                txt_C_cpf.setText(null);
                txt_C_telefone.setText(null);
                txt_C_email.setText(null);
                txt_C_cnh.setText(null);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
        tbl_Clientes = new javax.swing.JTable();
        txt_C_PESQUISAR = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_C_cnh = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        but_C_CREATE = new javax.swing.JButton();
        but_C_UPDATE = new javax.swing.JButton();
        but_C_DELETE = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txt_C_cpf = new javax.swing.JTextField();
        txt_C_nome = new javax.swing.JTextField();
        txt_C_telefone = new javax.swing.JTextField();
        txt_C_end = new javax.swing.JTextField();
        txt_C_email = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_C_id = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        tbl_Clientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbl_Clientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Nome", "Endereço", "CPF", "Telefone", "Email", "CNH"
            }
        ));
        tbl_Clientes.setFocusable(false);
        tbl_Clientes.getTableHeader().setReorderingAllowed(false);
        tbl_Clientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_ClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Clientes);

        txt_C_PESQUISAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_C_PESQUISARActionPerformed(evt);
            }
        });
        txt_C_PESQUISAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_C_PESQUISARKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vroom/icons/MIDDLE_45X45_Search.png"))); // NOI18N

        jLabel3.setText("* Campos obrigatórios");

        jLabel5.setText("id");

        txt_C_cnh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_C_cnhActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("*CNH");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText("Email");

        but_C_CREATE.setBackground(new java.awt.Color(204, 204, 204));
        but_C_CREATE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vroom/icons/ADD_ICON (2).png"))); // NOI18N
        but_C_CREATE.setToolTipText("ADICIONAR");
        but_C_CREATE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        but_C_CREATE.setMaximumSize(new java.awt.Dimension(80, 80));
        but_C_CREATE.setMinimumSize(new java.awt.Dimension(80, 80));
        but_C_CREATE.setPreferredSize(new java.awt.Dimension(80, 80));
        but_C_CREATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_C_CREATEActionPerformed(evt);
            }
        });

        but_C_UPDATE.setBackground(new java.awt.Color(204, 204, 204));
        but_C_UPDATE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vroom/icons/edit_icon_1.png"))); // NOI18N
        but_C_UPDATE.setToolTipText("EDITAR");
        but_C_UPDATE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        but_C_UPDATE.setMinimumSize(new java.awt.Dimension(80, 80));
        but_C_UPDATE.setPreferredSize(new java.awt.Dimension(80, 80));
        but_C_UPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_C_UPDATEActionPerformed(evt);
            }
        });

        but_C_DELETE.setBackground(new java.awt.Color(204, 204, 204));
        but_C_DELETE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vroom/icons/DELETE_ICON (2).png"))); // NOI18N
        but_C_DELETE.setToolTipText("REMOVER");
        but_C_DELETE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        but_C_DELETE.setMinimumSize(new java.awt.Dimension(80, 80));
        but_C_DELETE.setPreferredSize(new java.awt.Dimension(80, 80));
        but_C_DELETE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_C_DELETEActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Endereço");

        txt_C_cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_C_cpfActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("* Nome");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("*Telefone");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("*CPF");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(txt_C_PESQUISAR, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addGap(31, 31, 31)
                .addComponent(txt_C_id, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(jLabel10)
                                    .addGap(18, 18, 18)
                                    .addComponent(txt_C_cnh)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txt_C_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txt_C_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(32, 32, 32)
                                    .addComponent(jLabel9)
                                    .addGap(20, 20, 20)
                                    .addComponent(txt_C_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txt_C_end, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addComponent(jLabel11)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txt_C_email, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(but_C_CREATE, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(but_C_UPDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(but_C_DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(150, 150, 150)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txt_C_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txt_C_PESQUISAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txt_C_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(txt_C_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txt_C_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_C_cnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_C_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_C_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(but_C_CREATE, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(but_C_UPDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(but_C_DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        setBounds(0, 0, 753, 495);
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_ClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_ClientesMouseClicked
        // chamando o método preencher campos
        preencher_campos();
    }//GEN-LAST:event_tbl_ClientesMouseClicked
// O evento abaixo serve para preenchimento da caixa em tempo real
    private void txt_C_PESQUISARKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_C_PESQUISARKeyReleased
        // chamar o método pesquisar clientes
        pesquisar_cliente();
    }//GEN-LAST:event_txt_C_PESQUISARKeyReleased

    private void txt_C_cnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_C_cnhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_C_cnhActionPerformed

    private void but_C_CREATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_C_CREATEActionPerformed
        // método para adicionar cliente
        adicionar();
    }//GEN-LAST:event_but_C_CREATEActionPerformed

    private void but_C_UPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_C_UPDATEActionPerformed
        alterarC();
    }//GEN-LAST:event_but_C_UPDATEActionPerformed

    private void but_C_DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_C_DELETEActionPerformed
        remover();
    }//GEN-LAST:event_but_C_DELETEActionPerformed

    private void txt_C_cpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_C_cpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_C_cpfActionPerformed

    private void txt_C_PESQUISARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_C_PESQUISARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_C_PESQUISARActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_C_CREATE;
    private javax.swing.JButton but_C_DELETE;
    private javax.swing.JButton but_C_UPDATE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Clientes;
    private javax.swing.JTextField txt_C_PESQUISAR;
    private javax.swing.JTextField txt_C_cnh;
    private javax.swing.JTextField txt_C_cpf;
    private javax.swing.JTextField txt_C_email;
    private javax.swing.JTextField txt_C_end;
    private javax.swing.JTextField txt_C_id;
    private javax.swing.JTextField txt_C_nome;
    private javax.swing.JTextField txt_C_telefone;
    // End of variables declaration//GEN-END:variables
}
