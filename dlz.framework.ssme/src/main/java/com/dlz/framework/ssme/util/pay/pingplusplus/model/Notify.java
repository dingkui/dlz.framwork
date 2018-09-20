package com.dlz.framework.ssme.util.pay.pingplusplus.model;

import com.dlz.framework.ssme.util.pay.pingplusplus.net.APIResource;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Notify {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    class InnerObject {
        String object;
    }

    public static Object parseNotify(String notifyJson) {

        InnerObject innerObject;
        try {
            innerObject = new Gson().fromJson(notifyJson, InnerObject.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        if (innerObject == null || innerObject.object == null || innerObject.object.isEmpty())
            return null;

        if (innerObject.object.equals("charge")) {
            return APIResource.GSON.fromJson(notifyJson, Charge.class);
        } else if (innerObject.object.equals("refund")) {
            return new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()
                    .fromJson(notifyJson, Refund.class);
        }

        return null;
    }
}
