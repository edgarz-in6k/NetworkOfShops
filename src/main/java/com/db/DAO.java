package com.db;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Transactional
public abstract class DAO<T> {

    @Autowired
    public SessionFactory sessionFactory;

    public abstract void addTransaction(T t) throws SQLException;
    public abstract List getOperation(int prod_id);
    public abstract List getTransaction(int main_id) throws SQLException;
    public abstract List getAllTransaction() throws SQLException;

    public abstract void deleteAll() throws SQLException;
    public abstract void deleteTransaction(int main_id) throws SQLException;
    public abstract void deleteOperation(int prod_id) throws SQLException;

    public abstract List getCustomers() throws SQLException;
    public abstract List getCustomersInfo() throws SQLException;
    public abstract List getShops() throws SQLException;

    public abstract List outSQLQuery(String querySQL) throws SQLException;
    public abstract List outHQLQuery(String queryHQL) throws SQLException;
}
