package com.techprimers.springbatchexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Model {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   @Column(name = "ID_ARQUIVO")
   private Long fileId;
   @Column(name = "NUM_LINHA")
   private Integer lineNumber;
   @Column(name = "CONTEUDO_LINHA")
   private String line;

   public Model() {
   }

   public Model(Integer id, Long fileId, Integer lineNumber, String line) {
      this.id = id;
      this.fileId = fileId;
      this.lineNumber = lineNumber;
      this.line = line;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Long getFileId() {
      return fileId;
   }

   public void setFileId(Long fileId) {
      this.fileId = fileId;
   }

   public Integer getLineNumber() {
      return lineNumber;
   }

   public void setLineNumber(Integer lineNumber) {
      this.lineNumber = lineNumber;
   }

   public String getLine() {
      return line;
   }

   public void setLine(String line) {
      this.line = line;
   }
}
