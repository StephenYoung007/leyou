package tech.ityoung.item.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.ityoung.item.pojo.Category;

import java.util.List;

@Controller
@RequestMapping("category")
public interface CategoryApi {

    @RequestMapping
    public List<String> queryNamesByIds(@RequestParam("ids") List<Long> ids);

}
