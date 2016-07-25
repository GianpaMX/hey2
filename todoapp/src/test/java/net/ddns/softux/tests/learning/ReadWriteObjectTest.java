package net.ddns.softux.tests.learning;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by juan on 24/07/16.
 */

public class ReadWriteObjectTest {

    @Test
    public void readWriteObject() throws IOException, ClassNotFoundException {
        String[] messages = {"Hello", "world!"};
        String[] readMessages;

        File file = File.createTempFile("temp", "tmp");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(messages);

        objectOutputStream.close();
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        readMessages = (String[]) objectInputStream.readObject();

        objectInputStream.close();
        fileInputStream.close();

        assertTrue(messages.length > 0);
        for(int i = 0; i < messages.length; i++) {
            assertEquals(messages[i], readMessages[i]);
        }
    }
}
