package personnelInfo.mechanics;

public class Encrypting {

    public static String encrypt(String secret, int encryptMove) {
        return codingTextInner(secret, encryptMove, true);
    }


    public static String decrypt(String secret, int encryptMove) {
        return codingTextInner(secret, encryptMove, false);
    }

    private static String codingTextInner(String plainText, int codeMove, boolean isCryptOr_FalseIfIsDecrypt) {
        char[] listOfCharacters = new char[plainText.length()];
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < listOfCharacters.length; i++) {
            listOfCharacters[i] = plainText.charAt(i);
            if (Character.isLetter(listOfCharacters[i])) {
                char codingChar = listOfCharacters[i];
                if (isCryptOr_FalseIfIsDecrypt) {
                    codingChar = encryptInner(codingChar, codeMove);
                }
                else {
                    codingChar = decryptInner(codingChar, codeMove);
                }
                listOfCharacters[i] = codingChar;
            }
            temp.append(listOfCharacters[i]);
        }
        return String.valueOf(temp);
    }

    private static char encryptInner(char encryptingChar, int encryptMove) {
        int counter = 0;
        while (counter < encryptMove) {
            encryptingChar++;
            if (encryptingChar >= 123) encryptingChar = 65;
            counter++;
        }

        return encryptingChar;
    }

    private static char decryptInner(char encryptingChar, int encryptMove) {
        int counter = 0;
        while (counter < encryptMove) {
            encryptingChar--;
            if (encryptingChar <= 64) encryptingChar = 122;
            counter++;
        }

        return encryptingChar;
    }

}
