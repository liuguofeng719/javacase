package com.netty.example.ext;

/**
 * Created with IntelliJ IDEA.
 *
 * @author lgfcxx
 * @version 1.0
 * @desc 位运算
 * @createtime 2018/10/25 6:06 PM
 * @see jdk 1.7
 **/
public class BitsPermission {

    /**
        a&~b: 清除标志位b;
        a|b: 添加标志位b;
        a&b: 取出标志位b;
        a^b: 取出a与b的不同部分;
     * 权限 insert,select,update,delete
     */
    public static final int ALLOW_INSERT = 1 << 0;    // 1  = 0001
    public static final int ALLOW_SELECT = 1 << 1;    // 2  = 0010
    public static final int ALLOW_UPDATE = 1 << 2;    // 4  = 0100
    public static final int ALLOW_DELETE = 1 << 3;    // 8  = 1000

    /**
     * 权限集合
     */
    public int flag = 0;

    public static void main(String[] args) {
        BitsPermission b = new BitsPermission();
        b.enablePermission(ALLOW_DELETE | ALLOW_UPDATE);
        System.out.println(b.isAllow(ALLOW_DELETE));
        System.out.println(b.toString());
        b.removeAllow(ALLOW_DELETE);
        System.out.println(b.toString());
        //2010;
        System.out.println(Integer.lowestOneBit(2010));
    }

    /**
     * 重新设置权限
     */
    public void setPermission(int permission) {
        flag = permission;
    }

    /**
     * 增加一个权限或者多个权限
     * flag = 0001 | 0010 = 0011 增加和查询的权限
     * flag = 0001 | 0010 | 0100 = 0111 更新，查询，新增权限
     */
    public void enablePermission(int permission) {
        flag |= permission;//相当于 flag = flag | permission
    }

    /**
     * 检查是否有权限，检查一个或者多个
     * 例如：flag = 0011 增加和查询的权限
     * 0011 & 0001 = 0001 为true 是有新增权限
     * 0011 & 1000 = 0000
     */
    public boolean isAllow(int permission) {
        return (flag & permission) != 0;
    }

    /**
     * 移除一个权限或者多个权限
     * 例如：flag = 0011 增加和查询的权限
     * 删除新增的权限 0001
     * 非操作全部取反 ~0001 = 1110
     * flag = 0011 & 1110 = 0010 只剩下查询权限了
     */
    public void removeAllow(int permission) {
        flag = flag & ~permission;
    }

    /**
     * a^b: 取出a与b的不同部分;
     * 列如：flag = 0011 增加和查询权限
     * mask = 0001 | 0100 = 0101 增加和更新权限
     * flag = flag ^ mask = 0011 ^ 0101 = 0110 查询，更新权限
     * @param permission
     * @return8
     */
    public int not(int permission) {
        return flag ^= permission;
    }

    public String toString() {
        return toString(flag);
    }

    public static String toString(int permission) {
        StringBuilder sb = new StringBuilder();
        if ((ALLOW_INSERT & permission) != 0) {
            sb.append("allow_insert ");
        }
        if ((ALLOW_SELECT & permission) != 0) {
            sb.append("allow_select ");
        }
        if ((ALLOW_UPDATE & permission) != 0) {
            sb.append("allow_update ");
        }
        if ((ALLOW_DELETE & permission) != 0) {
            sb.append("allow_delete ");
        }
        return sb.toString();
    }
}
