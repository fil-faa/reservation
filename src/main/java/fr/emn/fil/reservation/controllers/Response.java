package fr.emn.fil.reservation.controllers;

/**
 * Created by Alexandre on 21/10/2015.
 */
public class Response {

    private String page;

    private Type type;


    public Response(String page, Type type) {
        this.page = page;
        this.type = type;
    }

    enum Type {
        REDIRECT, FORWARD
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
