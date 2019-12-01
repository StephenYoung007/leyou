package tech.ityoung.item.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.ityoung.item.mapper.CategoryMapper;
import tech.ityoung.item.pojo.Category;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoriesByPid(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        List<Category> categories = categoryMapper.select(record);
        return categories;
    }

    public List<String> queryNamesByIds(List<Long> ids) {
        List<Category> categories = categoryMapper.selectByIdList(ids);
        List<String> categoriesNames = categories.stream().map(category -> category.getName()).collect(Collectors.toList());
        return categoriesNames;
    }
}
