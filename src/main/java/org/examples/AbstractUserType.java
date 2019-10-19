package org.examples;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.jdbc.Size;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.internal.util.collections.ArrayHelper;
import org.hibernate.type.ForeignKeyDirection;
import org.hibernate.type.Type;
import org.hibernate.type.descriptor.java.IncomparableComparator;
import org.hibernate.usertype.UserType;

public abstract class AbstractUserType<T> implements UserType, Type {
	private static final long serialVersionUID = 1L;
    private final Class<T> clazz;
    
    public AbstractUserType(Class<T> clazz) {
        this.clazz = clazz;
    }
    
    protected abstract T get(ResultSet rs, String[] names,
            SharedSessionContractImplementor session, Object owner) throws SQLException;
    
    protected abstract void set(PreparedStatement st, T value, int index,
            SharedSessionContractImplementor session) throws SQLException;

	@Override
	public boolean isAssociationType() {
		return false;
	}

	@Override
	public boolean isCollectionType() {
		return false;
	}

	@Override
	public boolean isEntityType() {
		return false;
	}

	@Override
	public boolean isAnyType() {
		return false;
	}

	@Override
	public boolean isComponentType() {
		return false;
	}

	@Override
	public int getColumnSpan(Mapping mapping) throws MappingException {
		return 1;
	}

	@Override
	public int[] sqlTypes(Mapping mapping) throws MappingException {
		return sqlTypes();
	}

	@Override
	public Size[] dictatedSizes(Mapping mapping) throws MappingException {
		return new Size[] { new Size() };
	}

	@Override
	public Size[] defaultSizes(Mapping mapping) throws MappingException {
		return dictatedSizes(mapping);
	}

	@Override
	public Class getReturnedClass() {
		return returnedClass();
	}

	@Override
	public boolean isSame(Object x, Object y) throws HibernateException {
		return equals(x, y);
	}

	@Override
	public boolean isEqual(Object x, Object y) throws HibernateException {
		return equals(x, y);
	}

	@Override
	public boolean isEqual(Object x, Object y, SessionFactoryImplementor factory) throws HibernateException {
		return equals(x, y);
	}

	@Override
	public int getHashCode(Object x) throws HibernateException {
		return hashCode(x);
	}

	@Override
	public int getHashCode(Object x, SessionFactoryImplementor factory) throws HibernateException {
		return hashCode(x);
	}

	@Override
	public int compare(Object x, Object y) {
		return IncomparableComparator.INSTANCE.compare(x, y);
	}

	@Override
	public boolean isDirty(Object old, Object current, SharedSessionContractImplementor session)
			throws HibernateException {
		return isDirty(old, current);
	}

	protected final boolean isDirty(Object old, Object current) {
		return !isSame(old, current);
	}

	@Override
	public boolean isDirty(Object oldState, Object currentState, boolean[] checkable,
			SharedSessionContractImplementor session) throws HibernateException {
		return checkable[0] && isDirty(oldState, currentState);
	}

	@Override
	public boolean isModified(Object dbState, Object currentState, boolean[] checkable,
			SharedSessionContractImplementor session) throws HibernateException {
		 return isDirty(dbState, currentState);
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String name, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		return get(rs, new String[]{name}, session, owner);
	}
	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, boolean[] settable,
			SharedSessionContractImplementor session) throws HibernateException, SQLException {
		set(st, returnedClass().cast(value), index, session);
	}

	@Override
	public String toLoggableString(Object value, SessionFactoryImplementor factory) throws HibernateException {
		 return String.valueOf(value);
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	@Override
	public Object deepCopy(Object value, SessionFactoryImplementor factory) throws HibernateException {
		return deepCopy(value);
	}

	@Override
	public Serializable disassemble(Object value, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		return disassemble(value);
	}

	@Override
	public Object assemble(Serializable cached, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		return assemble(cached, session);
	}

	@Override
	public void beforeAssemble(Serializable cached, SharedSessionContractImplementor session) {

	}

	@Override
	public Object hydrate(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		 return nullSafeGet(rs, names, session, owner);
	}

	@Override
	public Object resolve(Object value, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		return value;
	}

	@Override
	public Object semiResolve(Object value, SharedSessionContractImplementor session, Object owner)
			throws HibernateException {
		return value;
	}

	@Override
	public Type getSemiResolvedType(SessionFactoryImplementor factory) {
		return this;
	}

	@Override
	public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner,
			Map copyCache) throws HibernateException {
		return replace(original, target, owner);
	}

	@Override
	public Object replace(Object original, Object target, SharedSessionContractImplementor session, Object owner,
			Map copyCache, ForeignKeyDirection foreignKeyDirection) throws HibernateException {
		return replace(original, target, owner);
	}

	@Override
	public boolean[] toColumnNullness(Object value, Mapping mapping) {
		return value == null ? ArrayHelper.FALSE : ArrayHelper.TRUE;
	}

	@Override
	public Class<T> returnedClass() {
		return clazz;
	}

	@Override
	public boolean equals(Object x, Object y) throws HibernateException {
		return (x == y) || (x != null && x.equals(y));
	}

	@Override
	public int hashCode(Object x) throws HibernateException {
		return x.hashCode();
	}

	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, SharedSessionContractImplementor session, Object owner)
			throws HibernateException, SQLException {
		return get(rs, names, session, owner);
	}

	@Override
	public void nullSafeSet(PreparedStatement st, Object value, int index, SharedSessionContractImplementor session)
			throws HibernateException, SQLException {
		set(st, clazz.cast(value), index, session);

	}

	@Override
	public Object deepCopy(Object value) throws HibernateException {
		return value;
	}

	@Override
	public boolean isMutable() {
		return false;
	}

	@Override
	public Serializable disassemble(Object value) throws HibernateException {
		return (Serializable) value;
	}

	@Override
	public Object assemble(Serializable cached, Object owner) throws HibernateException {
		return cached;
	}

	@Override
	public Object replace(Object original, Object target, Object owner) throws HibernateException {
		return original;
	}

}
