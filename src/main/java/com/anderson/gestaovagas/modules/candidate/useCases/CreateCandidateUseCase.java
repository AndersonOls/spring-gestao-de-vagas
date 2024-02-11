package com.anderson.gestaovagas.modules.candidate.useCases;

import com.anderson.gestaovagas.exceptions.UserFoundException;
import com.anderson.gestaovagas.modules.candidate.entities.CandidateEntity;
import com.anderson.gestaovagas.modules.candidate.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateEntity execute(CandidateEntity candidateEntity){
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) ->{
                    throw new UserFoundException();
                });
        return this.candidateRepository.save(candidateEntity);
    }
}
