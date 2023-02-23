package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Access(AccessType.FIELD)
@Table(name = "Armas")
public class Armas implements Serializable {
  @Id
  @Column(name = "ArmaID")
  private int ArmaID;
  @Column(name = "Nombre", length = 30)
  private String NombreArma;
  @Column(name = "TipodeArma", length = 20)
  private String TipodeArmas;
  @Column(name = "Numerodeestrellas")
  private int NumerodeestrellasArma;
  @Column(name = "PuntosAtaque")
  private int PuntosAtaque;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "ArmaID", referencedColumnName = "ArmaID")
  private List<Personaje> personajes = new ArrayList<Personaje>();

  public Armas(int ArmaID, String NombreArma, String TipodeArmas, int NumertodeestrellasArma, int PuntosAtaque) {
    super();
    this.NombreArma = NombreArma;
    this.TipodeArmas = TipodeArmas;
    this.ArmaID = ArmaID;
    this.NumerodeestrellasArma = NumertodeestrellasArma;
    this.PuntosAtaque = PuntosAtaque;
  }

  public Armas() {
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
        + TipodeArmas + ", NumerodeestrellasArma=" + NumerodeestrellasArma + ", PuntosAtaque=" + PuntosAtaque.toString() + "]";

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
