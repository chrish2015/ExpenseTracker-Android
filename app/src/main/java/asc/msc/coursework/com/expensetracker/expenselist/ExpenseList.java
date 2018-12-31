package asc.msc.coursework.com.expensetracker.expenselist;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.transition.TransitionManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import asc.msc.coursework.com.expensetracker.MainActivity;
import asc.msc.coursework.com.expensetracker.dto.Expense;
import asc.msc.coursework.com.expensetracker.dto.Income;
import asc.msc.coursework.com.expensetracker.dto.Transaction;
import asc.msc.coursework.com.expensetracker.R;

public class ExpenseList extends RecyclerView.Adapter<ExpenseList.ListHolder> {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private List<Transaction> arrayList;
    double total = 0;
    private int mExpandedPosition = -1;


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
    public void onBindViewHolder(@NonNull final ListHolder listHolder, final int i) {
        final Transaction transaction = arrayList.get(i);

        Double value = transaction.getValue();
        total += value;
        listHolder.value.setText(String.valueOf(value));


        final boolean isExpanded = i == mExpandedPosition;
        if (isExpanded) {
            listHolder.contentTxt.setText("Name    : " + transaction.getName());
            listHolder.dateTxt.setText("Date           : " + transaction.getDate().toString());
            listHolder.commentTxtView.setText("Comment  : " + transaction.getComment());
            listHolder.value.setTypeface(listHolder.value.getTypeface(), Typeface.BOLD_ITALIC);

        } else {
            listHolder.contentTxt.setText(transaction.getName());
            listHolder.dateTxt.setText(transaction.getDate().toString());
            listHolder.commentTxtView.setText(transaction.getComment());
            listHolder.linearLayoutMain.setBackground(ContextCompat.getDrawable(context, R.drawable.expense_list_click));
            listHolder.value.setTypeface(null, Typeface.BOLD);

        }

        if (transaction instanceof Expense) {
            listHolder.value.setTextColor(Color.RED);
            Expense expense = (Expense) transaction;
            String categoryID = String.valueOf(expense.getCategoryId());
            if(isExpanded){
                listHolder.linearLayoutMain.setBackground(ContextCompat.getDrawable(context, R.drawable.expense_list_clicked_red));
                categoryID+= "Category   : ";
            }
            listHolder.categoryTxtView.setText(categoryID);
        } else if (transaction instanceof Income) {
            listHolder.value.setTextColor(Color.GREEN);
            Income income = (Income) transaction;
            String sourceId = String.valueOf(income.getSourceId());
            if(isExpanded){
                listHolder.linearLayoutMain.setBackground(ContextCompat.getDrawable(context, R.drawable.expense_list_clicked_green));
                sourceId+= "Source     : ";
            }
            listHolder.categoryTxtView.setText(sourceId);

        }
        listHolder.hiddenLayout.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        listHolder.itemView.setActivated(isExpanded);
        listHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : i;
                TransitionManager.beginDelayedTransition(MainActivity.expenseListView);
                notifyDataSetChanged();
                TextView cmm = (TextView) v.findViewById(R.id.commentTxtView);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setArrayList(List<Transaction> arrayList) {
        this.arrayList = arrayList;
        total = 0;
        for (Transaction transaction : arrayList) {
            if (transaction instanceof Income)
                total += transaction.getValue();
            else
                total -= transaction.getValue();
        }
        MainActivity.totalValue.setText(String.valueOf(total));

    }

    public static class ListHolder extends RecyclerView.ViewHolder {
        TextView contentTxt;
        TextView dateTxt;
        TextView value;
        TextView totalValue;
        TextView commentTxtView;
        TextView categoryTxtView;
        LinearLayout hiddenLayout;
        LinearLayout linearLayoutMain;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            contentTxt = (TextView) itemView.findViewById(R.id.contentTxt);
            dateTxt = (TextView) itemView.findViewById(R.id.dateTxt);
            value = (TextView) itemView.findViewById(R.id.value);
            totalValue = itemView.findViewById(R.id.totalValue);
            commentTxtView = itemView.findViewById(R.id.commentTxtView);
            categoryTxtView = itemView.findViewById(R.id.categoryTxtView);
            hiddenLayout = itemView.findViewById(R.id.hiddenLayout);
            linearLayoutMain = itemView.findViewById(R.id.linearLayoutMain);
        }
    }
}

