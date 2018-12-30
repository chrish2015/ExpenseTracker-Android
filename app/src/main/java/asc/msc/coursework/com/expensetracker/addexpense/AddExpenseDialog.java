package asc.msc.coursework.com.expensetracker.addexpense;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import asc.msc.coursework.com.expensetracker.R;

public class AddExpenseDialog extends DialogFragment {

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
        Spinner category = (Spinner) view.findViewById(R.id.category);
        expenseListDropDown.createDropDown(category,getContext());
        addExpenseOnClickListner(view);

    }

    /**
     * All the logic's after clicking the add button goes here.
     *
     * @param view
     */
    private void addExpenseOnClickListner(@NonNull final View view) {
        View addExpense = view.findViewById(R.id.add_expense);
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView expenseNameView = (TextView) view.findViewById(R.id.expenseName);
                String expenseName = expenseNameView.getText().toString();
                TextView expenseDetailsView = (TextView) view.findViewById(R.id.expenseDetails);
                String expenseDetails = expenseDetailsView.getText().toString();
                DatePicker datePicker = (DatePicker) view.findViewById(R.id.datePicker);
                long minDate = datePicker.getMinDate();



                dismiss();
            }
        });
    }


}
