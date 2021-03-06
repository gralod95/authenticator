/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverprobnik;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.*;

public class Lexer {

	private List<Token> tokens = new ArrayList<Token>();

	String accum="";

	//patterns are here
	private Pattern pSM = Pattern.compile("^;$");
	private Pattern pASSIGN_OP = Pattern.compile("^=$");
	private Pattern pVALL = Pattern.compile("^(\"{1}([a-zA-Z]+|[0-9]+)(([a-zA-Z]*|[0-9]*)*))$");
	private Pattern pVAR = Pattern.compile("^[a-zA-Z]+$"); 
	private Pattern pVALL_CL = Pattern.compile("^\"{1}$"); 
	private Pattern pWS = Pattern.compile("^\\s*$");
        private Pattern pLOGIN  = Pattern.compile("^(I)$");
        private Pattern pSOLT  = Pattern.compile("^(S)$");
        private Pattern pA  = Pattern.compile("^(A)$");
        private Pattern pM  = Pattern.compile("^(M)$");
        private Pattern pVERIF_PASSWORD  = Pattern.compile("^(V)$");
        private Pattern pOLD_PASSWORD  = Pattern.compile("^(oldpassword)$");

        private Pattern pLOWER_CP = Pattern.compile("^[<]$");
        private Pattern pBIGER_CP = Pattern.compile("^[>]$");
        private Pattern pLOREQ_CP = Pattern.compile("^<=$");
        private Pattern pBOREQ_CP = Pattern.compile("^>=$");
        private Pattern pEQ_CP = Pattern.compile("^==$");
        
	private Pattern pPLUS_OP = Pattern.compile("^[+]$");
	private Pattern pMINUS_OP = Pattern.compile("^[-]$");
	private Pattern pDEL_OP = Pattern.compile("^[/]$");
	private Pattern pMULT_OP = Pattern.compile("^[*]$");
	
	private Pattern pBRK_OP = Pattern.compile("^[(]$");
	private Pattern pBRK_CL = Pattern.compile("^[)]$");
        
        private Pattern pGET_MESSAGE  = Pattern.compile("^(getmessage)$");
        private Pattern pSEND_MESSAGE  = Pattern.compile("^(sendmessage)$");
        private Pattern pREGISTRATE_OP  = Pattern.compile("^(registrate)$");
        private Pattern pLOGIN_OP  = Pattern.compile("^(login)$");
        private Pattern pOUT_OP  = Pattern.compile("^(out)$");
        private Pattern pCHANGE_PASSWORD  = Pattern.compile("^(changepassword)$");
        
        
        private Pattern pFBRK_OP  = Pattern.compile("^[{]$");
        private Pattern pFBRK_CL  = Pattern.compile("^[}]$");

	private Pattern pVAR_KW  = Pattern.compile("^var$");
        private Pattern pDO_OP = Pattern.compile("^do$");
	
	//maps are here
	private Map<String, Pattern> commonTerminals = new HashMap<String, Pattern> ();
	private Map<String, Pattern> keyWords = new HashMap<String, Pattern> ();

	private String currentLucky = null;
	private int i;

	public Lexer() {

		//add pattern to map for keywords recognition
		keyWords.put("VAR_KW", pVAR_KW);
		keyWords.put("SOLT", pSOLT); 
		keyWords.put("A", pA); 
		keyWords.put("M", pM); 
                keyWords.put("LOGIN_OP", pLOGIN_OP);
                keyWords.put("OUT_OP", pOUT_OP);
                keyWords.put("GET_MESSAGE", pGET_MESSAGE);
                keyWords.put("SEND_MESSAGE", pSEND_MESSAGE);
                keyWords.put("REGISTRATE_OP", pREGISTRATE_OP);
                keyWords.put("CHANGE_PASSWORD", pCHANGE_PASSWORD);
                keyWords.put("LOGIN", pLOGIN);
                keyWords.put("VERIF_PASSWORD", pVERIF_PASSWORD);
                keyWords.put("OLD_PASSWORD", pOLD_PASSWORD);

		//add pattern to map for regular terminals recognition
                commonTerminals.put("LOWER_CP", pLOWER_CP);
                commonTerminals.put("VALL", pVALL);
                commonTerminals.put("VALL_CL", pVALL_CL);
                commonTerminals.put("BIGER_CP", pBIGER_CP);
                commonTerminals.put("LOREQ_CP", pLOREQ_CP);
                commonTerminals.put("BOREQ_CP", pBOREQ_CP);
                commonTerminals.put("EQ_CP", pEQ_CP);
                commonTerminals.put("FBRK_OP", pFBRK_OP);
                commonTerminals.put("FBRK_CL", pFBRK_CL);
		commonTerminals.put("SM", pSM);
		commonTerminals.put("ASSIGN_OP", pASSIGN_OP);
		commonTerminals.put("VAR", pVAR);
		commonTerminals.put("WS", pWS);
		//commonTerminals.put("PLUS_OP", pPLUS_OP);
		commonTerminals.put("MINUS_OP", pMINUS_OP);
		commonTerminals.put("DEL_OP", pDEL_OP);
		commonTerminals.put("MULT_OP", pMULT_OP);
		commonTerminals.put("BRK_OP", pBRK_OP);
		commonTerminals.put("BRK_CL", pBRK_CL);
                commonTerminals.put("DO_OP", pDO_OP);
	}
        public void processInput(String message) throws IOException {
            processLine(message);
            
            System.out.println("TOKEN("
                    + currentLucky
                    + ") recognized with value : "
                    + accum
            );
            
            tokens.add(new Token(currentLucky, accum));
            System.out.println("List of tokens:");
            
            for (Token token: tokens) {
			System.out.println(token);
            }
        }

	private void processLine(String line) {
		for ( i=0; i<line.length(); i++ ) {
			accum = accum + line.charAt(i);
			processAcumm();
		}
	}

	private void processAcumm() {
		boolean found = false;
		for ( String regExpName : commonTerminals.keySet() ) {
			Pattern currentPattern = commonTerminals.get(regExpName);
			Matcher m = currentPattern.matcher(accum);
			if ( m.matches() ) {
				currentLucky = regExpName;
				found = true;
			}
		}
                

		if ( currentLucky != null && !found ) {
			System.out.println("TOKEN("
			+ currentLucky
			+ ") recognized with value : "
			+ accum.substring(0, accum.length()-1)
			);

			tokens.add(new Token(currentLucky, accum.substring(0, accum.length()-1)));
			i--;
			accum = "";
			currentLucky = null;
		}


		for ( String regExpName : keyWords.keySet() ) {
			Pattern currentPattern = keyWords.get(regExpName);
			Matcher m = currentPattern.matcher(accum);
			if ( m.matches() ) {
				currentLucky = regExpName;
				found = true;
			}
		}
                
		if ( currentLucky != null && !found ) {
			System.out.println("TOKEN("
			+ currentLucky
			+ ") recognized with value : "
			+ accum.substring(0, accum.length()-1)
			);

			tokens.add(new Token(currentLucky, accum.substring(0, accum.length()-1)));
			i--;
			accum = "";
			currentLucky = null;
		}
                
                if ( accum!="" && currentLucky == null && found == false ) {
			System.out.println("WRONG TOKEN");
                        accum = "";
		}
	}

	public List<Token> getTokens() {
		return tokens;
	}

}