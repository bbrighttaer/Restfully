/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restfully.shop.services;

import com.restfully.shop.domain.Customer;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import com.restfully.shop.Interface.CustomerResource;

/**
 *
 * @author AG BRIGHTER
 */
public abstract class AbstractCustomerResource implements CustomerResource
{
    private final Map<Integer, Customer> customerDB = new ConcurrentHashMap<>();
    private final AtomicInteger idCounter = new AtomicInteger();
    
    @Override
    public Response createCustomer(InputStream is)
    {
        Customer customer = readCustomer(is);
        customer.setId(idCounter.incrementAndGet());
        customerDB.put(customer.getId(), customer);
        System.out.println("Created customer "+ customer.getId());
        return Response.created(URI.create("/customers/"+ customer.getId())).build();
    }
    
    @Override
    public StreamingOutput getCustomer(@PathParam("id") int id)
    {
        final Customer customer = customerDB.get(id);
        if(customer == null)
        {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                outputCustomer(outputStream, customer);
            }
        };
    }
    
    @Override
    public void updateCustomer(@PathParam("id") int id, InputStream is)
    {
        Customer update = readCustomer(is);
        Customer current = customerDB.get(id);
        if(current==null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        current.setFirstname(update.getFirstname());
        current.setLastName(update.getLastName());
        current.setStreet(update.getStreet());
        current.setState(update.getState());
        current.setZip(update.getZip());
        current.setCountry(update.getCountry());
    }

    abstract protected void outputCustomer(OutputStream outputStream, Customer customer);

    abstract protected Customer readCustomer(InputStream is);
}
