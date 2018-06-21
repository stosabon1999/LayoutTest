package ru.production.ssobolevsky.layouttest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment {

    private Button mButtonOne;

    private Button mButtonTwo;

    private Button mButtonThree;

    public FragmentThree() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_three, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButtonOne = view.findViewById(R.id.b_three_one);
        mButtonTwo = view.findViewById(R.id.b_three_two);
        mButtonThree = view.findViewById(R.id.b_three_three);
    }

    public static Fragment newInstance() {
        Fragment fragment = new FragmentThree();
        return fragment;
    }

    public void setButtonsTexts(String[] texts) {
        mButtonOne.setText(texts[0]);
        mButtonTwo.setText(texts[1]);
        mButtonThree.setText(texts[2]);
    }

    public void setButtonText(String text) {

        mButtonTwo.setText(text);
    }
}
