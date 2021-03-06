/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.icesi.profesores.services;

import co.edu.icesi.profesores.controllers.M4ccbCvArtPubJpaController;
import co.edu.icesi.profesores.entities.M4ccbCvArtPub;
import co.edu.icesi.profesores.entities.M4ccbCvArtPubPK;
import java.util.List;
import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;

/**
 *
 * @author 1130619373
 */
@Path("artpub")
public class M4ccbCvArtPubRESTFacade {

    private EntityManagerFactory getEntityManagerFactory() throws NamingException {
        //return (EntityManagerFactory) new InitialContext().lookup("java:comp/env/persistence-factory");
        return Persistence.createEntityManagerFactory("profesoresPU");
        
    }

    private M4ccbCvArtPubJpaController getJpaController() {
        try {
            return new M4ccbCvArtPubJpaController(getEntityManagerFactory());
        } catch (NamingException ex) {
            throw new RuntimeException(ex);
        }
    }

    public M4ccbCvArtPubRESTFacade() {
    }

    @GET
    @Path("{id}/{professorid}")
    @Produces({"application/xml", "application/json"})
    public M4ccbCvArtPub find(@PathParam("id") int id, @PathParam("professorid") String professorId) {
        M4ccbCvArtPubPK pk=new M4ccbCvArtPubPK("0000", ((Integer)(id)).shortValue(), professorId);
        return getJpaController().findM4ccbCvArtPub(pk);
    }

    @GET
    @Produces({"application/xml", "application/json"})
    public List<M4ccbCvArtPub> findAll() {
        return getJpaController().findM4ccbCvArtPubEntities();
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String count() {
        return String.valueOf(getJpaController().getM4ccbCvArtPubCount());
    }
    
}
