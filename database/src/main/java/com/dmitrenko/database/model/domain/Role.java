package com.dmitrenko.database.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity()
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true, exclude = "users")
@ToString(callSuper = true, exclude = "users")
@NoArgsConstructor
@Table(name = "role")
public class Role extends BaseEntity{

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "role_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = Collections.emptyList();
}
