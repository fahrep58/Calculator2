package com.example.fahrep58.calculator2;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Buttons.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Buttons#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Buttons extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String thequestion=" ";

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Buttons.
     */
    // TODO: Rename and change types and number of parameters
    public static Buttons newInstance(String param1, String param2) {
        Buttons fragment = new Buttons();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void setTheMessage(String themessage){
        thequestion = themessage;
    }

    public void onClick(View v){
        Button b = (Button)v;

        String todo = b.getText().toString().toLowerCase();
        if (todo.equals("solve"))
        {
            mListener.timeToSolve(thequestion);
        }
        else if (todo.equals("clear") ){
            thequestion ="";
            mListener.updateProblem("");
        }
        else if( todo.equals("backspace")){
            if (!thequestion.equals(""))
            {
                thequestion = thequestion.substring(0,thequestion.length() -1);
                mListener.updateProblem(thequestion);
            }
        }
        else if( todo.equals("logout")){
            mListener.logoutuser();
        }
        else
        {
            thequestion += todo;
            mListener.updateProblem(todo);
        }


    }

    public Buttons() {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_buttons, container, false);
        ((Button)v.findViewById(R.id.button)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button2)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button3)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button4)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button5)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button6)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button7)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button8)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button9)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button10)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button11)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button12)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button13)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button14)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button15)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button16)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button17)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button18)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button19)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button20)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button21)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button22)).setOnClickListener(this);
        ((Button)v.findViewById(R.id.button26)).setOnClickListener(this);


        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

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
        public void updateProblem(String numbers);
        public void timeToSolve(String numbers);
        public void logoutuser();
    }

}
