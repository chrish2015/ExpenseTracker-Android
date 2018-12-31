package asc.msc.coursework.com.expensetracker.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 */
public class Income extends Transaction implements Serializable {
    public Income(String name,String comment,Date date, double value,int sourceId){
        super(name,comment,date,value);
        this.sourceId =sourceId;
    }

    private int sourceId;

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }
}