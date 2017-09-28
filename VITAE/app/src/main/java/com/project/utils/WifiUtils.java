// Developer: Ahmet Kaymak
// Date: 13.08.2017

package com.project.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class WifiUtils {

    public static String getIpAdress(Context context) throws UnknownHostException {
        WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        int ip = wifiInfo.getIpAddress();
        byte[] ipAddress = BigInteger.valueOf(ip).toByteArray();
        InetAddress myaddr = InetAddress.getByAddress(ipAddress);
        String hostIp = myaddr.getHostAddress();
        return  hostIp;
    }
}
