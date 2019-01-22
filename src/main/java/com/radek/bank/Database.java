package com.radek.bank;

import com.radek.bank.hibernate.Atm;
import com.radek.bank.hibernate.CashOperation;
import com.radek.bank.hibernate.NonCashOperation;
import com.radek.bank.hibernate.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Database {

    private static Database instance;
    private static SessionFactory sessionFactory;
    private static Session session;

    private Database() {
        var serviceRegistry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        try {
            var metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            sessionFactory = metadata.buildSessionFactory();

        } catch (Exception e) {
            e.printStackTrace();
            StandardServiceRegistryBuilder.destroy(serviceRegistry);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    public void test() {
        session = sessionFactory.openSession();

        session.getTransaction().begin();

        var user1 = session.get(User.class, 1);

        var user2 = session.get(User.class, 2);
      //  user.setName("Kazik");

//        var account = new Account("1234", user, 100);
//        user.getAccounts().add(account);

        var account1 = user1.getAccounts().get(0);
        var account2 = user2.getAccounts().get(0);

//        var transfer = new Transfer(account2, account1, 50);
//
//        if (account2.getBalance() >= transfer.getAmount()) {
//            session.save(transfer);
//        }


        var atm = session.get(Atm.class, 1);
        System.out.println(atm.getAddress());
        atm.getCashOperationList().forEach(cashOperation -> System.out.println(cashOperation.getAmount()));

        atm.getCashOperationList().forEach(cashOperation -> System.out.println(cashOperation.getAccount().getUser().getSurname()));

        atm.getCashOperationList()
                .stream()
                .filter(cashOperation -> cashOperation.getOperationMethod().equals("BLIK"))
                .forEach(cashOperation -> System.out.println(cashOperation.getAmount()));

        var cashOperation = session.get(CashOperation.class, 1);
        System.out.println(cashOperation.getAtm().getAddress());

        System.out.println(cashOperation.getAccount().getUser().getId());

        var nonCash = session.get(NonCashOperation.class, 1);
        System.out.println(nonCash.getDescription());



//        user.getAccounts().add(new Account("1234", user, 100));

        session.saveOrUpdate(user2);
        //session.saveOrUpdate(account); // ma dzialac bez tego

        session.getTransaction().commit();

        session.close();

    }

    // połączenie one to many 

}
