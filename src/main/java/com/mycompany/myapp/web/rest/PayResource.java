package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.WsAreaService;
import com.mycompany.myapp.service.dto.DeptDTO;
import com.mycompany.myapp.service.dto.JsonResult;
import com.mycompany.myapp.service.dto.WsAreaDTO;
import com.mycompany.myapp.service.util.PaymentService;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.WsArea}.
 */
@RestController
@RequestMapping("/api")
public class PayResource {

    private final Logger log = LoggerFactory.getLogger(PayResource.class);

    private static final String ENTITY_NAME = "testWsArea";
    @Autowired
    PaymentService paymentService;

    //    @GetMapping("/pay")
    public void pay(Pageable pageable) throws UnsupportedEncodingException {

        String remoteAddr = "";
        String orderNumber = "test123";
        String openid = "test";
        paymentService.getPayConfig(remoteAddr, orderNumber, openid);
    }

    @PostMapping("/Dept/list")
    public JsonResult<List<DeptDTO>> list() {
        List<DeptDTO> res = new ArrayList<>();
        DeptDTO deptDTO = new DeptDTO();
        deptDTO.setDeptName("test");
        deptDTO.setDeptNo("123");
        deptDTO.setEditUser("testuser");
        res.add(deptDTO);
        return JsonResult.get(res);


    }




}
