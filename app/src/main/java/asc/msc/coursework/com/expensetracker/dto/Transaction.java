package asc.msc.coursework.com.expensetracker.dto;

import java.io.Serializable;
import java.util.Date;

/**
 *
 */
public class Transaction implements Serializable {

    private String name;
    private String comment;
    private Date date;
    private Double value;
    public Transaction(String name,String comment,Date date, double value) {
        setName(name);
        setComment(comment);
        setDate(date);
        setValue(value);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}