package br.insper.loja.evento;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventoService {

    public void salvarEvento(String token, String usuario, String acao) {
        Evento evento = new Evento();
        evento.setEmail(usuario);
        evento.setAcao(acao);

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token); // ou "Bearer " + token, se necessário
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Evento> requestEntity = new HttpEntity<>(evento, headers);

        restTemplate.exchange(
                "http://usuario:8080/api/evento",
                HttpMethod.POST,
                requestEntity,
                Void.class // ou Evento.class, se quiser o retorno
        );
    }

}
