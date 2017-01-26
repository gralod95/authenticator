/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverprobnik;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UserBase {
    private static Map<String, UserData> userBase;
    private static final String fileName = "UserBase";
    
    public static void loadUserBase() throws IOException {
        if(Files.notExists(Paths.get(fileName))) {
            Files.createFile(Paths.get(fileName));
            userBase = new HashMap<String, UserData>();
        } else {
            List<String> content = Files.readAllLines(Paths.get(fileName));
            userBase = new HashMap<String, UserData>();
            for (String line : content) {
                String[] s = line.split(";");
                userBase.put(s[0], new UserData(s[1], s[2]));
            }
        }
    }

    public static boolean addNewUser(String login, String salt, String verif_password) throws NoSuchAlgorithmException {
        if (userBase.containsKey(login) == true) {
            return false;
        } else {
            userBase.put(login, new UserData(verif_password, salt));
            String newUser = login + ";" + verif_password + ";" + salt + "\n";
            System.out.println(newUser);
            try {
                Files.write(Paths.get(fileName), newUser.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }
    
    public static String getSolt(String login){
        return userBase.get(login).getSalt();
    }
    
    public static String getV(String login){
        return userBase.get(login).getPasswordHash();
    }
    
    public static boolean containsLogin(String login){
        return userBase.containsKey(login);
    }
    /*public static String getNewSolt(){
        Random ran = new SecureRandom();
        return Integer.toString(ran.nextInt(100));
    }*/
    
    /*public static boolean checkLoginAndPass( String login, String pass) throws NoSuchAlgorithmException{
        if(userBase.containsKey(login)){
            String solt = userBase.get(login).getSalt();
            String passHash = userBase.get(login).getPasswordHash();
            //if(passHash.equals(sha256(pass+solt))){
               // return true;
            //}else{
                //return false;
           // }
           return true;
        }else{
            return false;
        }
    }
    
    public static boolean singIn(String login, String pass) throws NoSuchAlgorithmException{
        return checkLoginAndPass(login, pass);
        }
    
    public static boolean changePassword(String login, String old_pass, String new_pass) throws IOException, NoSuchAlgorithmException{
        if(singIn(login,old_pass)){
            String new_solt = getNewSolt();
           // String new_passwordHash = sha256(new_pass+new_solt);
            //String old_info = sha256(old_pass+userBase.get(login).getSalt())+";"+userBase.get(login).getSalt();
           // Files.write(Paths.get(fileName), new String(Files.readAllBytes(Paths.get(fileName)), StandardCharsets.UTF_8).replace(old_info,new_passwordHash+";"+new_solt ).getBytes(StandardCharsets.UTF_8));
            //userBase.get(login).setPasswordHash(new_passwordHash);
            userBase.get(login).setSalt(new_solt);
            return true;
//sha256(pass+solt)
        }
        return false;
    }*/
}
