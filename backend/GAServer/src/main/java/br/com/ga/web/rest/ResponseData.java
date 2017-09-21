/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ga.web.rest;

/**
 *
 * @author Marciano
 */
public class ResponseData<T>
{

    private Class<T> objectClass;
    private T value;
    private ResponseCode status;
    private Class<?> exceptionType;
    private String exceptionMessage;

    public Class<T> getObjectClass()
    {
        return objectClass;
    }

    public void setObjectClass(Class<T> objectClass)
    {
        this.objectClass = objectClass;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(T value)
    {
        this.value = value;
    }

    public ResponseCode getStatus()
    {
        return status;
    }

    public void setStatus(ResponseCode status)
    {
        this.status = status;
    }

    public Class<?> getExceptionType()
    {
        return exceptionType;
    }

    public void setExceptionType(Class<?> exceptionType)
    {
        this.exceptionType = exceptionType;
    }

    public String getExceptionMessage()
    {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage)
    {
        this.exceptionMessage = exceptionMessage;
    }

    public ResponseData()
    {
        super();
    }

    public ResponseData(Class<T> objectClass, T value, ResponseCode status)
    {
        this.objectClass = objectClass;
        this.value = value;
        this.status = status;
    }

    public ResponseData(Class<T> objectClass, ResponseCode status, Class<?> exceptionType, String exceptionMessage)
    {
        this.objectClass = objectClass;
        this.status = status;
        this.exceptionType = exceptionType;
        this.exceptionMessage = exceptionMessage;
    }

    public ResponseData(Class<T> objectClass, T value, ResponseCode status, Class<?> exceptionType)
    {
        this.objectClass = objectClass;
        this.value = value;
        this.status = status;
        this.exceptionType = exceptionType;
    }

    public ResponseData(Class<T> objectClass, T value, ResponseCode status, Class<?> exceptionType, String exceptionMessage)
    {
        this.objectClass = objectClass;
        this.value = value;
        this.status = status;
        this.exceptionType = exceptionType;
        this.exceptionMessage = exceptionMessage;
    }

}
