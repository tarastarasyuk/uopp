package com.education.uopp.springcourse.service;

import com.education.uopp.springcourse.model.Opportunity;
import com.education.uopp.springcourse.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;

    public Opportunity create(Opportunity entity) {
        entity.setCreatedAt(Calendar.getInstance().getTime());
        return opportunityRepository.create(entity);
    }

    public Opportunity findById(Long id) {
        return opportunityRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Opportunity with id %d not found".formatted(id));
        });
    }

    public List<Opportunity> findAll() {
        return opportunityRepository.findAll();
    }

    public Opportunity update(Opportunity source, Opportunity target) {
        source.setCreatedAt(target.getCreatedAt());
        return findById(opportunityRepository.update(source, target).getId());
    }

    public void delete(Long id) {
        opportunityRepository.delete(id);
    }

    public List<Opportunity> findAll(String sort) {
        List<Opportunity> opportunityList = findAll();
        if (nonNull(sort)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(Calendar.getInstance().getTime());
            cal.add(Calendar.DATE, -3);
            switch (sort) {
                case "newest" -> opportunityList = opportunityList.stream()
                        .sorted(Comparator.comparing(Opportunity::getCreatedAt))
                        .collect(Collectors.toList());
                case "asap" -> opportunityList = opportunityList.stream()
                        .filter(Opportunity::getASAP)
                        .collect(Collectors.toList());
                case "deadline-soon" -> opportunityList = opportunityList.stream()
                        .filter(opportunity -> {
                                    long timeDiff = opportunity.getDeadline().getTime() - Calendar.getInstance().getTime().getTime();
                                    return timeDiff < TimeUnit.DAYS.toMillis(2);
                                }
                        )
                        .collect(Collectors.toList());
                default -> {
                    throw new IllegalStateException();
                }
            }
        }
        return opportunityList;
    }
}
