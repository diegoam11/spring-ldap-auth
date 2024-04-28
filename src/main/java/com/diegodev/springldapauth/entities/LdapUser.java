package com.diegodev.springldapauth.entities;

import lombok.Getter;
import lombok.Setter;

import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttributes;

@Getter
@Setter
public class LdapUser {
    private String username; // uid
    private String cn;
    private String sn;
    private String password;

    public Attributes toAttributes(){
        Attributes attributes = new BasicAttributes();
        attributes.put("objectClass", "inetOrgPerson");
        attributes.put("cn", cn);
        attributes.put("sn", sn);
        attributes.put("userPassword", password);
        attributes.put("uid", username);
        return attributes;
    };
}
