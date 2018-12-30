package asc.msc.coursework.com.expensetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import asc.msc.coursework.com.expensetracker.addexpense.AddExpenseDialog;
import asc.msc.coursework.com.expensetracker.dto.Transaction;
import asc.msc.coursework.com.expensetracker.expenselist.ExpenseListAdapter;
import asc.msc.coursework.com.expensetracker.dto.Budget;

public class MainActivity extends AppCompatActivity {

    private RecyclerView expenseListView;
    public static SharedPreferences sharedPreferences;
    ExpenseListAdapter.ExpenseListDiplayer expenseListDiplayer = new ExpenseListAdapter.ExpenseListDiplayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSharedPreferences();

        List<Transaction> initialData = expenseListDiplayer.getInitialData();

        expenseListView = (RecyclerView) findViewById(R.id.expenseList);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        ArrayList<Budget> list = new ArrayList<>();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        expenseListView.setLayoutManager(manager);
        expenseListView.setAdapter(new ExpenseListAdapter(this, initialData));

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddExpense();
            }
        });
    }

    private void showAddExpense() {
        AddExpenseDialog addExpenseDialog = new AddExpenseDialog();
        addExpenseDialog.show(getSupportFragmentManager(),"AddExpenses");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Initialize shared preference for data saving and retrieval.
     */
    private void setSharedPreferences() {
        sharedPreferences = getSharedPreferences("asc.msc.coursework.com.expensetracker", Context.MODE_PRIVATE);
    }
}
