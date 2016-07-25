package net.ddns.softux.hey.todoapp.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * Created by juan on 24/07/16.
 */
public class ObjectFile {
    private final File file;

    public ObjectFile(File file) {
        this.file = file;
    }

    public void writeObject(Object object) throws IOException {
        OutputStream fileOutputStream = null;
        ObjectOutput objectOutput = null;

        try {
            fileOutputStream = getFileOutputStream();
            objectOutput = getObjectOutput(fileOutputStream);

            objectOutput.writeObject(object);
        } catch (IOException e) {
            throw e;
        } finally {
            if (objectOutput != null) objectOutput.close();
            if (fileOutputStream != null) fileOutputStream.close();
        }
    }

    protected ObjectOutput getObjectOutput(OutputStream fileOutputStream) throws IOException {
        return new ObjectOutputStream(fileOutputStream);
    }

    protected OutputStream getFileOutputStream() throws FileNotFoundException {
        return new FileOutputStream(file);
    }

    public Object readObject() {
        return null;
    }
}
