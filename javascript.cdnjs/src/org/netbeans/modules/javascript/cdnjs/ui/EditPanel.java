/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.netbeans.modules.javascript.cdnjs.ui;

import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import org.netbeans.modules.javascript.cdnjs.Library;

/**
 * Panel for library editing. It allows selection of library version and its files.
 *
 * @author Jan Stola
 */
public class EditPanel extends javax.swing.JPanel {
    /** Installed (or the last selected) version of the library. */
    private final Library.Version installedVersion;

    /**
     * Creates a new {@code EditPanel} for the given library.
     * 
     * @param library library to edit.
     * @param installedVersion installed (or the last selected) version of the library.
     */
    public EditPanel(Library library, Library.Version installedVersion) {
        initComponents();
        this.installedVersion = installedVersion;
        Library.Version[] versions;
        if (library == null) {
            // No meta-data available
            versions = new Library.Version[] { installedVersion };
        } else {
            versions = library.getVersions();
        }
        versionComboBox.setModel(new DefaultComboBoxModel(versions));
        versionComboBox.setRenderer(new LibraryVersionRenderer());
        
        // Pre-select the installed version
        int indexOfInstalled = -1;
        for (int i=0; i<versions.length; i++) {
            if (versions[i].getName().equals(installedVersion.getName())) {
                indexOfInstalled = i;
                break;
            }
        }
        versionComboBox.setSelectedIndex(indexOfInstalled);

        updateFileSelectionPanel();
    }

    /**
     * Updates the file selection panel according to the selected version.
     */
    private void updateFileSelectionPanel() {
        Library.Version version = (Library.Version)versionComboBox.getSelectedItem();
        String[] installedFiles;
        if (version.getName().equals(installedVersion.getName())) {
            installedFiles = installedVersion.getFiles();
        } else {
            installedFiles = null;
        }
        fileSelectionPanel.setLibrary(version, installedFiles);
    }

    /**
     * Returns version of the library whose files reflect the selection made
     * by the user.
     * 
     * @return version of the library whose files reflect the selection made
     * by the user.
     */
    Library.Version getSelection() {
        return fileSelectionPanel.getSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        versionLabel = new javax.swing.JLabel();
        versionComboBox = new javax.swing.JComboBox();
        fileSelectionPanel = new org.netbeans.modules.javascript.cdnjs.ui.FileSelectionPanel();
        filesLabel = new javax.swing.JLabel();

        org.openide.awt.Mnemonics.setLocalizedText(versionLabel, org.openide.util.NbBundle.getMessage(EditPanel.class, "EditPanel.versionLabel.text")); // NOI18N

        versionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                versionComboBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(filesLabel, org.openide.util.NbBundle.getMessage(EditPanel.class, "EditPanel.filesLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(versionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(versionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fileSelectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(filesLabel))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(versionLabel)
                    .addComponent(versionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fileSelectionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void versionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_versionComboBoxActionPerformed
        updateFileSelectionPanel();
    }//GEN-LAST:event_versionComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.netbeans.modules.javascript.cdnjs.ui.FileSelectionPanel fileSelectionPanel;
    private javax.swing.JLabel filesLabel;
    private javax.swing.JComboBox versionComboBox;
    private javax.swing.JLabel versionLabel;
    // End of variables declaration//GEN-END:variables

    /**
     * Renderer of {@code Library.Version} objects.
     */
    static class LibraryVersionRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            if (value instanceof Library.Version) {
                Library.Version version = (Library.Version)value;
                value = version.getName();
            }
            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        }
    }

}