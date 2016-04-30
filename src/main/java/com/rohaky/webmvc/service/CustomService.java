package com.rohaky.webmvc.service;

import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@PreAuthorize("hasAuthority('PERM_ACCESS_LIST_PAGE')")
//@PreAuthorize("(authentication.principal.getUsername() == 'jessi') and hasAuthority('PERM_ACCESS_LIST_PAGE')")
public interface CustomService {

    List<String> listPost();
}
