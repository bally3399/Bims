package africa.semicolon.com.bims.services;


import africa.semicolon.com.bims.data.model.Token;

public interface TokenService {

    String  createToken(String email);

    Token findByUserEmail(String email);

    void deleteToken(Long id);

}
