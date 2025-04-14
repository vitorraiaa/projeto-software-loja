package br.insper.loja.usuario;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    public Usuario getUsuario(String token, String email) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://usuario:8080/api/usuario/" + email;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token); // ou "Bearer " + token, se for o caso

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        try {
            return restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    Usuario.class
            ).getBody();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
