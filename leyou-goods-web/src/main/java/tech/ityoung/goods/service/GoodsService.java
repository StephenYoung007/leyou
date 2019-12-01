package tech.ityoung.goods.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.ityoung.goods.client.BrandClient;
import tech.ityoung.goods.client.CategoryClient;
import tech.ityoung.goods.client.GoodsClient;
import tech.ityoung.goods.client.SpecificationClient;
import tech.ityoung.item.pojo.*;

import java.util.*;

@Service
public class GoodsService {

    @Autowired
    private BrandClient brandClient;

    @Autowired
    private CategoryClient categoryClient;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SpecificationClient specificationClient;

    public Map<String, Object> loadData(Long spuId){

        Map<String, Object> model = new HashMap<>();
        //获取spu
        Spu spu = goodsClient.querySpuById(spuId);
        //获取spuDetail
        SpuDetail spuDetail = goodsClient.querySpuDetailBySpuId(spuId);
        //获取skus
        List<Sku> skus = goodsClient.querySkusBySpuId(spuId);
        //获取brand
        Brand brand = brandClient.queryBrandById(spu.getBrandId());
        //获取categries
        List<Long> ids = Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3());
        List<String> names = categoryClient.queryNamesByIds(ids);
        List<Map<String, Object>> categories = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", ids.get(i));
            map.put("name", names.get(i));
            categories.add(map);
        }
        //获取groups
        List<SpecGroup> groups = specificationClient.queryGroupsWithParams(spu.getCid3());
        //获取paramMap
        Map<Long, String> paramMap = new HashMap<>();
        List<SpecParam> specParams = specificationClient.
                queryParams(null, spu.getCid3(), false, null);
        specParams.forEach(specParam -> {
            paramMap.put(specParam.getId(), specParam.getName());
        });
        model.put("spu", spu);
        model.put("spuDetail", spuDetail);
        model.put("skus", skus);
        model.put("brand", brand);
        model.put("categories", categories);
        model.put("groups", groups);
        model.put("paramMap", paramMap);
        return model;
    }
}
