package com.poladmahmudov.contactlist.persistence.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.URL;

@Data
@Accessors(chain = true)
@Entity(name = "UserProfile")
@Table(name = "user_profile")
public class UserPo implements Serializable {

    @Serial
    private static final long serialVersionUID = 5979087127573199430L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long id;

    @NotBlank
    @Column(name = "user_name", length = 150, nullable = false)
    private String name;

    @URL
    @NotBlank
    @Column(name = "avatar_url", length = 2000, nullable = false)
    private String avatar;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserPo productPo = (UserPo) o;
        return Objects.nonNull(id) && Objects.equals(id, productPo.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
