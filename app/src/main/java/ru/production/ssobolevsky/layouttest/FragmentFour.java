package ru.production.ssobolevsky.layouttest;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentFour extends Fragment {

    private TextView mTextView;

    private TextView mTextView2;

    private Button mButton;

    public FragmentFour() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_four, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView = view.findViewById(R.id.textView);
        mTextView2 = view.findViewById(R.id.textView2);
        mButton = view.findViewById(R.id.button);
    }

    public void setCornerAndTexts(int corner, String[] texts) {
        //Log.wtf("TAG",  texts[0] + "A");
        mTextView.setText(texts[0]);
        mTextView2.setText(texts[1]);
        mButton.setText(texts[2]);
    }

}
