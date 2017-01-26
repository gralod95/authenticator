/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverprobnik;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

/**
 *
 * @author macbook
 */
public class SRP {
    private static int k = 3;
    private static int q = 1481;
    private static int N = 2*q+1;
    private static int base = 2999;
    private static String v;
    private static int b =  getNewRandom();
    private static int solt = 0;
    private static String messageToClient = "";
    private static String B = "";
    private static String A = "";
    private static boolean firstMessageWas = false;
    private static long u = 0;
    private static float S = 0;
    private static long K = 0;
    private static String I = "";
    
    public static boolean analiticLogin1(String login, String setA) throws NoSuchAlgorithmException{
        if(Long.parseLong(setA) == 0){
            return false;
        }
        if(!UserBase.containsLogin(login)){
            return false;
        }
        I = login;
        solt = Integer.parseInt(UserBase.getSolt(login));
        v = UserBase.getV(login);
        b = getNewRandom();
        B = getB(Long.parseLong(v));
        messageToClient = "login{\n" +
"S = \""+solt+"\";\n" +
"B = \""+B+"\";\n" +
"}\n" +
"end of message";
        A = setA;
        firstMessageWas = true;
        System.out.println("b = "+b);
        System.out.println("A = "+A);
        System.out.println("B = "+B);
        u = sha256(A+B);
        System.out.println("u = "+u);
        if(u == 0){
            return false;
        }
        System.out.println("v = "+v);
        S = modExp((Long.parseLong(A)*modExp(Long.parseLong(v),u,N)),b,N);
        K = sha256(Float.toString(S));
        System.out.println("S = "+S);
        System.out.println("K = "+K);
        return true;
    }
    
    public static boolean analiticLogin2(String setM) throws NoSuchAlgorithmException{
        if(Long.parseLong(setM) == 0){
            return false;
        }
        long x1 = (sha256(Integer.toString(N))^modExp(base, 1, N));
        long x2 = sha256(I);
        String x3 = solt+A+B+K; 
        long M = sha256(x1+x2+x3);
        System.out.println("M = "+M+"\n"
                + "setM = "+setM);
        if((Long.parseLong(setM)) == M){
            //R = H( A, M, K )
            String x4 = A+M+K;
            long R = sha256(x4);
            System.out.println("R = "+ R);
            messageToClient = "login{\n" +
"R = \""+R+"\";\n" +
"}\n" +
"end of message";
        }
        return true;
    }
    
    public static String getMessageToClient(){
        String i = messageToClient;
        messageToClient = "";
        return i;
    }
    
    private static String getB(long v) throws NoSuchAlgorithmException{
        return Integer.toString((int)(k*v + modExp(base, b,N)));
    }
    
    private static long sha256(String input) throws NoSuchAlgorithmException {
        long output = 0;
        MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            output+= (result[i] & 0xff) + 0x100;
        }
        return output;
    }
    
    private static long modExp(long b, long e, long m){
       BigInteger b1, e1, m1;

	// create a BigInteger exponent

        b1 = new BigInteger(Long.toString(b));
        e1 = new BigInteger(Long.toString(e));
        m1 = new BigInteger(Long.toString(m));
        // perform modPow operation on bi1 using bi2 and exp
	b1 = b1.modPow(e1, m1);
        long res = b1.longValue();
        System.out.println(res);
        return res;
    }
    
    public static int getNewRandom(){
        Random ran = new SecureRandom();
        return ran.nextInt(100);
        
    }
    
    
}
