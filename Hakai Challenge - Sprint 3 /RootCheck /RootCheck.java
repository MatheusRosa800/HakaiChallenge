RootCheck.java


package com.example.sprint2;

import android.content.Context;

import com.scottyab.rootbeer.RootBeer;

public class RootCheck {

    // Function to check if the device is rooted
    public boolean isDeviceRooted(Context context) {
        RootBeer rootBeer = new RootBeer(context);
        return rootBeer.isRooted();
    }
}
