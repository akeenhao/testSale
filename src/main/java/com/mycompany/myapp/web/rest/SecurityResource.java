package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.WsBuyer;
import com.mycompany.myapp.domain.WsStore;
import com.mycompany.myapp.repository.WsBuyerRepository;
import com.mycompany.myapp.repository.WsStoreRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.security.jwt.JWTFilter;
import com.mycompany.myapp.security.jwt.TokenProvider;
import com.mycompany.myapp.service.WsBuyerService;
import com.mycompany.myapp.service.WsStoreService;
import com.mycompany.myapp.service.dto.UserDTO;
import com.mycompany.myapp.service.dto.WsBuyerDTO;
import com.mycompany.myapp.service.dto.WsStoreDTO;
import com.mycompany.myapp.service.util.PaymentService;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    WsBuyerService wsBuyerService;
    @Autowired
    WsBuyerRepository wsBuyerRepository;
    @Autowired
    WsStoreService wsStoreService;
    @Autowired
    WsStoreRepository wsStoreRepository;

    @PostMapping("/authenticate")
    @ApiOperation(value = "登陆", notes = "登陆")
    @Timed
    public ResponseEntity<UserDTO> authorize(@ApiParam(value = "loginUser", name = "登陆用户信息") @RequestBody UserDTO loginUser) {
        UserDTO res = new UserDTO();

        String openid = loginUser.getOpenid();

        if (loginUser.isBuyerFlag()) {
            WsBuyer wsBuyer = wsBuyerRepository.findByOpenid(openid);
            if (null == wsBuyer) {
                WsBuyerDTO wsBuyerDTO = new WsBuyerDTO();
                wsBuyerDTO.setOpenid(openid);
                try {
                    res.setId(wsBuyerService.insert(wsBuyerDTO).getId());
                } catch (Exception e) {
                    res.setMsg(e.getMessage());
                    return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else {
                res.setId(wsBuyer.getId());
            }
        } else {
            WsStore wsStore = wsStoreRepository.findByOpenid(openid);
            if (null == wsStore) {
                WsStoreDTO wsStoreDTO = new WsStoreDTO();
                wsStoreDTO.setOpenid(openid);
                try {
                    res.setId(wsStoreService.insert(wsStoreDTO).getId());
                } catch (Exception e) {
                    res.setMsg(e.getMessage());
                    return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }else {
                res.setId(wsStore.getId());
            }
        }


        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
            openid,
            openid,
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
