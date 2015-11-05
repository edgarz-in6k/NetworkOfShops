package com.output;

import com.db.CustomerDAO;

import java.sql.SQLException;

public abstract class PrinterQuery<T> {

    protected T printOut;

    protected abstract void print(CustomerDAO customerQuery) throws SQLException;

    public void printToStream(T printOut){
        this.printOut = printOut;
    }
}
