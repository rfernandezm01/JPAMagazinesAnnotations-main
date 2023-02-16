import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import controller.ArticleController;
import controller.AuthorController;
import controller.MagazineController;
import database.ConnectionFactory;
import model.*;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import view.Menu;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class Main {

  static SessionFactory sessionFactoryObj;
/*
  private static SessionFactory buildSessionFactory() {
    // Creating Configuration Instance & Passing Hibernate Configuration File
    Configuration configObj = new Configuration();
    configObj.configure("hibernate.cfg.xml");

    // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
    ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

    // Creating Hibernate SessionFactory Instance
    sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
    return sessionFactoryObj;
  } */

  private static SessionFactory buildSessionFactory() {
    try {
      StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
          .configure("hibernate.cfg.xml").build();
      Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
      return metadata.getSessionFactoryBuilder().build();

    } catch (HibernateException he) {
      System.out.println("Session Factory creation failure");
      throw he;
    }
  }

  public static EntityManagerFactory createEntityManagerFactory(){
    EntityManagerFactory emf;
    try {
      emf = Persistence.createEntityManagerFactory("JPAMagazines");
    } catch (Throwable ex) {
      System.err.println("Failed to create EntityManagerFactory object."+ ex);
      throw new ExceptionInInitializerError(ex);
    }
    return emf;
  }

  public static void main(String[] args) {
    ArrayList<Magazine> revistes = new ArrayList();

    ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
    Connection c = connectionFactory.connect();

//    SessionFactory sessionFactory = buildSessionFactory();
    EntityManagerFactory entityManagerFactory = createEntityManagerFactory();
    //sessionObj = buildSessionFactory().openSession();


    AuthorController authorController = new AuthorController(c, entityManagerFactory);
    ArticleController articleController = new ArticleController(c, entityManagerFactory);
    MagazineController magazineController = new MagazineController(c, entityManagerFactory);

    Menu menu = new Menu();
    int opcio;
    opcio = menu.mainMenu();

    switch (opcio) {

      case 1:

        System.out.println("1!!");
        try {

          // authorController.printAutors(authorController.readAuthorsFile("src/main/resources/autors.txt"));
        //

         // for (Author a : authors) {
         //   authorController.addAuthor(a);
         // }

          // magazineController.printMagazines(magazineController.readMagazinesFile("src/main/resources/revistes.txt"));
          // magazineController.printMagazines();

          List<Author> authors = authorController.readAuthorsFile("src/main/resources/autors.txt");
          List<Magazine> magazines = articleController.readArticlesFile("src/main/resources/articles.txt", "src/main/resources/revistes.txt", "src/main/resources/autors.txt");
          List<Article> articles = articleController.readArticlesFile("src/main/resources/articles.txt", "src/main/resources/autors.txt");

          System.out.println("Revistes llegides des del fitxer");
          for (int i = 0; i < magazines.size(); i++) {
            System.out.println(magazines.get(i).toString()+"\n");
            for (int j = 0; j < magazines.get(i).getArticles().size(); j++) {
              Author author = magazines.get(i).getArticles().get(j).getAuthor();
              authorController.addAuthor(author);

              System.out.println("EL AUTOR:");
              System.out.println(author);

              Article article = magazines.get(i).getArticles().get(j);
              article.setAuthor(author);

              System.out.println("EL ARTICLE:");
              System.out.println(article);

              articleController.addArticle(article);
            }

            magazineController.addMagazine(magazines.get(i));
          }

/*
          for (Magazine m : magazines) {
            System.out.println(m);
            magazineController.addMagazine(m);
          }

          for (Author a : authors) {
            authorController.addAuthor(a);
          }

          for (Article ar : articles) {
            articleController.addArticle(ar);
          }
*/
        } catch (NumberFormatException | IOException e) {

          e.printStackTrace();
        }
        break;

      default:
        System.out.println("Adeu!!");
        System.exit(1);
        break;

    }
  }
}


/*


    static User userObj;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");

        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }

    public static void main(String[] args) {
        System.out.println(".......Hibernate Maven Example.......\n");
        try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            for(int i = 101; i <= 105; i++) {
                userObj = new User();
                userObj.setUserid(i);
                userObj.setUsername("Editor " + i);
                userObj.setCreatedBy("Administrator");
                userObj.setCreatedDate(new Date());

                sessionObj.save(userObj);
            }
            System.out.println("\n.......Records Saved Successfully To The Database.......\n");

            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch(Exception sqlException) {
            if(null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if(sessionObj != null) {
                sessionObj.close();
            }
        }
    }


*/
