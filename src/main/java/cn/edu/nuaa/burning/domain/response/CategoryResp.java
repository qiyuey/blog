package cn.edu.nuaa.burning.domain.response;

import cn.edu.nuaa.burning.entity.Category;
import lombok.Data;

/**
 * Created by zxh on 2016/4/13.
 */
@Data
public class CategoryResp {
    private String id;
    private String value;

    public CategoryResp(Category category) {
        id = category.getId();
        value = category.getValue();
    }
}
