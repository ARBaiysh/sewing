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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @Column(length = 300)
    private String password;

    @ElementCollection(targetClass = ERole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    private Set<ERole> roles = new HashSet<>();
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

    public User(Long id, String username, String email, String password, EStatus status, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.status = status;
        this.authorities = authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return status.equals(EStatus.ACTIVE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}
