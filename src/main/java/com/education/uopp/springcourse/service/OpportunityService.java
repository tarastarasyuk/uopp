package com.education.uopp.springcourse.service;

import com.education.uopp.service.UserService;
import com.education.uopp.springcourse.model.SCOpportunity;
import com.education.uopp.springcourse.repository.SCOpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class OpportunityService {

    private final SCOpportunityRepository opportunityRepository;
    private final StudentService studentService;

    public SCOpportunity create(SCOpportunity entity) {
        return opportunityRepository.save(entity);
    }

    public SCOpportunity findById(Long id) {
        return opportunityRepository.findById(id).orElseThrow(() -> {
            throw new RuntimeException("Opportunity with id %d not found".formatted(id));
        });
    }

    public List<SCOpportunity> findAll() {
        return opportunityRepository.findAll();
    }

    public SCOpportunity update(SCOpportunity source, SCOpportunity target) {
        BeanUtils.copyProperties(source, target, "id", "createdAt");
        return opportunityRepository.save(target);
    }

    public void delete(SCOpportunity entity) {
        studentService.findAll().forEach(scStudent -> scStudent.getLikedOpportunities().remove(entity));
        opportunityRepository.delete(entity);
    }

    public List<SCOpportunity> findAll(String sort) {
        List<SCOpportunity> opportunityList = findAll();
        if (nonNull(sort)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(Calendar.getInstance().getTime());
            cal.add(Calendar.DATE, -3);
            switch (sort) {
                case "newest" -> opportunityList = opportunityList.stream()
                        .sorted(Comparator.comparing(SCOpportunity::getCreatedAt))
                        .toList();
                case "asap" -> opportunityList = opportunityList.stream()
                        .filter(SCOpportunity::getAsap)
                        .toList();
                case "deadline-soon" -> opportunityList = opportunityList.stream()
                        .filter(opportunity -> {
                                    long timeDiff = opportunity.getDeadline().getTime() - Calendar.getInstance().getTime().getTime();
                                    return timeDiff < TimeUnit.DAYS.toMillis(2);
                                }
                        )
                        .toList();
                default -> throw new IllegalStateException();
            }
        }
        return opportunityList;
    }
}
