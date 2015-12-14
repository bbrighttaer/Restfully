/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.restfully.shop.services;

import com.restfully.shop.annotation.ClassPreamble;
import com.restfully.shop.domain.Customer;
import com.restfully.shop.util.CustomerUtil;
import java.io.InputStream;
import java.io.OutputStream;
import javax.ws.rs.core.Response;

/**
 *
 * @author AG BRIGHTER
 */
@ClassPreamble(
   author = "John Doe",
   date = "3/17/2002",
   currentRevision = 6,
   lastModified = "4/12/2004",
   lastModifiedBy = "Jane Doe",
   // Note array notation
   reviewers = {"Alice", "Bob", "Cindy"}
)
public class CustomerResource extends AbstractCustomerResource
{
    private CustomerUtil customerUtil;
    
    public CustomerResource() {
        this.customerUtil = new CustomerUtil();
    }    

    @Override
    protected void outputCustomer(OutputStream outputStream, Customer customer) {
        customerUtil.outputCustomer(outputStream, customer);
    }

    @Override
    protected Customer readCustomer(InputStream is) {
        return customerUtil.readCustomer(is);
    }

    @Override
    public Response getMethod()
    {
        return Response.ok("Service is active").build();
    }

    @Override
    public Response lockTest()
    {
        return Response.ok("Locked test successful").build();
    }
    
}
