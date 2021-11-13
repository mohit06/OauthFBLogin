package com.oauth;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "users")
public class User {
   
	@Id
    private String username;
    private boolean enabled;
     
    @Enumerated(EnumType.STRING)
    private Provider provider;
 
    public Provider getProvider() {
        return provider;
    }
 
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

	public void setUsername(String username) {
	
		this.username = username;
	}


	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public User(String username, boolean enabled, Provider provider) {
		super();
		this.username = username;
		this.enabled = enabled;
		this.provider = provider;
	}

	public User() {
		super();
	}
	
	
     
    
}