package com.hashik.rvrandjc.models;

//This class is used to maintain a track of currently destroyed fragment in RootHomeFragment
public class RootFragmentManager {
    private static int currentFragment;
    private static RootFragmentManager rootFragmentManager;

    public static RootFragmentManager getInstance(){
        if(rootFragmentManager == null){
            return new RootFragmentManager();
        }
        return rootFragmentManager;
    }
    public static void setCurrentFragment(int currentFrag){
        currentFragment = currentFrag;
    }

    public static int getCurrentFragment(){
        return currentFragment;
    }
}
