package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Index;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexServiceImplTest {

    @Autowired
    private IndexService indexService;

    @Test
    public void shouldGetBardzoDobryIndexBySearchingItsId(){
        String expectedValue = "Bardzo dobry";
        int id = 1;

        Index index = indexService.findById(id);
        assertEquals(expectedValue, index.getName());

    }

    @Test
    public void shouldGetBardzoDobryIndexBySearchingItsName(){
        String expectedValue = "Bardzo dobry";

        Index index = indexService.findByName(expectedValue);
        assertEquals(expectedValue, index.getName());

    }


}