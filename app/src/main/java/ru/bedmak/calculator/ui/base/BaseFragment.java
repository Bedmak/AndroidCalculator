package ru.bedmak.calculator.ui.base;

import android.content.Context;

import ru.bedmak.calculator.ui.MainViewListener;

public class BaseFragment extends android.support.v4.app.Fragment {

    protected MainViewListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(getActivity() instanceof MainViewListener)) {
            throw new AssertionError("MainActivity must implement MainViewListener");
        }
        listener = (MainViewListener) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
