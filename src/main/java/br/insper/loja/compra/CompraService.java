package br.insper.loja.compra;

import br.insper.loja.evento.EventoService;
import br.insper.loja.usuario.Usuario;
import br.insper.loja.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private EventoService eventoService;

    public Compra salvarCompra(String token, Compra compra) {
        Usuario usuario = usuarioService.getUsuario(token, compra.getUsuario());

        compra.setNome(usuario.getNome());
        compra.setDataCompra(LocalDateTime.now());

        eventoService.salvarEvento(token, usuario.getEmail(), "Compra realizada");
        return compraRepository.save(compra);
    }

    public List<Compra> getCompras() {
        return compraRepository.findAll();
    }

}
