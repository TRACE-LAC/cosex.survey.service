package com.monkeypox.survey.service.security.secrets;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SecretsAppRepository extends JpaRepository<SecretsApp, Long> {

    Optional<SecretsApp> findByName(String name);
    
}
