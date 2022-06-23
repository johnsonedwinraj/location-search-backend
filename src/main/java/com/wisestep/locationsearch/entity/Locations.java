package com.wisestep.locationsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Document(indexName = "locations")
@Data
public class Locations implements Serializable {

    @Id
    @Field(name = "id", type = FieldType.Text)
    private String placeId;

    @Field(name = "display_name", type = FieldType.Text)
    private String displayName;

    @Field(name = "latitude", type = FieldType.Text)
    private String latitude;

    @Field(name = "longitude", type = FieldType.Text)
    private String longitude;

    @Field(name = "data", type = FieldType.Text)
    private String data;


}
