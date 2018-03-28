package jptrade.core.input.impl;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import jptrade.core.constants.ErrorCodes;
import jptrade.core.input.iface.Input;

/*
 * Class to read a file, and return a line of data on request
 * Error codes are stored and can be retrieved from getError()
 */
public class InputFile implements Input
{

    private int            error  = 0;
    private BufferedReader reader = null;;

    /*
     * Function to read a line
     * if EOF or data error, return NULL
     * (non-Javadoc)
     * @see jptrade.core.input.iface.Input#getLine()
     */
    @Override
    public String getLine()
    {
        String data = null;
        setError(0);
        if (reader != null)
        {
            try
            {
                data = reader.readLine();
                if (data==null)
                    setError (ErrorCodes.ERR_NO_DATA);
            } catch (IOException e)
            {
                e.printStackTrace();
                setError(ErrorCodes.ERR_IO_ERROR);
                data = null;
            }
        }
        else
        {
            data = null;
            setError(ErrorCodes.ERR_NO_READER);
        }
        return data;
    }

    /*
     * Given a filename, open the file ready to read
     * On an error return false, and store an errorcode
     * (non-Javadoc)
     * @see jptrade.core.input.iface.Input#initialise(java.lang.String)
     */
    @Override
    public boolean initialise(String args)
    {
        boolean result = false;
        Path fileName = null;
        try
        {
            fileName = Paths.get(args);
            result = true;
        } catch (Exception e)
        {
            e.printStackTrace();
            result = false;
            error = ErrorCodes.ERR_INVALID_PATH;
        }

        if (result) try
        {
            reader = Files.newBufferedReader(fileName);
        } catch (NoSuchFileException e)
        {
            e.printStackTrace();
            reader = null;
            error = ErrorCodes.ERR_FILE_NOT_FOUND;
        } catch (IOException e)
        {
            e.printStackTrace();
            reader = null;
            error = ErrorCodes.ERR_IO_ERROR;
        }

        if (reader == null)
            return false;
        else
            return true;
    }

    public int getError()
    {
        return error;
    }

    public void setError(int error)
    {
        this.error = error;
    }

}
