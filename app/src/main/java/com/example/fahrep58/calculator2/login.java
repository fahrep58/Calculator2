package com.example.fahrep58.calculator2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link login.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class login extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment login.
     */
    // TODO: Rename and change types and number of parameters
    public static login newInstance(String param1, String param2) {
        login fragment = new login();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public login() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }



    public void onClick(View v){
        Button b = (Button)v;





        String todo = b.getText().toString().toLowerCase();
        if (todo.equals("login"))
        {
            EditText user1 = (EditText) this.getView().findViewById(R.id.editText);
            String user = user1.getText().toString();
            EditText pass1 = (EditText) this.getView().findViewById(R.id.editText2);
            String pass = pass1.getText().toString();
            if (user.equals("") || pass.equals(""))
            {
                mListener.alertme("enter user/pass");
            }else
            {
                mListener.trytologin(user, pass);
            }

        }
        else if (todo.equals("register"))
        {
            EditText user1 = (EditText) this.getView().findViewById(R.id.editText);
            String user = user1.getText().toString();
            EditText pass1 = (EditText) this.getView().findViewById(R.id.editText2);
            String pass = pass1.getText().toString();
            if (user.equals("") || pass.equals(""))
            {

            }else {

                mListener.trytoregister(user, pass);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        ((Button) v.findViewById(R.id.button24)).setOnClickListener(this);
        ((Button) v.findViewById(R.id.button25)).setOnClickListener(this);


        return v;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void trytologin(String user, String pass);
        public void trytoregister(String user, String pass);
        public void alertme(String x);
    }

}
