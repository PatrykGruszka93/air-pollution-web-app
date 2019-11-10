package com.gruszka.airpollutionwebapp.service;

import com.gruszka.airpollutionwebapp.entity.Index;
import com.gruszka.airpollutionwebapp.entity.PollutionData;

public interface IndexService {

    Index findById(Integer id);
    Index choosePollutionIndexByItsValue(PollutionData pollutionData);
    Index findByName(String name);



}
