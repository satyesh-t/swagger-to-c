package org.converter.swagger.model.clang.output;


import lombok.Builder;
import lombok.Data;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;


@Data
@Builder
public class CType {

    String typeName;
    boolean isArray;
    boolean isStruct;
    boolean isEnum;
    boolean isDateTime;

    Integer minLength;
    Integer maxLength;

    String dateTimeFormat;





}
