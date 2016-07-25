package net.ddns.softux.hey.tests.todoapp.utils;

import net.ddns.softux.hey.todoapp.utils.ObjectFile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OutputStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ObjectFileTest {
    public final static String[] messages = {"Hello", "world!"};

    @Mock
    public ObjectOutput mockObjectOutput;

    @Mock
    private ObjectInput mockObjectInput;

    public ObjectFile objectFile;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        objectFile = new TestableObjectFile();
    }

    @Test
    public void writeObject() throws IOException {
        objectFile.writeObject(messages);

        verify(mockObjectOutput).writeObject(messages);
    }

    @Test
    public void readObject() throws IOException, ClassNotFoundException {
        when(mockObjectInput.readObject()).thenReturn(messages);

        String[] readMessages = (String[]) objectFile.readObject();

        for (int i = 0; i < messages.length; i++) {
            assertEquals(messages[i], readMessages[i]);
        }
    }

    private class TestableObjectFile extends ObjectFile {
        public TestableObjectFile() {
            super(null);
        }

        @Override
        protected ObjectOutput getObjectOutput(OutputStream fileOutputStream) throws IOException {
            return mockObjectOutput;
        }

        @Override
        protected OutputStream getFileOutputStream() throws FileNotFoundException {
            return null;
        }

        @Override
        protected ObjectInput getObjectInputStream(InputStream fileInputStream) throws IOException {
            return mockObjectInput;
        }

        @Override
        protected InputStream getFileInputStream() throws FileNotFoundException {
            return null;
        }
    }
}