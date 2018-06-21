package ru.production.ssobolevsky.layouttest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentOne extends Fragment {

    private Button mButtonOne;

    private Button mButtonTwo;

    private Button mButtonThree;

    public FragmentOne() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButtonOne = view.findViewById(R.id.b_one_one);
        mButtonTwo = view.findViewById(R.id.b_one_two);
        mButtonThree = view.findViewById(R.id.b_one_three);
    }

    public static Fragment newInstance() {
        Fragment fragment = new FragmentOne();
        return fragment;
    }

    public void setButtonsColors(int[] colors) {
        mButtonOne.setBackgroundColor(colors[0]);
        mButtonTwo.setBackgroundColor(colors[1]);
        mButtonThree.setBackgroundColor(colors[2]);
    }

}
