package com.example.parking.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
public abstract class AbstractDateEntity<T extends Serializable> extends AbstractEntity<T> {

    @CreatedDate
    @Column(name = "creation_date", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    @CreatedBy
    @Column(name = "creation_login", nullable = false, length = 50, updatable = false)
    private String creationLogin;

    @LastModifiedDate
    @Column(name = "modification_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationDate;

    @LastModifiedBy
    @Column(name = "modification_login", length = 50)
    private String modificationLogin;

    @Column(name = "deletion_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletionDate;

    @Column(name = "deletion_login")
    private String deletionLogin;

    /**
     * Désactive l'entity si elle ne l'est pas déjà.
     *
     * @param userNni
     * @param deletionDate
     * @return true si l'activité a été désactivée, false si elle l'était déjà
     */
    public boolean delete(String userNni, Date deletionDate) {
        if (!isDeleted()) {
            setDeletionDate(deletionDate);
            setDeletionLogin(userNni);
            return true;
        } else {
            return false;
        }
    }

    public boolean isDeleted() {
        return getDeletionDate() != null;
    }
}

