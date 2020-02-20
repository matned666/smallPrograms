package personnelInfo.mechanics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptingTest {

    @Test
    void isEncryptmentWorking(){
        String text = "Ala ma kota i go zje";
        String encryptedText = Encrypting.encrypt(text,1);

        assertEquals(encryptedText, "Bmb nb lpub j hp Akf" );
    }

    @Test
    void isDecryptmentWorking(){
        String text = "Bmb nb lpub j hp Akf";
        String encryptedText = Encrypting.decrypt(text,1);

        assertEquals(encryptedText, "Ala ma kota i go zje" );
    }

}