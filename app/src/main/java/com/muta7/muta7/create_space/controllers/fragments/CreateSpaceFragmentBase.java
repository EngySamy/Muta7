package com.muta7.muta7.create_space.controllers.fragments;

import android.support.v4.app.Fragment;

/**
 * Created by DeLL on 12/07/2017.
 */

public abstract class CreateSpaceFragmentBase extends Fragment {

    public abstract boolean validate();
    public abstract Object getData();
}
