package com.zhaojm2.tank;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    private static volatile  PropertyMgr propertyIntance;
    Properties property = null;
    private PropertyMgr() {
        property = new Properties();
        try {
            property.load(new FileInputStream(new File("D:/IDEA/TANKWAR/src/config")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static PropertyMgr getIntance() {
        if(propertyIntance == null) {
            synchronized (PropertyMgr.class) {
                if (propertyIntance == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    propertyIntance = new PropertyMgr();
                }
            }
        }
        return propertyIntance;
    }

    public Object get(String key) {
        if(property == null) return null;
        return property.get(key);
    }
}
