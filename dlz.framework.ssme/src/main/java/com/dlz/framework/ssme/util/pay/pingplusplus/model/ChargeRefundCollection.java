package com.dlz.framework.ssme.util.pay.pingplusplus.model;

import java.util.Map;

import com.dlz.framework.ssme.util.pay.pingplusplus.Pingpp;
import com.dlz.framework.ssme.util.pay.pingplusplus.exception.APIConnectionException;
import com.dlz.framework.ssme.util.pay.pingplusplus.exception.APIException;
import com.dlz.framework.ssme.util.pay.pingplusplus.exception.AuthenticationException;
import com.dlz.framework.ssme.util.pay.pingplusplus.exception.ChannelException;
import com.dlz.framework.ssme.util.pay.pingplusplus.exception.InvalidRequestException;

public class ChargeRefundCollection extends PingppColllectionAPIResource<Refund> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    public ChargeRefundCollection all(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException {

        String url = String.format("%s%s", Pingpp.getApiBase(), this.getURL());
        return request(RequestMethod.GET, url, params, ChargeRefundCollection.class);
    }

    public Refund retrieve(String id)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException {

        String url = String.format("%s%s/%s", Pingpp.getApiBase(), this.getURL(), id);
        return request(RequestMethod.GET, url, null, Refund.class);
    }

    public Refund create(Map<String, Object> params)
            throws AuthenticationException, InvalidRequestException,
            APIConnectionException, APIException, ChannelException {

        String url = String.format("%s%s", Pingpp.getApiBase(), this.getURL());
        return request(RequestMethod.POST, url, params, Refund.class);
    }

}
