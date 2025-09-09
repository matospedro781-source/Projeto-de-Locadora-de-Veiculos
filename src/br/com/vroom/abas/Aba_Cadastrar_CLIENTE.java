/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.vroom.abas;

import java.sql.*;
import br.com.vroom.dal_cad.Principal;
import javax.swing.JOptionPane;

/**
 * Janela interna para cadastrar novos clientes.
 * Esta classe permite que o usuário insira os detalhes de um novo cliente, como
 * nome, CPF, telefone, login, senha e perfil, e adicione-os ao banco de dados.
 * Também inclui validação para garantir que todos os campos obrigatórios sejam
 * preenchidos.
 */
public class Aba_Cadastrar_CLIENTE extends javax.swing.JInternalFrame {

    // Variáveis para manipulação do banco de dados
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
    public Aba_Cadastrar_CLIENTE() {
        initComponents();
        conexao = Principal.conector();
    }

    /**
     * Método para adicionar um novo cliente ao banco de dados.
     */
    private void adicionar() {
        // SQL para inserir um novo cliente na tabela tabclientes
        String sql = "insert into tabclientes(nomecliente, cpfcliente, CNH, telcliente, endcliente, emailcliente) values(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            // Define os parâmetros da instrução SQL com base nos valores dos campos de texto
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        txt_C_cpf = new javax.swing.JTextField();
        txt_C_nome = new javax.swing.JTextField();
        txt_C_telefone = new javax.swing.JTextField();
        txt_C_end = new javax.swing.JTextField();
        txt_C_email = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_C_cnh = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        but_C_CREATE = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Gerenciar Clientes");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Endereço");

        txt_C_cpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_C_cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_C_cpfActionPerformed(evt);
            }
        });

        txt_C_nome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_C_telefone.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_C_end.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txt_C_email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("* Nome");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(153, 153, 153));
        jLabel2.setText("*Telefone");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 153));
        jLabel9.setText("*CPF");

        txt_C_cnh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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

        jLabel3.setText("* Campos obrigatórios");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vroom/icons/icone_Cliente(usuario).png"))); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Cadastrar CLIENTE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(267, 267, 267)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_C_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_C_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_C_cnh, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_C_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addComponent(jLabel3))
                .addGap(15, 15, 15))
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(but_C_CREATE, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_C_end, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_C_email, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_C_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(txt_C_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_C_cnh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(txt_C_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(67, 67, 67)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_C_end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_C_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(but_C_CREATE, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        setBounds(0, 0, 753, 499);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_C_cnhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_C_cnhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_C_cnhActionPerformed

    private void txt_C_cpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_C_cpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_C_cpfActionPerformed

    private void but_C_CREATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_C_CREATEActionPerformed
        // chama o método para adicionar cliente
        adicionar();
    }//GEN-LAST:event_but_C_CREATEActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_C_CREATE;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txt_C_cnh;
    private javax.swing.JTextField txt_C_cpf;
    private javax.swing.JTextField txt_C_email;
    private javax.swing.JTextField txt_C_end;
    private javax.swing.JTextField txt_C_nome;
    private javax.swing.JTextField txt_C_telefone;
    // End of variables declaration//GEN-END:variables
}
