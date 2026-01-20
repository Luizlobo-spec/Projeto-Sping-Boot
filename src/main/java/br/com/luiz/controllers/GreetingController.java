package br.com.luiz.controllers;

import br.com.luiz.model.Greeting;//import da classe que será retornada na resposta
import org.springframework.web.bind.annotation.GetMapping;//usado para mapear requisições http get
import org.springframework.web.bind.annotation.RequestParam;//capturar parametros da url
import org.springframework.web.bind.annotation.RestController;//marcar a classe como um controller REST

import java.util.concurrent.atomic.AtomicLong;//Um contador seguro para múltiplas requisições simultâneas

@RestController//diz ao spring: essa classe recebe requisições HTTP,o retorno dos métodos vira JSON automaticamente, sem isso o spring nao cria endpoint nenhum
public class GreetingController {

    private static final String template = "Hello, %s!";//static final define uma constante, o %s será substituído pelo nome recebido
    private final AtomicLong counter = new AtomicLong();//a cada requsição soma mais um

    @GetMapping("/greeting")//cria o endpoint
    public Greeting greeting(//retorna um objeto do tipo Greeting(primeira palavra após o public, está instanciando a model Greeting)
            @RequestParam(value = "name", defaultValue = "World")//diz o seguinte, pegue um valor da requisição http  e coloque dentro do parametro "nome" , se não conseguir use o World
            String name) {

        return new Greeting(
                counter.incrementAndGet(),//método incrementAndGet incrementa ao contador
                String.format(template, name)//troca o %s por name
        );
    }
}
