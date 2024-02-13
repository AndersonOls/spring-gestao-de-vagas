package com.anderson.gestaovagas.modules.company.useCases;

import javax.naming.AuthenticationException;

import com.anderson.gestaovagas.modules.company.dto.AuthCompanyDTO;
import com.anderson.gestaovagas.modules.company.repositories.CompanyRepository;
import com.auth0.jwt.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthCompanyUseCase {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
                () ->{
                    throw new UsernameNotFoundException("Company not found");
                });

        //Verificar se a senha são igauis
        var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }
        //Se for igual

    }

}
