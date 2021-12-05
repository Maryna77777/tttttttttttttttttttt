package com.example.tickets.service;

//import com.example.tickets.dto.EventDTO;
import com.example.tickets.dto.EventCustomerSaleDTO;
import com.example.tickets.dto.EventDTO;
import com.example.tickets.dto.EventMapperDTO;
import com.example.tickets.dto.SaleCustomerDTO;
import com.example.tickets.entity.Event;
//import com.example.tickets.repository.EventDTORepository;
//import com.example.tickets.mapper.EventMapper;
import com.example.tickets.mapper.EventMapper;
import com.example.tickets.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
  //  private EventDTORepository eventDTORepository;

    public List<Event> getEvent() {
        return eventRepository.findAll();
    }
    public List<Event> getEventLastName(String lastName){
        return eventRepository.findByLastName (lastName);
    }

    public List<Event> getSortedEvent(){
        return eventRepository.findAllOrderByData();}

    public List <EventDTO> getAllEvent() {
        EventDTO eventDTO = new EventDTO();
        return eventDTO.getEventDTOList(eventRepository.findAll());
    }

    public Event getByTitle(String title) {
        return eventRepository.findByTitle(title);
    }
    public List<EventMapperDTO> getAllEvent1() {
        List<EventMapperDTO> eventMapperDTOList = new ArrayList<>();
        List<Event> eventList = eventRepository.findAll();
        for (Event event : eventList) {
            eventMapperDTOList.add(EventMapper.EVENT_MAPPER.fromEvent(event));
        }
        return eventMapperDTOList;
    }


    public List<EventCustomerSaleDTO> getAllEventCustomerSaleDTO() {
        EventCustomerSaleDTO  eventCustomerSaleDTO = new EventCustomerSaleDTO();
        return eventCustomerSaleDTO.getEventCustomerSaleDTO (eventRepository.findAll());
    }
}
