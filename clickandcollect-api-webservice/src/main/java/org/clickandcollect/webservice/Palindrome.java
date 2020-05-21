package org.clickandcollect.webservice;
/*
    This class is for code coverage test purpose only and will be deleted
 */
public class Palindrome {
    // FOR CODECOVERAGE TEST PURPOSE
    public boolean isPalindrome(String inputString) {
        if (inputString.length() == 0) {
            return true;
        } else {
            char firstChar = inputString.charAt(0);
            char lastChar = inputString.charAt(inputString.length() - 1);
            String mid = inputString.substring(1, inputString.length() - 1);
            return (firstChar == lastChar) && isPalindrome(mid);
        }
    }
}
