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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author AG BRIGHTER
 */
@Path("/customers")
public class CustomerResource
{
    private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
    private AtomicInteger idCounter = new AtomicInteger();
    
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public Response createCustomer(InputStream is)
    {
        Customer customer = readCustomer(is);
        customer.setId(idCounter.incrementAndGet());
        customerDB.put(customer.getId(), customer);
        System.out.println("Created customer "+ customer.getId());
        return Response.created(URI.create("/customers/"+ customer.getId())).build();
    }

    private Customer readCustomer(InputStream is) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @GET
    @Path("{id}")
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

    private void outputCustomer(OutputStream outputStream, Customer customer) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
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
}
