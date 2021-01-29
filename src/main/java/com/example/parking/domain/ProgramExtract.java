package com.example.parking.domain;

import com.example.parking.domain.enums.ProgramWorkflowStatusEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class ProgramExtract {
    private Integer idtheme;
    private String themecode;
    private String themelabel;
    private Integer subthemeid;
    private String codesubtheme;
    private String subthemelabel;
    private Integer idprogram;
    private String psLabel;
    private String orderCode;
    private ProgramWorkflowStatusEnum workFlow;
    private String site;
    private Boolean aip;
    private String projectCycle;
    private Short projectUnit;
    private Integer fasId;
    private Date startDate;
    private String libelleFas;
    private Short workUnit;
    private String workElSystem;
    private String workNumber;
    private String workComponent;
    private String workOrder;
    private String autre;
    private boolean fasExpected;
    private Boolean signatureRefused;
    private Integer idProfil;
    private String prestataire;
    private String libelleObservable;
    private String serviceLabel;
    private Integer severity;
    private String commentaire;
    private boolean inadequate;
    private String agentSupervisionSheet;
    private Date signatureDate;
    private String signatureBy;
    private Long image;
    private Long nbNonSolde;
    //Computed
    private String projectLabel;
    private String rf;
    private String observationCode;

    public ProgramExtract(Integer idtheme, String themecode, String themelabel, Integer subthemeid,
                          String codesubtheme, String subthemelabel, Integer idprogram, String psLabel,
                          String orderCode, ProgramWorkflowStatusEnum workFlow, String site, Boolean aip,
                          String projectCycle, Short projectUnit, Integer fasId,
                          Date startDate, String libelleFas, Short workUnit, String workElSystem,
                          String workNumber, String workComponent, String workOrder, String autre,
                          boolean fasExpected, Boolean signatureRefused, Integer idProfil,
                          String prestataire, String libelleObservable, String serviceLabel, Integer severity,
                          String commentaire,
                          boolean inadequate, String agentSupervisionSheet, Date signatureDate,
                          String signatureBy, Long image,
                          Long nbNonSolde) {
        this.idtheme = idtheme;
        this.themecode = themecode;
        this.themelabel = themelabel;
        this.subthemeid = subthemeid;
        this.codesubtheme = codesubtheme;
        this.subthemelabel = subthemelabel;
        this.idprogram = idprogram;
        this.psLabel = psLabel;
        this.orderCode = orderCode;
        this.workFlow = workFlow;
        this.site = site;
        this.aip = aip;
        this.projectCycle = projectCycle;
        this.projectUnit = projectUnit;
        this.fasId = fasId;
        this.startDate = startDate;
        this.libelleFas = libelleFas;
        this.workUnit = workUnit;
        this.workElSystem = workElSystem;
        this.workNumber = workNumber;
        this.workComponent = workComponent;
        this.workOrder = workOrder;
        this.autre = autre;
        this.fasExpected = fasExpected;
        this.signatureRefused = signatureRefused;
        this.idProfil = idProfil;
        this.prestataire = prestataire;
        this.libelleObservable = libelleObservable;
        this.serviceLabel = serviceLabel;
        this.severity = severity;
        this.commentaire = commentaire;
        this.inadequate = inadequate;
        this.agentSupervisionSheet = agentSupervisionSheet;
        this.signatureDate = signatureDate;
        this.signatureBy = signatureBy;
        this.image = image;
        this.nbNonSolde = nbNonSolde;
    }
}

