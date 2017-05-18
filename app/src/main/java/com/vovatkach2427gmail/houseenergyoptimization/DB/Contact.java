package com.vovatkach2427gmail.houseenergyoptimization.DB;

/**
 * Created by vovat on 31.03.2017.
 */

public class Contact {
    public final static String DATABASE_NAME = "myDB.sqlite";
    public final static int DATABASE_VERSION = 1;

    public final class TABLE_SET {
        final public static String TABLE_NAME = "sets";
        final public static String ID = "id";
        final public static String NAME = "name";
        final public static String LIST_OF_DEVICE = "listOfDevice";;
    }
    public final class TABLE_DEVICE {
        final public static String TABLE_NAME = "device";
        final public static String ID = "id";
        final public static String NAME = "name";
        final public static String EXTRA_INFO = "extraInfo";
        final public static String POWER_CONSUMPTION = "powerConsumption";
        final public static String PRIORITY = "priority";
        final public static String T_MIN = "tMin";
        final public static String T_MAX = "tMax";
    }
}
