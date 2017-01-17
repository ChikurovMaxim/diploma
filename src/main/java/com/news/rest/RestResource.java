package com.news.rest;

import com.news.dao.PlainDao;
import com.news.dao.RecordDao;
import com.news.dao.UserDAO;
import com.news.entities.PlainModel;
import com.news.entities.Record;
import com.news.entities.Users;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * REST Service to expose the data to display in the UI grid.
 *
 * @author Roberto Cortez
 */
@Stateless
@ApplicationPath("/resources")
@Path("server")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestResource extends Application {

    @EJB(name = "java:global/RecordDaoImpl")
    RecordDao recordDao;

    @EJB(name = "java:global/UserDAOImpl")
    UserDAO userDao;

    @EJB(name = "java:global/PlainDAOImpl")
    PlainDao plainDao;

    @GET
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    public String logOut(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("personId", null);
        session.invalidate();
        return "Bye =3";
    }

    @GET
    @Path("/logedIn")
    @Consumes(MediaType.APPLICATION_JSON)
    public Users getLogedP(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        return userDao.findPerson((int) session.getAttribute("personId"));
    }

    @GET
    @Path("/get-all-user-records/{id}")
    public Collection<Record> getRecordsForUser(@PathParam("id") int id){
        return recordDao.findRecordsByUser(userDao.findPerson(id));
    }

    @GET
    @Path("/get-plains")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<PlainModel> getAllPlains() {
        return plainDao.getAll();
    }

}
