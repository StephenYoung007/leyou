package tech.ityoung.item.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tech.ityoung.item.pojo.SpecGroup;
import tech.ityoung.item.pojo.SpecParam;

import java.util.List;

@Controller
@RequestMapping("spec")
public interface SpecificationApi {

    @GetMapping("params")
    public List<SpecParam> queryParams(
            @RequestParam(value = "gid", required = false) Long gid,
            @RequestParam(value = "cid", required = false) Long cid,
            @RequestParam(value = "generic", required = false) Boolean generic,
            @RequestParam(value = "searching", required = false) Boolean searching
            );

    @GetMapping("group/param/{cid}")
    public List<SpecGroup> queryGroupsWithParams(@PathVariable("cid") Long cid);
}
