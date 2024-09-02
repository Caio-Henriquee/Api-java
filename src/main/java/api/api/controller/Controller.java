package api.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import api.api.modelo.Cliente;
import api.api.modelo.Pessoa;
import api.api.repositorio.Repositorio;
import api.api.servico.Servico;
import jakarta.validation.Valid;


@RestController
public class Controller  {

    @Autowired
    private Repositorio acao;  

    @Autowired
    private Servico servico;

    @PostMapping("/cliente")
    public void cliente (@Valid @RequestBody Cliente obj) { //nao preciso de cogigo pois as anotations @NotEmpty e @Emal ja fazem o trabalho de validacao
        
    }
    


    @GetMapping("/status")
    public ResponseEntity<?>  status(){
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("api/idademaior")
    public List<Pessoa> idadeMaior() {
        return acao.idadeMaiorIgual(24); //filtrando por idade
    }
    

    @GetMapping("/api/somaridades")
    public int somarIdades() {
        return acao.somarIdades(); //somando as idades
    }
    

    @GetMapping("/api/contem")
    public List<Pessoa> nomeContem() {
        return acao.findByNomeContaining("c"); //usando o contem para ternorna os nomes que contem o parametro passado
    }
    

    @GetMapping("api/nome/{nome}")
    public List<Pessoa> pegarNome(@PathVariable String nome) {
        return acao.findByNomeOrderByIdadeAsc(nome); //pegando o nome e ordenando pela a idade
    }
    

    @GetMapping("api/ordenar")
    public List<Pessoa> ordenarNome() {
        return acao.findByOrderByNome(); //ordenando atraves do nome
    }
    

    @GetMapping("/api/contador")
    public long contador() {
        return acao.count(); //contando quantos pessoas estao cadastrada no banco
    }
    

    @DeleteMapping("api/{codigo}")//pegando o id que sera usado para selecionar pessoa a ser deletada
    public ResponseEntity<?> remover(@PathVariable int codigo){
       return servico.remover(codigo);
    }

    @PutMapping("/api")
    public ResponseEntity<?> editar(@RequestBody Pessoa obj){ 
       return servico.editar(obj); //o save tambem serve para alteracao, alem do cadastro 
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<?> selecionarPessoaId(@PathVariable int id){
        return servico.selecionarId(id);//selecionando pessoa pelo id
    }

    @GetMapping("/api")
    public ResponseEntity<?> listarPessoa(){
        return servico.selecionar();//listando pessoas usando o jpa
    }

    @PostMapping("/api")
    public ResponseEntity<?> cadastrarPessoa(@RequestBody Pessoa obj){
        return servico.cadastrar(obj);//salvando pessoa no banco usando o jpa
    }

    
}
