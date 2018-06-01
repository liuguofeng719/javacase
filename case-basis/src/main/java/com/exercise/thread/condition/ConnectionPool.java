package com.exercise.thread.condition;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 简单的连接池
 * @createtime 2018/4/4 上午11:00
 * @see jdk 1.7
 **/
public class ConnectionPool {

    private int MAX_CONNETIONS = 10;
    private String pwd;
    private String uName;
    private String url;
    private LinkedList<Connection> connections = new LinkedList<>();

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public ConnectionPool() {
        for (int i = 0; i < MAX_CONNETIONS; ++i) {
            final Connection connection = getConnection();
            connections.addLast(connection);
        }
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(url, uName, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Connection get() {
        Connection connection = null;
        try {
            lock.lock();
            while (connections.size() == 0) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            connection = connections.removeFirst();
            notFull.signal();
            return connection;
        } finally {
            lock.unlock();
        }
    }

    public void release(Connection connection) {
        lock.lock();
        try {
            if (connection != null) {
                while (connections.size() == MAX_CONNETIONS) {
                    try {
                        notFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                connections.addLast(connection);
                notEmpty.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
