package kg.ssb.sewing.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import kg.ssb.sewing.entity.enums.ERole;
import kg.ssb.sewing.entity.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String inn;
    private String personalId;
    private String fullName;
    private String uuid;
    private String position;
    private String positionUuid;
    private String division;
    private String divisionUuid;

    private String workPlace;

    private String workPlaceUuid;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private ERole roles;
    @Enumerated(value = EnumType.STRING)
    private EStatus status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;


    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
    }


    public User(Long id, String inn, String personalId, String fullName, String uuid, String position, String positionUuid,
                String division, String divisionUuid, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.inn = inn;
        this.personalId = personalId;
        this.fullName = fullName;
        this.uuid = uuid;
        this.position = position;
        this.positionUuid = positionUuid;
        this.division = division;
        this.divisionUuid = divisionUuid;
        this.password = password;
        this.authorities = authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return personalId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(personalId, user.personalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personalId);
    }
}
