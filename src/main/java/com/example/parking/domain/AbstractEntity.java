package com.example.parking.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;

@Setter
@Getter
@MappedSuperclass
public abstract class AbstractEntity<T extends Serializable> {

    public static final Sort.Order SORT_BY_ID_DESC = new Sort.Order(Sort.Direction.DESC, "title");


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private T id;

    @Override
    public int hashCode() {
        T currentId = getId();
        if (currentId == null) {
            return super.hashCode();
        }

        return new HashCodeBuilder(17, 37).append(currentId).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        T currentId = getId();
        if (currentId == null) {
            return super.equals(obj);
        }

        boolean result;
        if (obj == null) {
            result = false;
        } else if (obj == this) {
            result = true;
        } else if (obj.getClass() == getClass() || obj.getClass().isAssignableFrom(getClass()) || getClass().isAssignableFrom(obj.getClass())) {
            AbstractEntity<?> rhs = (AbstractEntity<?>) obj;
            result = new EqualsBuilder().append(currentId, rhs.getId()).isEquals();
        } else {
            result = false;
        }
        return result;

    }

    @Override
    public String toString() {
        return new StringBuilder(this.getClass().getName()).append("[id : ").append(getId()).append("]").toString();
    }

}

