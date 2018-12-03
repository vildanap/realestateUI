package com.draos.app.nekretnine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class DatabaseHelper extends _Default implements Runnable {

    private Connection conn;
    private String host = "ec2-54-225-110-156.compute-1.amazonaws.com";
    private String db = "d9jjd360um1du1";
    private int port = 5432;
    private String user = "qdtkslemfjxjjg";
    private String pass = "af8d177ed7f868f0de7a1a947e1d0c96222b4941a328f0c866801ba7c6a5704c";
    private String url = "jdbc:postgresql://%s:%d/%s"+"?sslmode=require";

    public DatabaseHelper() {
        super();
        this.url = String.format(this.url,this.host, this.port, this.db);

        this.connect();
        this.disconnect();
    }

    @Override
    public void run() {
        try{
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(this.url,this.user,this.pass);
        }catch (Exception e){
            this._mensagem = e.getMessage();
            this._status = false;
        }
    }

    public void connect(){
        Thread thread = new Thread(this);
        thread.start();
        try{
            thread.join();
        }catch (Exception e){
            this._mensagem = e.getMessage();
            this._status = false;
        }
    }

    public void disconnect(){
        if (this.conn!= null){
            try{
                this.conn.close();
            }catch (Exception e){

            }finally {
                this.conn = null;
            }
        }
    }

    public ResultSet select(String query){
        this.connect();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteDB(this.conn, query).execute().get();
        }catch (Exception e){
            this._status = false;
            this._mensagem = e.getMessage();
        }
        return resultSet;
    }

    public ResultSet execute(String query){
        this.connect();
        ResultSet resultSet = null;
        try {
            resultSet = new ExecuteDB(this.conn, query).execute().get();

        }catch (Exception e){
            this._status = false;
            this._mensagem = e.getMessage();
        }
        return resultSet;
    }

}