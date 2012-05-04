package budstore;

import java.util.Collections;
import java.util.List;

import play.db.jpa.GenericModel;

import budstore.graphstore.BudNode;
import models.basebuds.BudEntity;

public interface IBud
{

    String identity();

    BudEntity entity();

    BudNode graphNode();

    BudAttachments attachments();

    List<Role<?>> roles();

    abstract class GenericRoleEntity
            extends GenericModel
    {

        public IBud bud;

    }

    interface BudPack
    {

        String name();

        String version();

        List<RoleDescriptor> roles();

    }

    interface RoleDescriptor
    {

        String name();

        List<ActionDescriptor> actions();

        Class<? extends GenericRoleEntity> roleEntity();

        // extensions de vues etc..
    }

    interface ActionDescriptor
    {

        String name();

    }

    abstract class Role<T extends GenericRoleEntity>
    {

        protected final IBud bud;

        public Role( IBud bud )
        {
            this.bud = bud;
        }

        public abstract T roleEntity();

        public List<RoleAction> actions()
        {
            return Collections.emptyList();
        }

        protected void onBudCreation()
        {
        }

    }

    public class Person<PersonEntity>
            extends Role
    {

        public Person( IBud bud )
        {
            super( bud );
        }

        // Attribut
        // -> Storage JPA
        // -> Accessible à la vue
        // -> Dispo dans le Role
        public String email;

        @Override
        public GenericRoleEntity roleEntity()
        {
            throw new UnsupportedOperationException( "Not supported yet." );
        }

    }

    public class PersonEntity
            extends GenericRoleEntity
    {
    }

    abstract class RoleAction<Params extends Object, Result extends Object, ThrowableType extends Throwable>
    {

        protected abstract Result invokeAction( Params params )
                throws ThrowableType;

    }

    interface BudAttachments
    {
    }

}
