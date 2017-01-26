/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package authentication;

import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author macbook
 */
public class Authentication {

    /**
     * @param args the command line arguments
     */
    private static BufferedReader in = null;
    private static PrintWriter    out = null;
    private static Socket fromserver = null;
    private static int q = 1481;
    private static int N = 2*q+1;
    private static int base = 2999;
    private static int a = 0;
    private static long A = 0;
    private static String password;
    private static int salt = 0;
    private static float S =0;
    private static long B =0;
    private static long K = 0;
    private static long M = 0;
    private static boolean enter;
    private static Window ResultOfSingInWindow;
    public static void main(String[] args) throws IOException {
        
        conectToServer();
        windowLogic();
        //out.close();
        //in.close();
        //fromserver.close();
        
    }
    
    private static void windowLogic(){
        Window welcomeWindow = new Window("Welcome", "Registration", "Sing In" );
        welcomeWindow.pack();
        welcomeWindow.addWindowListener(new WindowListener() {
            
            public void windowActivated(WindowEvent event) {
 
            }
 
            public void windowClosed(WindowEvent event) {
 
            }
 
            public void windowClosing(WindowEvent event) {
                try {
                    sendMassegeToServer("");
                } catch (IOException ex) {
                    Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
                
            }
 
            public void windowDeactivated(WindowEvent event) {
 
            }
 
            public void windowDeiconified(WindowEvent event) {
 
            }
 
            public void windowIconified(WindowEvent event) {
 
            }
 
            public void windowOpened(WindowEvent event) {
 
            }
        });
        welcomeWindow.button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                welcomeWindow.setVisible(false);
                Window loginWindow = new Window("Sing In","Next","Back");
                loginWindow.addSingInPanel();
                loginWindow.addWindowListener(new WindowListener() {
            
            public void windowActivated(WindowEvent event) {
 
            }
 
            public void windowClosed(WindowEvent event) {
 
            }
 
            public void windowClosing(WindowEvent event) {
                try {
                    sendMassegeToServer("");
                } catch (IOException ex) {
                    Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
                
            }
 
            public void windowDeactivated(WindowEvent event) {
 
            }
 
            public void windowDeiconified(WindowEvent event) {
 
            }
 
            public void windowIconified(WindowEvent event) {
 
            }
 
            public void windowOpened(WindowEvent event) {
 
            }
        });
                loginWindow.button1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)  {
                        String setLogin = loginWindow.getSetLog();
                        String setPassword = loginWindow.getSetPass();
                        System.out.println(setLogin);
                        System.out.println(setPassword);
                        try {
                            enter = logIn(setLogin, setPassword);
                        } catch (Exception ex) {
                            Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        loginWindow.setVisible(false);
                            Window Result =  new Window("Result Of Login","To Start","login");
                            Result.addWindowListener(new WindowListener() {
            
            public void windowActivated(WindowEvent event) {
 
            }
 
            public void windowClosed(WindowEvent event) {
 
            }
 
            public void windowClosing(WindowEvent event) {
                try {
                    sendMassegeToServer("");
                } catch (IOException ex) {
                    Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
                
            }
 
            public void windowDeactivated(WindowEvent event) {
 
            }
 
            public void windowDeiconified(WindowEvent event) {
 
            }
 
            public void windowIconified(WindowEvent event) {
 
            }
 
            public void windowOpened(WindowEvent event) {
 
            }
        });
                            if(enter){
                                Result.setLN("you sing in");
                                enter = false;
                            }
                            else{
                                Result.setLN("login or password wrong"); 
                            }
                            Result.button1.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Result.setVisible(false);
                                welcomeWindow.setVisible(true);
                            }
                        });
                        Result.button2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                Result.setVisible(false);
                                loginWindow.setVisible(true);
                            }
                        });
                    }
                });
                
                loginWindow.button2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        loginWindow.setVisible(false); 
                        welcomeWindow.setVisible(true);   
                    }
                });
                
            }
        });
        welcomeWindow.button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                welcomeWindow.setVisible(false);
                Window registrationWindow = new Window("Registration","Next","Back");
                //w2.setLN("Registration");
                //w2.setB2N("back");
                //w2.setB1N("next");
                registrationWindow.addRegistratiosPanel();
                registrationWindow.addWindowListener(new WindowListener() {
            
            public void windowActivated(WindowEvent event) {
 
            }
 
            public void windowClosed(WindowEvent event) {
 
            }
 
            public void windowClosing(WindowEvent event) {
                try {
                    sendMassegeToServer("");
                } catch (IOException ex) {
                    Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
                
            }
 
            public void windowDeactivated(WindowEvent event) {
 
            }
 
            public void windowDeiconified(WindowEvent event) {
 
            }
 
            public void windowIconified(WindowEvent event) {
 
            }
 
            public void windowOpened(WindowEvent event) {
 
            }
        });
                registrationWindow.button1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String login = registrationWindow.getSetLog();
                        String password = registrationWindow.getSetPass();
                        String repitPassword = registrationWindow.getSetPass1();
                        System.out.println(login);
                        System.out.println(password);
                        System.out.println(repitPassword);
                        registrationWindow.setVisible(false);
                        Window resultOfRegistrationWindow = new Window("Result Of Registration","To Start","Reg");
                        resultOfRegistrationWindow.addWindowListener(new WindowListener() {
            
            public void windowActivated(WindowEvent event) {
 
            }
 
            public void windowClosed(WindowEvent event) {
 
            }
 
            public void windowClosing(WindowEvent event) {
                try {
                    sendMassegeToServer("");
                } catch (IOException ex) {
                    Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
                
            }
 
            public void windowDeactivated(WindowEvent event) {
 
            }
 
            public void windowDeiconified(WindowEvent event) {
 
            }
 
            public void windowIconified(WindowEvent event) {
 
            }
 
            public void windowOpened(WindowEvent event) {
 
            }
        }); 
                        if(password.equals(repitPassword)){
                                try {
                                    try {
                                        if(registration(login, password)){
                                            resultOfRegistrationWindow.setLN("new user added");
                                        }else{
                                            resultOfRegistrationWindow.setLN("this login already used");
                                        }
                                    } catch (NoSuchAlgorithmException ex) {
                                        Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(Authentication.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }else{
                                resultOfRegistrationWindow.setLN("wrong repeted password");
                            }
                        resultOfRegistrationWindow.button1.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                resultOfRegistrationWindow.setVisible(false);
                                welcomeWindow.setVisible(true);
                            }
                        });
                        resultOfRegistrationWindow.button2.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                resultOfRegistrationWindow.setVisible(false);
                                registrationWindow.setVisible(true);
                            }
                        });
                    
                registrationWindow.button2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        registrationWindow.setVisible(false); 
                        welcomeWindow.setVisible(true);   
                    }
                });
            }
                
                });
            }
        });
    }

    private static boolean logIn(String login, String setPassword) throws IOException, Exception{
        password = setPassword;
        a = getNewRandom();
        A =  (modExp(base, a, N));
        sendFirstMessage(login);
        if (!analiticFirstMessage(getMessage())){
            return false;
        }
        sendSecondMessage(login);
        return analiticSecondMessage(getMessage());
        
    }
    private static String getMessage() throws IOException{
        String input = "";
        String output;
        String buffer = "";
        long wdt = 0;
        while (true) {
            boolean lw = false;
            boolean eom = false;
            System.out.println("wdt = "+ wdt);
            if(in.ready()){
                input = in.readLine();
                lw = true;
                wdt = 0;
            }
            if(!input.equalsIgnoreCase("end of message")&&lw) {
                System.out.println("next line");
                buffer = buffer + input;
                System.out.println(buffer);
                lw = false;
            }
            if(input.equalsIgnoreCase("end of message")){
                eom = true;
            }
            if(eom){
                System.out.println("end of message");
                break;
            }
            if(wdt == 10000){
                break;
            }
            wdt++;
        }
        return buffer;
    }
    
    private static void sendFirstMessage(String login) throws IOException, Exception{
        sendMassegeToServer(" \n"
                + "login{\n" +
"I = \""+login+"\";\n" +
"A = \""+A+"\";\n" +
"}\n" +
"end of message");
        
    }
    
    private static boolean analiticFirstMessage(String message) throws IOException, Exception{
        if(message.equals("")){
            return false;
        }
        Lexer lexer = new Lexer();
        lexer.processInput(message);
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser();
        parser.setTokens(tokens);
        parser.lang();
        System.out.println("end of Analitic First Message");
        return true;
    }
    
    private static void sendSecondMessage(String login) throws IOException, Exception{
        long x1 = (sha256(Integer.toString(N))^modExp(base, 1, N));
        long x2 = sha256(login);
        String x3 = Long.toString(salt)+A+B+K; 
        M = sha256(x1+x2+x3);
        sendMassegeToServer(" \n"
                + "login{\n" +
"M = \""+M+"\";\n" +
"}\n" +
"end of message");
    }
    
    private static boolean analiticSecondMessage(String message) throws IOException, NoSuchAlgorithmException, Exception{
        if(message.equals("")){
            return false;
        }
        Lexer lexer = new Lexer();
        lexer.processInput(message);
        List<Token> tokens = lexer.getTokens();
        Parser parser = new Parser();
        parser.setTokens(tokens);
        parser.lang();
        System.out.println("end of Analitic Second Message");
        return enter;
    }
    
    private static boolean registration(String login, String password) throws IOException, NoSuchAlgorithmException{
        int salt = getNewRandom();
        sendMassegeToServer(" \n"
                + "registrate{\n" +
"I = \""+login+"\";\n" +
"S = \""+salt+"\";\n" +
"V = \""+modExp(base, sha256(Integer.toString(salt)+password), N)+"\";\n" +
"}\n" +
"end of message");
        String messageFromServer = in.readLine();
        System.out.println(messageFromServer);
        if(messageFromServer.equals("true")){
            return true;
        }else{
            return false;
        }
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
    
    private static boolean sendMassegeToServer(String message) throws IOException{
        String messageToServer;
        messageToServer = message;
        System.out.println(messageToServer);
        out.println(messageToServer);
        return true;
    }
    
    private static void conectToServer() throws IOException{
        fromserver = new Socket("localhost",9999);
        in  = new BufferedReader(new 
              InputStreamReader(fromserver.getInputStream()));
        out = new PrintWriter(fromserver.getOutputStream(),true);
        System.out.print("Conection find!\n");
        //sendMassegeToServer( "hello");
    }
    
    public static void logicLogin(String s, String setB) throws NoSuchAlgorithmException{
        // create 3 BigInteger objects
        B = Long.parseLong(setB);
        salt = Integer.parseInt(s);
        long u = sha256(A + setB);
        System.out.println("u = "+u);
        long x = sha256(s + password);
        long v = (modExp( base,x,N));
        System.out.println("v = "+v);
        S = op3(op2(B, op1(base,x,N)), u, x);
        K = sha256(Float.toString(S));
        System.out.println("S = "+S);
        System.out.println("K = "+K);
    }
    
    public static void logicLogin1(String setR) throws NoSuchAlgorithmException{
        String x4 = A+Long.toString(M)+K;
        long R = sha256(x4);
        if(Long.parseLong(setR) == R){
            enter = true;
        }else{
            enter = false;
        }
    }
    
    private static long op1(long base, long x, long N){
        float op1 = 3* (modExp(base, x, N));
        return (long)op1;
    }
    private static long op2(long B, long op1){
        float op2  = B - op1;
        return (long) op2;
    }
    private static long op3(long op2, long u,long x){
        float op3  = (modExp(op2,a+u*x,N));
        return (long)op3;
    }
}
