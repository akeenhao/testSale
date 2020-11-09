package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.WsBuyer;
import com.mycompany.myapp.domain.WsStore;
import com.mycompany.myapp.repository.WsBuyerRepository;
import com.mycompany.myapp.repository.WsStoreRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.security.jwt.JWTFilter;
import com.mycompany.myapp.security.jwt.TokenProvider;
import com.mycompany.myapp.service.dto.UserDTO;
import com.mycompany.myapp.service.dto.WsBuyerDTO;
import com.mycompany.myapp.service.util.PaymentService;
import io.micrometer.core.annotation.Timed;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Collections;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.WsArea}.
 */
@RestController
@RequestMapping("/api")
public class SecurityResource {

    private final Logger log = LoggerFactory.getLogger(SecurityResource.class);

    private static final String ENTITY_NAME = "testWsArea";

    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    WsBuyerRepository wsBuyerRepository;
    @Autowired
    WsStoreRepository wsStoreRepository;

    @PostMapping("/authenticate")
    @Timed
    public ResponseEntity<UserDTO> authorize(@RequestBody UserDTO loginUser) {
        UserDTO res = new UserDTO();

        String userName = loginUser.getPhone();
        String phone = loginUser.getPhone();
        String password = loginUser.getPassword();

        String err = "";
        if (loginUser.isBuyerFlag()) {
            WsBuyer wsBuyer = wsBuyerRepository.findByPhone(phone);
            if (null == wsBuyer) {
                err = "手机号不存在！";
                res.setMsg(err);
                return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (StringUtils.isNotBlank(wsBuyer.getPassword()) && !wsBuyer.getPassword().equals(password)) {
                err = "密码错误！";
                res.setMsg(err);
                return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            res.setId(wsBuyer.getId());
        } else {
            WsStore wsStore = wsStoreRepository.findByPhone(phone);
            if (null == wsStore) {
                err = "手机号不存在！";
                res.setMsg(err);
                return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (StringUtils.isNotBlank(wsStore.getPassword()) && !wsStore.getPassword().equals(password)) {
                err = "密码错误！";
                res.setMsg(err);
                return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            res.setId(wsStore.getId());
        }


        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            userName,
            password,
            Collections.singletonList(new SimpleGrantedAuthority(AuthoritiesConstants.USER))
        );
        boolean rememberMe = false;
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        res.setMsg("登陆成功");
        res.setToken(jwt);
        res.setBuyerFlag(loginUser.isBuyerFlag());
        return new ResponseEntity<>(res, httpHeaders, HttpStatus.OK);
    }

}
