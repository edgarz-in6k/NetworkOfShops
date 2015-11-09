package com.db;

import com.core.ProductInfo;
import com.core.ProductName;
import com.core.TransactionByCustomer;
import org.hibernate.Query;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CustomerDAO extends DAO<TransactionByCustomer> {

    @Override
    public void addTransaction(TransactionByCustomer transaction) throws SQLException {
        MainEntity mainEntity = new MainEntity(transaction.getCustomer().getName(), transaction.getShop().getName());
        for (Map.Entry<ProductName, ProductInfo> entry : transaction.getProductSets().entrySet()){
            ProductEntity productEntity = new ProductEntity(entry.getKey().name(), entry.getValue().getCount(), entry.getValue().getPrice(), mainEntity);
            mainEntity.getProductSet().add(productEntity);
        }
        sessionFactory.getCurrentSession().save(mainEntity);

        for (Map.Entry<ProductName, ProductInfo> entry :
                transaction.getProductSets().entrySet()) {
            sessionFactory.getCurrentSession().save(new ProductEntity(
                    entry.getKey().name(),
                    entry.getValue().getCount(),
                    entry.getValue().getPrice(),
                    mainEntity
            ));
        }
    }

    @Override
    public List getOperation(int prod_id) {
        String SQL_QUERY =
                        "SELECT * " +
                        "FROM Main M, Product P " +
                        "WHERE M.main_id = P.fk_main_id and prod_id = " + prod_id;
        return sessionFactory.getCurrentSession().createSQLQuery(SQL_QUERY).list();
        /*String SQL_QUERY =
                        "FROM com.db.MainEntity M, com.db.ProductEntity P " +
                        "WHERE M.main_id = P.main.main_id and P.prod_id = " + prod_id;
        return sessionFactory.getCurrentSession().createQuery(SQL_QUERY).list();*/
    }

    @Override
    public List getTransaction(int main_id) throws SQLException {
        String SQL_QUERY =
                        "SELECT * " +
                        "FROM Main M, Product P " +
                        "WHERE M.main_id = P.fk_main_id and main_id = " + main_id;
        return sessionFactory.getCurrentSession().createSQLQuery(SQL_QUERY).list();
    }

    @Override
    public List getAllTransaction() throws SQLException {
        String SQL_QUERY =
                        "SELECT * " +
                        "FROM Main M, Product P " +
                        "WHERE M.main_id = P.fk_main_id";
        return sessionFactory.getCurrentSession().createSQLQuery(SQL_QUERY).list();
    }

    @Override
    public void deleteAll() throws SQLException {
        String SQL_QUERY =
                        "DELETE M, P " +
                        "FROM Main M, Product P";
        sessionFactory.getCurrentSession().createSQLQuery(SQL_QUERY).executeUpdate();
    }

    @Override
    public void deleteTransaction(int main_id) throws SQLException {
        String SQL_QUERY =
                        "DELETE M, P " +
                        "FROM Main M, Product P " +
                        "WHERE M.main_id = P.fk_main_id and M.main_id = " + main_id;
        sessionFactory.getCurrentSession().createSQLQuery(SQL_QUERY).executeUpdate();
    }

    @Override
    public void deleteOperation(int prod_id) throws SQLException {
        String SQL_QUERY = "DELETE FROM Product WHERE prod_id = " + prod_id;
        sessionFactory.getCurrentSession().createSQLQuery(SQL_QUERY).executeUpdate();
        SQL_QUERY = "DELETE FROM Main WHERE main_id NOT IN (SELECT fk_main_id FROM Product)";
        sessionFactory.getCurrentSession().createSQLQuery(SQL_QUERY).executeUpdate();
    }

    @Override
    public List getCustomers() throws SQLException {
        String SQL_QUERY = "SELECT nameCustomer FROM Main GROUP BY nameCustomer";
        return sessionFactory.getCurrentSession().createSQLQuery(SQL_QUERY).list();
    }

    @Override
    public List getCustomersInfo() throws SQLException {
        String SQL_QUERY = "SELECT TT.nameCustomer, TT.nameShop, TT.ct, sum(PP.count*PP.price) " +
                "FROM " +
                "(SELECT * " +
                "FROM " +
                "(SELECT nameCustomer, nameShop, count(nameShop) ct " +
                "FROM Main GROUP BY nameCustomer, nameShop " +
                "ORDER BY ct DESC) T " +
                "GROUP BY nameCustomer) TT, " +
                "(SELECT M.nameCustomer, M.nameShop, P.count, P.price " +
                "FROM Main M, Product P " +
                "WHERE M.main_id = P.fk_main_id ORDER BY M.nameCustomer) PP " +
                "WHERE TT.nameCustomer = PP.nameCustomer and TT.nameShop = PP.nameShop " +
                "GROUP BY TT.nameCustomer;";
        return sessionFactory.getCurrentSession().createSQLQuery(SQL_QUERY).list();
    }

    @Override
    public List getShops() throws SQLException {
        String SQL_QUERY = "SELECT nameShop FROM Main GROUP BY nameShop";
        return sessionFactory.getCurrentSession().createSQLQuery(SQL_QUERY).list();
    }

    @Override
    public List outSQLQuery(String querySQL) throws SQLException {
        Query query = sessionFactory.getCurrentSession().createSQLQuery(querySQL);
        return query.list();
    }

    @Override
    public List outHQLQuery(String queryHQL) throws SQLException {
        //queryHQL = "SELECT M.nameCustomer FROM com.db.MainEntity M";
        Query query = sessionFactory.getCurrentSession().createQuery(queryHQL);
        return query.list();
    }
}

//"SELECT TT.nameCustomer, TT.nameShop, TT.ct, sum(PP.count*PP.price) FROM (SELECT * FROM (SELECT nameCustomer, nameShop, count(nameShop) ct FROM Main GROUP BY nameCustomer, nameShop ORDER BY ct DESC) T GROUP BY nameCustomer) TT, (SELECT M.nameCustomer, M.nameShop, P.count, P.price FROM Main M, Product P WHERE M.main_id = P.fk_main_id ORDER BY M.nameCustomer) PP WHERE TT.nameCustomer = PP.nameCustomer and TT.nameShop = PP.nameShop GROUP BY TT.nameCustomer;";