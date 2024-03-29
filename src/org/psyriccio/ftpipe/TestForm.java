/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.psyriccio.ftpipe;

import java.io.UnsupportedEncodingException;
import org.slf4j.LoggerFactory;

/**
 *
 * @author psyriccio
 */
public class TestForm extends javax.swing.JFrame {

    private static ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger("ftpipe");

    private final FTPipe pipe;

    /**
     * Creates new form TestForm
     */
    public TestForm(FTPipe pipe) {
        initComponents();
        this.pipe = pipe;
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
        jTAInbound = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAOutbound = new javax.swing.JTextArea();
        jBtnInboundPush = new javax.swing.JButton();
        jBtnInboundPoll = new javax.swing.JButton();
        jBtnOutboundPush = new javax.swing.JButton();
        jBtnOutboundPoll = new javax.swing.JButton();
        jBtnInboundInfo = new javax.swing.JButton();
        jBtnOutboundInfo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        jTAInbound.setColumns(20);
        jTAInbound.setRows(5);
        jScrollPane1.setViewportView(jTAInbound);

        jTAOutbound.setColumns(20);
        jTAOutbound.setRows(5);
        jScrollPane2.setViewportView(jTAOutbound);

        jBtnInboundPush.setText("Push");
        jBtnInboundPush.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnInboundPushActionPerformed(evt);
            }
        });

        jBtnInboundPoll.setText("Poll");
        jBtnInboundPoll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnInboundPollActionPerformed(evt);
            }
        });

        jBtnOutboundPush.setText("Push");
        jBtnOutboundPush.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOutboundPushActionPerformed(evt);
            }
        });

        jBtnOutboundPoll.setText("Poll");
        jBtnOutboundPoll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOutboundPollActionPerformed(evt);
            }
        });

        jBtnInboundInfo.setText("Info");
        jBtnInboundInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnInboundInfoActionPerformed(evt);
            }
        });

        jBtnOutboundInfo.setText("Info");
        jBtnOutboundInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnOutboundInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnInboundPoll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnOutboundPush, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(jBtnInboundPush, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jBtnOutboundPoll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jBtnInboundInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jBtnOutboundInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jBtnInboundPush)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnInboundPoll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnInboundInfo)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBtnOutboundPush)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnOutboundPoll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBtnOutboundInfo))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnInboundPushActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnInboundPushActionPerformed
        try {
            byte[] data = jTAInbound.getText().getBytes("UTF-8");
            jTAInbound.setText("");
            pipe.getInbound().add(new FTPipe.DataBlock(data));
        } catch (UnsupportedEncodingException ex) {
            log.error("Exception: {}", ex);
        }
    }//GEN-LAST:event_jBtnInboundPushActionPerformed

    private void jBtnOutboundPushActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOutboundPushActionPerformed
        try {
            byte[] data = jTAOutbound.getText().getBytes("UTF-8");
            jTAOutbound.setText("");
            pipe.getOutbound().add(new FTPipe.DataBlock(data));
        } catch (UnsupportedEncodingException ex) {
            log.error("Exception: {}", ex);
        }
    }//GEN-LAST:event_jBtnOutboundPushActionPerformed

    private void jBtnInboundPollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnInboundPollActionPerformed
        FTPipe.DataBlock block = pipe.getInbound().poll();
        if (block != null) {
            try {
                jTAInbound.setText(new String(block.getData(), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                log.error("Exception: {}", ex);
            }
        }
    }//GEN-LAST:event_jBtnInboundPollActionPerformed

    private void jBtnOutboundPollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOutboundPollActionPerformed
        FTPipe.DataBlock block = pipe.getOutbound().poll();
        if (block != null) {
            try {
                jTAOutbound.setText(new String(block.getData(), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                log.error("Exception: {}", ex);
            }
        }
    }//GEN-LAST:event_jBtnOutboundPollActionPerformed

    private void jBtnInboundInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnInboundInfoActionPerformed
        jTAInbound.setText(
                pipe.getInbound().stream()
                .map(
                        (itm) -> Integer.toHexString(itm.hashCode())
                        + ", size=" + Integer.toString(itm.getData().length)
                        + "\n")
                .reduce("", String::concat)
        );
    }//GEN-LAST:event_jBtnInboundInfoActionPerformed

    private void jBtnOutboundInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnOutboundInfoActionPerformed
        jTAOutbound.setText(
                pipe.getOutbound().stream()
                .map(
                        (itm) -> Integer.toHexString(itm.hashCode())
                        + ", size=" + Integer.toString(itm.getData().length)
                        + "\n")
                .reduce("", String::concat)
        );
    }//GEN-LAST:event_jBtnOutboundInfoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnInboundInfo;
    private javax.swing.JButton jBtnInboundPoll;
    private javax.swing.JButton jBtnInboundPush;
    private javax.swing.JButton jBtnOutboundInfo;
    private javax.swing.JButton jBtnOutboundPoll;
    private javax.swing.JButton jBtnOutboundPush;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTAInbound;
    private javax.swing.JTextArea jTAOutbound;
    // End of variables declaration//GEN-END:variables
}
