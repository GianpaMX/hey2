package net.ddns.softux.hey.todoapp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
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
        try (OutputStream fileOutputStream = getFileOutputStream(); ObjectOutput objectOutput = getObjectOutput(fileOutputStream)) {
            objectOutput.writeObject(object);
        }
    }

    public Object readObject() throws IOException, ClassNotFoundException {
        Object object;

        try (InputStream fileInputStream = getFileInputStream(); ObjectInput objectInputStream = getObjectInputStream(fileInputStream)) {
            object = objectInputStream.readObject();
        }

        return object;
    }

    protected ObjectOutput getObjectOutput(OutputStream fileOutputStream) throws IOException {
        return new ObjectOutputStream(fileOutputStream);
    }

    protected OutputStream getFileOutputStream() throws FileNotFoundException {
        return new FileOutputStream(file);
    }

    protected ObjectInput getObjectInputStream(InputStream fileInputStream) throws IOException {
        return new ObjectInputStream(fileInputStream);
    }

    protected InputStream getFileInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }
}
