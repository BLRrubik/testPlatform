package ru.vibelab.tplatfom.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import ru.vibelab.tplatfom.DTO.UserAuthDTO;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class AuthService {


    @Autowired
    JwtEncoder encoder;

    public UserAuthDTO authUser(Authentication authentication){

        Instant now = Instant.now();
        long expiry = 36000L;
        // @formatter:off
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
        System.out.println(scope);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        System.out.println(claims);
        // @formatter:on
        return new UserAuthDTO(authentication.getName(), this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue());

    }

}
