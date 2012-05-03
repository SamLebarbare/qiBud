/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.basebuds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.ParameterizedType;
import org.junit.Before;
import play.Logger;
import play.Play;
import play.db.Model;
import play.mvc.Controller;
import play.utils.Java;
import java.lang.reflect.Type;
import models.basebuds.Bud;
import play.db.Model.Factory;
import play.db.jpa.GenericModel;
/**
 *
 * @author sloup
 */
public abstract class BaseBuds extends Controller{
 
    @Before
    public static void addType() throws Exception {
        ObjectType type = ObjectType.get(getControllerClass());
        renderArgs.put("type", type);
    }
    
    
    public static void install()
    {
        
        render();
    }
    
    
    
    protected static ObjectType createObjectType(Class<? extends Model> entityClass) {
        return new ObjectType(entityClass);
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface For {
        Class<? extends Model> value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Exclude {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Hidden {}
    
    
    public static class ObjectType implements Comparable<ObjectType> {

        public Class<? extends Controller> controllerClass;
        public Class<? extends Model> entityClass;
        public String name;
        public String modelName;
        public String controllerName;
        public String keyName;
		public Factory factory;

        public ObjectType(Class<? extends Model> modelClass) {
            this.modelName = modelClass.getSimpleName();
            this.entityClass = modelClass;
            this.factory = Model.Manager.factoryFor(entityClass);
            this.keyName = factory.keyName();
        }

        @SuppressWarnings("unchecked")
        public ObjectType(String modelClass) throws ClassNotFoundException {
            this((Class<? extends Model>) Play.classloader.loadClass(modelClass));
        }

        public int compareTo(ObjectType o) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        
        public static ObjectType get(Class<? extends Controller> controllerClass) {
            Class<? extends Bud> entityClass = getEntityClassForController(controllerClass);
            if (entityClass == null || !Bud.class.isAssignableFrom(entityClass)) {
                System.out.println("NULL");
                return null;
            }
            ObjectType type;
            try {
                type = (ObjectType) Java.invokeStaticOrParent(controllerClass, "createObjectType", entityClass);
            } catch (Exception e) {
                Logger.error(e, "Couldn't create an ObjectType. Use default one.");
                type = new ObjectType(entityClass);
            }
            type.name = controllerClass.getSimpleName().replace("$", "");
            type.controllerName = controllerClass.getSimpleName().toLowerCase().replace("$", "");
            type.controllerClass = controllerClass;
            return type;
        
        }
        
        
        @SuppressWarnings("unchecked")
        public static Class<? extends Bud> getEntityClassForController(Class<? extends Controller> controllerClass) {
                   
            String name = controllerClass.getSimpleName().replace("$", "");
            name = "models.basebuds." + name.substring(0, name.length() - 1);
            System.out.println(name);
            try {
                return (Class<? extends Bud>) Play.classloader.loadClass(name);
            } catch (ClassNotFoundException e) {
                return null;
            }
        }
    }
    
}