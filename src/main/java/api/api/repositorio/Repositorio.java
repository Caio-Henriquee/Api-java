package api.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import api.api.modelo.Pessoa;

//a interface Repositorio tem como superclass a crudRepository, no qual se encontra os metodos de salvar no banco, lista, etc
@Repository
public interface Repositorio extends CrudRepository<Pessoa,Integer> {

    @SuppressWarnings("null")
    List<Pessoa> findAll(); //estou especificando qual sera o meu retorno sobrescrevendo o metodo da superclass
    
    Pessoa findById(int id);

    List<Pessoa> findByOrderByNome(); //posso passar variaos parametros como findBy, findByOrderNome,

    List<Pessoa> findByNomeOrderByIdadeAsc(String nome);

    List<Pessoa> findByNomeContaining(String termo);

    @Query(value = "SELECT SUM(idade) FROM pessoas",nativeQuery = true) //utilizando o sql de forma nativa
    int somarIdades();

    @Query(value = "SELECT * FROM pessoas WHERE idade >= :idade", nativeQuery = true)
    List <Pessoa> idadeMaiorIgual(int idade);

    int  countById(int id);
    
}
