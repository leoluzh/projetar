package br.gov.mg.fazenda.geral.dao.impl;

import java.beans.Expression;
import java.beans.Statement;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author leonardo luz fernandes <a href="mailto:leonardo.luz@fazenda.gov.br">leonardo.luz@fazenda.mg.gov.br</a> 
 * @version 0.1
 */

public abstract class AbstractDao<Entity,Key extends Serializable>{ 
    
	protected static final String DEFAULT_NAME_ENTITY_KEY_ID = "id" ;
	protected static final String PREFIX_GET_METHOD = "get" ;
	protected static final String PREXIF_SET_METHOD = "set" ;
	
    //Workaround, because Java Gerenics use erasable type.
    //Think how to implement solution to work with extended classes.
    private Class<Entity> entityClass;    
    protected abstract EntityManager getEntityManager();
    //cacheing name - speedup ;
    private String nameOfEntityKeyId;

    {
    	this.entityClass = getTypeParameterEntityClazz();
    }
    
    public AbstractDao(){
        this.entityClass = getTypeParameterEntityClazz();
        //this.nameOfEntityKeyId = getNameOfEntityKeyId();
    }//end method  
    
    @SuppressWarnings("unchecked")
    private Class<Entity> getTypeParameterEntityClazz(){
        return (Class<Entity>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]; 
    }//end method
    
    protected final String getEntityClassName(){
        return this.entityClass.getSimpleName();
    }//end method
        
    protected final Class<Entity> getEntityClass(){
        return this.entityClass;
    }//end mehtod

    protected final String getNameOfEntityKeyId(){
    	if( this.nameOfEntityKeyId == null ){
	    	String id = DEFAULT_NAME_ENTITY_KEY_ID ;
	    	for( SingularAttribute<?,?> sa : this.getEntityManager().getMetamodel().managedType( getEntityClass() ).getSingularAttributes() ){
	    		if( sa.isId() ){
	    			id = sa.getName();
	    	    	this.nameOfEntityKeyId = id;
	    			return id ;
	    		}
	    	}
	    	this.nameOfEntityKeyId = id;
	    	return id;
    	}else{
    		return this.nameOfEntityKeyId;
    	}
    }//end method
    
    protected final String getNameOfEntityKeyIdAsGetMethod(){
    	String name = getNameOfEntityKeyId();
    	name = WordUtils.capitalize(name);
    	return PREFIX_GET_METHOD + name ;
    }//end method
    
    protected final String getNameOfEntityKeyIdAsSetMethod(){
    	String name = getNameOfEntityKeyId();
    	name = WordUtils.capitalize( name );
    	return PREXIF_SET_METHOD + name ;
    }//end method
    

    @SuppressWarnings("unchecked")
    protected final Key getEntityKeyIdValue( Entity entity ) throws Exception {
    	java.beans.Expression expression = new Expression( entity , getNameOfEntityKeyIdAsGetMethod() , new Object[0] );
    	expression.execute();
    	Key value = (Key)expression.getValue();
    	return value;
    }//end method
    
    protected final void setEntityIdValue( Entity entity , Key value ) throws Exception {
    	java.beans.Statement statement = new Statement( entity , getNameOfEntityKeyIdAsSetMethod() , new Object[]{ value } );
    	statement.execute();
    }//end method
     
}
