package net.ddns.softux.hey.androidapp;

import android.content.Context;
import android.support.v4.app.Fragment;

public abstract class BaseFragment<T> extends Fragment {
    protected T containerListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(isContainerListener()) {
            containerListener = (T) getActivity();
        } else {
            containerListener = null;
        }
    }

    protected abstract boolean isContainerListener();

    @Override
    public void onDetach() {
        super.onDetach();
        containerListener = null;
    }
}
