package com.epam.customexceptions;

public class DuplicateQuestionFoundException extends Exception{
    public DuplicateQuestionFoundException (String msg)
    {
        super(msg);
    }
}
