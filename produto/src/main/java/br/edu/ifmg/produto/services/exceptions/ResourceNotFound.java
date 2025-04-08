package br.edu.ifmg.produto.services.exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(){
        super(); //invocando o construtor da classe pai
    }
    public ResourceNotFound(String message){
        super(message);
    }
}
