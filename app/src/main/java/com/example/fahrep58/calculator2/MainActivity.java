package com.example.fahrep58.calculator2;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.TextView;


public class MainActivity extends Activity implements Buttons.OnFragmentInteractionListener, Output.OnFragmentInteractionListener, Solved.OnFragmentInteractionListener, ReturnButton.OnFragmentInteractionListener, Equations.OnFragmentInteractionListener
{
    private Buttons butt;
    private Output out;
    private Equations equation;
    private InfixEvaluator infix;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butt = new Buttons();
        getFragmentManager().beginTransaction().add(R.id.containButtons, butt).commit();

        equation = new Equations();
        getFragmentManager().beginTransaction().add(R.id.containEquations, equation).commit();

        out = new Output();
        getFragmentManager().beginTransaction().add(R.id.containProb, out).commit();

    }

    public void saveEquation(){

    }

    public void timeToSolve(String numbers){

        infix = new InfixEvaluator();

        infix.convertToPostfix(numbers);
        //hides the buttons and the equation that was entered
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.containButtons)).commit();
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.containProb)).commit();

        Solved solved = new Solved();
        solved.setans1(numbers);
        solved.setans2("\n" + infix.evaluatePostfix(infix.getExpression()));


        getFragmentManager().beginTransaction().add(R.id.containButtons, solved).commit();

        ReturnButton retbutt = new ReturnButton();
        getFragmentManager().beginTransaction().add(R.id.containProb, retbutt).commit();





    }
    public void returnToKeypad(){

        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.containButtons)).commit();
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.containProb)).commit();

        getFragmentManager().beginTransaction().add(R.id.containButtons, butt).commit();
        getFragmentManager().beginTransaction().add(R.id.containProb, out).commit();
    }
    public void doStuff()
    {

    }

    public void solveProblem()
    {


    }

    public void updateProblem(String numbers)
    {
        TextView v = (TextView) getFragmentManager().findFragmentById(R.id.containProb).getView().findViewById(R.id.txtOutput);
        v.setText(numbers);
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

    public void saveTheEquation(){

    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
