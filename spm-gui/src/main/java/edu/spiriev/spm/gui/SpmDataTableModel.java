/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.spiriev.spm.gui;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author root_spiriev
 */
public class SpmDataTableModel extends AbstractTableModel{
    
    private String[] columnNames;
    private String[][] data;

    public SpmDataTableModel() {
        this.columnNames = new String[0];
        this.data = new String[0][0];
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public String[][] getData() {
        return data;
    }
    
    

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

}
