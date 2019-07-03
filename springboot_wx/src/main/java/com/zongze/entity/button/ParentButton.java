package com.zongze.entity.button;

import lombok.Data;
import java.util.List;

/**
 * Create By xzz on 2019/7/3
 */
@Data
public class ParentButton extends AbstractButton {

    List<SubButton> sub_button;


}
