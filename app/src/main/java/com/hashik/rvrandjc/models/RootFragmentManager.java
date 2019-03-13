package com.hashik.rvrandjc.models;

//This class is used to maintain a track of currently destroyed fragment in RootHomeFragment
public class RootFragmentManager {
    private int currentFragment;
    private static RootFragmentManager rootFragmentManager;

    public static RootFragmentManager getInstance(){
        if(rootFragmentManager == null){
            return new RootFragmentManager();
        }
        return rootFragmentManager;
    }
    public void setCurrentFragment(int currentFrag){
        currentFragment = currentFrag;
    }

    public int getCurrentFragment(){
        return currentFragment;
    }
}
