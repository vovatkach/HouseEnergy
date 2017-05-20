package com.vovatkach2427gmail.houseenergyoptimization.DB;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;

import java.util.List;

/**
 * Created by vovat on 18.05.2017.
 */

public class Translator {
    //робота з списоком приладів для БД
    public static String listOfDevivesToJson(List<Device> devices)
    {
       DevicesContainer container=new DevicesContainer(devices);
        GsonBuilder builder=new GsonBuilder();
        Gson gson=builder.create();
        return gson.toJson(container);
    }
    public static List<Device> fromJsonToListOfDevice(String jSon)
    {
        GsonBuilder builder=new GsonBuilder();
        Gson gson=builder.create();
        DevicesContainer container=gson.fromJson(jSon,DevicesContainer.class);
        return container.getDevices();
    }
}
//--------Контейнери
class DevicesContainer {
    private List<Device> devices;
    public DevicesContainer(List<Device> devices)
    {
        this.devices=devices;
    }

    public List<Device> getDevices() {
        return devices;
    }
}

