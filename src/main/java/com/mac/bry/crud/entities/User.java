package com.mac.bry.crud.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="User_ID")
    private long id;
    @NotBlank(message = "First Name is mandatory")
    @Length( min = 2)
    private String firstName;
    
    @NotBlank(message = "Last Name i mandatory")
    @Length(min = 2)
    private String lastName;
    
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    
    @NotBlank(message = "Password is mandatory")
    @Length(min = 3)
    private String password;
    
    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private Set<UserRole> roles = new HashSet<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT) // to remove "cannot simultaneously fetch multiple bags"
    private List<UserDescription> userDescription = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)// to remove "cannot simultaneously fetch multiple bags"
    private List<Task> userTasks = new ArrayList<>(); 

    public User() {}

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserDescription> getUserDescription() {
		return userDescription;
	}
    
    public void addDescription (UserDescription description) {
    	this.userDescription.add(description);
    }
    
    public List<Task> setUserTasks(){
    	return userTasks;
    }
    
    public void addTaks(Task task) {
    	this.userTasks.add(task);
    }
    
	public List<Task> getUserTasks() {
		return userTasks;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	public void setUserDescription(List<UserDescription> userDescription) {
		this.userDescription = userDescription;
	}
	
	public void setUserTasks(List<Task>userTasks) {
		this.userTasks = userTasks;
	}
	
	

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", userDescription=" + userDescription + "]";
	}
}
