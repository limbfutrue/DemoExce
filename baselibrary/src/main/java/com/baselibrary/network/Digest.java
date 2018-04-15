package com.baselibrary.network;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Digest{

    public Digest(){
    }

    public static String getMD5Digest(String strInfo){
        String strInfoDigest = "";
        try
        {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(strInfo.getBytes());
            byte bInfoDigest[] = messageDigest.digest();
            strInfoDigest = byteToHex(bInfoDigest);
        }
        catch(NoSuchAlgorithmException ex)
        {
            System.out.println("没有此算法");
        }
        return strInfoDigest;
    }

    public static String byteToHex(byte bInfoDigest[]){
        String strInfoDigest = "";
        String strTemp = "";
        for(int i = 0; i < bInfoDigest.length; i++){
            strTemp = Integer.toHexString(bInfoDigest[i] & 0xff);
            if(strTemp.length() == 1)
                strInfoDigest = strInfoDigest + "0" + strTemp;
            else
                strInfoDigest = strInfoDigest + strTemp;
        }

        strInfoDigest = strInfoDigest.toUpperCase();
        return strInfoDigest;
    }

    public static byte[] hexToByte(String strInfo){
        String strHexIndex = "0123456789abcdef0123456789ABCDEF";
        int iInfoLength = strInfo.length() / 2;
        byte bData[] = new byte[iInfoLength];
        int j = 0;
        for(int i = 0; i < iInfoLength; i++){
            char c = strInfo.charAt(j++);
            int n = strHexIndex.indexOf(c);
            int b = (n & 0xf) << 4;
            c = strInfo.charAt(j++);
            n = strHexIndex.indexOf(c);
            b += n & 0xf;
            bData[i] = (byte)b;
        }

        return bData;
    }
    
    /*public static void main(String[] args) {
    	String strAgentPWD="";
    	Digest digest = new Digest();
		strAgentPWD = digest.getMD5Digest(strAgentPWD);
		System.out.println("===1=="+strAgentPWD);
		StringBuffer newStrAgentPWD=new StringBuffer();
		byte bData[]=null;
		bData=digest.hexToByte(strAgentPWD);
		if(bData!=null){
			for(int i=0;i<bData.length;i++){
				newStrAgentPWD.append(bData[i]);
			}
		}
		System.out.println("===2=="+newStrAgentPWD);
	}*/
}