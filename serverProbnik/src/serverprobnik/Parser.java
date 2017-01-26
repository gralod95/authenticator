package serverprobnik;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author macbook
 */
public class Parser {
    private List<Token> tokens;
    private Token currentToken;
    private int currentTokenNumber = 0;
    private int numOfRTok = 0;
    private List<Token> variables = new ArrayList<Token>();
    private List<Token> varVal = new ArrayList<Token>();
    private List<String> valVar = new ArrayList<String>();
    private String nameOfVar;
    private boolean acs = true;
    
    private String login;
    private String password;
    private String solt;
    private String verif_password;
    private String A;
    private String M;
    private String old_password;
    
    public void setTokens(List<Token> tokens){
		this.tokens = tokens;
	}
    
    public boolean match(){
        currentToken = tokens.get(currentTokenNumber);
        currentTokenNumber++;
        numOfRTok++;
        return false;
    }
    
    public void lang() throws Exception {
        boolean exist = false;
        while ( currentTokenNumber < tokens.size() && expr() ) {
            exist = true;
        }
        if (!exist) {
            System.out.print("end");
            //ServerProbnik.messageWrong();
            //throw new Exception("expr expected");
        }
    }
    
    private boolean expr() throws Exception{
        System.out.print("Вход в expr"+"\n");
        if(log_in()||registrate()||CHANGE_PASSWORD()){
            return true;  
        }
        return false;
    }
    
    private boolean log_in() throws NoSuchAlgorithmException{
        System.out.print("Вход в log_in"+"\n");
        if(log_in1()||log_in2()){
            return true;  
        }
        return false;
    }
    
    private boolean log_in1() throws NoSuchAlgorithmException{
        System.out.println("Вход log_in1");
        boolean enter = false;
        numOfRTok = 0;
        ws();
        match();
        if(currentToken.getName().equals("LOGIN_OP")){
            if(FBRK_OP()){
                if(LOGIN()){
                    if(ASSIGN_OP()){
                        if(VALL()){
                            login = currentToken.getValue().substring(1);
                            if(VALL_CL()){
if(SM()){
    if(A()){
        if(ASSIGN_OP()){
            if(VALL()){
                A = currentToken.getValue().substring(1);
                if(VALL_CL()){
                    if(SM()){
                        if(FBRK_CL()){
                            System.out.println(" login: "+login);
                            System.out.println(" A: "+A);
                            SRP.analiticLogin1(login, A);
                            ServerProbnik.out.println(SRP.getMessageToClient());
                            System.out.println(SRP.getMessageToClient());
                            login = "";
                            A = "";
                            return true;
                        }
                    }
                }
            }
        }
    }
}                                
                            }
                        }
                    }
                    
                }
            }
        }
        currentTokenNumber-=numOfRTok;
        return false;
    } 
    
    private boolean log_in2() throws NoSuchAlgorithmException{
        System.out.println("Вход log_in2");
        boolean enter = false;
        numOfRTok = 0;
        ws();
        match();
        if(currentToken.getName().equals("LOGIN_OP")){
            if(FBRK_OP()){
                if(M()){
                    if(ASSIGN_OP()){
                        if(VALL()){
                            M = currentToken.getValue().substring(1);
                            if(VALL_CL()){
if(SM()){
    if(FBRK_CL()){
        System.out.println(" M: "+M);
        SRP.analiticLogin2( M );
        ServerProbnik.out.println(SRP.getMessageToClient());
        M = "";
        return true;
    }
}                                
                            }
                        }
                    }
                    
                }
            }
        }
        currentTokenNumber-=numOfRTok;
        return false;
    }
    
