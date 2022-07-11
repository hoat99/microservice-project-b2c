package com.savvycom.auth_service.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

import javax.persistence.*;


@Entity
@Table(name="role")
@Getter
@Setter
@NoArgsConstructor
public class Role extends BaseTimeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(unique=true)
    private String roleCode;

	@Override
	public int hashCode() {
		return Objects.hash(roleCode, roleId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(roleCode, other.roleCode) && Objects.equals(roleId, other.roleId);
	}
    
}
