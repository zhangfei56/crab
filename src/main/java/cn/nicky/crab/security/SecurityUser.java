package cn.nicky.crab.security;

import cn.nicky.crab.model.po.Role;
import cn.nicky.crab.model.po.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SecurityUser extends User implements UserDetails {

    private static final long serialVersionUID = 1L;

    public SecurityUser(User suser) {

        if (suser != null)
        {
            this.setId(suser.getId());

            this.setName(suser.getName());

            this.setPhoneNumber(suser.getPhoneNumber());

            this.setEmail(suser.getEmail());

            this.setPassword(suser.getPassword());

            this.setRoles(suser.getRoles());
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        List<Role> userRoles = this.getRoles();

        if (userRoles != null)
        {
            for (Role role : userRoles) {

                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());

                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {

        return super.getPhoneNumber();

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
}