    private boolean registrate() throws NoSuchAlgorithmException{
        System.out.println("Вход registrate");
        boolean registration = false;
        numOfRTok = 0;
        ws();
        match();
        if(currentToken.getName().equals("REGISTRATE_OP")){
            if(FBRK_OP()){
                if(LOGIN()){
                    if(ASSIGN_OP()){
                        if(VALL()){
                            login = currentToken.getValue().substring(1);
                            if(VALL_CL()){
if(SM()){
    if(SOLT()){
        if(ASSIGN_OP()){
            if(VALL()){
                solt = currentToken.getValue().substring(1);
                if(VALL_CL()){
                    if(SM()){
                        if(VERIF_PASSWORD()){
                            if(ASSIGN_OP()){
                                if(VALL()){
                                    verif_password = currentToken.getValue().substring(1);
                                    if(VALL_CL()){
                                        if(SM()){
                                            if(FBRK_CL()){
                                                if(UserBase.addNewUser(login, solt, verif_password)){
                                                    System.out.println("registration");
                                                    ServerProbnik.out.println("true");
                                                }else{
                                                    System.out.println("false");
                                                    ServerProbnik.out.println("false");
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}                                
                            }
                        }
                    }
                    
                }
            }
        }
        currentTokenNumber-=numOfRTok;
        return false;
    }
    
    private boolean CHANGE_PASSWORD() throws NoSuchAlgorithmException, IOException{
        System.out.println("Вход CHANGE_PASSWORD");
        boolean change = false;
        ws();
        match();
        if(currentToken.getName().equals("CHANGE_PASSWORD")){
            if(FBRK_OP()){
                if(LOGIN()){
                    if(ASSIGN_OP()){
                        if(VALL()){
                            login = currentToken.getValue().substring(1);
                            if(VALL_CL()){
if(SM()){
    if(OLD_PASSWORD()){
        if(ASSIGN_OP()){
            if(VALL()){
                old_password = currentToken.getValue().substring(1);
                if(VALL_CL()){
                    if(SM()){
                        if(PASSWORD()){
                            if(ASSIGN_OP()){
                                if(VALL()){
                                    password = currentToken.getValue().substring(1);
                                    if(VALL_CL()){
                                        if(SM()){
                                            if(FBRK_CL()){
                                                System.out.println(" login: "+login);
                                                System.out.println(" password: "+password);
                                                //change = UserBase.changePassword(login,old_password,password);
                                                if(change){
                                                    System.out.println(" Changes are saved ");
                                                }else{
                                                    System.out.println("Wrong old password");
                                                }
                                                login = "";
                                                password = "";
                                                numOfRTok = 0;
                                                return true;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}                                
                            }
                        }
                    }
                    
                }
            }
        }
        currentTokenNumber-=numOfRTok;
        return false;
    } 
    
    private boolean FBRK_OP(){
        System.out.println("Вход FBRK_OP");
        ws();
        match();
        if(currentToken.getName().equals("FBRK_OP")){
        return true;
        }
        return false;
    }
    
    private boolean M(){
        System.out.println("Вход M");
        ws();
        match();
        if(currentToken.getName().equals("M")){
        return true;
        }
        return false;
    }
    
    private boolean SOLT(){
        System.out.println("Вход SOLT");
        ws();
        match();
        if(currentToken.getName().equals("SOLT")){
        return true;
        }
        return false;
    }
    
    private boolean VERIF_PASSWORD(){
        System.out.println("Вход VERIF_PASSWORD");
        ws();
        match();
        if(currentToken.getName().equals("VERIF_PASSWORD")){
        return true;
        }
        return false;
    }
    
    private boolean ASSIGN_OP(){
        System.out.println("Вход ASSIGN_OP");
        ws();
        match();
        if(currentToken.getName().equals("ASSIGN_OP")){
        return true;
        }
        return false;
    }
    
    private boolean VALL(){
        System.out.println("Вход VALL");
        ws();
        match();
        if(currentToken.getName().equals("VALL")){
        return true;
        }
        return false;
    }
    
    private boolean VALL_CL(){
        System.out.println("Вход VALL_CL");
        ws();
        match();
        if(currentToken.getName().equals("VALL_CL")){
        return true;
        }
        return false;
    }
    
    private boolean SM(){
        System.out.println("Вход SM");
        ws();
        match();
        if(currentToken.getName().equals("SM")){
        return true;
        }
        return false;
    }
    
    private boolean A(){
        System.out.println("Вход A");
        ws();
        match();
        if(currentToken.getName().equals("A")){
            return true;
        }
        return false;
    }
    
    private boolean PASSWORD(){
        System.out.println("Вход PASSWORD");
        ws();
        match();
        if(currentToken.getName().equals("PASSWORD")){
            return true;
        }
        return false;
    }
    
    private boolean OLD_PASSWORD(){
        System.out.println("Вход OLD_PASSWORD");
        ws();
        match();
        if(currentToken.getName().equals("OLD_PASSWORD")){
            return true;
        }
        return false;
    }
    
    private boolean FBRK_CL(){
        System.out.println("Вход FBRK_CL");
        ws();
        match();
        if(currentToken.getName().equals("FBRK_CL")){
        return true;
        }
        return false;
    }
    
    private boolean LOGIN(){
        System.out.println("Вход LOGIN");
        ws();
        match();
        if(currentToken.getName().equals("LOGIN")){
            return true;
        }
        return false;
    }
    
    private void ws(){
        System.out.print("Вход в ws"+"\n");
        match();
        if(!currentToken.getName().equals("WS")){
            currentTokenNumber--;
            numOfRTok--;
        }else{
            ws();
        }
        
    }
}