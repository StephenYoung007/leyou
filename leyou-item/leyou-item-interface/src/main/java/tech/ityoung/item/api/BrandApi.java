package tech.ityoung.item.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import tech.ityoung.common.pojo.PageResult;
import tech.ityoung.item.pojo.Brand;

import java.util.List;

@Controller
@RequestMapping("brand")
public interface BrandApi {


    @GetMapping("{id}")
    public Brand queryBrandById(@PathVariable("id") Long id);
}
