package com.muta7.muta7;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;

/**
 * Created by DeLL on 12/07/2017.
 */

public abstract class CreateSpaceFragment extends Fragment {

    public abstract boolean validate();
    public abstract Object getData();
}
