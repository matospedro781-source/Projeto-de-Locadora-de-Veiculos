/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.vroom.abas;

import java.sql.*;
import br.com.vroom.dal_cad.Principal;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
// alinha abaixo importa recursos da biblioteca rs2xml
import net.proteanit.sql.DbUtils;

/**
 *
 * @author irene
 */
public class Aba_Relatório_Alocamentos extends javax.swing.JInternalFrame {

    //usando variavel conexao do DAL(CAD)
    Connection conexao = null;
    /*
    PreparedStatement e ResultSet são frameworks do package java.sql
    Eles servem para preparar e executar as instruções SQL
     */
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Cria uma nova instância da aba "Relatório de Alocações".
     */
    public Aba_Relatório_Alocamentos() {
        initComponents();
        conexao = Principal.conector();
    }

    /**
     * Método para adicionar um veículo desalocado.
     */
    public void adicionar() {
        String sql = "insert into tabveiculos (Marca, Modelo, Ano, RENAVAM, Placa, Cor, valor) values (?,?,?,?,?,?,?)";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txt_marca.getText());
            pst.setString(2, txt_modelo.getText());
            pst.setString(3, txt_ano.getText());
            pst.setString(4, txt_A_renavam.getText());
            pst.setString(5, txt_placa.getText());
            pst.setString(6, txt_cor.getText());
            pst.setString(7, txt_valor.getText());
            pst.executeUpdate();

            if (txt_marca.getText().isEmpty() || txt_modelo.getText().isEmpty() || txt_ano.getText().isEmpty() || txt_A_renavam.getText().isEmpty() || txt_placa.getText().isEmpty() || txt_cor.getText().isEmpty() || txt_valor.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios");
            } else {
                // Executa a instrução SQL para adicionar o novo usuário atualiza a tabusuarios com os dados inseridos na Aba_Gerenciar_CLIENTE
                int adicionado = pst.executeUpdate();
                // Confirma a inserção(insert into) dos dados na tabusuarios
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Veículo desalocado com sucesso!");
                    // as linhas abaixa "limpa" os campos
                    txt_marca.setText(null);
                    txt_modelo.setText(null);
                    txt_ano.setText(null);
                    txt_A_renavam.setText(null);
                    txt_placa.setText(null);
                    txt_cor.setText(null);
                    txt_valor.setText(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Veículo desalocado com sucesso!");
            txt_marca.setText(null);
            txt_modelo.setText(null);
            txt_ano.setText(null);
            txt_A_renavam.setText(null);
            txt_placa.setText(null);
            txt_cor.setText(null);
            txt_valor.setText(null);
        }
    }

    private void removerVeiculo() {
        String DELETEsql = "DELETE FROM tab_veiculos_alocados WHERE id = ?";
        try {
            pst = conexao.prepareStatement(DELETEsql);
            pst.setString(1, txt_id.getText());
            pst.executeUpdate();
            txt_id.setText(null);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao remover veículo: " + e.getMessage());
        }
    }

    /**
     * Método para pesquisar clientes no banco de dados com base no nome.
     */
    private void pesquisar_cliente() {
        String slq = "select * from tab_veiculos_alocados where Marca like ?";
        try {
            pst = conexao.prepareStatement(slq);
            //passando o conteúdo da caixa de pesquisa para o ?
            // adicionado o %
            pst.setString(1, txt_E_PESQUISAR.getText() + "%");
            rs = pst.executeQuery();
            // a linha abaixo usa a biblioteca importada (rs2xml) para a tabela
            tbl_Estoque.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // método para preencher os campos do formulário com o conteúdo da tabela
    public void preencher_campos() {
        int pree = tbl_Estoque.getSelectedRow();
        txt_id.setText(tbl_Estoque.getModel().getValueAt(pree, 0).toString());
        txt_marca.setText(tbl_Estoque.getModel().getValueAt(pree, 1).toString());
        txt_modelo.setText(tbl_Estoque.getModel().getValueAt(pree, 2).toString());
        txt_ano.setText(tbl_Estoque.getModel().getValueAt(pree, 3).toString());
        txt_A_renavam.setText(tbl_Estoque.getModel().getValueAt(pree, 4).toString());
        txt_placa.setText(tbl_Estoque.getModel().getValueAt(pree, 5).toString());
        txt_cor.setText(tbl_Estoque.getModel().getValueAt(pree, 6).toString());
        txt_valor.setText(tbl_Estoque.getModel().getValueAt(pree, 7).toString());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        but_Desalocar = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        txt_cor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txt_E_PESQUISAR = new javax.swing.JTextField();
        txt_A_renavam = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_modelo = new javax.swing.JTextField();
        txt_placa = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_marca = new javax.swing.JTextField();
        txt_valor = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_ano = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Estoque = new javax.swing.JTable();
        txt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        but_Desalocar.setText("DESALOCAR");
        but_Desalocar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                but_DesalocarActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel12.setText("Cor");

        txt_cor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_corActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar pela Marca");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel11.setText("Renavam");

        txt_E_PESQUISAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_E_PESQUISARActionPerformed(evt);
            }
        });
        txt_E_PESQUISAR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_E_PESQUISARKeyReleased(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/vroom/icons/MIDDLE_45X45_Search.png"))); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setText("Placa");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel16.setText("Modelo");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel14.setText("Valor");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Marca");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setText("Ano");

        tbl_Estoque = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        tbl_Estoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Marca", "Modelo", "Ano", "RENAVAM", "Placa", "Cor", "Valor"
            }
        ));
        tbl_Estoque.getTableHeader().setReorderingAllowed(false);
        tbl_Estoque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_EstoqueMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_Estoque);

        txt_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_idActionPerformed(evt);
            }
        });
        txt_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idKeyReleased(evt);
            }
        });

        jLabel2.setText("id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_cor, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_A_renavam, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(but_Desalocar)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 102, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_E_PESQUISAR, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addComponent(txt_E_PESQUISAR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_marca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txt_modelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txt_ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txt_A_renavam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_placa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(txt_valor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(but_Desalocar)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        setBounds(0, 0, 753, 495);
    }// </editor-fold>//GEN-END:initComponents

    private void but_DesalocarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_but_DesalocarActionPerformed
        adicionar();
        removerVeiculo();
    }//GEN-LAST:event_but_DesalocarActionPerformed

    private void txt_corActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_corActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_corActionPerformed

    private void txt_E_PESQUISARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_E_PESQUISARActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_E_PESQUISARActionPerformed

    private void txt_E_PESQUISARKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_E_PESQUISARKeyReleased
        // chamar o método pesquisar clientes
        pesquisar_cliente();
    }//GEN-LAST:event_txt_E_PESQUISARKeyReleased

    private void tbl_EstoqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_EstoqueMouseClicked
        preencher_campos();
    }//GEN-LAST:event_tbl_EstoqueMouseClicked

    private void txt_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idActionPerformed

    private void txt_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_idKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton but_Desalocar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_Estoque;
    private javax.swing.JTextField txt_A_renavam;
    private javax.swing.JTextField txt_E_PESQUISAR;
    private javax.swing.JTextField txt_ano;
    private javax.swing.JTextField txt_cor;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_marca;
    private javax.swing.JTextField txt_modelo;
    private javax.swing.JTextField txt_placa;
    private javax.swing.JTextField txt_valor;
    // End of variables declaration//GEN-END:variables
}
