package com.education.uopp.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity(name = "Editor")
@Table(name = "editor")
public class Editor extends User {

}
