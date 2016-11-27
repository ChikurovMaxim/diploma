package dbtests;

import com.news.dao.CompanyDAO;
import com.news.dao.TopicDAO;
import com.news.dao.UserDAO;
import com.news.entities.Company;
import com.news.entities.PlainModel;
import com.news.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.*;

/**
 * Created by Maksym on 1/21/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-context.xml")
public class ArticlesTopicsTest {

    @Autowired
    private TopicDAO topicDAO;

    @Autowired
    private CompanyDAO companyDAO;

    @Autowired
    private UserDAO userDAO;

    private PlainModel t;
    private Company a;

    @Before
    public void beforeTest(){
        User u = userDAO.findPerson(2l);
        t = new PlainModel("java","java", null);
        a = new Company("context", "content", u, t ,"");
        companyDAO.saveArticle(2l, a);
    }



    @Test
    public void testSaveArticle(){
        int firstLook, secondLook;
        assertNotNull(topicDAO.saveTopic(2l, t));
        firstLook = companyDAO.getAll().size();
        assertNotNull(companyDAO.saveArticle(2l, a));
        secondLook = companyDAO.getAll().size();
        assertNotEquals(firstLook,secondLook);
    }

    @Test
    public void testFindArticle(){
        assertNotNull(companyDAO.findArticle(2L));
        assertNotNull(companyDAO.findArticlesForCreator(2L));
        assertNotNull(companyDAO.findArticlesForDate("2016-01-26-14-44"));
    }

    @Test
    public void testChangeCreator(){
        companyDAO.changeArticlesCreator(2l,4l,1l);
        assertEquals(companyDAO.findArticle(4l).getCreator().getName(),"UNDEFINED");
    }

    @Test
    public void removeTest(){
        int firstLook, secondLook;

        firstLook = companyDAO.getAll().size();
        Company a = companyDAO.findArticle(2l);
        companyDAO.deleteArticle(2l,a);
        secondLook = companyDAO.getAll().size();
        assertNotEquals(firstLook, secondLook);

        firstLook = topicDAO.getAll().size();
        PlainModel t = topicDAO.findTopic(1l);
        topicDAO.deleteTopic(2l, t);
        secondLook = topicDAO.getAll().size();
        assertNotEquals(firstLook,secondLook);
    }
}
