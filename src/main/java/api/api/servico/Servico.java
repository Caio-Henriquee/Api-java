package api.api.servico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import api.api.modelo.Mensagem;
import api.api.modelo.Pessoa;
import api.api.repositorio.Repositorio;

@Service
public class Servico {

    @Autowired
    private Mensagem mensagem;

    @Autowired
    private Repositorio acao;

    // metodo para cadastra pessoas
    public ResponseEntity<?> cadastrar(Pessoa obj) {

        if (obj.getNome().equals("")) {
            mensagem.setMensagem("o nome precisa ser prrenchido");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getIdade() < 0) {
            mensagem.setMensagem("informe uma idade valida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else {

            return new ResponseEntity<>(acao.save(obj), HttpStatus.CREATED);
        }
    }

    // metodo para selecionar pessoa

    public ResponseEntity<?> selecionar() {
        return new ResponseEntity<>(acao.findAll(), HttpStatus.OK);
    }

    // selecionar pessoas atraves do codigo
    public ResponseEntity<?> selecionarId(int codigo) {
        if (acao.countById(codigo) == 0) {
            mensagem.setMensagem("nenhuma pessoa de com o codigo " + codigo + " encontrado :");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(acao.findById(codigo), HttpStatus.OK);
    }

    // editat pessoa
    public ResponseEntity<?> editar(Pessoa obj) {
        if (acao.countById(obj.getId()) == 0) {
            mensagem.setMensagem("o codigo informado nao existe");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        } else if (obj.getNome().equals("")) {
            mensagem.setMensagem("necessario informa um nome");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        } else if (obj.getIdade() < 0) {
            mensagem.setMensagem("informe uma idade valida");
            return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(acao.save(obj), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> remover(int codigo){
        if(acao.countById(codigo) == 0){
            mensagem.setMensagem("o codigo não existe no banco");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }else{
            Pessoa obj = acao.findById(codigo);
            acao.delete(obj);


            mensagem.setMensagem("pessoa deletada com sucesso");
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        }
    }

}
