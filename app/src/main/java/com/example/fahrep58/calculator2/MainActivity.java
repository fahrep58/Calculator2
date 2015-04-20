package com.example.fahrep58.calculator2;

import android.app.Activity;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity implements Buttons.OnFragmentInteractionListener, Output.OnFragmentInteractionListener, Solved.OnFragmentInteractionListener, ReturnButton.OnFragmentInteractionListener, Equations.OnFragmentInteractionListener, login.OnFragmentInteractionListener
{
    private Buttons butt;
    private Output out;
    private Equations equation;
    private InfixEvaluator infix;
    private login login;
    private String mainuser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        butt = new Buttons();

        login = new login();
        getFragmentManager().beginTransaction().add(R.id.containButtons, butt).commit();

        equation = new Equations();

        out = new Output();
        getFragmentManager().beginTransaction().add(R.id.containProb, out).commit();
        infix = new InfixEvaluator();
    }

    public void saveEquation(String var, String number){

    }

    public void timeToSolve(String numbers){




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

    public void loginsuccess(){

        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.containButtons)).commit();


        getFragmentManager().beginTransaction().add(R.id.containButtons, butt).commit();
        getFragmentManager().beginTransaction().add(R.id.containEquations, equation).commit();

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
        Button bsolve = (Button) getFragmentManager().findFragmentById(R.id.containButtons).getView().findViewById(R.id.button22);
        v.setText(numbers);
        boolean worked = false;
        try{

            infix.convertToPostfix(numbers);
            infix.evaluatePostfix(infix.getExpression());
            worked = true;
        } catch (Exception ex)
        {
            worked = false;
            bsolve.setEnabled(false);
        }
       if (worked)
       {
           bsolve.setEnabled(true);
       }
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

    public void trytologin(String user, String pass){
        loginuser(user, pass);
    }

    public void trytoregister(String user, String pass){
        registeruser(user, pass);
    }

    public void logoutuser(){
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.containButtons)).commit();
        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.containEquations)).commit();

        getFragmentManager().beginTransaction().add(R.id.containButtons, login).commit();
    }

    public void registeruser(String user, String pass){

        NumberDBHelper n = new NumberDBHelper(getApplicationContext());
        SQLiteDatabase s = n.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(NumberContract.NumberEntry.COLUMN_NAME_ID2, user);
        cv.put(NumberContract.NumberEntry.COLUMN_NAME_VALUE2, pass);

        s.insert(NumberContract.NumberEntry.TABLE_NAME2, "null", cv);
        s.close();

    }

    public void loginuser(String user, String pass){
        NumberDBHelper mdb = new NumberDBHelper(getApplicationContext());
        SQLiteDatabase db = mdb.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        this.mainuser = user;
        String[] projection = {
                NumberContract.NumberEntry._ID,
                NumberContract.NumberEntry.COLUMN_NAME_ID2,
                NumberContract.NumberEntry.COLUMN_NAME_VALUE2};

        String where = NumberContract.NumberEntry.COLUMN_NAME_ID2 + " = ?";


        boolean worked = false;
        String[] whereArgs = new String[] {user};

        Cursor c = db.query(
                NumberContract.NumberEntry.TABLE_NAME2,
                projection,
                where,
                whereArgs,
                null,
                null,
                null,
                null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            String userdb = c.getString(c.getColumnIndexOrThrow(NumberContract.NumberEntry.COLUMN_NAME_ID2));
            String passdb = c.getString(c.getColumnIndexOrThrow(NumberContract.NumberEntry.COLUMN_NAME_VALUE2));
            if(pass.equals(passdb) && user.equals(userdb)) {
                alertme("login success!");
                loginsuccess();
                worked = true;
            }
                else
            {

            }
            c.moveToNext();
        }
        if (!worked){
            alertme("login failed, try again");
        }
    }

    public void addvariable(String var, String number){

        NumberDBHelper n = new NumberDBHelper(getApplicationContext());
        SQLiteDatabase s = n.getWritableDatabase();
        String type = "var";
        ContentValues cv = new ContentValues();
      //  cv.put(NumberContract.NumberEntry.COLUMN_NAME_USER, mainuser);
      //  cv.put(NumberContract.NumberEntry.COLUMN_NAME_TYPE, type);
        cv.put(NumberContract.NumberEntry.COLUMN_NAME_ID, var);

        cv.put(NumberContract.NumberEntry.COLUMN_NAME_VALUE, number);

        s.insert(NumberContract.NumberEntry.TABLE_NAME, "null", cv);
        s.close();

    }

    public void getvariables(String user, String pass){
        NumberDBHelper mdb = new NumberDBHelper(getApplicationContext());
        SQLiteDatabase db = mdb.getReadableDatabase();
        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                NumberContract.NumberEntry._ID,
                NumberContract.NumberEntry.COLUMN_NAME_ID2,
                NumberContract.NumberEntry.COLUMN_NAME_VALUE2};

        String where = NumberContract.NumberEntry.COLUMN_NAME_ID2 + " = ?";


        boolean worked = false;
        String[] whereArgs = new String[] {user};

        Cursor c = db.query(
                NumberContract.NumberEntry.TABLE_NAME2,
                projection,
                where,
                whereArgs,
                null,
                null,
                null,
                null);
        c.moveToFirst();
        while(!c.isAfterLast()) {
            String userdb = c.getString(c.getColumnIndexOrThrow(NumberContract.NumberEntry.COLUMN_NAME_ID2));
            String passdb = c.getString(c.getColumnIndexOrThrow(NumberContract.NumberEntry.COLUMN_NAME_VALUE2));
            if(pass.equals(passdb) && user.equals(userdb)) {
                alertme("login success!");
                loginsuccess();
                worked = true;
            }
            else
            {

            }
            c.moveToNext();
        }
        if (!worked){
            alertme("login failed, try again");
        }
    }




    public void alertme(String x){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(x)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
