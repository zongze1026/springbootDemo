package com.zongze.entity.button;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * Create By xzz on 2019/7/3
 */
@Data
public class Button implements Serializable {

    List<ParentButton> button;



}
