package com.example.shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Project: shiro
 * @Package com.example.shiro.entity
 * @Description: ${todo}
 * @author 吴成卓
 * @date 2020/3/9 星期一 10:41
 * @version V1.0 
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer rid;

    private Integer role;

    private String explain;
}