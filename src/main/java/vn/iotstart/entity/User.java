package vn.iotstart.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity

@Table(name = "users")

@NamedQuery(name = "User.findAll", query = "SELECT c FROM User c")
public class User implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 7319752950461436669L;
	@Id
	@Column(name = "UserId")
	private String userid;

	@Column(name = "Username", columnDefinition = "NVARCHAR(200) NOT NULL")
	private String username;

	@Column(name = "Password", columnDefinition = "NVARCHAR(MAX) NULL")
	private String password;

	@Column(name = "Fullname", columnDefinition = "NVARCHAR(MAX) NULL")
	private String fullname;

	@Column(name = "Email", columnDefinition = "NVARCHAR(MAX) NULL")
	private String email;

	@Column(name = "Role")
	private int role;

	// bi-directional many-to-one association to Video

}
