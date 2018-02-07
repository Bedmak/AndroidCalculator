package ru.bedmak.calculator.ui.base;

import android.content.Context;

import ru.bedmak.calculator.ui.MvpView;

public class BaseFragment extends android.support.v4.app.Fragment {

    protected MvpView listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(getActivity() instanceof MvpView)) {
            throw new AssertionError("MainActivity must implement MvpView");
        }
        listener = (MvpView) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}
