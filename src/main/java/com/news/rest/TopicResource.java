package com.news.rest;

import com.news.dao.RoleDAO;
import com.news.dao.TopicDAO;
import com.news.entities.PlainModel;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Maksym on 1/13/2016.
 */
@ApplicationPath("/resources")
@Path("topic")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TopicResource {
    @EJB(name = "java:global/TopicDAOImpl")
    TopicDAO topicDAO;

    @EJB(name = "java:global/RoleDAOImpl")
    RoleDAO roleDAO;

    @GET
    @Path("getAll")
    public List<PlainModel> getAllTopics(){return topicDAO.getAll();}

    @GET
    @Path("getPlainModel/{id}")
    public PlainModel getTopic(@PathParam("id")Long topicId){
        return topicDAO.findTopic(topicId);
    }

    @POST
    @Path("save/{topicId}/{topicName}")
    public PlainModel saveNews(@PathParam("topicId")Long id, @PathParam("topicName")String topicName, String description, @Context HttpServletRequest request){
        HttpSession session = request.getSession();
        PlainModel plainModel = new PlainModel();
        if(id!=null) plainModel = topicDAO.findTopic(id);
        plainModel.setName(topicName);
        plainModel.setDescription(description);
        topicDAO.saveTopic((Long) session.getAttribute("personId"), plainModel);
        return plainModel;
    }

    @POST
    @Path("save/")
    public PlainModel saveNews(String topicName, String description, @Context HttpServletRequest request){
        return saveNews(null,topicName, description, request);
    }

    @DELETE
    @Path("delete/{topicId}")
    public void removeTopic(@PathParam("topicId") Long topicId,@Context HttpServletRequest request){
        HttpSession session = request.getSession();
        topicDAO.deleteTopic((Long)session.getAttribute("personId"),topicDAO.findTopic(topicId));
    }
}
