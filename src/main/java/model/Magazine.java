package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "Armas")
public class Magazine implements Serializable {
  @Id
  @Column(name = "ArmaID")
  private int magazineId;
  @Column(name = "Nombre", length = 30)
  private String title;
  @Column(name = "TipodeArma", length = 20)
  private String publicationDate;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "ArmaID", referencedColumnName = "ArmaID")
  private List<Personaje> articles = new ArrayList<Personaje>();

  public Magazine(int magazineId, String title, String publicationDate) {
    super();
    this.title = title;
    this.publicationDate = publicationDate;
    this.magazineId = magazineId;
  }

  public Magazine() {
    super();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  public int getMagazineId() {
    return magazineId;
  }

  public void setMagazineId(int magazineId) {
    this.magazineId = magazineId;
  }

  public void addArticle(Personaje art) {
    articles.add(art);
  }

  public Personaje getArticle(int i) {
    return articles.get(i);
  }

  public List<Personaje> getArticles() {
    return articles;
  }

  public void setArticles(List<Personaje> articles) {
    this.articles = articles;
  }


  @Override
  public String toString() {
    String result = "Armas [ArmaID=" + magazineId + ",Nombre=" + title + ", TipodeArma="
        + publicationDate.toString() + "]";

    result += "\n Llista de Armas: [ \n";

    for (Personaje a : articles) {
      result += "\t";
      result += a.toString();
      result += "\n";
    }

    result += "] \n";

    return result;
  }

}
