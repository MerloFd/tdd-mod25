package br.com.merlo.dao.generic;


import br.com.merlo.annotation.TipoChave;
import br.com.merlo.domain.Persistente;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class GenericDAO <T extends Persistente> implements IGenericDAO <T>{

    public Map<Class, Map<Long,T>> map; //cria um mapa especifico pra classe fornecida

    public GenericDAO(){
        if (this.map == null){
            this.map=new HashMap<>();
        }
    }

    public abstract Class<T> getClassType();

    public abstract void atualizarDados(T entity, T entityCadastrada);

    //olha todos os campos da entidade e procura a annotation TipoChave
    public Long getChave(T entity){
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(TipoChave.class)){
                TipoChave tipoChave = field.getAnnotation(TipoChave.class);
                //apos achar, ele pega o value dela (que é o nome do metodo que retorna o codigo)
                String nomeMetodo = tipoChave.value();
                try {
                    Method metodo = entity.getClass().getMethod(nomeMetodo);
                    Long value = (Long) metodo.invoke(entity);
                    return value;
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @Override
    public T consultar(Long valor) {
        Map<Long,T> mapaInterno = this.map.get(getClassType());
        return mapaInterno.get(valor);
    }
    @Override
    public Boolean cadastrar(T entity) { //metodo generico de validacao

        //inserir if de produto ou cliente
        Map<Long,T> mapaInterno = this.map.get(getClassType());
        Long chave = getChave(entity);
        if(mapaInterno.containsKey(chave)){
           return false;
        }
        mapaInterno.put(chave, entity);
            return true;
    }

    @Override
    public void excluir(Long valor) {
        Map<Long,T> mapaInterno = this.map.get(getClassType());
        T entityCadastrada = mapaInterno.get(valor);

       if (entityCadastrada != null){
           mapaInterno.remove(valor, entityCadastrada);
        }
    }

    @Override
    public void alterar(T entity) {
        Map<Long,T> mapaInterno = this.map.get(getClassType());
        Long chave = getChave(entity);
        T entityCadastrada = mapaInterno.get(chave); //obj do map
        if (entityCadastrada != null){
            atualizarDados(entity, entityCadastrada);
        }
    }




    @Override
    public Collection<T> buscarTodos() {
        Map<Long,T> mapaInterno = this.map.get(getClassType());
        return mapaInterno.values();
    }
}
