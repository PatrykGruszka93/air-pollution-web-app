package com.gruszka.airpollutionwebapp.rest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.gruszka.airpollutionwebapp.entity.AirQualityIndex;
import com.gruszka.airpollutionwebapp.entity.Station;
import com.gruszka.airpollutionwebapp.rest.model.StationRestApiModel;
import com.gruszka.airpollutionwebapp.service.AirQualityIndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeoJsonAdapter {

    private RestApiModelAdapter restApiModelAdapter;
    private AirQualityIndexService aqiService;

    @Autowired
    public GeoJsonAdapter(RestApiModelAdapter restApiModelAdapter, AirQualityIndexService aqiService) {
        this.restApiModelAdapter = restApiModelAdapter;
        this.aqiService = aqiService;
    }

    public JsonNode getGeoJsonOfStations(List<Station> stations){

        AirQualityIndex index;
        ObjectMapper mapper = new ObjectMapper();

        JsonNode rootNode = mapper.createObjectNode();
        ((ObjectNode) rootNode).put("type", "FeatureCollection");

        ArrayNode featuresArray = mapper.createArrayNode();

        for(Station station : stations){

            index = aqiService.findMostCurrentIndexForStation(station);
            StationRestApiModel stationModel= restApiModelAdapter.getStationRestApiModel(station, index);

            JsonNode geometryNode = getGeometryNode(mapper, stationModel);
            ObjectNode propertiesNode = getPropertiesNode(mapper, stationModel);

            JsonNode featureNode = getFeatureNode(mapper, geometryNode, propertiesNode);

            featuresArray.add(featureNode);
        }
        ((ObjectNode) rootNode).putArray("features").addAll(featuresArray);

        return rootNode;
    }

    private JsonNode getGeometryNode(ObjectMapper mapper, StationRestApiModel stationModel) {
        JsonNode geometryNode = mapper.createObjectNode();
        ((ObjectNode) geometryNode).put("type","Point");
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(stationModel.getGegrLon());
        coordinates.add(stationModel.getGegrLat());
        ArrayNode coordinatesNode = mapper.valueToTree(coordinates);
        ((ObjectNode) geometryNode).putArray("coordinates").addAll(coordinatesNode);
        return geometryNode;
    }

    private ObjectNode getPropertiesNode(ObjectMapper mapper, StationRestApiModel stationModel) {
        return mapper.valueToTree(stationModel);
    }

    private JsonNode getFeatureNode(ObjectMapper mapper, JsonNode geometryNode, ObjectNode propertiesNode) {
        JsonNode featureNode = mapper.createObjectNode();
        ((ObjectNode) featureNode).put("type", "Feature");
        ((ObjectNode) featureNode).set("geometry", geometryNode);
        ((ObjectNode) featureNode).set("properties", propertiesNode);
        return featureNode;
    }
}
