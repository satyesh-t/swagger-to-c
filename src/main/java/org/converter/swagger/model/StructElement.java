package org.converter.swagger.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StructElement {
StructTypes type;
String typeName;
String elementName;



}
