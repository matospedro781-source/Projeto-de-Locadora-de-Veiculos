/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.vroom.abas;

import java.sql.*;
import br.com.vroom.dal_cad.Principal;
import javax.swing.JOptionPane;
// alinha abaixo importa recursos da biblioteca rs2xml
import net.proteanit.sql.DbUtils;

/**
 * Janela interna para gerenciar clientes. Esta classe permite ao usuário
 * adicionar, editar, remover e pesquisar clientes no banco de dados. Ela se
 * integra com o módulo de conexão ao banco de dados e utiliza a biblioteca
 * rs2xml.jar para exibir os resultados das consultas em uma tabela. Os métodos
 * incluídos nesta classe permitem realizar operações CRUD (Create, Read,
 * Update, Delete) sobre os dados dos clientes.
 */
public class Aba_Gerenciar_USUARIOS extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Construtor da classe.
     *
     * Inicializa os componentes da interface gráfica e estabelece a conexão com
     * o banco de dados.
     */
    public Aba_Gerenciar_USUARIOS() {
        initComponents();
        conexao = Principal.conector();
    }

    /**
     * Método para pesquisar usuarios pelo nome de usuario.
     */
    public void pesquisar() {
        String sql = "select * from tabusuarios where usuario like ?";

        try {
            pst = conexao.prepareStatement(sql);
            // Passa o conteúdo da caixa de pesquisa para o ? e adiciona o %
            pst.setString(1, txt_C_PESQUISAR.getText() + "%");
            rs = pst.executeQuery();
            // Usa a biblioteca importada (rs2xml) para preencher a tabela com os resultados da consulta
            tbl_Usuarios.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Preenche os campos do formulário com o conteúdo da tabela.
     */
    public void preencher_campos() {
        int pree = tbl_Usuarios.getSelectedRow();
        txt_U_id.setText(tbl_Usuarios.getModel().getValueAt(pree, 0).toString());
        txt_U_nome.setText(tbl_Usuarios.getModel().getValueAt(pree, 1).toString());
        txt_U_cpf.setText(tbl_Usuarios.getModel().getValueAt(pree, 2).toString());
        txt_U_telefone.setText(tbl_Usuarios.getModel().getValueAt(pree, 3).toString());
        txt_U_login.setText(tbl_Usuarios.getModel().getValueAt(pree, 4).toString());
        txt_U_senha.setText(tbl_Usuarios.getModel().getValueAt(pree, 5).toString());
    }

    /**
     * Remove um usuario do banco de dados.
     */
    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM tabusuarios where idusuario=?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txt_U_id.getText());
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Usuário removido com sucesso!");
                txt_U_nome.setText(null);
                txt_U_cpf.setText(null);
                txt_U_telefone.setText(null);
                txt_U_login.setText(null);
                txt_U_senha.setText(null);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    /**
     * Altera os dados de um usuario no banco de dados.
     */
    private void alterarU() {
        //
        String sql = "update tabusuarios set usuario=?,cpfusuario=?,telusuario=?,login=?,senha=?,perfil=? where cpfusuario=?";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txt_U_nome.getText());
            pst.setString(2, txt_U_cpf.getText());
            pst.setString(3, txt_U_telefone.getText());
            pst.setString(4, txt_U_login.getText());
            pst.setString(5, txt_U_senha.getText());
            pst.setString(6, cbx_U_perfil.getSelectedItem().toString());
            pst.setString(7, txt_U_cpf.getText());

            if (txt_U_nome.getText().isEmpty() || txt_U_cpf.getText().isEmpty() || txt_U_telefone.getText().isEmpty() || txt_U_login.getText().isEmpty() || txt_U_senha.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                // a linha abaixo atualiza a tabusuarios com os dados inseridos na Aba_Gerenciar_USUARIO
                // a estrutura abaixo confirma a inserção(insert into) dos dados na tabusuarios
                int alterado = pst.executeUpdate();
                // Confirma a alteração (UPDATE) dos dados na tabusuarios
                if (alterado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do usuário alterados com sucesso!");
                    // as linhas abaixa "limpa" os campos
                    txt_U_nome.setText(null);
                    txt_U_cpf.setText(null);
                    txt_U_telefone.setText(null);
                    txt_U_login.setText(null);
                    txt_U_senha.setText(null);
                }
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

        jLabel2 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Usuarios = new javax.swing.JTable();
        txt_C_PESQUISAR = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        cbx_U_perfil = new javax.swing.JComboBox<>();
        Nome_NOME = new javax.swing.JLabel();
        txt_U_telefone = new javax.swing.JTextField();
        Nome_NOME1 = new javax.swing.JLabel();
        txt_U_cpf = new javax.swing.JTextField();
        Nome_NOME2 = new javax.swing.JLabel();
        txt_U_login = new javax.swing.JTextField();
        Nome_NOME3 = new javax.swing.JLabel();
        txt_U_senha = new javax.swing.JTextField();
        but_C_DELETE = new javax.swing.JButton();
        Nome_NOME4 = new javax.swing.JLabel();
        but_U_UPDATE = new javax.swing.JButton();
        Nome_PERFIL = new javax.swing.JLabel();
        txt_U_nome = new javax.swing.JTextField();
        txt_U_id = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Gerenciar USUARIOS");

        jLabel2.setText("* Campos obrigatórios");

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vroom/icons/icone_Cliente(usuario).png"))); // NOI18N

        tbl_Usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "idusuario", "usuario", "cpfusuario", "telusuario", "login", "senha", "perfil"
            }
        ));
        tbl_Usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_UsuariosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Usuarios);

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

        cbx_U_perfil.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbx_U_perfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "administrador", "usuario" }));
        cbx_U_perfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_U_perfilActionPerformed(evt);
            }
        });

        Nome_NOME.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Nome_NOME.setForeground(new java.awt.Color(153, 153, 153));
        Nome_NOME.setText("*Nome");

        txt_U_telefone.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Nome_NOME1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Nome_NOME1.setForeground(new java.awt.Color(153, 153, 153));
        Nome_NOME1.setText("*Telefone");

        txt_U_cpf.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Nome_NOME2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Nome_NOME2.setForeground(new java.awt.Color(153, 153, 153));
        Nome_NOME2.setText("*CPF");

        txt_U_login.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        Nome_NOME3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Nome_NOME3.setForeground(new java.awt.Color(153, 153, 153));
        Nome_NOME3.setText("Login");

        txt_U_senha.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

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

        Nome_NOME4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Nome_NOME4.setForeground(new java.awt.Color(153, 153, 153));
        Nome_NOME4.setText("*Senha");

        but_U_UPDATE.setBackground(new java.awt.Color(204, 204, 204));
        but_U_UPDATE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vroom/icons/edit_icon_1.png"))); // NOI18N
        but_U_UPDATE.setToolTipText("EDITAR");
        but_U_UPDATE.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        but_U_UPDATE.setMinimumSize(new java.awt.Dimension(80, 80));
        but_U_UPDATE.setPreferredSize(new java.awt.Dimension(80, 80));
        but_U_UPDATE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_U_UPDATEActionPerformed(evt);
            }
        });

        Nome_PERFIL.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        Nome_PERFIL.setText("Perfil");

        txt_U_nome.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txt_U_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_U_idActionPerformed(evt);
            }
        });
        txt_U_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_U_idKeyReleased(evt);
            }
        });

        jLabel1.setText("id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(Nome_PERFIL)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_U_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(but_U_UPDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(but_C_DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txt_C_PESQUISAR, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_U_id, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Nome_NOME3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_U_login, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Nome_NOME)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_U_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Nome_NOME1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_U_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(Nome_NOME4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_U_senha, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Nome_NOME2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_U_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel2))
                        .addComponent(txt_C_PESQUISAR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_U_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nome_NOME)
                    .addComponent(txt_U_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nome_NOME1)
                    .addComponent(txt_U_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nome_NOME2)
                    .addComponent(txt_U_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Nome_NOME3)
                    .addComponent(txt_U_login, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Nome_NOME4)
                    .addComponent(txt_U_senha, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(but_U_UPDATE, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(but_C_DELETE, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbx_U_perfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Nome_PERFIL))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_UsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_UsuariosMouseClicked
        preencher_campos();
    }//GEN-LAST:event_tbl_UsuariosMouseClicked

    private void txt_C_PESQUISARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_C_PESQUISARActionPerformed
        pesquisar();
    }//GEN-LAST:event_txt_C_PESQUISARActionPerformed

    private void txt_C_PESQUISARKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_C_PESQUISARKeyReleased
        pesquisar();
    }//GEN-LAST:event_txt_C_PESQUISARKeyReleased

    private void cbx_U_perfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_U_perfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_U_perfilActionPerformed

    private void but_C_DELETEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_C_DELETEActionPerformed
        remover();        // TODO add your handling code here:
    }//GEN-LAST:event_but_C_DELETEActionPerformed

    private void but_U_UPDATEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_U_UPDATEActionPerformed
        alterarU();
    }//GEN-LAST:event_but_U_UPDATEActionPerformed

    private void txt_U_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_U_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_U_idActionPerformed

    private void txt_U_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_U_idKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_U_idKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Nome_NOME;
    private javax.swing.JLabel Nome_NOME1;
    private javax.swing.JLabel Nome_NOME2;
    private javax.swing.JLabel Nome_NOME3;
    private javax.swing.JLabel Nome_NOME4;
    private javax.swing.JLabel Nome_PERFIL;
    private javax.swing.JButton but_C_DELETE;
    private javax.swing.JButton but_U_UPDATE;
    private javax.swing.JComboBox<String> cbx_U_perfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Usuarios;
    private javax.swing.JTextField txt_C_PESQUISAR;
    private javax.swing.JTextField txt_U_cpf;
    private javax.swing.JTextField txt_U_id;
    private javax.swing.JTextField txt_U_login;
    private javax.swing.JTextField txt_U_nome;
    private javax.swing.JTextField txt_U_senha;
    private javax.swing.JTextField txt_U_telefone;
    // End of variables declaration//GEN-END:variables
}
