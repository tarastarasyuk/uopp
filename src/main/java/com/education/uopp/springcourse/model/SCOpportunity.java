package com.education.uopp.springcourse.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "sc_opportunity")
public class SCOpportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    @Column(nullable = false)
    private Boolean asap;

    @Column(nullable = false, length = 2000)
    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    public SCOpportunity(String name, Date deadline, Boolean asap, String content) {
        this.name = name;
        this.deadline = deadline;
        this.asap = asap;
        this.content = content;
    }

    public SCOpportunity(Long postId, String content, Date createdAt) {
        this.postId = postId;
        this.content = content;
        this.createdAt = createdAt;

        this.asap = false;
        this.deadline = new Date(); // FIXME

        this.name = extractNameFromContent(content, 3);
    }

    private String extractNameFromContent(String content, int q) {
        Matcher m = Pattern.compile("^(?:[^ ]+ ){".concat(String.valueOf(q)).concat("}")).matcher(content);
        return m.find() ? m.group() : "POST";
    }
}
