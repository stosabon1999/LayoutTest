package ru.production.ssobolevsky.layouttest;


import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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
public class FragmentTwo extends Fragment {

    private Button mButtonOne;

    private Button mButtonTwo;

    private Button mButtonThree;

    private Button mButtonFour;

    private Button mButtonFive;

    private Button mButtonSix;

    private Button mButtonSeven;

    private Button mButtonEight;

    private Button mButtonNine;
    private IActivityCallback mViewCallBack;

    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mViewCallBack = ((MainActivity) context);
        } catch (ClassCastException c) {
            throw new ClassCastException("Приведи активность к нужному интерфейсу");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_two, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mButtonOne = view.findViewById(R.id.b_two_one);
        mButtonTwo = view.findViewById(R.id.b_two_two);
        mButtonThree = view.findViewById(R.id.b_two_three);
        mButtonFour = view.findViewById(R.id.b_two_four);
        mButtonFive = view.findViewById(R.id.b_two_five);
        mButtonSix = view.findViewById(R.id.b_two_six);
        mButtonSeven = view.findViewById(R.id.b_two_seven);
        mButtonEight = view.findViewById(R.id.b_two_eight);
        mButtonNine = view.findViewById(R.id.b_two_nine);
    }

    public void setTextAndColors(int[] colors, String[] texts) {
        mButtonOne.setBackgroundColor(colors[0]);
        mButtonOne.setText(texts[0]);
        mButtonTwo.setBackgroundColor(colors[1]);
        mButtonTwo.setText(texts[1]);
        mButtonThree.setBackgroundColor(colors[2]);
        mButtonThree.setText(texts[2]);
        mButtonFour.setBackgroundColor(colors[3]);
        mButtonFour.setText(texts[3]);
        mButtonFive.setBackgroundColor(colors[4]);
        mButtonFive.setText(texts[4]);
        mButtonSix.setBackgroundColor(colors[5]);
        mButtonSix.setText(texts[5]);
        mButtonSeven.setBackgroundColor(colors[6]);
        mButtonSeven.setText(texts[6]);
        mButtonEight.setBackgroundColor(colors[7]);
        mButtonEight.setText(texts[7]);
        mButtonNine.setBackgroundColor(colors[8]);
        mButtonNine.setText(texts[8]);
        sendText();
    }

    private void sendText() {
        mViewCallBack.onFragmentData(mButtonFive.getText().toString());
    }
}
