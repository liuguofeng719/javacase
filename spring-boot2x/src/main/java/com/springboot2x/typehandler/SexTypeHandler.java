package com.springboot2x.typehandler;

import com.springboot2x.enumeration.SexEnum;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author guofeng
 * @version 1.0
 * @desc
 * @createtime 2019/2/8 8:57 PM
 * @see jdk 1.7
 **/
//@MappedJdbcTypes(JdbcType.INTEGER)
@MappedTypes({SexEnum.class})
public class SexTypeHandler implements TypeHandler<SexEnum> {
    @Override
    public void setParameter(PreparedStatement ps, int i, SexEnum sexEnum, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, sexEnum.getCode());
    }

    @Override
    public SexEnum getResult(ResultSet rs, String colName) throws SQLException {
        final int rsInt = rs.getInt(colName);
        return SexEnum.getEnumById(rsInt);
    }

    @Override
    public SexEnum getResult(ResultSet rs, int colIndex) throws SQLException {
        final int rsInt = rs.getInt(colIndex);
        return SexEnum.getEnumById(rsInt);
    }

    @Override
    public SexEnum getResult(CallableStatement cs, int i) throws SQLException {
        final int csInt = cs.getInt(i);
        return SexEnum.getEnumById(csInt);
    }
}
