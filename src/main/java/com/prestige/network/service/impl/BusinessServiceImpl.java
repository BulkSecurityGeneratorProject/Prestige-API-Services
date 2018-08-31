package com.prestige.network.service.impl;

import com.prestige.network.service.BusinessService;
import com.prestige.network.domain.Business;
import com.prestige.network.repository.BusinessRepository;
import com.prestige.network.service.dto.BusinessDTO;
import com.prestige.network.service.mapper.BusinessMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prestige.network.domain.User;

import java.util.Optional;
/**
 * Service Implementation for managing Business.
 */
@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {

    private final Logger log = LoggerFactory.getLogger(BusinessServiceImpl.class);

    private final BusinessRepository businessRepository;

    private final BusinessMapper businessMapper;

    public BusinessServiceImpl(BusinessRepository businessRepository, BusinessMapper businessMapper) {
        this.businessRepository = businessRepository;
        this.businessMapper = businessMapper;
    }

    /**
     * Save a business.
     *
     * @param businessDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BusinessDTO save(BusinessDTO businessDTO) {
        log.debug("Request to save Business : {}", businessDTO);
        Business business = businessMapper.toEntity(businessDTO);
        business = businessRepository.save(business);
        return businessMapper.toDto(business);
    }

    /**
     * Get all the businesses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BusinessDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Businesses");
        return businessRepository.findAll(pageable)
            .map(businessMapper::toDto);
    }

    /**
     * Return all Business by getcurrentuser.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Transactional(readOnly = true)
    public Page<BusinessDTO> findAllById(User user, Pageable pageable) {
        log.debug("Request to get all Business with User login");
        return businessRepository.findByUserOrderById(user,pageable).map(businessMapper::toDto);
    }


    /**
     * Get one business by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BusinessDTO> findOne(Long id) {
        log.debug("Request to get Business : {}", id);
        return businessRepository.findById(id)
            .map(businessMapper::toDto);
    }

    /**
     * Delete the business by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Business : {}", id);
        businessRepository.deleteById(id);
    }
}
