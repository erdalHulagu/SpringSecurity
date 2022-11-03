package com.tpro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.tpro.domain.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Entity
@Table(name="tbl_role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING) //enum yapilar normallde database'e intigear olarak gecer, biz degil gelsin String dedik
	@Column(length = 30, nullable = false)  // 
	private UserRole name;
	
@Override
public String toString() {
	// TODO Auto-generated method stub
	return "Role [name="+name+"]";
}
	

}
