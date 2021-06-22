package org.ibm;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/product")
public class ProductResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String findAll() {
        return "Product findAll";
    }

    @GET
    @Path("/{productId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getProductById(@PathParam("productId") String id) {
        return id;
    }

    // http://localhost:8080/product/filter?category=sports
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/filter")
    public String filterProductByCategoriees(@QueryParam("category") String category){
        System.out.println("");
        return category;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void create(String product) {
        System.out.println(product);
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    public String update(String product) {
        return product + "Updated";
    }

    @DELETE
    public void remove() {
        System.out.println("Product deleted");
    }
}
