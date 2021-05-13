package com.ForumJavaWS.demo.rest.entity;

import javax.persistence.*;

@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;


    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ReportReason reason;

    public Report() {

    }

    public Report(ReportReason reason) {
        this.reason = reason;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
