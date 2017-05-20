package com.vovatkach2427gmail.houseenergyoptimization.Model;

import java.util.List;

/**
 * Created by vovat on 16.05.2017.
 */

public class Set {
    private int id;
    private String name;
    private List<Device> devices;
    public Set(int id,String name,List<Device> devices)
    {
        this.id=id;
        this.name=name;
        this.devices=devices;
    }

    public List<Device> getListOfDevice() {
        return devices;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
