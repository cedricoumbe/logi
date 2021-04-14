package com.example.demo7.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="reseautransfert")
public class Reseautransfert{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reseautransfertid;

    @NotNull
    @Size(min=2, max=30)
    @Column(name="reseautransfertnom")
    private String reseautransfertnom;

   
    @Column(name="reseautransfertnomimage",nullable = false)
    private String reseautransfertnomimage;
    

    @Column(name="reseautransfertdatecre")
    private String reseautransfertdatecre;

    @Column(name="reseautransfertdatemod")
    private String reseautransfertdatemod;
    
    @Column(name="reseautransfertcodeunique")
    private String reseautransfertcodeunique;

    public long getReseautransfertid() {
		return reseautransfertid;
	}

    public void setReseautransfertid(long reseautransfertid) {
		this.reseautransfertid = reseautransfertid;
	}

    public String getReseautransfertnom() {
		return reseautransfertnom;
	}

    public void setReseautransfertnom(String reseautransfertnom) {
		this.reseautransfertnom = reseautransfertnom;
	}

    public String getReseautransfertnomimage() {
		return reseautransfertnomimage;
	}

    public void setReseautransfertnomimage(String reseautransfertnomimage) {
		this.reseautransfertnomimage = reseautransfertnomimage;
	}

    public String getReseautransfertdatecre() {
		return reseautransfertdatecre;
	}

    public void setReseautransfertdatecre(String reseautransfertdatecre) {
		this.reseautransfertdatecre = reseautransfertdatecre;
	}

    public String getReseautransfertcodeunique() {
		return reseautransfertcodeunique;
	}

    public void setReseautransfertcodeunique(String reseautransfertcodeunique) {
		this.reseautransfertcodeunique = reseautransfertcodeunique;
	}

    public String getReseautransfertdatemod() {
		return reseautransfertdatemod;
	}

    public void setReseautransfertdatemod(String reseautransfertdatemod) {
		this.reseautransfertdatemod = reseautransfertdatemod;
	}

  
}
