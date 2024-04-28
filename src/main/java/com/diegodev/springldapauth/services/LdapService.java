package com.diegodev.springldapauth.services;

import com.diegodev.springldapauth.entities.LdapUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;
import javax.naming.Name;

import java.util.List;

@Service
public class LdapService {
    private final String BASE_DN = "ou=users,ou=system";

    @Autowired
    LdapTemplate ldapTemplate;

    public void addUser(LdapUser ldapUser) {
        ldapTemplate.bind(
                "uid=" + ldapUser.getUsername() + "," + BASE_DN,
                null,
                ldapUser.toAttributes());
    }

    public List<LdapUser> getAllUsers() {
        return ldapTemplate.search(
                BASE_DN,
                "(objectclass=inetOrgPerson)",
                (AttributesMapper<LdapUser>) ldapUserAttributes -> {
                    LdapUser ldapUser = new LdapUser();
                    ldapUser.setUsername(ldapUserAttributes.get("uid").get().toString());
                    ldapUser.setCn(ldapUserAttributes.get("cn").get().toString());
                    ldapUser.setSn(ldapUserAttributes.get("sn").get().toString());
                    ldapUser.setPassword(ldapUserAttributes.get("userPassword").get().toString());
                    return ldapUser;
                }
        );
    }

    public LdapUser getUserByUid(String uid) {
        List<LdapUser> users = ldapTemplate.search(
                BASE_DN,
                "(uid=" + uid + ")" ,
                (AttributesMapper<LdapUser>) ldapUserAttributes -> {
                    LdapUser ldapUser = new LdapUser();
                    ldapUser.setUsername(ldapUserAttributes.get("uid").get().toString());
                    ldapUser.setCn(ldapUserAttributes.get("cn").get().toString());
                    ldapUser.setSn(ldapUserAttributes.get("sn").get().toString());
                    ldapUser.setPassword(ldapUserAttributes.get("userPassword").get().toString());
                    return ldapUser;
                }
        );
        if(!users.isEmpty()) {
            return users.getFirst();
        }
        return null;
    }

    public void deleteUserByUid(String uid){
        Name userDn = LdapNameBuilder.newInstance(BASE_DN).add("uid", uid).build();
        ldapTemplate.unbind(userDn);
    }
}
