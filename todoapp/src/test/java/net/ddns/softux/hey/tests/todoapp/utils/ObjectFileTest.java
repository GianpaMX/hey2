package net.ddns.softux.hey.tests.todoapp.utils;

import net.ddns.softux.hey.todoapp.utils.ObjectFile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.OutputStream;

import static org.mockito.Mockito.verify;

public class ObjectFileTest {
    @Mock
    public ObjectOutput mockObjectOutput;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void writeObject() throws IOException {
        ObjectFile objectFile = new TestableObjectFile();
        String[] messages = {"Hello", "world!"};

        objectFile.writeObject(messages);

        verify(mockObjectOutput).writeObject(messages);
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
    }
}