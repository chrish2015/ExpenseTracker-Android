package asc.msc.coursework.com.expensetracker.addexpense;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import asc.msc.coursework.com.expensetracker.MainActivity;
import asc.msc.coursework.com.expensetracker.R;
import asc.msc.coursework.com.expensetracker.dao.DataManipulation;
import asc.msc.coursework.com.expensetracker.dto.Expense;
import asc.msc.coursework.com.expensetracker.dto.Income;

public class AddExpenseDialog extends DialogFragment {

    DataManipulation dataManipulation=new DataManipulation();
    View.OnClickListener onClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismiss();
        }
    };
    public AddExpenseDialog(){

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_expense, container, false);
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View closeButton = view.findViewById(R.id.closeButton);
        closeButton.setOnClickListener(onClickListener);
        ExpenseListDropDown expenseListDropDown = new ExpenseListDropDown();
        Spinner category = (Spinner) view.findViewById(R.id.categoryId);
        expenseListDropDown.createDropDown(category,getContext(),dataManipulation.getCategories());
        addExpenseOnClickListner(view);

    }

    /**
     * All the logic's after clicking the add button goes here.
     *
     * @param view
     */
    private void addExpenseOnClickListner(@NonNull final View view) {
        View addExpense = view.findViewById(R.id.add_expense);
        final TextView expenseNameView = (TextView) view.findViewById(R.id.expenseName);
        final TextView expenseDetailsView = (TextView) view.findViewById(R.id.expenseDetails);
        final DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
        final RadioGroup transactionType = (RadioGroup) view.findViewById(R.id.transactionType);
        final RadioButton incomeRadio = (RadioButton) view.findViewById(R.id.incomeRadio);
        final RadioButton expenseRadio = (RadioButton) view.findViewById(R.id.expenseRadio);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expenseName = expenseNameView.getText().toString();
                String expenseDetails = expenseDetailsView.getText().toString();
                Date minDate = new Date(datePicker.getMinDate());

                if(transactionType.getCheckedRadioButtonId() == incomeRadio.getId()){
                    dataManipulation.addTransaction(new Income(expenseName,expenseDetails,minDate,1000d,0));
                } else {
                    dataManipulation.addTransaction(new Expense(expenseName,expenseDetails,minDate,1000d,0));
                }
                MainActivity.expenseList.setArrayList(dataManipulation.getTransactions());
                MainActivity.expenseList.notifyDataSetChanged();
                dismiss();
            }
        });
    }


}
