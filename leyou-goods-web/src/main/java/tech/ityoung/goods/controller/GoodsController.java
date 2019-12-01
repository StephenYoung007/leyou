package tech.ityoung.goods.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.ityoung.goods.service.GoodsHtmlService;
import tech.ityoung.goods.service.GoodsService;

import java.util.Map;

@Controller
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsHtmlService goodsHtmlService;

    @GetMapping("item/{id}.html")
    public String toItemPage(@PathVariable("id") Long id, Model model) {
        model.addAllAttributes(goodsService.loadData(id));
        goodsHtmlService.createHtml(id);
        return "item";
    }

    @GetMapping("item/json/{id}.html")
    @ResponseBody
    public Map jsonData(@PathVariable("id") Long id, Model model) {
        model.addAllAttributes(goodsService.loadData(id));
        return goodsService.loadData(id);
    }
}
