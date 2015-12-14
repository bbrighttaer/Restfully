/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restfully.shop.Interface;

import com.restfully.shop.annotation.LOCK;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 *
 * @author AG BRIGHTER
 */
@Path("/customers")
public interface CustomerResource
{
    @GET
    public Response getMethod();
    
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public Response createCustomer(InputStream is);
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_XML)
    public StreamingOutput getCustomer(@PathParam("id") int id);
    
    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML})
    public void updateCustomer(@PathParam("id") int id, InputStream is);
    
    /**
     * Custom annotation trial
     * @return 
     */
    @LOCK
    public Response lockTest();
}
