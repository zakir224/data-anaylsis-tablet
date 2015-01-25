package com.nhf.adapter;

import android.content.Context;
import android.content.res.Resources;

import com.nhf.icu.R;

/**
 * Created by Zakir on 9/9/2014.
 */
public class ReportTableAdapter extends ICUTableAdapter {

    private final int width;
    private final int height;
    private int rowCount;
    private int columnCount;
    private String[][] values;

    public ReportTableAdapter(Context context,String[][] values) {
        super(context);

        Resources resources = context.getResources();
        this.values=values;
        width = resources.getDimensionPixelSize(R.dimen.table_width);
        height = resources.getDimensionPixelSize(R.dimen.table_height);
    }

    @Override
    public int getRowCount() {
        return rowCount;
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public int getWidth(int column) {
        return width;
    }

    @Override
    public int getHeight(int row) {
        return height;
    }

    @Override
    public String getCellString(int row, int column) {
        return values[row+1][column+1];
    }

    @Override
    public int getLayoutResource(int row, int column) {
        final int layoutResource;
        switch (getItemViewType(row, column)) {
            case 0:
                layoutResource = R.layout.item_table1_header;
                break;
            case 1:
                layoutResource = R.layout.item_table1;
                break;
            default:
                throw new RuntimeException("wtf?");
        }
        return layoutResource;
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount=rowCount;
    }

    @Override
    public void setColumnCount(int columnCount) {
        this.columnCount=columnCount;
    }

    @Override
    public int getItemViewType(int row, int column) {
        if (row < 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
