package asc.msc.coursework.com.expensetracker.dto;

import java.util.Date;
import java.util.List;

/**
 * 
 */
public class Expense extends Transaction{

    private int category;


    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
}