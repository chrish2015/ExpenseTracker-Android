package asc.msc.coursework.com.expensetracker.expenselist;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import asc.msc.coursework.com.expensetracker.dto.Expense;
import asc.msc.coursework.com.expensetracker.dto.Income;
import asc.msc.coursework.com.expensetracker.dto.Transaction;
import asc.msc.coursework.com.expensetracker.R;

public class ExpenseList extends RecyclerView.Adapter<ExpenseList.ListHolder> {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private List<Transaction> arrayList;



    public ExpenseList(Context context, List<Transaction> list) {
        this.context = context;
        this.setArrayList(list);
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Called every time when the TextView area is generated for the recycle view.
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.expense_list, viewGroup, false);
        ListHolder listHolder = new ListHolder(view);
        return listHolder;
    }

    /**
     * Called when data is populated to the View Holders.
     *
     * @param listHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {
        Transaction transaction = arrayList.get(i);
        listHolder.contentTxt.setText(transaction.getName());
        listHolder.dateTxt.setText(transaction.getDate().toString());
        listHolder.value.setText(String.valueOf(transaction.getValue()));
        if (transaction instanceof Expense) {
            listHolder.value.setTextColor(Color.RED);
        } else if (transaction instanceof Income) {
            listHolder.value.setTextColor(Color.GREEN);

        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setArrayList(List<Transaction> arrayList) {
        this.arrayList = arrayList;
    }

    public static class ListHolder extends RecyclerView.ViewHolder {
        TextView contentTxt;
        TextView dateTxt;
        TextView value;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            contentTxt = (TextView) itemView.findViewById(R.id.contentTxt);
            dateTxt = (TextView) itemView.findViewById(R.id.dateTxt);
            value = (TextView) itemView.findViewById(R.id.value);
        }
    }
}

