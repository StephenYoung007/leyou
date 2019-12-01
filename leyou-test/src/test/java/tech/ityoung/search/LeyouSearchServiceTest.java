package tech.ityoung.search;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import tech.ityoung.common.pojo.PageResult;
import tech.ityoung.item.bo.SpuBo;
import tech.ityoung.search.client.GoodsClient;
import tech.ityoung.search.pojo.Goods;
import tech.ityoung.search.repository.GoodsRepository;
import tech.ityoung.search.service.SearchService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LeyouSearchServiceTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SearchService searchService;

    @Test
    public void test(){

        elasticsearchTemplate.createIndex(Goods.class);
        elasticsearchTemplate.putMapping(Goods.class);
        Integer page = 1;
        Integer row = 100;
        do{
            PageResult<SpuBo> result = goodsClient.querySpuByPage(null, null, page, row);
            List<SpuBo> spuBos = result.getItems();
            List<Goods> goodsList = spuBos.stream().map(spuBo -> {
                try {
                    return searchService.buildGoods(spuBo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            goodsRepository.saveAll(goodsList);
            row = spuBos.size();
            page++;

        }while (row == 100);


    }

    @Test
    public void getTest(){
        Iterable<Goods> all = goodsRepository.findAll();
        for (Goods goods : all) {
            System.out.println(goods);
        }
    }
}
