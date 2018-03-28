package jptrade.core.input.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import jptrade.core.constants.ErrorCodes;
import jptrade.core.input.impl.InputFile;

@RunWith(MockitoJUnitRunner.class)
class testInputFile
{

    // @Mock File file;
    // @Mock Paths;
    private InputFile iFile;

    @Test
    void testGetGoodLine()
    {
        InputFile iFile = setup("resources/testfile.txt");
        String data = iFile.getLine();
        if (data == null) fail("No data from testfile.txt");
        assertTrue("Data expected from testfile.txt", data.equals("TestLine1"));
        
        data = iFile.getLine();
        assertTrue("Data not expected from testfile.txt", data==null);
        assertTrue("Data not expected from testfile. Error " + iFile.getError(), iFile.getError()==ErrorCodes.ERR_NO_DATA);
    }
    @Test
    void testGetBadLine()
    {
        InputFile iFile = setup("resources/testfile.txt");
        String data = iFile.getLine();
        if (data == null) fail("No data from testfile.txt");
        assertFalse("Data expected from testfile.txt", data.equals("TestLine2"));
    }

    @Test
    void testInitialise()
    {
        /*
         * Good filename
         */
        InputFile iFile = setup("resources/testfile.txt");
        assertTrue("ErrorCode should be 0", iFile.getError()==ErrorCodes.ERR_OK);
        assertTrue("File NOT found and opened .1 ", iFile != null);

        /*
         * Missing filename
         */
        boolean result = iFile.initialise("test file");
        assertTrue("Missing File specified " + iFile.getError(),iFile.getError()==ErrorCodes.ERR_FILE_NOT_FOUND);
        assertFalse("File NOT found and opened .2 ", result);
        /*
         * Bad filename
         */
        result = iFile.initialise(null);
        assertTrue("Invalid Filename specified " + iFile.getError(),iFile.getError()==ErrorCodes.ERR_INVALID_PATH);
        assertFalse("File NOT2 found and opened .3 ", result);
        /*
         * IO error
         */
        result = iFile.initialise("/root/t.t");
        assertTrue("Invalid Filename specified " + iFile.getError(),iFile.getError()==ErrorCodes.ERR_IO_ERROR);
        assertFalse("File NOT2 found and opened .4 ", result);
}
    @Test
    public void TestNoReaderOnRead ()
    {
        InputFile iFile = new InputFile ();
        String data = iFile.getLine();
        assertTrue ("No reader test ", iFile.getError()==ErrorCodes.ERR_NO_READER);
        assertTrue ("No reader test NULL check ", data==null);
        
    }

    /*
     * Basic setup, limiting use to single external jar as per requirements
     * so here require real test file
     */
    private InputFile setup(String file)
    {
        boolean result = false;
        String filename;
        URL uri = ClassLoader.getSystemResource(file);
        if (uri != null)
        {
            filename = uri.getFile();
            iFile = new InputFile();
            if (iFile == null) return null;
            result = iFile.initialise(filename);
            if (result)
                return iFile;
            else
                return null;

        }
        else
            return null;
    }
    
}
