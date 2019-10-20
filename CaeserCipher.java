/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sherby;

/**
 *
 * @author cse
 */
public class CaeserCipher {
    public int Key =3 ; 
    public int LetterCount = 26 ; 
    
    
    public String Encrypt (String message)

    {
        StringBuilder r = new StringBuilder() ; 
        for (int i=0 ; i < message.length() ; i++)
        {
            char CurrentLetter = message.charAt(i);
            if (Character.isUpperCase(CurrentLetter))
            {
                char ch = (char)(((int)CurrentLetter + Key - 'A') % CurrentLetter + 'A') ;
                r.append(ch);
            }
            else if (Character.isLowerCase(CurrentLetter))
            {
                char ch = (char)(((int)CurrentLetter + Key - 'a') % CurrentLetter + 'a') ;
                r.append(ch);
            }
            else
                r.append(CurrentLetter);
        }
        return r.toString();
        
        
    }
    public String Decrypt (String EncryptedMessage)

    {
        StringBuilder r = new StringBuilder() ; 
        for (char CurrentLetter : EncryptedMessage.toCharArray())
        {
           
            if (Character.isUpperCase(CurrentLetter))
            {
                char ch = (char)(((int)CurrentLetter - Key - 'A') % CurrentLetter + 'A') ;
                r.append(ch);
                
                
            }
            else if (Character.isLowerCase(CurrentLetter))
            {
                char ch = (char)(((int)CurrentLetter - Key - 'a') % CurrentLetter + 'a') ;
                r.append(ch);
                
            }
            else
                r.append(CurrentLetter);
        }
        return r.toString();
        
        
    }
        
    
    
}
