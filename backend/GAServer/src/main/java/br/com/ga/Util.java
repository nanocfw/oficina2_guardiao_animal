/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Marciano
 */
public class Util
{

    public static boolean isEmailValid(String email)
    {
        if (email != null && email.length() > 0)
        {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }
}
