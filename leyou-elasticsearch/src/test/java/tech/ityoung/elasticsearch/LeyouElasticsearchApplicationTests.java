package tech.ityoung.elasticsearch;

import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import tech.ityoung.elasticsearch.pojo.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeyouElasticsearchApplicationTests {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void indexTest(){
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }

    @Test
    public void creatTest(){
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        itemRepository.save(item);

        List<Item> list = new ArrayList<>();
        list.add(new Item(2L, "坚果手机R1", " 手机", "锤子", 3699.00, "http://image.leyou.com/123.jpg"));
        list.add(new Item(3L, "华为META10", " 手机", "华为", 4499.00, "http://image.leyou.com/3.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
    }

    @Test
    public void findTest(){
        Iterable<Item> items = itemRepository.findAll();
        for (Item item : items) {
            System.out.println(item);
        }
        /*Optional<Item> item = itemRepository.findById(1L);
        Item item1 = item.get();
        System.out.println(item1);*/
    }

    @Test
    public void aggsTest(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.addAggregation(AggregationBuilders.terms("brandAgg").field("brand"));
        builder.withSourceFilter(new FetchSourceFilter(new String[]{}, null));
        AggregatedPage search = (AggregatedPage<Item>)itemRepository.search(builder.build());
        StringTerms brandAgg = (StringTerms)search.getAggregation("brandAgg");
        List<StringTerms.Bucket> buckets = brandAgg.getBuckets();
        buckets.forEach(bucket -> {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
        });
    }

    @Test
    public void subAggsTest(){
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.addAggregation(AggregationBuilders.terms("brandAgg").field("brand").
                subAggregation(AggregationBuilders.avg("avgPrice").field("price")));
        builder.withSourceFilter(new FetchSourceFilter(new String[]{}, null));
        AggregatedPage search = (AggregatedPage<Item>)itemRepository.search(builder.build());
        StringTerms brandAgg = (StringTerms)search.getAggregation("brandAgg");
        List<StringTerms.Bucket> buckets = brandAgg.getBuckets();
        buckets.forEach(bucket -> {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
            Map<String, Aggregation> aggregationMap = bucket.getAggregations().asMap();
            InternalAvg avgPrice = (InternalAvg)aggregationMap.get("avgPrice");
            double value = avgPrice.getValue();
            System.out.println(value);
        });
    }

}
