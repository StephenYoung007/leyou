package tech.ityoung.item.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tech.ityoung.item.pojo.Brand;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {

    @Insert("INSERT INTO tb_category_brand(category_id, brand_id) VALUES (#{cid},#{bid})")
    int insertCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);

    @Select("SELECT * FROM tb_brand INNER JOIN tb_category_brand ON tb_brand.id = tb_category_brand.brand_id WHERE tb_category_brand.category_id = #{cid}")
    List<Brand> selectByCid(Long cid);
}
