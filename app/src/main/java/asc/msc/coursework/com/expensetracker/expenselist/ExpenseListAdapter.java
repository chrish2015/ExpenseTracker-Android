package asc.msc.coursework.com.expensetracker.expenselist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import asc.msc.coursework.com.expensetracker.dto.Transaction;
import asc.msc.coursework.com.expensetracker.MainActivity;
import asc.msc.coursework.com.expensetracker.R;
import asc.msc.coursework.com.expensetracker.dao.Serializer;
import asc.msc.coursework.com.expensetracker.dto.Transaction;

public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ListHolder> {

    private final LayoutInflater layoutInflater;
    private final Context context;
    private List<Transaction> arrayList;

    public ExpenseListAdapter(Context context, List<Transaction> list) {
        this.context = context;
        this.arrayList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Called every time when the TextView area is generated for the recycle view.
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
     * @param listHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {
        for(Transaction transaction:arrayList) {
            listHolder.contentTxt.setText(transaction.getName());
            listHolder.dateTxt.setText(transaction.getDate().toString());
            listHolder.value.setText(String.valueOf(transaction.getValue()));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
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


    public static class ExpenseListDiplayer {
        String serialize;
        Transaction deserialize;
        Object deserializedTransaction;

        /**
         * Generate the initial data for the first screen.
         * Check if there is a transaction variable in the preferences and
         * if not it will display the default values.
         *
         * @return
         */
        public List<Transaction> getInitialData() {
            String transactions = MainActivity.sharedPreferences.getString("transactions", null);
            if (transactions == null) {
                Transaction transaction = new Transaction();
                transaction.setValue(100d);
                transaction.setName("Test");
                transaction.setDate(new Date());
                return new ArrayList<Transaction>(Arrays.asList(transaction));
            }
            try {
                deserializedTransaction = Serializer.deserialize(transactions);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return (ArrayList<Transaction>) deserializedTransaction;
        }
    }
}

