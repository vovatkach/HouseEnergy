package com.vovatkach2427gmail.houseenergyoptimization.Model;

/**
 * Created by vovat on 16.05.2017.
 */

public class Set {
    private int id;
    private String name;
    private String listOfDevice;
    public Set(int id,String name,String listOfDevice)
    {
        this.id=id;
        this.name=name;
        this.listOfDevice=listOfDevice;
    }

    public String getListOfDevice() {
        return listOfDevice;
    }

    public String getName() {
        return name;
    }
}
