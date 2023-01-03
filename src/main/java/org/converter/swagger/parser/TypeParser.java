package org.converter.swagger.parser;

import io.swagger.oas.models.media.*;
import jakarta.annotation.PostConstruct;
import org.converter.swagger.model.clang.output.CType;
import org.converter.swagger.model.clang.output.CtypeEnum;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Optional;

@Component
public class TypeParser {

    HashMap<String,String> knownType=new HashMap<>();
    HashMap<String ,String> numberTypes=new HashMap<>();
    HashMap<String ,String> integerTypes=new HashMap<>();

    static EnumMap<CtypeEnum,String> typeMap=new EnumMap<CtypeEnum,String>(CtypeEnum.class);

    static
    {
        typeMap.put(CtypeEnum.INT,"int ");
        typeMap.put(CtypeEnum.DOUBLE,"double ");
        typeMap.put(CtypeEnum.FLOAT,"float ");
        typeMap.put(CtypeEnum.CHAR,"char ");
        typeMap.put(CtypeEnum.CHAR_ARR,"char * ");
    }

  @PostConstruct
    public void initTypes() {
        numberTypes.put("float","float ");
        numberTypes.put("double","double ");

        integerTypes.put("int32","int ");
        integerTypes.put("int64","long ");
    }



    public CType getCType(Schema<?> schema)
    {
        String schemaType= Optional.ofNullable(schema.getType()).orElse("");
        String schemaRef=Optional.ofNullable(schema.get$ref()).orElse("null");
        String format=Optional.ofNullable(schema.getPattern()).orElse("");

        if(schema instanceof  NumberSchema ns) {
            return CType.builder().typeName( Optional.ofNullable(numberTypes
                            .get(ns.getFormat()))
                    .orElse("long "))
                    .isEnum(false).isArray(false).isStruct(false)
                    .build();

        }
        else if(schema instanceof  IntegerSchema is) {
            return CType.builder().typeName( Optional.ofNullable(integerTypes
                                    .get(is.getFormat()))
                            .orElse("long "))
                    .build();
        }
        else if(schema instanceof  StringSchema is) {
            return CType.builder().
                    typeName("str ")
                    .build();

        }
        else if(schema instanceof  DateTimeSchema dts) {

            return CType.builder().
                    typeName("str ")
                    .isDateTime(true)
                    .dateTimeFormat(dts.getFormat())
                    .build();
        }
        else if(schema instanceof BooleanSchema bs) {
            return CType.builder().
                    typeName("bool")
                    .isDateTime(true)
                    .build();
        }
        else if(schema instanceof ArraySchema as) {
            Schema<?> arraySchema=as.getItems();
          CType type= getCType(arraySchema);
          type.setArray(true);
          return type;
        }



        if(!schemaRef.equals("null"))
        {
           return  CType.builder().
                    typeName(extractSchemaString(schemaRef))
                   .isStruct(true)
                    .build();

        }


        return CType.builder().
                typeName("unkown")
                .build();
    }

    private String extractSchemaString(String schemaName) {
        int lastIndex=schemaName.lastIndexOf("/");
                if(lastIndex>0)
                {return schemaName.substring(lastIndex+1).toLowerCase()+"_t ";}
                else {return "_";}
    }


}
